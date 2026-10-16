package com.axonivy.portal.smart.statistic.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bean.StatisticConfigurationBean;
import com.axonivy.portal.bo.BarChartConfig;
import com.axonivy.portal.bo.ColumnChartConfig;
import com.axonivy.portal.bo.LineChartConfig;
import com.axonivy.portal.bo.NumberChartConfig;
import com.axonivy.portal.bo.PieChartConfig;
import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.smart.statistic.dto.SmartChartFilterSpec;
import com.axonivy.portal.smart.statistic.dto.SmartChartSpec;
import com.axonivy.portal.smart.statistic.dto.SmartStatisticMappingResult;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.AggregationInterval;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.field.CaseFilterFieldFactory;
import com.axonivy.portal.util.statisticfilter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

/**
 * Translates the agent's slim {@link SmartChartSpec} into a real {@link Statistic}.
 *
 * Policy: clamp recoverable problems and report them, reject unrecoverable ones. Nothing is
 * ever retried against the agent automatically - a silent retry doubles a multi-second latency
 * and can loop. Warnings surface in the chat instead, which lets the user decide and puts the
 * correction into the next turn's transcript for free.
 */
public class SmartChartSpecMapper {

  private static final Pattern HEX_COLOR = Pattern.compile("^#[0-9a-fA-F]{6}$");
  private static final int MIN_COLOR_SLOTS = ChartAppearanceService.MIN_COLOR_SLOTS;
  private static final String DEFAULT_AGGREGATION_METHOD = "sum";
  /** Same bounds the classic configurator validates against. */
  private static final int MIN_REFRESH_INTERVAL_SECONDS = 60;
  private static final int MAX_REFRESH_INTERVAL_SECONDS = 1000000;
  private static final String STATE_FIELD = "state";
  private static final String PRIORITY_FIELD = "priority";

  private SmartChartSpecMapper() {}

