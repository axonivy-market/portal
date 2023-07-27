package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.dto.statisticChart.callbackData;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.statistics.NewStatisticChart;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class StatisticChartService extends JsonConfigurationService<NewStatisticChart> {

  private static StatisticChartService instance;
  private List<NewStatisticChart> availableCharts;

  public StatisticChartService getInstance() {
    if (instance == null) {
      instance = new StatisticChartService();
    }
    return StatisticChartService.instance;
  }

  public AggregationResult callBack(callbackData payload) {
    NewStatisticChart chart = findById(payload.getChartId());
    if (chart == null) {
      Ivy.log().error("chart null");
      return null;
    } else if (!isPemissionValid(chart)) {
      Ivy.log().error("Role null");
      return null;
    } else {
       if (chart.getIsCaseFilter()) {
         return getCaseData(chart.getAggregates(), chart.getFilter());
       } else {
         return getTaskData(chart.getAggregates(), chart.getFilter());
       }
    }

  }

  @Override
  public NewStatisticChart findById(String chartId) {
    List<NewStatisticChart> findList = getStatisticChart().stream()
        .filter(statisticChart -> statisticChart.getId().equalsIgnoreCase(chartId)).collect(Collectors.toList());
    if (findList.size() == 0) {
      return null;
    } else {
      return findList.get(0);
    }
  }

  private Boolean isPemissionValid(NewStatisticChart chart) {
    Ivy.log().error("Role" + Ivy.session().getSessionUser().getRoles().toString());
    return Ivy.session().getSessionUser().getAllRoles().stream().map(IRole::getDisplayName)
        .anyMatch(displayName -> displayName.equalsIgnoreCase(chart.getPermissions().get(0)));
  }

  private List<NewStatisticChart> getStatisticChart() {
    availableCharts = new ArrayList<>();
    availableCharts.addAll(getPublicConfig());
    return availableCharts;
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
    return PortalVariable.STATISTIC_CHART.key;
  }
}
