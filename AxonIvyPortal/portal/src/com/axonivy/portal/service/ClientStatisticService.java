package com.axonivy.portal.service;

import java.security.InvalidParameterException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.ClientStatistic;
import com.axonivy.portal.dto.ClientStatisticDto;
import com.axonivy.portal.enums.AdditionalChartConfig;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.service.JsonConfigurationService;
import ch.ivy.addon.portalkit.statistics.ClientStatisticResponse;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class ClientStatisticService extends JsonConfigurationService<ClientStatistic> {

  private static ClientStatisticService instance;

  public static ClientStatisticService getInstance() {
    if (instance == null) {
      instance = new ClientStatisticService();
    }
    return ClientStatisticService.instance;
  }

  public List<ClientStatistic> findAllCharts() {
    return findAll();
  }

  /**
   * get client chart by payload then call Ivy API to get statistic data from
   * ElasticSearch
   * 
   * @param payload
   * @return Ivy statistic data from ElasticSearch
   * @throws NotFoundException
   * @throws NoPermissionException
   */
  public ClientStatisticResponse getStatisticData(ClientStatisticDto payload)
      throws NotFoundException, NoPermissionException {
    ClientStatistic chart = findById(payload.getChartId());
    validateChart(payload.getChartId(), chart);
    AggregationResult result = getChartData(chart);
    chart.setAdditionalConfig(getAdditionalConfig());
    return new ClientStatisticResponse(result, chart);
  }

  private void validateChart(String chartId, ClientStatistic chart)
      throws NotFoundException, NoPermissionException {
    if (chart == null) {
      throw new NotFoundException(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/idNotFound",
          Arrays.asList(chartId)));
    }

    if (!isPermissionValid(chart)) {
      throw new NoPermissionException(
          Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/ClientStatisticWidget/NoPermissionChartMessage"));
    }
  }

  private AggregationResult getChartData(ClientStatistic chart) {
    chart.setFilter(StringUtils.stripToNull(chart.getFilter()));

    return switch (chart.getChartTarget()) {
    case CASE -> WorkflowStats.current().caze().aggregate(chart.getAggregates(),
        chart.getFilter());
    case TASK -> WorkflowStats.current().task().aggregate(chart.getAggregates(),
        chart.getFilter());
    default -> throw new InvalidParameterException();
    };
  }

  private List<Entry<String, String>> getAdditionalConfig() {
    return Stream.of(AdditionalChartConfig.values()).flatMap(Stream::of)
        .map(config -> new SimpleEntry<>(config.getKey(), config.getCms()))
        .collect(Collectors.toList());
  }

  private boolean isPermissionValid(ClientStatistic data) {
    return Optional.ofNullable(data.getPermissions()).orElse(new ArrayList<>()).stream()
        .anyMatch(permission -> Ivy.session().getSessionUser().has().role(permission));
  }

  @Override
  public Class<ClientStatistic> getType() {
    return ClientStatistic.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.CLIENT_STATISTIC.key;
  }

}
