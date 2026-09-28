package com.axonivy.portal.smart.statistic.provider;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.BarChartConfig;
import com.axonivy.portal.bo.ColumnChartConfig;
import com.axonivy.portal.bo.LineChartConfig;
import com.axonivy.portal.bo.NumberChartConfig;
import com.axonivy.portal.bo.PieChartConfig;
import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicField;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicFieldCatalogue;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartProposal;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartProposal.FilterProposal;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Aggregation;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.DateBucket;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Filter;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.SortOrder;
import com.axonivy.portal.smart.statistic.dto.SmartStatisticMappingResult;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.enums.statistic.CustomChartType;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;
import ch.ivyteam.ivy.security.ISecurityConstants;

/**
 * Turns the agent's {@link ProviderChartProposal} into a stored {@link Statistic}.
 *
 * Same policy as {@code SmartChartSpecMapper}: clamp what is recoverable and report it, reject what
 * is not. The dividing line is whether a substitution would still answer the question that was
 * asked - a limit of 500 can be clamped to 50 and still mean the same thing, but a field that does
 * not exist cannot be swapped for one that does.
 *
 * Everything is validated against the {@link com.axonivy.portal.smart.statistic.provider.dto.DynamicFieldCatalogue DynamicFieldCatalogue} that was derived from real data,
 * so a hallucinated field name is caught here rather than producing an empty chart later.
 */
public class ProviderChartMapper {

  private ProviderChartMapper() {}

