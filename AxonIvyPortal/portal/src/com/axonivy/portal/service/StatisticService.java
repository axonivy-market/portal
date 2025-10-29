package com.axonivy.portal.service;

import static com.axonivy.portal.bean.StatisticConfigurationBean.DEFAULT_COLORS;

import java.time.Instant;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import com.axonivy.portal.bo.PieChartConfig;
import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.StatisticDto;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.AdditionalChartConfig;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.migration.statistic.migrator.JsonStatisticMigrator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.field.CaseFilterFieldFactory;
import com.axonivy.portal.util.statisticfilter.field.TaskFilterFieldFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.statistics.StatisticResponse;
import ch.ivy.addon.portalkit.util.DefaultDashboardUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class StatisticService {

  private static final String DEFAULT_STATISTIC_KEY = PortalVariable.STATISTIC.key;
  private static final String CUSTOM_STATISTIC_KEY = PortalVariable.CUSTOM_STATISTIC.key;
  private static final String CLIENT_STATISTIC_KEY = "Portal.ClientStatistic";
  private static final List<String> ALL_AGGREGATION_DATE_FIELDS =
      Arrays.asList("startTimestamp", "endTimestamp", "expiryTimestamp");
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
  public StatisticResponse getStatisticData(StatisticDto payload)
      throws NotFoundException {
    Statistic chart = findByStatisticId(payload.getChartId());
    validateChart(payload.getChartId(), chart);
    AggregationResult result = getChartData(chart);
    chart.setAdditionalConfigs(new ArrayList<>());
    chart.getAdditionalConfigs().addAll(getAdditionalConfig());
    chart.getAdditionalConfigs().add(getManipulateValueBy(chart));
    return new StatisticResponse(result, chart);
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

    Ivy.log().warn("chart name: {0} ||| aggregates: {1} ||| filter {2}", chart.getName(), aggregates, filter);
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
  
  public List<Entry<String, String>> getAdditionalConfig() {
    
    List<Entry<String, String>> entries = new ArrayList<>();
    entries.add(new SimpleEntry<>(AdditionalChartConfig.EMPTY_CHART_DATA_MESSAGE.getKey(),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/StatisticWidget/EmptyChartDataMessage")));
    entries.add(new SimpleEntry<>(AdditionalChartConfig.FAIL_TO_RENDER_CHART_MESSAGE.getKey(),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/StatisticWidget/failToRenderChartMessage")));
    return entries;
  }

  private boolean hasPermission(Statistic data) {
    return Optional.ofNullable(data.getPermissions())
                   .orElse(List.of())
                   .stream()
                   .anyMatch(permission -> Ivy.session().getSessionUser().has().role(permission));
  }

  public Entry<String, String> getManipulateValueBy(Statistic data) {
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
    String statisticsJson = BusinessEntityConverter.entityToJsonValue(statistics);
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

  public void getStatisticDrillDownData(com.axonivy.portal.dto.StatisticDrillDownDto drillDownData) {
    Statistic chart = findByStatisticId(drillDownData.getChartId());
    validateChart(drillDownData.getChartId(), chart);

    if ("case".equals(drillDownData.getChartTarget())) { // TODO z1 consider chart target as enum
      searchCases(drillDownData);
    } else if ("task".equals(drillDownData.getChartTarget())) {
      searchTasks(drillDownData);
    }
  }

  private void searchCases(com.axonivy.portal.dto.StatisticDrillDownDto drillDownData) {
    Statistic chart = findByStatisticId(drillDownData.getChartId());

    Dashboard drillDownDashboard = DefaultDashboardUtils.getCaseDrillDownDashboard();
    CaseDashboardWidget widget = (CaseDashboardWidget) drillDownDashboard.getWidgets().get(0);
    
    ensureAllStatisticFieldsInCaseColumns(widget, chart);
    
    List<DashboardFilter> filters = widget.getFilters();
    // Add filter based on the drill-down field and value
    if (StringUtils.isNotBlank(drillDownData.getFilterKey()) && drillDownData.getFilterValue() != null) {
      DashboardFilter drillDownFilter = new DashboardFilter();
      drillDownFilter.setFilterType(DashboardColumnType.STANDARD);
      drillDownFilter.setField(getDashboardCaseFilterFieldByAggregation(chart.getStatisticAggregation().getField()));

      if (ALL_AGGREGATION_DATE_FIELDS.contains(chart.getStatisticAggregation().getField())
          || (DashboardColumnType.CUSTOM == chart.getStatisticAggregation().getType()
              && chart.getStatisticAggregation().getInterval() != null)) {
        drillDownFilter.setOperator(FilterOperator.IS);
        drillDownFilter.setFromDate(Date.from(Instant.parse(drillDownData.getFilterValue())));
      } else {
        drillDownFilter.setOperator(FilterOperator.IN); // TODO z1 maybe not IN
        drillDownFilter.setValues(Arrays.asList(String.valueOf(drillDownData.getFilterValue())));
      }
      filters.add(drillDownFilter);
    }
    // Add chart's existing filters to the dashboard widget
    if (CollectionUtils.isNotEmpty(chart.getFilters())) {
      filters.addAll(chart.getFilters());
    }

    Ivy.session().setAttribute(SessionAttribute.DRILL_DOWN_DASHBOARD.name(), drillDownDashboard);
    Ivy.log().warn("=====statistic {0}", BusinessEntityConverter.entityToJsonValue(chart));
    Ivy.log().warn("=====dashboard {0}", BusinessEntityConverter.entityToJsonValue(drillDownDashboard));
  }


  private String getDashboardCaseFilterFieldByAggregation(String field) {
    return switch (field) {
      case StatisticConstants.CREATOR_NAME -> DashboardStandardCaseColumn.CREATOR.getField();
      default -> field;
    };
  }

  private void searchTasks(com.axonivy.portal.dto.StatisticDrillDownDto drillDownData) {
    Statistic chart = findByStatisticId(drillDownData.getChartId());

    Dashboard drillDownDashboard = DefaultDashboardUtils.getTaskDrillDownDashboard();
    TaskDashboardWidget widget = (TaskDashboardWidget) drillDownDashboard.getWidgets().get(0);
    
    ensureAllStatisticFieldsInTaskColumns(widget, chart);
    
    List<DashboardFilter> filters = widget.getFilters();
    // Add filter based on aggregation
    if (StringUtils.isNotBlank(drillDownData.getFilterKey()) && drillDownData.getFilterValue() != null) {
      DashboardFilter drillDownFilter = new DashboardFilter();
      drillDownFilter.setFilterType(chart.getStatisticAggregation().getType());
      drillDownFilter.setField(getDashboardTaskFilterFieldByAggregation(chart.getStatisticAggregation().getField()));
      
      if (ALL_AGGREGATION_DATE_FIELDS.contains(chart.getStatisticAggregation().getField())
          || (DashboardColumnType.CUSTOM == chart.getStatisticAggregation().getType()
              && chart.getStatisticAggregation().getInterval() != null)) {
        drillDownFilter.setOperator(FilterOperator.IS);
        drillDownFilter.setFromDate(Date.from(Instant.parse(drillDownData.getFilterValue())));
      } else {
        drillDownFilter.setOperator(FilterOperator.IN); // TODO z1 maybe not IN
        drillDownFilter.setValues(Arrays.asList(String.valueOf(drillDownData.getFilterValue())));
      }
      filters.add(drillDownFilter);
    }

    // TODO z1 Add chart's existing filters to the dashboard widget
    if (CollectionUtils.isNotEmpty(chart.getFilters())) {
      for (DashboardFilter filter : chart.getFilters()) {
        if (StatisticConstants.CAN_WORK_ON.equals(filter.getField())) {
          widget.setCanWorkOn(true);
        } else {
          filters.add(filter);
        }
      }
    }

    Ivy.session().setAttribute(SessionAttribute.DRILL_DOWN_DASHBOARD.name(), drillDownDashboard);
    Ivy.log().warn("=====statistic {0}", BusinessEntityConverter.entityToJsonValue(chart));
    Ivy.log().warn("=====dashboard {0}", BusinessEntityConverter.entityToJsonValue(drillDownDashboard));
  }

  private String getDashboardTaskFilterFieldByAggregation(String field) {
    return switch (field) {
      case StatisticConstants.RESPONSIBLE_NAME -> DashboardStandardTaskColumn.RESPONSIBLE.getField();
      default -> field;
    };
  }

  private void ensureAllStatisticFieldsInTaskColumns(TaskDashboardWidget widget, Statistic chart) {
    List<TaskColumnModel> columns = widget.getColumns();
    boolean columnsModified = false;
    
    if (chart.getStatisticAggregation() != null && StringUtils.isNotBlank(chart.getStatisticAggregation().getField())) {
      String field = chart.getStatisticAggregation().getField();
      DashboardColumnType fieldType = chart.getStatisticAggregation().getType();
      if (ensureSingleFieldInTaskColumns(columns, field, fieldType)) {
        columnsModified = true;
      }
    }
    
    if (chart.getStatisticAggregation() != null && StringUtils.isNotBlank(chart.getStatisticAggregation().getKpiField())) {
      String kpiField = chart.getStatisticAggregation().getKpiField();
      DashboardColumnType fieldType = DashboardColumnType.CUSTOM; // KPI fields are typically custom numeric fields
      if (ensureSingleFieldInTaskColumns(columns, kpiField, fieldType)) {
        columnsModified = true;
      }
    }
    
    if (CollectionUtils.isNotEmpty(chart.getFilters())) {
      for (DashboardFilter filter : chart.getFilters()) {
        if (StringUtils.isNotBlank(filter.getField())) {
          String field = filter.getField();
          DashboardColumnType fieldType = filter.getFilterType();
          if (ensureSingleFieldInTaskColumns(columns, field, fieldType)) {
            columnsModified = true;
          }
        }
      }
    }
    
    if (columnsModified) {
      widget.setColumns(columns);
    }
  }
  
  private boolean ensureSingleFieldInTaskColumns(List<TaskColumnModel> columns, String field, DashboardColumnType fieldType) {
    if (StringUtils.isBlank(field)) {
      return false;
    }

    boolean fieldExists = columns.stream()
        .anyMatch(column -> field.equals(column.getField()));
    
    if (!fieldExists) {
      TaskColumnModel newColumn = TaskColumnModel.constructColumn(fieldType, field);
      newColumn.setField(field);
      newColumn.setType(fieldType);
      newColumn.setWidth("120"); // Default width
      
      int insertPosition = Math.max(0, columns.size() - 1);
      columns.add(insertPosition, newColumn);
      return true;
    }
    
    return false;
  }
  
  private void ensureAllStatisticFieldsInCaseColumns(CaseDashboardWidget widget, Statistic chart) {
    List<CaseColumnModel> columns = widget.getColumns();
    boolean columnsModified = false;
    
    // TODO z1 Ensure aggregation field is included
    if (chart.getStatisticAggregation() != null && StringUtils.isNotBlank(chart.getStatisticAggregation().getField())) {
      String field = getDashboardCaseFilterFieldByAggregation(chart.getStatisticAggregation().getField());
      DashboardColumnType fieldType = chart.getStatisticAggregation().getType();
      if (ensureSingleFieldInCaseColumns(columns, field, fieldType)) {
        columnsModified = true;
      }
    }
    
    // TODO z1 Ensure KPI field is included
    if (chart.getStatisticAggregation() != null && StringUtils.isNotBlank(chart.getStatisticAggregation().getKpiField())) {
      String kpiField = chart.getStatisticAggregation().getKpiField();
      DashboardColumnType fieldType = DashboardColumnType.CUSTOM; // KPI fields are typically custom numeric fields
      if (ensureSingleFieldInCaseColumns(columns, kpiField, fieldType)) {
        columnsModified = true;
      }
    }
    
    // TODO z1 Ensure filter fields are included
    if (CollectionUtils.isNotEmpty(chart.getFilters())) {
      for (DashboardFilter filter : chart.getFilters()) {
        if (StringUtils.isNotBlank(filter.getField())) {
          String field = filter.getField();
          DashboardColumnType fieldType = filter.getFilterType();
          if (ensureSingleFieldInCaseColumns(columns, field, fieldType)) {
            columnsModified = true;
          }
        }
      }
    }

    if (columnsModified) {
      widget.setColumns(columns);
    }
  }
  
  private boolean ensureSingleFieldInCaseColumns(List<CaseColumnModel> columns, String field, DashboardColumnType fieldType) {
    if (StringUtils.isBlank(field)) {
      return false;
    }

    boolean fieldExists = columns.stream()
        .anyMatch(column -> field.equals(column.getField()));
    
    if (!fieldExists) {
      CaseColumnModel newColumn = CaseColumnModel.constructColumn(fieldType, field);
      newColumn.setField(field);
      newColumn.setType(fieldType);
      newColumn.setWidth("120"); // TODO z1 width
      int insertPosition = Math.max(0, columns.size() - 1);
      columns.add(insertPosition, newColumn);
      return true;
    }
    
    return false;
  }
  
}