  public static SmartStatisticMappingResult toStatistic(SmartChartSpec spec, Statistic previous) {
    List<String> warnings = new ArrayList<>();
    if (spec == null) {
      return reject("The response contained no chart configuration.");
    }

    // 1 + 2. Target and chart type. Both must end up non-null: getChartData() throws on a null
    // target, and statistic.js dereferences the chart type without a guard.
    ChartTarget target = toTarget(spec.getTarget());
    if (target == null) {
      target = ChartTarget.TASK;
      warnings.add(text("I did not recognise what to count, so I used \"{0}\".", target.getCmsName()));
    }
    ChartType chartType = toChartType(spec.getChartType());
    if (chartType == null) {
      chartType = ChartType.BAR;
      warnings.add(text("I did not recognise the chart type, so I used \"{0}\".", chartType.getCmsName()));
    }

    // 3. Group by. Rejected rather than defaulted: substituting a field would produce a
    // plausible chart that answers a different question than the one asked.
    String groupBy = StringUtils.trimToNull(spec.getGroupByField());
    if (groupBy == null) {
      return reject(text("The configuration had no group-by field. Available: {0}.", available(target, chartType)));
    }
    Optional<AggregationField> aggregationField =
        SmartStatisticVocabularyService.findAggregationField(groupBy);
    if (aggregationField.isEmpty()) {
      return reject(text("\"{0}\" is not a known field to group by. Available: {1}.", groupBy, available(target, chartType)));
    }
    if (!SmartStatisticVocabularyService.availableGroupByFields(target, chartType)
        .contains(aggregationField.get())) {
      // A field that is legal for every other chart type most likely means the model wanted
      // that field and picked the chart type carelessly - the field is the stronger signal.
      if (ChartType.NUMBER == chartType && SmartStatisticVocabularyService
          .availableGroupByFields(target, ChartType.BAR).contains(aggregationField.get())) {
        chartType = ChartType.BAR;
        warnings.add(text("A number chart cannot group by \"{0}\", so I made it a bar chart.", groupBy));
      } else {
        return reject(text("\"{0}\" cannot be used to group a {1} {2} chart. Available: {3}.", groupBy,
            target.getCmsName(), chartType.getCmsName(), available(target, chartType)));
      }
    }

    // 4. Custom field. convertAggregatesFromChartAggregation() expects the placeholder name in
    // `field` and the real name in `customFieldValue`.
    boolean isCustomGroupBy = AggregationField.CUSTOM_FIELD == aggregationField.get();
    String customFieldName = null;
    boolean customFieldIsTimestamp = false;
    if (isCustomGroupBy) {
      Optional<ICustomFieldMeta> meta =
          SmartStatisticVocabularyService.findCustomField(target, spec.getCustomFieldName());
      if (meta.isEmpty() || meta.get().type() == CustomFieldType.NUMBER) {
        return reject(text("\"{0}\" is not a known custom field. Available: {1}.", StringUtils.defaultString(spec.getCustomFieldName()),
            String.join(", ", SmartStatisticVocabularyService.groupableCustomFieldNames(target))));
      }
      customFieldName = meta.get().name();
      customFieldIsTimestamp = meta.get().type() == CustomFieldType.TIMESTAMP;
    }

    // 5. Interval - required for timestamp buckets, meaningless otherwise.
    boolean needsInterval = isCustomGroupBy
        ? customFieldIsTimestamp
        : SmartStatisticVocabularyService.isTimestampAggregation(groupBy);
    AggregationInterval interval = toInterval(spec.getInterval());
    if (needsInterval && interval == null) {
      interval = AggregationInterval.DAY;
      warnings.add(text("\"{0}\" needs a time interval, so I used \"{1}\".", groupBy, interval.getCmsName()));
    } else if (!needsInterval && interval != null) {
      warnings.add(text("\"{0}\" is not a date field, so the time interval was ignored.", groupBy));
      interval = null;
    }

    // 6. KPI. An aggregation method without a KPI field is meaningless - getAggregationMethod()
    // returns "" in that case anyway.
    String kpiField = StringUtils.trimToNull(spec.getKpiField());
    String kpiMethod = null;
    if (kpiField != null && !SmartStatisticVocabularyService.COUNTING.equalsIgnoreCase(kpiField)) {
      boolean known = SmartStatisticVocabularyService.BUILT_IN_KPI_FIELDS.contains(kpiField)
          || SmartStatisticVocabularyService.numericCustomFieldNames(target).contains(kpiField);
      if (!known) {
        warnings.add(text("\"{0}\" is not a numeric field, so I counted records instead.", kpiField));
        kpiField = null;
      } else {
        kpiMethod = spec.getKpiMethod() == null
            ? null
            : spec.getKpiMethod().name().toLowerCase(Locale.ROOT);
        if (kpiMethod == null) {
          kpiMethod = DEFAULT_AGGREGATION_METHOD;
          warnings.add(text("No aggregation method was given, so I used \"{0}\".", kpiMethod));
        }
      }
    } else {
      kpiField = null;
    }

    // 7. Filters - a bad filter drops itself, never the whole chart.
    List<DashboardFilter> filters = new ArrayList<>();
    for (SmartChartFilterSpec filterSpec : nullSafe(spec.getFilters())) {
      DashboardFilter filter = toFilter(filterSpec, target, warnings);
      if (filter != null) {
        filters.add(filter);
      }
    }

    // 8. Colors and the settings that only apply to one chart type.
    List<String> colors = sanitizeColors(spec.getBackgroundColors(), warnings);
    if (ChartType.NUMBER == chartType && CollectionUtils.isNotEmpty(spec.getBackgroundColors())) {
      warnings.add("A number chart has no colored areas, so the colors were ignored.");
    }
    if (ChartType.NUMBER != chartType && spec.getHideLabel() != null) {
      warnings.add("Only a number chart can hide its label, so that setting was ignored.");
    }
    boolean supportsAxisTitles = ChartType.BAR == chartType || ChartType.LINE == chartType;
    if (!supportsAxisTitles
        && (StringUtils.isNotBlank(spec.getxAxisTitle()) || StringUtils.isNotBlank(spec.getyAxisTitle()))) {
      warnings.add("Only bar and line charts have axis titles, so I left them out.");
    }
    Integer refreshInterval = clampRefreshInterval(spec.getRefreshInterval(), warnings);

    // 9 + 10. Assemble, with the invariants that keep the query layer and statistic.js safe.
    Statistic statistic = new Statistic();
    if (previous != null && StringUtils.isNotBlank(previous.getId())) {
      statistic.setId(previous.getId());
    }
    statistic.setChartTarget(target);
    statistic.setChartType(chartType);
    statistic.setNames(new ArrayList<>());
    statistic.setDescriptions(new ArrayList<>());
    statistic.setName(StringUtils.trimToNull(spec.getName()));
    statistic.setDescription(StringUtils.trimToNull(spec.getDescription()));

    StatisticAggregation aggregation = new StatisticAggregation();
    aggregation.setField(groupBy);
    aggregation.setType(isCustomGroupBy ? DashboardColumnType.CUSTOM : DashboardColumnType.STANDARD);
    aggregation.setCustomFieldValue(customFieldName);
    aggregation.setInterval(interval);
    aggregation.setKpiField(kpiField);
    aggregation.setAggregationMethod(kpiMethod);
    statistic.setStatisticAggregation(aggregation);

    statistic.setFilters(filters);
    statistic.setRefreshInterval(refreshInterval);
    applyChartConfig(statistic, chartType, colors, spec);

    statistic.setPermissions(new ArrayList<>(List.of(ISecurityConstants.TOP_LEVEL_ROLE_NAME)));
    // Left off in this version; both are separate features with their own vocabulary.
    statistic.setConditionBasedColoringEnabled(false);
    statistic.setThresholdStatisticCharts(new ArrayList<>());
    statistic.setChartDrillDownEnabled(false);
    // The legacy raw-query fields must stay empty, or isOutdatedChart() flags the result and
    // getCanDrillDown() trips.
    statistic.setAggregates(null);
    statistic.setFilter(null);

    return SmartStatisticMappingResult.usable(statistic, warnings);
  }

