package com.axonivy.portal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;

import com.axonivy.portal.bo.StatisticData;
import com.axonivy.portal.dto.statisticChart.StatisticDataDto;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.service.JsonConfigurationService;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class StatisticDataService extends JsonConfigurationService<StatisticData> {

  private static StatisticDataService instance;

  public StatisticDataService getInstance() {
    if (instance == null) {
      instance = new StatisticDataService();
    }
    return StatisticDataService.instance;
  }

  public AggregationResult getData(StatisticDataDto payload) throws NotFoundException, NoPermissionException {
	  StatisticData chart = findById(payload.getChartId());
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

  private AggregationResult getData(StatisticData chart) {
    return chart.getIsCaseFilter() ? WorkflowStats.current().caze().aggregate(chart.getAggregates(), chart.getFilter())
        : WorkflowStats.current().task().aggregate(chart.getAggregates(), chart.getFilter());
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
