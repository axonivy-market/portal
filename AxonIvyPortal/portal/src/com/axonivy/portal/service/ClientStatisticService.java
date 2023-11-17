package com.axonivy.portal.service;

import java.security.InvalidParameterException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;

import com.axonivy.portal.bo.ChartTarget;
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

  public ClientStatisticResponse getStatisticData(ClientStatisticDto payload) throws NotFoundException, NoPermissionException {
    ClientStatistic chart = findById(payload.getChartId());
    if (chart == null) {
      throw new NotFoundException(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/idNotFound",
          Arrays.asList(payload.getChartId())));
    }

    if (!isPermissionValid(chart)) {
      throw new NoPermissionException(
          Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/ClientStatisticWidget/NoPermissionChartMessage"));
    }
    AggregationResult result = getChartData(chart);
    chart.setAdditionalConfig(getAdditionalConfig());
    return new ClientStatisticResponse(result, chart);
  }

  private AggregationResult getChartData(ClientStatistic chart) {
    if (ChartTarget.CASE.equals(chart.getChartTarget())) {
      return WorkflowStats.current().caze().aggregate(chart.getAggregates(), chart.getFilter());
    } else if (ChartTarget.TASK.equals(chart.getChartTarget())) {
      return WorkflowStats.current().task().aggregate(chart.getAggregates(), chart.getFilter());
    } else {
      throw new InvalidParameterException();
    }

  }

  private List<Entry<String, String>> getAdditionalConfig() {
    return List.of(new SimpleEntry<>(AdditionalChartConfig.EMPTY_CHART_DATA_MESSAGE.getKey(),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/StatisticWidget/EmptyChartDataMessage")));
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