  /**
   * A rejection reports only why it failed. Any clamps collected beforehand describe defaults
   * that were discarded along with the rest, so repeating them would misdescribe what happened.
   */
  private static SmartStatisticMappingResult reject(String reason) {
    List<String> only = new ArrayList<>();
    only.add(reason);
    return SmartStatisticMappingResult.rejected(only);
  }

  // ------------------------------------------------------------------ filters

  private static DashboardFilter toFilter(SmartChartFilterSpec spec, ChartTarget target,
      List<String> warnings) {
    if (spec == null) {
      return null;
    }
    String fieldName = StringUtils.trimToNull(spec.getField());
    if (fieldName == null) {
      warnings.add("A filter without a field was ignored.");
      return null;
    }

    // The `custom` hint is frequently wrong, so fall back to the other bucket before giving up.
    DashboardColumnType type =
        Boolean.TRUE.equals(spec.getCustom()) ? DashboardColumnType.CUSTOM : DashboardColumnType.STANDARD;
    FilterField filterField = findFilterField(target, fieldName, type);
    if (filterField == null) {
      type = DashboardColumnType.CUSTOM == type ? DashboardColumnType.STANDARD : DashboardColumnType.CUSTOM;
      filterField = findFilterField(target, fieldName, type);
    }
    if (filterField == null) {
      warnings.add(text("I could not use a filter on \"{0}\" because that field cannot be filtered. Available: {1}.", fieldName, filterFieldNames(target)));
      return null;
    }

    if (spec.getOperator() == null) {
      warnings.add(text("The filter on \"{0}\" had no operator, so I left it out.", fieldName));
      return null;
    }
    FilterOperator operator = FilterOperator.fromString(spec.getOperator().name()).orElse(null);
    List<FilterOperator> supported = StatisticFilterFieldService.statisticOperatorsFor(filterField);
    if (operator == null || !supported.contains(operator)) {
      warnings.add(text("\"{0}\" cannot be used on \"{1}\", so I left that filter out. Supported: {2}.", spec.getOperator().name(), fieldName,
          supported.stream().map(Enum::name).collect(Collectors.joining(", "))));
      return null;
    }

    DashboardFilter filter = new DashboardFilter();
    filter.setField(fieldName);
    filter.setFilterType(type);
    filter.setOperator(operator);
    filter.setValues(sanitizeValues(fieldName, target, spec.getValues(), warnings));
    filter.setFrom(StringUtils.trimToNull(spec.getFrom()));
    filter.setTo(StringUtils.trimToNull(spec.getTo()));
    filter.setPeriods(spec.getPeriods());
    filter.setPeriodType(toPeriodType(spec.getPeriodType()));

    if (!hasRequiredShape(filter, operator, fieldName, warnings)) {
      return null;
    }

    filterField.initFilter(filter);

    // The authoritative check: run exactly the call StatisticService.processFilter will make.
    // A blank result means the operator produces no query, and the filter would be silently
    // ignored at render time. Doing it here also parses any dates eagerly - getFromDate()
    // throws PortalException on bad input, which would otherwise surface much later.
    try {
      String query = ChartTarget.TASK == target
          ? filterField.generateTaskFilter(filter)
          : filterField.generateCaseFilter(filter);
      if (StringUtils.isBlank(query)) {
        warnings.add(text("The filter on \"{0}\" with operator \"{1}\" was incomplete, so I left it out.", fieldName, operator.name()));
        return null;
      }
    } catch (Exception e) {
      Ivy.log().warn("Smart statistic: cannot build a query for filter '" + fieldName + "'", e);
      warnings.add(text("The filter on \"{0}\" could not be applied and was left out.", fieldName));
      return null;
    }
    return filter;
  }

