package com.axonivy.portal.smart.statistic.provider;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.smart.statistic.dto.SmartStatisticMappingResult;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicField;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicFieldCatalogue;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartProposal;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartProposal.FilterProposal;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Aggregation;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.DateBucket;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Filter;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.FilterOperator;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.SortOrder;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartForm;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.enums.statistic.CustomChartType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;
import jakarta.faces.model.SelectItem;
import jakarta.faces.model.SelectItemGroup;

/**
 * The configuration form's side of provider-backed charts: which sources exist, how to switch a
 * chart between a Portal target and a provider, and how the form's state becomes a proposal the
 * mapper can validate.
 *
 * Deliberately has no opinion on whether a configuration is valid - that stays in
 * {@link ProviderChartMapper}, so a hand-built chart and an agent-built one are checked by the
 * same rules. What lives here is only what the mapper cannot do: talk to the form.
 */
public class ProviderChartService {

  public static final String PROVIDER_PREFIX = "provider:";

  /** Enough to see the shape of the data and check a few figures against the chart. */
  public static final int RESULT_PREVIEW_ROWS = 50;

  private static final ObjectWriter PRETTY = new ObjectMapper().writerWithDefaultPrettyPrinter();

  private ProviderChartService() {}

  // ------------------------------------------------------------------ sources

  /** Two groups, so a Portal target and a data provider read as the alternatives they are. */
  public static List<SelectItem> dataSourceGroups() {
    List<SelectItem> groups = new ArrayList<>();

    SelectItemGroup portalData = new SelectItemGroup("Portal data");
    portalData.setSelectItems(Arrays.stream(ChartTarget.values())
        .map(target -> new SelectItem(target.name(), target.getCmsName()))
        .toArray(SelectItem[]::new));
    groups.add(portalData);

    List<SelectItem> providers = new ProviderCollector().providerStarts().stream()
        .map(start -> new SelectItem(PROVIDER_PREFIX + start.description().name(),
            humanize(start.description().name())))
        .toList();
    if (!providers.isEmpty()) {
      SelectItemGroup providerGroup = new SelectItemGroup("Data providers");
      providerGroup.setSelectItems(providers.toArray(SelectItem[]::new));
      groups.add(providerGroup);
    }
    return groups;
  }

  public static String sourceKeyOf(Statistic chart) {
    if (chart != null && chart.isProviderBacked()) {
      return PROVIDER_PREFIX + StringUtils.defaultString(chart.getCustomChart().getProviderName());
    }
    ChartTarget target = chart == null ? null : chart.getChartTarget();
    return (target == null ? ChartTarget.TASK : target).name();
  }

  public static boolean isProviderKey(String key) {
    return key != null && key.startsWith(PROVIDER_PREFIX);
  }

  public static String providerNameOf(String key) {
    return isProviderKey(key) ? key.substring(PROVIDER_PREFIX.length()) : null;
  }

  /** {@code provideAccountsReceivable} reads as "Accounts receivable" in a dropdown. */
  public static String humanize(String methodName) {
    if (StringUtils.isBlank(methodName)) {
      return StringUtils.EMPTY;
    }
    String words = methodName.startsWith("provide") ? methodName.substring("provide".length()) : methodName;
    words = words.replaceAll("(?<=[a-z0-9])(?=[A-Z])", " ").trim();
    return words.isEmpty() ? methodName
        : words.substring(0, 1).toUpperCase(Locale.ROOT) + words.substring(1).toLowerCase(Locale.ROOT);
  }

  // ---------------------------------------------------------------- catalogue

  /**
   * Loads what the provider returns right now into the form, for display next to the chart.
   *
   * Capped at {@value #RESULT_PREVIEW_ROWS} rows: the point is to let the numbers be read and
   * compared, and a few thousand pretty-printed records would only be scrolled past. The row count
   * reported is the real one, so the cap is never mistaken for the whole data set.
   */
  private static void readResultInto(ProviderChartForm form, SubProcessCallStartEvent provider) {
    form.setResultLoaded(true);
    form.setResultJson(null);
    form.setResultTotalRows(0);
    form.setResultShownRows(0);

    JsonNode data = ProviderRunner.fetchData(provider).orElse(null);
    if (data == null) {
      return;
    }
    List<JsonNode> rows = ProviderRunner.rowsOf(data);
    form.setResultTotalRows(rows.size());

    List<JsonNode> shown = rows.size() > RESULT_PREVIEW_ROWS ? rows.subList(0, RESULT_PREVIEW_ROWS) : rows;
    form.setResultShownRows(shown.size());
    try {
      form.setResultJson(PRETTY.writeValueAsString(shown));
    } catch (JsonProcessingException e) {
      // The data was parsed on the way in, so this is not a case worth failing the page over.
      Ivy.log().warn("Smart statistic: cannot render the result of "
          + ProviderRunner.signatureOf(provider), e);
    }
  }

