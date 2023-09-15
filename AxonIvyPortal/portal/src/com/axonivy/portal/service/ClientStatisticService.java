package com.axonivy.portal.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;

import com.axonivy.portal.bo.ChartTarget;
import com.axonivy.portal.bo.ClientStatistic;
import com.axonivy.portal.dto.ClientStatisticDto;

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

  public ClientStatisticResponse getData(ClientStatisticDto payload) throws NotFoundException, NoPermissionException {
    ClientStatistic chart = findById(payload.getChartId());
    if (chart == null) {
      throw new NotFoundException(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/idNotFound",
          Arrays.asList(payload.getChartId())));
    }

    if (!isPermissionValid(chart)) {
      throw new NoPermissionException(
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/noPermission"));
    }
    AggregationResult result = getData(chart);
    return new ClientStatisticResponse(result, chart);
  }

  private AggregationResult getData(ClientStatistic chart) {
    if (ChartTarget.CASE.equals(chart.getChartTarget())) {
      return WorkflowStats.current().caze().aggregate(chart.getAggregates(), chart.getFilter());
    } else if (ChartTarget.TASK.equals(chart.getChartTarget())) {
      return WorkflowStats.current().task().aggregate(chart.getAggregates(), chart.getFilter());
    } else {
      throw new InvalidParameterException();
    }

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