  private static boolean hasRequiredShape(DashboardFilter filter, FilterOperator operator,
      String fieldName, List<String> warnings) {
    boolean valid = switch (operator) {
      case IN, NOT_IN -> CollectionUtils.isNotEmpty(filter.getValues());
      case BETWEEN -> StringUtils.isNotBlank(filter.getFrom()) && StringUtils.isNotBlank(filter.getTo());
      case LAST, NEXT -> filter.getPeriods() != null && filter.getPeriods() > 0
          && filter.getPeriodType() != null;
      case CURRENT -> filter.getPeriodType() != null
          && FilterPeriodType.PERIOD_TYPES_FOR_CURRENT_OPERATOR.contains(filter.getPeriodType());
      case IS, BEFORE, AFTER -> StringUtils.isNotBlank(filter.getFrom());
      case CONTAINS, NOT_CONTAINS -> CollectionUtils.isNotEmpty(filter.getValues())
          || StringUtils.isNotBlank(filter.getValue());
      default -> true;
    };
    if (!valid) {
      warnings.add(text("The filter on \"{0}\" with operator \"{1}\" was incomplete, so I left it out.", fieldName, operator.name()));
    }
    return valid;
  }

  /**
   * State and priority are closed vocabularies. Unknown members are dropped individually; a
   * filter left with no values at all is then rejected by the shape check.
   */
  private static List<String> sanitizeValues(String fieldName, ChartTarget target,
      List<String> values, List<String> warnings) {
    List<String> input = nullSafe(values).stream()
        .filter(StringUtils::isNotBlank)
        .map(String::trim)
        .toList();
    if (input.isEmpty()) {
      return new ArrayList<>();
    }

    Set<String> allowed = null;
    if (STATE_FIELD.equals(fieldName)) {
      allowed = ChartTarget.TASK == target
          ? enumNames(TaskBusinessState.values())
          : enumNames(CaseBusinessState.values());
    } else if (PRIORITY_FIELD.equals(fieldName)) {
      allowed = enumNames(WorkflowPriority.values());
    }
    if (allowed == null) {
      return new ArrayList<>(input);
    }

    final Set<String> permitted = allowed;
    List<String> kept = input.stream()
        .filter(value -> permitted.contains(value.toUpperCase(Locale.ROOT)))
        .map(value -> value.toUpperCase(Locale.ROOT))
        .collect(Collectors.toCollection(ArrayList::new));
    if (kept.size() < input.size()) {
      List<String> dropped = new ArrayList<>(input);
      dropped.removeIf(value -> permitted.contains(value.toUpperCase(Locale.ROOT)));
      warnings.add(text("I ignored the unknown {1} values {0}. Allowed: {2}.", String.join(", ", dropped), fieldName,
          String.join(", ", permitted)));
    }
    return kept;
  }

  // ------------------------------------------------------------- chart config

  /**
   * Populates the config matching the chart type and leaves the other three null, mirroring
   * {@code cleanUpRedundantChartConfigs}. Uses a plain PieChartConfig rather than the anonymous
   * subclass the classic bean instantiates - an anonymous type can serialize differently.
   */
  private static void applyChartConfig(Statistic statistic, ChartType chartType,
      List<String> colors, SmartChartSpec spec) {
    switch (chartType) {
      case BAR -> {
        BarChartConfig config = new BarChartConfig();
        applyColumnConfig(config, colors, spec);
        statistic.setBarChartConfig(config);
      }
      case LINE -> {
        LineChartConfig config = new LineChartConfig();
        applyColumnConfig(config, colors, spec);
        statistic.setLineChartConfig(config);
      }
      case PIE -> {
        PieChartConfig config = new PieChartConfig();
        config.setBackgroundColors(colors);
        config.setConditionBasedColoringEnabled(false);
        statistic.setPieChartConfig(config);
      }
      case NUMBER -> {
        NumberChartConfig config = new NumberChartConfig();
        config.setHideLabel(Boolean.TRUE.equals(spec.getHideLabel()));
        statistic.setNumberChartConfig(config);
      }
    }
  }