  // -------------------------------------------------------------- conversions

  /**
   * Turns a chart into a provider-backed one, mirroring the non-destructive half of
   * {@code ProviderChartMapper.assemble} - the name, description, permissions and colours are the
   * form's, not this method's.
   *
   * A group-by field is preselected because otherwise the very first preview after the switch would
   * be rejected for something the user never did.
   */
  public static void toProvider(Statistic chart, SubProcessCallStartEvent provider,
      DynamicFieldCatalogue catalogue) {
    ProviderChartSpec spec = new ProviderChartSpec();
    spec.setProviderSignature(ProviderRunner.signatureOf(provider));
    spec.setProviderName(provider.description().name());
    spec.setApplicationId(ProviderRunner.currentApplicationId());
    spec.setFieldCatalogue(catalogue);
    if (!isSingleValue(chart.getChartType())) {
      catalogue.getGroupableFields().stream().findFirst()
          .ifPresent(field -> spec.setLabelPath(field.getFieldName()));
    }

    chart.setCustomChart(spec);
    chart.setChartTarget(null);
    chart.setStatisticAggregation(ProviderChartMapper.syntheticAggregation(spec));
    chart.setFilters(new ArrayList<>());
    chart.setRefreshInterval(null);
    chart.setChartDrillDownEnabled(false);
    chart.setConditionBasedColoringEnabled(false);
    chart.setThresholdStatisticCharts(new ArrayList<>());
    chart.setAggregates(null);
    chart.setFilter(null);
  }

  /** The inverse: back to a Portal target, with the aggregation a new chart starts from. */
  public static void toStandard(Statistic chart, ChartTarget target) {
    chart.setCustomChart(null);
    chart.setChartTarget(target);
    StatisticAggregation aggregation = new StatisticAggregation();
    aggregation.setField(AggregationField.PRIORITY.getName());
    aggregation.setType(DashboardColumnType.STANDARD);
    chart.setStatisticAggregation(aggregation);
    chart.setFilters(new ArrayList<>());
  }

  public static ProviderChartForm toForm(Statistic chart) {
    ProviderChartForm form = new ProviderChartForm();
    if (chart != null && chart.isProviderBacked()) {
      form.apply(chart.getCustomChart());
      // Only the id is stored, and this is the one application whose name can be resolved. A chart
      // naming a different one shows the raw id rather than a name that is not its own; a chart
      // stored before the id existed is read as this one, which is where it is being rendered.
      Long applicationId = form.getApplicationId();
      if (applicationId == null
          || Objects.equals(applicationId, ProviderRunner.currentApplicationId())) {
        form.setApplicationName(ProviderRunner.currentApplicationName());
      }
    }
    return form;
  }

  public static ProviderChartProposal toProposal(ProviderChartForm form, Statistic chart,
      String xTitle, String yTitle) {
    ProviderChartProposal proposal = new ProviderChartProposal();
    proposal.setProvider(form.getProviderName());
    proposal.setName(chart.getName());
    proposal.setDescription(chart.getDescription());
    CustomChartType.fromChartType(chart.getChartType())
        .ifPresent(type -> proposal.setChartType(type.name()));
    proposal.setLabelPath(form.getLabelPath());
    proposal.setValuePath(form.getValuePath());
    proposal.setAggregation(form.getAggregation());
    proposal.setDateBucket(form.getDateBucket());
    proposal.setSort(form.getSort());
    proposal.setLimit(form.getLimit());
    proposal.setxAxisTitle(xTitle);
    proposal.setyAxisTitle(yTitle);

    List<FilterProposal> filters = new ArrayList<>();
    for (Filter filter : form.getFilters()) {
      FilterProposal filterProposal = new FilterProposal();
      filterProposal.setPath(filter.getPath());
      filterProposal.setOperator(filter.getOperator());
      filterProposal.setValue(filter.getValue());
      filters.add(filterProposal);
    }
    proposal.setFilters(filters);
    return proposal;
  }

  // ----------------------------------------------------------------- shaping