  public static SmartStatisticMappingResult toStatistic(ProviderChartProposal proposal,
      SubProcessCallStartEvent provider, DynamicFieldCatalogue catalogue, Statistic previous) {
    List<String> warnings = new ArrayList<>();
    if (proposal == null || provider == null) {
      return reject("The response contained no chart configuration.");
    }
    if (catalogue == null || catalogue.isEmpty()) {
      return reject(text("The \"{0}\" data source returned no records, so there is nothing to chart yet.", provider.description().name()));
    }

    // 1. Chart type. Defaulted rather than rejected: the fields are the substance of the request
    // and a bar chart of the right numbers still answers it.
    CustomChartType type = CustomChartType.find(proposal.getChartType()).orElse(null);
    if (type == null) {
      type = CustomChartType.BAR;
      warnings.add(text("I did not recognise the chart type, so I used \"{0}\".", type.name()));
    }

    // 2. Group-by. A NUMBER chart legitimately has none; anything else without one cannot be drawn.
    DynamicField labelField = null;
    String labelPath = StringUtils.trimToNull(proposal.getLabelPath());
    if (type.isSingleValue()) {
      if (labelPath != null) {
        warnings.add(text("A single figure has no breakdown, so I ignored the grouping by \"{0}\".", labelPath));
        labelPath = null;
      }
    } else {
      if (labelPath == null) {
        return reject(text("The configuration had no field to group by. Available: {0}.", groupable(catalogue)));
      }
      labelField = resolve(catalogue, labelPath, warnings);
      if (labelField == null) {
        return reject(text("\"{0}\" is not a field in that data. Available: {1}.", labelPath, groupable(catalogue)));
      }
      if (!labelField.isGroupable()) {
        return reject(text("\"{0}\" cannot be grouped by. Available: {1}.", labelField.getTitle(), groupable(catalogue)));
      }
      labelPath = labelField.getFieldName();
    }

    // 3. Measure. Falls back to counting records, which is a different but still honest answer,
    // so it warns instead of rejecting.
    Aggregation aggregation = proposal.getAggregation() == null ? Aggregation.COUNT : proposal.getAggregation();
    String valuePath = StringUtils.trimToNull(proposal.getValuePath());
    if (Aggregation.COUNT != aggregation) {
      DynamicField valueField = valuePath == null ? null : resolve(catalogue, valuePath, warnings);
      if (valueField == null) {
        warnings.add(text("I counted the records instead, because \"{0}\" is not a field in that data. Numeric fields: {1}.",
            StringUtils.defaultString(valuePath), measurable(catalogue)));
        aggregation = Aggregation.COUNT;
        valuePath = null;
      } else if (!valueField.isMeasurable()) {
        warnings.add(text("I counted the records instead, because \"{0}\" is not a number. Numeric fields: {1}.", valuePath, measurable(catalogue)));
        aggregation = Aggregation.COUNT;
        valuePath = null;
      } else if (!valueField.getType().supports(aggregation)) {
        // Totalling a percentage, and the like: the field is numeric but the measure is not
        // meaningful for what it holds.
        Aggregation fallback = valueField.getType().defaultMeasure();
        warnings.add(text("\"{0}\" cannot be measured by {1}, so I used {2} instead.", valueField.getTitle(),
            aggregation.name(), fallback.name()));
        aggregation = fallback;
        if (Aggregation.COUNT == aggregation) {
          valuePath = null;
        }
      } else {
        valuePath = valueField.getFieldName();
      }
    } else {
      valuePath = null;
    }

    // 4. Date bucketing. Required by a line chart over dates, meaningless over anything else.
    DateBucket dateBucket = proposal.getDateBucket();
    boolean labelIsDate = labelField != null && labelField.isNeedsDateBucket();
    if (dateBucket != null && !labelIsDate) {
      warnings.add("The field being grouped by is not a date, so I ignored the time period.");
      dateBucket = null;
    }
    if (dateBucket == null && labelIsDate && CustomChartType.LINE == type) {
      dateBucket = DateBucket.MONTH;
      warnings.add(text("I grouped the dates by {0}, which was the most useful period here.", dateBucket.name()));
    }

    // 5. Sort and limit, clamped to what a card can actually show.
    SortOrder sort = proposal.getSort();
    if (sort == null) {
      // A time series read out of value order is unreadable, whatever it was asked to sort by.
      sort = dateBucket != null ? SortOrder.LABEL_ASC : SortOrder.VALUE_DESC;
    } else if (dateBucket != null && (SortOrder.VALUE_ASC == sort || SortOrder.VALUE_DESC == sort)) {
      sort = SortOrder.LABEL_ASC;
      warnings.add("A chart over time only reads correctly in date order, so I sorted it chronologically.");
    }
    int limit = clampLimit(proposal.getLimit(), type, warnings);

    // 6. Filters. A bad filter drops itself rather than the whole chart, as elsewhere.
    List<Filter> filters = new ArrayList<>();
    for (FilterProposal filterProposal : nullSafe(proposal.getFilters())) {
      toFilter(filterProposal, catalogue, warnings).ifPresent(filters::add);
    }

    boolean supportsAxisTitles = CustomChartType.BAR == type || CustomChartType.LINE == type;
    if (!supportsAxisTitles
        && (StringUtils.isNotBlank(proposal.getxAxisTitle()) || StringUtils.isNotBlank(proposal.getyAxisTitle()))) {
      warnings.add("Only bar and line charts have axis titles, so I left them out.");
    }

    ProviderChartSpec spec = new ProviderChartSpec();
    spec.setProviderSignature(ProviderRunner.signatureOf(provider));
    spec.setProviderName(provider.description().name());
    spec.setApplicationId(ProviderRunner.currentApplicationId());
    spec.setLabelPath(labelPath);
    spec.setValuePath(valuePath);
    spec.setAggregation(aggregation);
    spec.setDateBucket(dateBucket);
    spec.setSort(sort);
    spec.setLimit(limit);
    spec.setFilters(filters);
    spec.setFieldCatalogue(catalogue);

    return SmartStatisticMappingResult.usable(assemble(proposal, spec, type, previous), warnings);
  }

