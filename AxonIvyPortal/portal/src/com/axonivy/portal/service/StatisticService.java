package com.axonivy.portal.service;

import static com.axonivy.portal.bean.StatisticConfigurationBean.DEFAULT_COLORS;
import static com.axonivy.portal.enums.statistic.ChartTarget.CASE;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import com.axonivy.portal.bo.PieChartConfig;
import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.StatisticDrillDownDTO;
import com.axonivy.portal.dto.StatisticDTO;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.AdditionalChartConfig;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.migration.statistic.migrator.JsonStatisticMigrator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.field.CaseFilterFieldFactory;
import com.axonivy.portal.util.statisticfilter.field.TaskFilterFieldFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import com.axonivy.portal.dto.statistic.AggregationDTO;
import com.axonivy.portal.dto.statistic.AggregationResultDTO;
import com.axonivy.portal.dto.statistic.BucketDTO;
import com.axonivy.portal.util.AggregationResultMapper;
import ch.ivy.addon.portalkit.statistics.StatisticResponse;
import ch.ivy.addon.portalkit.util.PortalCustomFieldUtils;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class StatisticService {

  private static final String DEFAULT_STATISTIC_KEY = PortalVariable.STATISTIC.key;
  private static final String CUSTOM_STATISTIC_KEY = PortalVariable.CUSTOM_STATISTIC.key;
  private static final String CLIENT_STATISTIC_KEY = "Portal.ClientStatistic";
  private static StatisticService instance;

  public static StatisticService getInstance() {
    if (instance == null) {
      instance = new StatisticService();
    }
    return StatisticService.instance;
  }

  public List<Statistic> findAllCharts() {
    Set<String> seenIds = new HashSet<>();
    List<Statistic> statistics = getDefaultStatistic();
    statistics = statistics.stream().filter(statistic -> seenIds.add(statistic.getId())).collect(Collectors.toList());
    getCustomStatistic().stream().filter(obj -> seenIds.add(obj.getId())).forEach(statistics::add);
    return statistics;
  }

  /**
   * get client chart by payload then call Ivy API to get statistic data
   * 
   * @param payload
   * @return Ivy statistic data from ElasticSearch
   * @throws NotFoundException
   */
  public StatisticResponse getStatisticData(StatisticDTO payload)
      throws NotFoundException {
    Statistic chart = findByStatisticId(payload.getChartId());
    validateChart(payload.getChartId(), chart);
    AggregationResultDTO chartResult = AggregationResultMapper.toAggResultDTO(this.getChartData(chart));
    this.localizeChartKeys(chartResult, chart.getChartTarget(), chart.getStatisticAggregation());
    chart.setAdditionalConfigs(this.getAdditionalConfig(chart));
    return new StatisticResponse(chartResult, chart);
  }

  private void validateChart(String chartId, Statistic chart) {
    if (chart == null) {
      throw new PortalException(Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/StatisticWidget/IdNotFound",
          Arrays.asList(chartId)));
    }

    if (!hasPermission(chart)) {
      throw new PortalException(
          Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/StatisticWidget/NoPermissionChartMessage"));
    }
  }
  
  private String processFilter(List<DashboardFilter> filters, ChartTarget chartTarget) {
    if (CollectionUtils.isEmpty(filters)) {
      return null;
    }

    StringBuilder sbFilter = new StringBuilder();
    for (DashboardFilter statisticFilter : filters) {
      if (Optional.ofNullable(statisticFilter).map(DashboardFilter::getOperator).isEmpty()) {
        continue;
      }
      FilterField filterField = ChartTarget.TASK == chartTarget
          ? TaskFilterFieldFactory.findBy(statisticFilter.getField(), statisticFilter.getFilterType())
          : CaseFilterFieldFactory.findBy(statisticFilter.getField(), statisticFilter.getFilterType());

      if (filterField != null) {
        filterField.initFilter(statisticFilter);
        String filterQuery = ChartTarget.TASK == chartTarget 
            ? filterField.generateTaskFilter(statisticFilter)
            : filterField.generateCaseFilter(statisticFilter);

        if (filterQuery != null) {
          sbFilter.append(filterQuery).append(",");
        }
      }
    }

    if (Strings.EMPTY.equals(sbFilter.toString())) {
      return null;
    }
    return sbFilter.toString();
  }

  public AggregationResult getChartData(Statistic chart) {
    String filter = null;
    String aggregates = chart.getAggregates();
    filter = processFilter(chart.getFilters(), chart.getChartTarget());

    if(StringUtils.isEmpty(aggregates)) {
      aggregates = convertAggregatesFromChartAggregation(chart);
    }

    if (!StringUtils.isEmpty(chart.getFilter())) {
      filter = chart.getFilter();
    }

    switch (chart.getChartTarget()) {
      case CASE:
        filter = StringUtils.isBlank(filter) ? "isBusinessCase:true" : "isBusinessCase:true," + filter;
        return WorkflowStats.current().caze().aggregate(aggregates, filter);
      case TASK:
        return WorkflowStats.current().task().aggregate(aggregates, filter);
      default:
        throw new PortalException("Cannot parse chartTarget " + chart.getChartTarget());
    }
  }
  
  public List<Entry<String, String>> getAdditionalConfig(Statistic chart) {
    List<Entry<String, String>> entries = new ArrayList<>();
    entries.add(new SimpleEntry<>(AdditionalChartConfig.EMPTY_CHART_DATA_MESSAGE.getKey(),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/StatisticWidget/EmptyChartDataMessage")));
    entries.add(new SimpleEntry<>(AdditionalChartConfig.FAIL_TO_RENDER_CHART_MESSAGE.getKey(),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/StatisticWidget/failToRenderChartMessage")));
    Optional.ofNullable(getManipulateValueBy(chart)).ifPresent(entries::add);
    entries.add(new SimpleEntry<>(AdditionalChartConfig.TOOLTIP_TOTAL_LABEL.getKey(),
        this.getTooltipTotalLabel(chart.getChartTarget())));
    entries.add(new SimpleEntry<>(AdditionalChartConfig.TOOLTIP_KPI_LABEL.getKey(),
        this.getTooltipKpiLabel(chart.getChartTarget(), chart.getStatisticAggregation())));
    return entries;
  }

  private boolean hasPermission(Statistic data) {
    return Optional.ofNullable(data.getPermissions())
                   .orElse(List.of())
                   .stream()
                   .anyMatch(permission -> Ivy.session().getSessionUser().has().role(permission));
  }

  private Entry<String, String> getManipulateValueBy(Statistic data) {
    return Optional.ofNullable(data.getManipulateValueBy())
                   .map(value -> new SimpleEntry<>(AdditionalChartConfig.MANIPULATE_BY.getKey(), value))
                   .orElse(null);
  }
  
  private Statistic findByStatisticId(String id) {
    return findAllCharts().stream()
        .filter(e -> e.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public Statistic findByIdCustomStatistic(String id) {
    return getCustomStatistic().stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
  }

  public void saveJsonToVariable(List<Statistic> statistics) {
    String statisticsJson = BusinessEntityConverter.entityToJsonValueExcludeInternalView(statistics);
    Ivy.var().set(CUSTOM_STATISTIC_KEY, statisticsJson);
  }

  private List<Statistic> getDefaultStatistic() {
    String value = Ivy.var().get(DEFAULT_STATISTIC_KEY);
    List<Statistic> statistics = BusinessEntityConverter.jsonValueToEntities(value, Statistic.class);
    statistics.forEach(cs -> cs.setIsCustom(false));
    configDefaultStatisticSettings(statistics);
    return statistics;
  }

  public List<Statistic> getCustomStatistic() {
    migrateClientStatistic();
    List<Statistic> statistics =
        BusinessEntityConverter.jsonValueToEntities(Ivy.var().get(CUSTOM_STATISTIC_KEY), Statistic.class);
    configDefaultStatisticSettings(statistics);
    return statistics;
  }

  public void migrateClientStatistic() {
    try {
      String clientStatisticJson = Ivy.var().get(CLIENT_STATISTIC_KEY);
      if (StringUtils.isNotBlank(clientStatisticJson)) {
        String customStatisticJson = Ivy.var().get(CUSTOM_STATISTIC_KEY);
        if (StringUtils.isBlank(customStatisticJson) || "[]".equals(customStatisticJson)) {
          List<Statistic> customStatistics = BusinessEntityConverter.jsonValueToEntities(Ivy.var().get(CUSTOM_STATISTIC_KEY), Statistic.class);

          ObjectMapper mapper = new ObjectMapper();
          JsonStatisticMigrator migrator = new JsonStatisticMigrator(mapper.readTree(clientStatisticJson));
          List<Statistic> clientStatistics = BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), Statistic.class);

          Set<String> seenIds = new HashSet<>();
          customStatistics = customStatistics.stream().filter(statistic -> seenIds.add(statistic.getId())).collect(Collectors.toList());
          clientStatistics.stream().filter(obj -> seenIds.add(obj.getId())).forEach(customStatistics::add);

          String statisticsJson = BusinessEntityConverter.entityToJsonValue(customStatistics);
          Ivy.var().set(CUSTOM_STATISTIC_KEY, statisticsJson);
        }
        Ivy.var().reset(CLIENT_STATISTIC_KEY);
      }
    } catch (Exception e) {
      Ivy.log().warn("Migrate client statistic failed due to ", e);
    }
  }

  private void configDefaultStatisticSettings(List<Statistic> statistics) {
    for (Statistic statistic : statistics) {
      if (ChartType.PIE == statistic.getChartType()) {
        if (statistic.getPieChartConfig() == null) { // could be null due to migration from version 12
          PieChartConfig pieChartConfig = new PieChartConfig();
          pieChartConfig.setBackgroundColors(DEFAULT_COLORS);
          statistic.setPieChartConfig(pieChartConfig);
        } else if (CollectionUtils.isEmpty(statistic.getPieChartConfig().getBackgroundColors())) {
          statistic.getPieChartConfig().setBackgroundColors(DEFAULT_COLORS);
        }
      }
      if (ChartType.BAR == statistic.getChartType()
          && CollectionUtils.isEmpty(statistic.getBarChartConfig().getBackgroundColors())) {
        statistic.getBarChartConfig().setBackgroundColors(DEFAULT_COLORS);
      }
      if (ChartType.LINE == statistic.getChartType()
          && CollectionUtils.isEmpty(statistic.getLineChartConfig().getBackgroundColors())) {
        statistic.getLineChartConfig().setBackgroundColors(DEFAULT_COLORS);
      }
    }
  }

  public String convertAggregatesFromChartAggregation(Statistic chart) {
    StatisticAggregation chartAggregation = chart.getStatisticAggregation();
    String metricAggregationQuery = this.getMetricAggregationQuery(chartAggregation);

    if (chartAggregation.getType() == DashboardColumnType.CUSTOM) {
      // INIT EXISTED CUSTOM FIELD STATISTIC WHEN LOADING
      if (!chartAggregation.getField().equals(AggregationField.CUSTOM_FIELD.getName())) {
        chartAggregation.setCustomFieldValue(chartAggregation.getField());
        chartAggregation.setField(AggregationField.CUSTOM_FIELD.getName());
      }

      // CUSTOM FIELD TYPE TIMESTAMP
      if (chart.getStatisticAggregation().getInterval() != null) {
        return "customFields.timestamps." + chartAggregation.getCustomFieldValue() + ":bucket:"
            + chartAggregation.getInterval().toString().toLowerCase() + metricAggregationQuery;
      }

      // CUSTOM FIELD TYPE STRING
      return "customFields.strings." + chartAggregation.getCustomFieldValue() + metricAggregationQuery;
    }

    // STANDARD FIELD
    // IF INTERVAL NOT NULL -> TIMESTAMP
    // OTHERWISE -> STRING
    String bucketQuery = chartAggregation.getInterval() == null ? transformField(chartAggregation.getField())
        : transformField(chartAggregation.getField()) + ":bucket:"
            + chartAggregation.getInterval().toString().toLowerCase();
    
    return bucketQuery + metricAggregationQuery;
  }

  private String getMetricAggregationQuery(StatisticAggregation chartAggregation) {
    return StringUtils.isNotBlank(chartAggregation.getKpiField())
        ? "," + (isBuiltInKPIField(chartAggregation.getKpiField()) ? "" : "customFields.numbers.") + chartAggregation.getKpiField() + ":" + chartAggregation.getAggregationMethod()
        : "";
  }

  // These fields are not numeric custom fields, so do not need to append customFields.numbers. in above function when building query
  private boolean isBuiltInKPIField(String field) {
    final Set<String> BUILT_IN_KPI_FIELDS = Set.of("businessRuntime", "workingTime");
    return BUILT_IN_KPI_FIELDS.contains(field);
  }

  private String transformField(String field) {
    return switch (field) {
    case StatisticConstants.STATE -> StatisticConstants.BUSSINESS_STATE;
    default -> field;
    };
  }

  public void createDrillDownDashboard(String drillDownDataJson) {
    if (StringUtils.isBlank(drillDownDataJson)) {
      return;
    }
    StatisticDrillDownDTO drillDownData =
        BusinessEntityConverter.jsonValueToEntity(drillDownDataJson, StatisticDrillDownDTO.class);
    Statistic statistic = findByStatisticId(drillDownData.getChartId());
    validateChart(drillDownData.getChartId(), statistic);
    AbstractDrillDownService drillDownService;
    if (CASE == statistic.getChartTarget()) {
      drillDownService = CaseDrillDownService.getInstance();
    } else {
      drillDownService = TaskDrillDownService.getInstance();
    }
    drillDownService.createDrillDownDashboardInSession(statistic, drillDownData.getDrillDownValue());
  }

  public void localizeChartKeys(AggregationResultDTO chartResult, ChartTarget chartTarget, StatisticAggregation aggregation) {
    if (chartResult == null || CollectionUtils.isEmpty(chartResult.getAggs())) {
      return;
    }

    UnaryOperator<String> keyLocalizer = buildKeyLocalizer(chartTarget, aggregation);
    for (AggregationDTO agg : chartResult.getAggs()) {
      this.localizeBuckets(agg, keyLocalizer);
    }
  }

  private void localizeBuckets(AggregationDTO agg, UnaryOperator<String> keyLocalizer) {
    if (CollectionUtils.isEmpty(agg.getBuckets())) {
      return;
    }
    for (BucketDTO bucket : agg.getBuckets()) {
      if (bucket.getKey() instanceof String strKey) {
        bucket.setKey(keyLocalizer.apply(strKey));
      }
      if (CollectionUtils.isNotEmpty(bucket.getAggs())) {
        for (AggregationDTO nestedAgg : bucket.getAggs()) {
          this.localizeBuckets(nestedAgg, keyLocalizer);
        }
      }
    }
  }

  private UnaryOperator<String> buildKeyLocalizer(ChartTarget chartTarget, StatisticAggregation aggregation) {
    String field = aggregation.getField();

    if (StatisticConstants.CATEGORY.equals(field)) {
      return this::resolveCategoryName;
    }

    if (StatisticConstants.STATE.equals(field)) {
      Map<String, String> map = this.buildStateLocalizedMap(chartTarget);
      return key -> map.getOrDefault(key, key);
    }

    if (StatisticConstants.PRIORITY.equals(field)) {
      Map<String, String> map = this.buildPriorityLocalizedMap();
      return key -> map.getOrDefault(key, key);
    }

    if (aggregation.getType() == DashboardColumnType.CUSTOM) {
      Map<String, String> map = this.buildCustomFieldLocalizedMap(chartTarget, aggregation.getCustomFieldValue());
      return key -> map.getOrDefault(key, key);
    }

    return key -> key;
  }

  private Map<String, String> buildStateLocalizedMap(ChartTarget chartTarget) {
    Map<String, String> map = new HashMap<>();
    if (CASE == chartTarget) {
      for (CaseBusinessState state : CaseBusinessState.values()) {
        map.put(state.name(), Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/businessCaseState/" + state));
      }
    } else {
      for (TaskBusinessState state : TaskBusinessState.values()) {
        map.put(state.name(), Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskBusinessState/" + state));
      }
    }
    return map;
  }

  private Map<String, String> buildPriorityLocalizedMap() {
    Map<String, String> map = new HashMap<>();
    for (WorkflowPriority priority : WorkflowPriority.values()) {
      map.put(priority.name(), Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + priority));
    }
    return map;
  }

  private Map<String, String> buildCustomFieldLocalizedMap(ChartTarget chartTarget, String customFieldValue) {
    return CASE == chartTarget ? PortalCustomFieldUtils.getLocalizedValueLabelMapOnCaseField(customFieldValue)
        : PortalCustomFieldUtils.getLocalizedValueLabelMapOnTaskField(customFieldValue);
  }

  private String resolveCategoryName(String categoryPath) {
    String localizedCategory = IApplicationRepository.of(ISecurityContext.current()).all()
      .stream().flatMap(app -> app.getProcessModelVersions())
      .map(pmv -> ContentManagement.of(pmv).co("/Categories/" + categoryPath + "/name"))
      .filter(StringUtils::isNotBlank)
      .findFirst()
      .orElse(StringUtils.EMPTY);

    return StringUtils.isBlank(localizedCategory) ? categoryPath : localizedCategory;
  }

  private String getTooltipTotalLabel(ChartTarget chartTarget) {
    String cmsKey = CASE == chartTarget ? "TooltipTotalCaseLabel" : "TooltipTotalTaskLabel";
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/StatisticWidget/" + cmsKey);
  }

  private String getTooltipKpiLabel(ChartTarget chartTarget, StatisticAggregation aggregation) {
    String kpiField = aggregation.getKpiField();

    if (StringUtils.isBlank(kpiField)) {
      return StringUtils.EMPTY;
    }

    String localizedKpiLabel = CASE == chartTarget ? PortalCustomFieldUtils.getLocalizedLabelOnCaseField(kpiField)
      : PortalCustomFieldUtils.getLocalizedLabelOnTaskField(kpiField);

    String cmsKey = switch (aggregation.getAggregationMethod()) {
      case "sum" -> "TooltipSumKpiLabel";
      case "avg" -> "TooltipAverageKpiLabel";
      case "max" -> "TooltipMaxKpiLabel";
      case "min" -> "TooltipMinKpiLabel";
      default -> null;
    };

    if (cmsKey == null) {
      return StringUtils.EMPTY;
    }

    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/StatisticWidget/" + cmsKey, Arrays.asList(localizedKpiLabel));
  }
}