  /**
   * Applies the rules that need nothing but the catalogue, so the controls on screen already agree
   * with each other before anything is submitted. The mapper still has the last word on save.
   */
  public static void normalise(ProviderChartForm form, ChartType chartType) {
    form.setMaxLimit(maxLimit(chartType));

    // A field to group by that this data has no longer - or none chosen yet. Left alone it would be
    // the dropdown's value without being one of its items, which reads as an empty dropdown.
    List<DynamicField> groupable = form.getGroupableFields();
    if (!isSingleValue(chartType) && !groupable.isEmpty()
        && groupable.stream().noneMatch(field -> field.getFieldName().equals(form.getLabelPath()))) {
      form.setLabelPath(groupable.get(0).getFieldName());
    }

    // Asking for a sum without saying of what: the first numeric field, so the aggregation the user
    // just picked survives instead of being clamped straight back to COUNT.
    if (form.isMeasureRequired() && StringUtils.isBlank(form.getValuePath())) {
      form.getMeasurableFields().stream().findFirst()
          .ifPresent(field -> form.setValuePath(field.getFieldName()));
    }

    // A measure the chosen field's type does not allow, such as totalling a percentage.
    if (!form.getAvailableMeasures().contains(form.getAggregation())) {
      form.setAggregation(form.getAvailableMeasures().stream().findFirst().orElse(Aggregation.COUNT));
    }

    if (isSingleValue(chartType)) {
      form.setLabelPath(null);
      form.setDateBucket(null);
      form.setLimit(1);
    } else if (form.getLimit() < 1 || form.getLimit() > form.getMaxLimit()) {
      form.setLimit(form.getMaxLimit());
    }

    if (!form.isLabelDate()) {
      form.setDateBucket(null);
    } else if (form.getDateBucket() == null && ChartType.LINE == chartType) {
      form.setDateBucket(DateBucket.MONTH);
    }

    if (form.getDateBucket() != null
        && (SortOrder.VALUE_ASC == form.getSort() || SortOrder.VALUE_DESC == form.getSort())) {
      form.setSort(SortOrder.LABEL_ASC);
    }

    if (!form.isMeasureRequired()) {
      form.setValuePath(null);
    }
  }

  /** The same ceiling {@code ProviderChartMapper.clampLimit} enforces. */
  public static int maxLimit(ChartType chartType) {
    return CustomChartType.fromChartType(chartType)
        .map(type -> type.isSingleValue() ? 1
            : Math.min(type.getMaxBuckets(), ProviderChartProjector.MAX_BUCKETS))
        .orElse(ProviderChartSpec.DEFAULT_LIMIT);
  }

  private static boolean isSingleValue(ChartType chartType) {
    return CustomChartType.fromChartType(chartType).map(CustomChartType::isSingleValue).orElse(false);
  }

  // ------------------------------------------------------------- select items

  /**
   * The measures the chosen field's type allows - or, while no field is chosen yet, every measure
   * this data allows at all.
   */
  public static List<SelectItem> aggregationsFor(DynamicFieldCatalogue catalogue, String valuePath) {
    Set<Aggregation> allowed;
    if (catalogue == null) {
      allowed = Set.of(Aggregation.values());
    } else if (StringUtils.isBlank(valuePath)) {
      allowed = catalogue.getAvailableMeasures();
    } else {
      allowed = catalogue.measuresFor(valuePath);
    }
    return Arrays.stream(Aggregation.values()).filter(allowed::contains)
        .map(value -> new SelectItem(value, value.getLabel()))
        .toList();
  }

  public static List<SelectItem> dateBuckets() {
    return Arrays.stream(DateBucket.values())
        .map(value -> new SelectItem(value, value.getLabel()))
        .toList();
  }

  public static List<SelectItem> sortOrders() {
    return Arrays.stream(SortOrder.values())
        .map(value -> new SelectItem(value, value.getLabel()))
        .toList();
  }

  public static List<SelectItem> filterOperators() {
    return Arrays.stream(FilterOperator.values())
        .map(value -> new SelectItem(value, value.getLabel()))
        .toList();
  }

  /** The path and type under the title, so the right field is picked without guessing. */
  public static String describe(DynamicField field) {
    if (field == null) {
      return StringUtils.EMPTY;
    }
    // The type's own label, not a second list of type labels that has to be kept in step.
    String type = field.getType() == null ? StringUtils.EMPTY : field.getType().getLabel();
    return field.getFieldName() + (type.isEmpty() ? StringUtils.EMPTY : " · " + type);
  }

  // ================================================================== the form
  //
  // Finding the provider behind a chart, keeping its field catalogue current, and turning what
  // the form holds into a stored chart.
  //
  // Every method that can fail says so by returning the reason rather than by reporting it, so
  // the form decides how a failure is shown and the agent path can reuse the same checks.
  // ==========================================================================

  public static Optional<SubProcessCallStartEvent> findProvider(String name) {
    return new ProviderCollector().findByName(name);
  }

  /**
   * Builds the catalogue for a chart stored before its provider was ever profiled, which would
   * otherwise have no fields to offer.
   *
   * @return true once the form has fields to offer.
   */
  public static boolean ensureCatalogue(ProviderChartForm form, List<String> warnings) {
    if (!form.isCatalogueEmpty()) {
      return true;
    }
    findProvider(form.getProviderName())
        .map(provider -> FieldCatalogueService.catalogueFor(provider, warnings))
        .ifPresent(form::setCatalogue);
    return !form.isCatalogueEmpty();
  }

