package ch.ivy.addon.portalkit.service;

import java.util.Arrays;

import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;

import ch.ivy.addon.portalkit.dto.statisticChart.StatisticDataDto;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.statistics.NewStatisticChart;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class StatisticChartService extends JsonConfigurationService<NewStatisticChart> {

  private static StatisticChartService instance;

  public StatisticChartService getInstance() {
    if (instance == null) {
      instance = new StatisticChartService();
    }
    return StatisticChartService.instance;
  }

  public AggregationResult callBack(StatisticDataDto payload) throws NotFoundException, NoPermissionException {
    NewStatisticChart chart = findById(payload.getChartId());
    if (chart == null) {
      Ivy.log().error(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/idNotFound",
          Arrays.asList(payload.getChartId())));
      throw new NotFoundException(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/idNotFound",
          Arrays.asList(payload.getChartId())));
    } else if (!isPermissionValid(chart)) {
      Ivy.log().error(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/noPermission"));
      throw new NoPermissionException(
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/noPermission"));
    } else {
      if (chart.getIsCaseFilter()) {
        return getCaseData(chart.getAggregates(), chart.getFilter());
      } else {
        return getTaskData(chart.getAggregates(), chart.getFilter());
      }
    }

  }

  private boolean isPermissionValid(NewStatisticChart chart) {
    return chart.getPermissions().stream().anyMatch(permission -> Ivy.session().getSessionUser().getAllRoles().stream()
        .map(IRole::getDisplayName).anyMatch(displayName -> displayName.equalsIgnoreCase(permission)));
  }

  private AggregationResult getTaskData(String agg, String filter) {
    return WorkflowStats.current().task().aggregate(agg, filter);
  }


  private AggregationResult getCaseData(String agg, String filter) {
    return WorkflowStats.current().caze().aggregate(agg, filter);
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