  /**
   * The reverse trip, so a saved chart can be refined by prompt.
   *
   * Without this a follow-up would have no idea what is on screen and would rebuild from scratch -
   * the same reason {@code SmartChartSpecMapper.toSpec} exists for task and case charts.
   */
  public static ProviderChartProposal toProposal(Statistic statistic) {
    if (statistic == null || !statistic.isProviderBacked()) {
      return null;
    }
    ProviderChartSpec spec = statistic.getCustomChart();
    ProviderChartProposal proposal = new ProviderChartProposal();
    proposal.setProvider(spec.getProviderName());
    proposal.setName(statistic.getName());
    proposal.setDescription(statistic.getDescription());
    CustomChartType.fromChartType(statistic.getChartType())
        .ifPresent(type -> proposal.setChartType(type.name()));
    proposal.setLabelPath(spec.getLabelPath());
    proposal.setValuePath(spec.getValuePath());
    proposal.setAggregation(spec.getAggregation());
    proposal.setDateBucket(spec.getDateBucket());
    proposal.setSort(spec.getSort());
    proposal.setLimit(spec.getLimit());

    List<FilterProposal> filters = new ArrayList<>();
    for (Filter filter : spec.getFilters()) {
      FilterProposal filterProposal = new FilterProposal();
      filterProposal.setPath(filter.getPath());
      filterProposal.setOperator(filter.getOperator());
      filterProposal.setValue(filter.getValue());
      filters.add(filterProposal);
    }
    proposal.setFilters(filters);
    return proposal;
  }

  // ---------------------------------------------------------------- assembly

  private static Statistic assemble(ProviderChartProposal proposal, ProviderChartSpec spec,
      CustomChartType type, Statistic previous) {
    ChartType chartType = type.getChartType();
    Statistic statistic = new Statistic();
    if (previous != null && StringUtils.isNotBlank(previous.getId())) {
      statistic.setId(previous.getId());
    }
    statistic.setChartType(chartType);
    statistic.setNames(new ArrayList<>());
    statistic.setDescriptions(new ArrayList<>());
    statistic.setName(StringUtils.trimToNull(proposal.getName()));
    statistic.setDescription(StringUtils.trimToNull(proposal.getDescription()));
    statistic.setCustomChart(spec);

    // No chart target: the data is neither tasks nor cases, and getChartData() is never reached
    // for a provider-backed chart because resolveChartResult() branches before it.
    statistic.setChartTarget(null);
    statistic.setStatisticAggregation(syntheticAggregation(spec));

    statistic.setFilters(new ArrayList<>());
    statistic.setRefreshInterval(null);
    applyChartConfig(statistic, chartType, proposal);

    statistic.setPermissions(new ArrayList<>(List.of(ISecurityConstants.TOP_LEVEL_ROLE_NAME)));
    statistic.setConditionBasedColoringEnabled(false);
    statistic.setThresholdStatisticCharts(new ArrayList<>());
    statistic.setChartDrillDownEnabled(false);
    statistic.setAggregates(null);
    statistic.setFilter(null);
    return statistic;
  }

  /**
   * statistic.js reads the aggregation without guarding it - {@code shouldRenderEmptyChart} tests
   * {@code kpiField} and the number chart's label formatter reads {@code field} - so one has to
   * exist even though no Elasticsearch query is ever built from it.
   *
   * The field name deliberately avoids the word "timestamp": {@code isTimestampField()} matches on
   * that substring and would re-format labels this projector has already formatted.
   */
  public static StatisticAggregation syntheticAggregation(ProviderChartSpec spec) {
    StatisticAggregation aggregation = new StatisticAggregation();
    aggregation.setField(StringUtils.defaultIfBlank(spec.getLabelPath(), "total"));
    aggregation.setType(DashboardColumnType.STANDARD);
    if (Aggregation.COUNT != spec.getAggregation() && StringUtils.isNotBlank(spec.getValuePath())) {
      aggregation.setKpiField(spec.getValuePath());
      aggregation.setAggregationMethod(spec.getAggregation().name().toLowerCase(Locale.ROOT));
    }
    return aggregation;
  }