  /**
   * Axis titles persist as {@code List<DisplayName>}; the transient xTitle/yTitle fields exist
   * only for the classic form's binding, so writing them alone would store nothing.
   */
  private static void applyColumnConfig(ColumnChartConfig config, List<String> colors, SmartChartSpec spec) {
    config.setBackgroundColors(colors);
    config.setxTitles(toDisplayNames(spec.getxAxisTitle()));
    config.setyTitles(toDisplayNames(spec.getyAxisTitle()));
  }

  private static List<DisplayName> toDisplayNames(String value) {
    List<DisplayName> names = new ArrayList<>();
    if (StringUtils.isBlank(value)) {
      return names;
    }
    DisplayNameConvertor.initMultipleLanguages(value, names);
    DisplayNameConvertor.setValue(value, names);
    return names;
  }

  /**
   * Matches the bounds the classic configurator validates against. statistic.js independently
   * clamps to 60s at render time, so clamping here keeps the saved config and the observed
   * behaviour in agreement rather than silently diverging.
   */
  private static Integer clampRefreshInterval(Integer requested, List<String> warnings) {
    if (requested == null || requested <= 0) {
      return null;
    }
    if (requested < MIN_REFRESH_INTERVAL_SECONDS) {
      warnings.add(text("An auto-refresh of {0} seconds is not allowed, so I used {1} seconds.", requested, MIN_REFRESH_INTERVAL_SECONDS));
      return MIN_REFRESH_INTERVAL_SECONDS;
    }
    if (requested > MAX_REFRESH_INTERVAL_SECONDS) {
      warnings.add(text("An auto-refresh of {0} seconds is not allowed, so I used {1} seconds.", requested, MAX_REFRESH_INTERVAL_SECONDS));
      return MAX_REFRESH_INTERVAL_SECONDS;
    }
    return requested;
  }

  private static List<String> sanitizeColors(List<String> colors, List<String> warnings) {
    List<String> input = nullSafe(colors);
    List<String> kept = input.stream()
        .filter(color -> color != null && HEX_COLOR.matcher(color.trim()).matches())
        .map(String::trim)
        .collect(Collectors.toCollection(ArrayList::new));
    if (kept.size() < input.size()) {
      warnings.add("Some colors were not valid hex values and were replaced by the default palette.");
    }
    List<String> defaults = StatisticConfigurationBean.DEFAULT_COLORS;
    while (kept.size() < MIN_COLOR_SLOTS) {
      kept.add(defaults.get(kept.size() % defaults.size()));
    }
    return kept;
  }

  // ------------------------------------------------------------------ reverse

  /**
   * Renders an existing configuration back into the agent's vocabulary, so a chart loaded from
   * storage can be refined by chat and so each turn can be told what the current state is.
   */
  public static SmartChartSpec toSpec(Statistic statistic) {
    if (statistic == null) {
      return null;
    }
    SmartChartSpec spec = new SmartChartSpec();
    spec.setName(statistic.getName());
    spec.setDescription(statistic.getDescription());
    spec.setChartType(statistic.getChartType() == null
        ? null
        : SmartChartSpec.ChartTypeValue.valueOf(statistic.getChartType().name()));
    spec.setTarget(statistic.getChartTarget() == null
        ? null
        : SmartChartSpec.ChartTargetValue.valueOf(statistic.getChartTarget().name()));

    StatisticAggregation aggregation = statistic.getStatisticAggregation();
    if (aggregation != null) {
      spec.setGroupByField(aggregation.getField());
      spec.setCustomFieldName(aggregation.getCustomFieldValue());
      spec.setInterval(aggregation.getInterval() == null
          ? null
          : SmartChartSpec.IntervalValue.valueOf(aggregation.getInterval().name()));
      spec.setKpiField(aggregation.getKpiField());
      String method = aggregation.getAggregationMethod();
      if (StringUtils.isNotBlank(method)) {
        try {
          spec.setKpiMethod(SmartChartSpec.AggregationMethodValue.valueOf(method.toUpperCase(Locale.ROOT)));
        } catch (IllegalArgumentException e) {
          spec.setKpiMethod(null);
        }
      }
    }

    spec.setBackgroundColors(new ArrayList<>(backgroundColorsOf(statistic)));
    if (statistic.getNumberChartConfig() != null) {
      spec.setHideLabel(statistic.getNumberChartConfig().getHideLabel());
    }
    spec.setFilters(nullSafe(statistic.getFilters()).stream()
        .map(SmartChartSpecMapper::toFilterSpec)
        .collect(Collectors.toCollection(ArrayList::new)));
    return spec;
  }