  /**
   * Converts the chart onto a data provider and rebuilds the form against that provider's schema.
   *
   * @return the reason the provider could not be used, or empty once the chart is on it.
   */
  public static Optional<String> switchToProvider(Statistic chart, String dataSourceKey,
      List<String> warnings) {
    String providerName = providerNameOf(dataSourceKey);
    Optional<SubProcessCallStartEvent> provider = findProvider(providerName);
    DynamicFieldCatalogue catalogue = provider
        .map(start -> FieldCatalogueService.catalogueFor(start, warnings)).orElse(null);
    if (catalogue == null) {
      return Optional.of(noData(providerName));
    }
    toProvider(chart, provider.get(), catalogue);
    return Optional.empty();
  }

  /**
   * Reads the provider as it is now and flags the form when its fields differ from the ones this
   * chart was built on. Reads live rather than trusting the cache, which is keyed by signature and
   * so would still describe the data by the fields it used to have.
   */
  public static void verifyFields(ProviderChartForm form) {
    form.setFieldsChanged(false);
    if (form.isCatalogueEmpty()) {
      return;
    }
    SubProcessCallStartEvent provider = findProvider(form.getProviderName()).orElse(null);
    if (provider == null) {
      return;
    }
    JsonNode data = ProviderRunner.fetchData(provider).orElse(null);
    if (data == null) {
      return;
    }
    DynamicFieldCatalogue live = FieldCatalogueService.toCatalogue(data);
    if (!live.isEmpty()) {
      form.setFieldsChanged(!live.getFieldNames().equals(form.getCatalogue().getFieldNames()));
    }
  }

  public static String fieldsChanged(ProviderChartForm form) {
    return text("The fields of the \"{0}\" data source have changed. Regenerate the fields and check this chart.",
        form.getProviderName());
  }

  /**
   * Reads the provider again and derives its fields from scratch, replacing the catalogue.
   *
   * This is the way out of a chart whose fields have changed: asking for it is the user accepting
   * that the chart now describes different data, so the substitution {@code normalise} then makes
   * is theirs rather than one made behind their back.
   */
  public static Optional<String> regenerate(ProviderChartForm form, ChartType chartType,
      List<String> warnings) {
    Optional<SubProcessCallStartEvent> provider = findProvider(form.getProviderName());
    if (provider.isEmpty()) {
      return Optional.of(providerUnknown(form.getProviderName()));
    }
    DynamicFieldCatalogue rebuilt = FieldCatalogueService.catalogueFor(provider.get(), warnings);
    if (rebuilt == null) {
      return Optional.of(noData(form.getProviderName()));
    }
    form.setCatalogue(rebuilt);
    form.setFieldsChanged(false);
    normalise(form, chartType);
    return Optional.empty();
  }

  /**
   * Runs the provider and shows what it returned. On demand rather than on render: this runs the
   * callable, which is far too expensive to repeat every time the form is drawn.
   *
   * @return the reason nothing could be loaded, or empty once the result is on the form.
   */
  public static Optional<String> loadResult(ProviderChartForm form) {
    Optional<SubProcessCallStartEvent> provider = findProvider(form.getProviderName());
    if (provider.isEmpty()) {
      return Optional.of(providerUnknown(form.getProviderName()));
    }
    readResultInto(form, provider.get());
    return Optional.empty();
  }

  /**
   * Validates what the form holds through the same mapper the agent path uses, so a hand-built
   * chart and an agent-built one are checked by the same rules.
   */
  public static SmartStatisticMappingResult map(Statistic chart, ProviderChartForm form,
      SubProcessCallStartEvent provider, String xTitle, String yTitle) {
    return ProviderChartMapper.toStatistic(toProposal(form, chart, xTitle, yTitle),
        provider, form.getCatalogue(), chart);
  }

  public static String providerUnknown(String providerName) {
    return text("There is no data source called \"{0}\" in this installation.", providerName);
  }

  public static String noData(String providerName) {
    return text("The \"{0}\" data source returned no records, so there is nothing to chart yet.", providerName);
  }

  /** The measures a field can be aggregated by, as one readable line. */
  public static String describeMeasures(DynamicField field) {
    if (field == null) {
      return StringUtils.EMPTY;
    }
    return field.getMeasures().stream()
        .map(Aggregation::getLabel)
        .collect(Collectors.joining(", "));
  }

  private static String text(String template, Object... params) {
    return MessageFormat.format(template, Arrays.stream(params).map(String::valueOf).toArray());
  }
}
