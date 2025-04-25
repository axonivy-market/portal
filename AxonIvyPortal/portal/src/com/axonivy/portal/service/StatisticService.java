package com.axonivy.portal.service;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.dto.StatisticDto;
import com.axonivy.portal.dto.statistic.StatisticFilter;
import com.axonivy.portal.enums.AdditionalChartConfig;
import com.axonivy.portal.enums.statistic.AggregationInterval;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.util.statisticfilter.field.CaseFilterFieldFactory;
import com.axonivy.portal.util.statisticfilter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.statistics.StatisticResponse;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class StatisticService {
  
  private static final String DEFAULT_STATISTIC_KEY = PortalVariable.STATISTIC.key;
  private static final String CUSTOM_STATISTIC_KEY = PortalVariable.CUSTOM_STATISTIC.key;
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
   * @throws NoPermissionException
   */
  public StatisticResponse getStatisticData(StatisticDto payload)
      throws NotFoundException, NoPermissionException {
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
  
  private String processFilter(List<StatisticFilter> filters, ChartTarget chartTarget) {
    // TODO remove logging
    Ivy.log().info("processTaskFilter");
    if (CollectionUtils.isEmpty(filters)) {
      return null;
    }

    StringBuilder sbFilter = new StringBuilder();
    for (StatisticFilter statisticFilter : filters) {
      if (Optional.ofNullable(statisticFilter).map(StatisticFilter::getOperator).isEmpty()) {
        continue;
      }
      FilterField filterField = ChartTarget.TASK == chartTarget
          ? TaskFilterFieldFactory.findBy(statisticFilter.getField(), statisticFilter.getFilterType())
          : CaseFilterFieldFactory.findBy(statisticFilter.getField(), statisticFilter.getFilterType());      

      if (filterField != null) {
        String filterQuery = filterField.generateStringFilter(statisticFilter);

        // TODO checking filterQuery
        Ivy.log().info("checking filter Query: " + filterQuery);
        Ivy.log().info(filterQuery );

        if (filterQuery != null) {
          sbFilter.append(filterQuery).append(",");
        }
      }
    }
    if (Strings.EMPTY.equals(sbFilter.toString())) {
      return null;
    }
    
    // TODO checking filterQuery
    Ivy.log().info("final sbFilter " + sbFilter.toString());
    return sbFilter.toString();
  }

  public AggregationResult getChartData(Statistic chart) {
    String filter = null;
    String aggregates = chart.getAggregates();
    filter = processFilter(chart.getFilters(), chart.getChartTarget());
    
    chart.getFilter();
    if(StringUtils.isEmpty(aggregates)) {
      aggregates = convertAggregatesFromChartAggregation(chart);
    }

    if (!StringUtils.isEmpty(chart.getFilter())) {
      filter = chart.getFilter();
    }

    Ivy.log().info("getChartData's querying");
    Ivy.log().info("filter " + filter);
    Ivy.log().info("chartTarget " + chart.getChartTarget());

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
    return statistics;
  }
  
  public List<Statistic> getCustomStatistic() {
    return BusinessEntityConverter.jsonValueToEntities(Ivy.var().get(CUSTOM_STATISTIC_KEY), Statistic.class);
  }

  private String convertAggregatesFromChartAggregation(Statistic chart) {
    String aggregates = "";
    StatisticAggregation chartAggregation = chart.getStatisticAggregation();
    String aggregationField = chartAggregation.getField();
    AggregationInterval interval = chartAggregation.getInterval();

    if (aggregationField.toLowerCase().contains("custom")) {
      /**
       * Custom field
       */
      aggregates = "customFields.strings." + chartAggregation.getField();
//      switch (customFieldType) {
//      case CustomFieldType.STRING: {
//        aggregates = "customFields.strings." + chartAggregation.getCustomFieldValue();
//        break;
//      }
//      case CustomFieldType.NUMBER: {
//        Ivy.log().info("CUSTOM FIELD IS TYPE NUMBER! CURRENTLY NOT SUPPORTED");
//        break;
//      }
//      case CustomFieldType.TIMESTAMP: {
//        aggregates = "customFields.timestamps." + chartAggregation.getCustomFieldValue();
//        break;
//      }
//      default: {
//      }
//      }
//      aggregates = interval != null ? aggregates + ":bucket:" + interval.getName().toLowerCase() : aggregates;

      return aggregates;

    } else if (aggregationField.toLowerCase().contains("timestamp")) {
      /**
       * Normal timestamp
       */
      if (interval != null) {
        aggregates = aggregationField + ":bucket:" + interval.getName().toLowerCase();
      }

      return aggregates;
    }
    /**
     * Normal
     */
    aggregates = aggregationField;

    return aggregates;
  }

}
