package com.axonivy.portal.service;

import static com.axonivy.portal.bean.StatisticConfigurationBean.DEFAULT_COLORS;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.axonivy.portal.dto.StatisticDto;
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
import ch.ivy.addon.portalkit.statistics.StatisticResponse;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

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

    return switch (chart.getChartTarget()) {
      case CASE -> WorkflowStats.current().caze().aggregate(aggregates, filter);
      case TASK ->  WorkflowStats.current().task().aggregate(aggregates, filter);
      default -> throw new PortalException("Cannot parse chartTarget " + chart.getChartTarget());
    };
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
      String json = Ivy.var().get(CLIENT_STATISTIC_KEY);
      if (StringUtils.isNotBlank(json)) {
        List<Statistic> customStatistics = BusinessEntityConverter.jsonValueToEntities(Ivy.var().get(CUSTOM_STATISTIC_KEY), Statistic.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonStatisticMigrator migrator = new JsonStatisticMigrator(mapper.readTree(json));
        List<Statistic> clientStatistics = BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), Statistic.class);

        Set<String> seenIds = new HashSet<>();
        customStatistics = customStatistics.stream().filter(statistic -> seenIds.add(statistic.getId())).collect(Collectors.toList());
        clientStatistics.stream().filter(obj -> seenIds.add(obj.getId())).forEach(customStatistics::add);

        String statisticsJson = BusinessEntityConverter.entityToJsonValue(customStatistics);
        Ivy.var().set(CUSTOM_STATISTIC_KEY, statisticsJson);
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

    if (chartAggregation.getType() == DashboardColumnType.CUSTOM) {
      // INIT EXISTED CUSTOM FIELD STATISTIC WHEN LOADING
      if (!chartAggregation.getField().equals(AggregationField.CUSTOM_FIELD.getName())) {
        chartAggregation.setCustomFieldValue(chartAggregation.getField());
        chartAggregation.setField(AggregationField.CUSTOM_FIELD.getName());
      }

      // CUSTOM FIELD TYPE TIMESTAMP
      if (chart.getStatisticAggregation().getInterval() != null) {
        return "customFields.timestamps." + chartAggregation.getCustomFieldValue() + ":bucket:"
            + chartAggregation.getInterval().toString().toLowerCase();
      }

      // CUSTOM FIELD TYPE STRING
      return "customFields.strings." + chartAggregation.getCustomFieldValue();
    }

    // STANDARD FIELD
    // IF INTERVAL NOT NULL -> TIMESTAMP
    // OTHERWISE -> STRING
    return chartAggregation.getInterval() == null ? chartAggregation.getField()
        : chartAggregation.getField() + ":bucket:" + chartAggregation.getInterval().toString().toLowerCase();
  }
  
}