  private static SmartChartFilterSpec toFilterSpec(DashboardFilter filter) {
    SmartChartFilterSpec spec = new SmartChartFilterSpec();
    spec.setField(filter.getField());
    if (filter.getOperator() != null) {
      try {
        spec.setOperator(SmartChartFilterSpec.OperatorValue.valueOf(filter.getOperator().name()));
      } catch (IllegalArgumentException e) {
        spec.setOperator(null);
      }
    }
    spec.setValues(new ArrayList<>(nullSafe(filter.getValues())));
    spec.setFrom(filter.getFrom());
    spec.setTo(filter.getTo());
    spec.setPeriods(filter.getPeriods());
    if (filter.getPeriodType() != null) {
      spec.setPeriodType(SmartChartFilterSpec.PeriodTypeValue.valueOf(filter.getPeriodType().name()));
    }
    spec.setCustom(DashboardColumnType.CUSTOM == filter.getFilterType());
    return spec;
  }

  private static List<String> backgroundColorsOf(Statistic statistic) {
    if (statistic.getChartType() == null) {
      return List.of();
    }
    List<String> colors = switch (statistic.getChartType()) {
      case BAR -> Optional.ofNullable(statistic.getBarChartConfig())
          .map(ColumnChartConfig::getBackgroundColors).orElse(null);
      case LINE -> Optional.ofNullable(statistic.getLineChartConfig())
          .map(ColumnChartConfig::getBackgroundColors).orElse(null);
      case PIE -> Optional.ofNullable(statistic.getPieChartConfig())
          .map(PieChartConfig::getBackgroundColors).orElse(null);
      default -> null;
    };
    return colors == null ? List.of()
        : colors.stream().filter(StringUtils::isNotBlank).toList();
  }

  // ------------------------------------------------------------------ helpers

  private static FilterField findFilterField(ChartTarget target, String field, DashboardColumnType type) {
    try {
      return ChartTarget.TASK == target
          ? TaskFilterFieldFactory.findBy(field, type)
          : CaseFilterFieldFactory.findBy(field, type);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  private static String filterFieldNames(ChartTarget target) {
    return StatisticFilterFieldService.filterFields(target).stream()
        .map(FilterField::getName)
        .collect(Collectors.joining(", "));
  }

  private static String available(ChartTarget target, ChartType chartType) {
    return SmartStatisticVocabularyService.availableGroupByFields(target, chartType).stream()
        .map(AggregationField::getName)
        .sorted()
        .collect(Collectors.joining(", "));
  }

  private static ChartTarget toTarget(SmartChartSpec.ChartTargetValue value) {
    return value == null ? null : ChartTarget.valueOf(value.name());
  }

  private static ChartType toChartType(SmartChartSpec.ChartTypeValue value) {
    return value == null ? null : ChartType.valueOf(value.name());
  }

  private static AggregationInterval toInterval(SmartChartSpec.IntervalValue value) {
    return value == null ? null : AggregationInterval.valueOf(value.name());
  }

  private static FilterPeriodType toPeriodType(SmartChartFilterSpec.PeriodTypeValue value) {
    return value == null ? null : FilterPeriodType.valueOf(value.name());
  }

  private static Set<String> enumNames(Enum<?>[] values) {
    return Arrays.stream(values).map(Enum::name).collect(Collectors.toCollection(java.util.LinkedHashSet::new));
  }

  private static <T> List<T> nullSafe(List<T> list) {
    return list == null ? Collections.emptyList() : list;
  }

  private static String text(String template, Object... params) {
    return MessageFormat.format(template, Arrays.stream(params).map(String::valueOf).toArray());
  }
}
