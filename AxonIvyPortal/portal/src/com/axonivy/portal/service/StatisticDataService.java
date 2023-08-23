package com.axonivy.portal.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;

import com.axonivy.portal.bo.ChartTarget;
import com.axonivy.portal.bo.StatisticData;
import com.axonivy.portal.dto.statisticChart.StatisticDataDto;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.service.JsonConfigurationService;
import ch.ivy.addon.portalkit.statistics.StatisticsChartResponse;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class StatisticDataService extends JsonConfigurationService<StatisticData> {

  private static StatisticDataService instance;

  public static StatisticDataService getInstance() {
    if (instance == null) {
      instance = new StatisticDataService();
    }
    return StatisticDataService.instance;
  }

  public List<StatisticData> findAllCharts() {
    return findAll();
  }

  public StatisticsChartResponse getData(StatisticDataDto payload) throws NotFoundException, NoPermissionException {
    StatisticData chart = findById(payload.getChartId());
    if (chart == null) {
      throw new NotFoundException(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/idNotFound",
          Arrays.asList(payload.getChartId())));
    }

    if (!isPermissionValid(chart)) {
      throw new NoPermissionException(
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/noPermission"));
    }
    AggregationResult result = getData(chart);
    return new StatisticsChartResponse(result, chart);
  }

  private AggregationResult getData(StatisticData chart) {
    if (ChartTarget.CASE.equals(chart.getChartTarget())) {
      return WorkflowStats.current().caze().aggregate(chart.getAggregates(), chart.getFilter());
    } else if (ChartTarget.TASK.equals(chart.getChartTarget())) {
      return WorkflowStats.current().task().aggregate(chart.getAggregates(), chart.getFilter());
    } else {
      throw new InvalidParameterException();
    }

  }

  private boolean isPermissionValid(StatisticData data) {
    return Optional.ofNullable(data.getPermissions()).orElse(new ArrayList<>()).stream()
        .anyMatch(permission -> Ivy.session().getSessionUser().has().role(permission));
  }

  @Override
  public Class<StatisticData> getType() {
    return StatisticData.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.STATISTIC_DATA.key;
  }

}
