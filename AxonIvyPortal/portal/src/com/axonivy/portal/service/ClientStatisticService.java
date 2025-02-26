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

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.ClientStatistic;
import com.axonivy.portal.dto.ClientStatisticDto;
import com.axonivy.portal.enums.AdditionalChartConfig;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.statistics.ClientStatisticResponse;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class ClientStatisticService {
  
  private static final String DEFAULT_CLIENT_STATISTIC_KEY = PortalVariable.CLIENT_STATISTIC.key;
  private static final String CUSTOM_CLIENT_STATISTIC_KEY = PortalVariable.CUSTOM_CLIENT_STATISTIC.key;
  private static ClientStatisticService instance;

  public static ClientStatisticService getInstance() {
    if (instance == null) {
      instance = new ClientStatisticService();
    }
    return ClientStatisticService.instance;
  }

  public List<ClientStatistic> findAllCharts() {
    Set<String> seenIds = new HashSet<>();
    List<ClientStatistic> statistics = getDefaultClientStatistic();
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
  public ClientStatisticResponse getStatisticData(ClientStatisticDto payload)
      throws NotFoundException, NoPermissionException {
    ClientStatistic chart = findByClientStatisticId(payload.getChartId());
    validateChart(payload.getChartId(), chart);
    AggregationResult result = getChartData(chart);
    chart.setAdditionalConfigs(new ArrayList<>());
    chart.getAdditionalConfigs().addAll(getAdditionalConfig());
    chart.getAdditionalConfigs().add(getManipulateValueBy(chart));
    return new ClientStatisticResponse(result, chart);
  }

  private void validateChart(String chartId, ClientStatistic chart) {
    if (chart == null) {
      throw new PortalException(Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/ClientStatisticWidget/IdNotFound",
          Arrays.asList(chartId)));
    }

    if (!hasPermission(chart)) {
      throw new PortalException(
          Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/ClientStatisticWidget/NoPermissionChartMessage"));
    }
  }

  public AggregationResult getChartData(ClientStatistic chart) {
    chart.setFilter(StringUtils.stripToNull(chart.getFilter()));
    return switch (chart.getChartTarget()) {
    case CASE -> WorkflowStats.current().caze().aggregate(chart.getAggregates(),
        chart.getFilter());
    case TASK -> WorkflowStats.current().task().aggregate(chart.getAggregates(),
        chart.getFilter());
    default -> throw new PortalException("Cannot parse chartTarget " + chart.getChartTarget());
    };
  }

  private List<Entry<String, String>> getAdditionalConfig() {
    return List.of(new SimpleEntry<>(AdditionalChartConfig.EMPTY_CHART_DATA_MESSAGE.getKey(),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/StatisticWidget/EmptyChartDataMessage")));
  }

  private boolean hasPermission(ClientStatistic data) {
    return Optional.ofNullable(data.getPermissions())
                   .orElse(List.of())
                   .stream()
                   .anyMatch(permission -> Ivy.session().getSessionUser().has().role(permission));
  }

  private Entry<String, String> getManipulateValueBy(ClientStatistic data) {
    return Optional.ofNullable(data.getManipulateValueBy())
                   .map(value -> new SimpleEntry<>(AdditionalChartConfig.MANIPULATE_BY.getKey(), value))
                   .orElse(null);
  }
  
  private ClientStatistic findByClientStatisticId(String id) {
    return findAllCharts().stream()
        .filter(e -> e.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public ClientStatistic findByIdCustomClientStatistic(String id) {
    return getCustomStatistic().stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
  }

  public void saveJsonToVariable(List<ClientStatistic> clientStatistics) {
    String statisticsJson = BusinessEntityConverter.entityToJsonValue(clientStatistics);
    Ivy.var().set(PortalVariable.CUSTOM_CLIENT_STATISTIC.key, statisticsJson);
  }
  
  private List<ClientStatistic> getDefaultClientStatistic() {
    String value = Ivy.var().get(DEFAULT_CLIENT_STATISTIC_KEY);
    List<ClientStatistic> clientStatistics = BusinessEntityConverter.jsonValueToEntities(value, ClientStatistic.class);
    clientStatistics.stream().forEach(cs -> cs.setIsCustom(false));
    return clientStatistics;
  }
  
  public List<ClientStatistic> getCustomStatistic() {
    String value = Ivy.var().get(CUSTOM_CLIENT_STATISTIC_KEY);
    List<ClientStatistic> clientStatistics = BusinessEntityConverter.jsonValueToEntities(value, ClientStatistic.class);
    return clientStatistics;
  }
}
