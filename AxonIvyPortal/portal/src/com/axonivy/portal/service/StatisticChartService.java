package com.axonivy.portal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;

import com.axonivy.portal.dto.statisticChart.StatisticDataDto;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.service.JsonConfigurationService;
import ch.ivy.addon.portalkit.statistics.NewStatisticChart;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class StatisticChartService extends JsonConfigurationService<NewStatisticChart> {

  private static StatisticChartService instance;

  public StatisticChartService getInstance() {
    if (instance == null) {
      instance = new StatisticChartService();
    }
    return StatisticChartService.instance;
  }

  public AggregationResult getData(StatisticDataDto payload) throws NotFoundException, NoPermissionException {
    NewStatisticChart chart = findById(payload.getChartId());
    if (chart == null) {
      throw new NotFoundException(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/idNotFound",
          Arrays.asList(payload.getChartId())));
    }

    if (!isPermissionValid(chart)) {
      throw new NoPermissionException(
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/noPermission"));
    }

    return getData(chart);
  }

  private AggregationResult getData(NewStatisticChart chart) {
    return chart.getIsCaseFilter() ? WorkflowStats.current().caze().aggregate(chart.getAggregates(), chart.getFilter())
        : WorkflowStats.current().task().aggregate(chart.getAggregates(), chart.getFilter());
  }

  private boolean isPermissionValid(NewStatisticChart chart) {
    return Optional.ofNullable(chart.getPermissions()).orElse(new ArrayList<>()).stream()
        .anyMatch(permission -> Ivy.session().getSessionUser().has().role(permission));
  }

  @Override
  public Class<NewStatisticChart> getType() {
    return NewStatisticChart.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.NEW_STATISTIC_CHART.key;
  }
}