  private static void applyChartConfig(Statistic statistic, ChartType chartType,
      ProviderChartProposal proposal) {
    switch (chartType) {
      case BAR -> {
        BarChartConfig config = new BarChartConfig();
        applyColumnConfig(config, proposal);
        statistic.setBarChartConfig(config);
      }
      case LINE -> {
        LineChartConfig config = new LineChartConfig();
        applyColumnConfig(config, proposal);
        statistic.setLineChartConfig(config);
      }
      case PIE -> {
        PieChartConfig config = new PieChartConfig();
        config.setConditionBasedColoringEnabled(false);
        statistic.setPieChartConfig(config);
      }
      case NUMBER -> {
        NumberChartConfig config = new NumberChartConfig();
        // There is only ever one figure and its label would repeat the chart title.
        config.setHideLabel(true);
        statistic.setNumberChartConfig(config);
      }
    }
  }

  private static void applyColumnConfig(ColumnChartConfig config, ProviderChartProposal proposal) {
    config.setxTitles(toDisplayNames(proposal.getxAxisTitle()));
    config.setyTitles(toDisplayNames(proposal.getyAxisTitle()));
  }

  private static List<DisplayName> toDisplayNames(String value) {
    List<DisplayName> names = new ArrayList<>();
    if (StringUtils.isBlank(value)) {
      return names;
    }
    DisplayNameConvertor.initMultipleLanguages(value.trim(), names);
    DisplayNameConvertor.setValue(value.trim(), names);
    return names;
  }

  // ----------------------------------------------------------------- pieces

  private static java.util.Optional<Filter> toFilter(FilterProposal proposal,
      DynamicFieldCatalogue catalogue, List<String> warnings) {
    if (proposal == null) {
      return java.util.Optional.empty();
    }
    String path = StringUtils.trimToNull(proposal.getPath());
    if (path == null || proposal.getOperator() == null) {
      warnings.add("A filter without a field was ignored.");
      return java.util.Optional.empty();
    }
    DynamicField field = resolve(catalogue, path, warnings);
    if (field == null) {
      warnings.add(text("I could not filter on \"{0}\" because that data has no such field.", path));
      return java.util.Optional.empty();
    }
    Filter filter = new Filter();
    filter.setPath(field.getFieldName());
    filter.setOperator(proposal.getOperator());
    filter.setValue(proposal.getValue());
    return java.util.Optional.of(filter);
  }

  private static int clampLimit(Integer requested, CustomChartType type, List<String> warnings) {
    int max = type.isSingleValue() ? 1 : Math.min(type.getMaxBuckets(), ProviderChartProjector.MAX_BUCKETS);
    if (requested == null || requested <= 0) {
      return max;
    }
    if (requested > max) {
      warnings.add(text("Showing {0} groups would be unreadable, so I limited the chart to {1}.", requested, max));
      return max;
    }
    return requested;
  }

  /** Accepts a title where a path belongs, but only when it is unambiguous. */
  private static DynamicField resolve(DynamicFieldCatalogue catalogue, String value, List<String> warnings) {
    DynamicField field = catalogue.find(value);
    if (field != null) {
      return field;
    }
    field = catalogue.findByTitle(value);
    if (field != null) {
      warnings.add(text("I read \"{0}\" as the field \"{1}\".", value, field.getFieldName()));
    }
    return field;
  }

  private static String groupable(DynamicFieldCatalogue catalogue) {
    return catalogue.getGroupableFields().stream()
        .map(DynamicField::getFieldName).collect(Collectors.joining(", "));
  }

  private static String measurable(DynamicFieldCatalogue catalogue) {
    String fields = catalogue.getMeasurableFields().stream()
        .map(DynamicField::getFieldName).collect(Collectors.joining(", "));
    return StringUtils.defaultIfBlank(fields, "none");
  }

  private static <T> List<T> nullSafe(List<T> values) {
    return values == null ? List.of() : values;
  }

  private static SmartStatisticMappingResult reject(String reason) {
    List<String> only = new ArrayList<>();
    only.add(reason);
    return SmartStatisticMappingResult.rejected(only);
  }

  private static String text(String template, Object... params) {
    return MessageFormat.format(template, Arrays.stream(params).map(String::valueOf).toArray());
  }
}
