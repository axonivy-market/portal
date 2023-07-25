package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class StatisticChartService extends JsonConfigurationService<StatisticChart> {

  private static StatisticChartService instance;
  private List<StatisticChart> availableCharts;

  public StatisticChartService getInstance() {
    if (instance == null) {
      instance = new StatisticChartService();
    }
    return StatisticChartService.instance;
  }

  @Override
  public StatisticChart findById(String chartId) {
    List<StatisticChart> findList = fetchStatisticCharts().stream()
        .filter(statisticChart -> statisticChart.getId().equalsIgnoreCase(chartId)).collect(Collectors.toList());
    if (findList.size() == 0) {
      return null;
    } else {
      return findList.get(0);
    }
  }

  private Boolean isPemissionValid(StatisticChart chart) {
    return chart.getPermissions().contains(Ivy.session().getSessionUser().getRoles());
  }

  private List<StatisticChart> getStatisticChart() {
    availableCharts = new ArrayList<>();
    availableCharts.addAll(getPublicConfig());
    return availableCharts;
  }


  private AggregationResult getTaskData(String agg) {
    return WorkflowStats.current().task().aggregate(agg);
  }

  private AggregationResult getTaskData(String agg, String filter) {
    return WorkflowStats.current().task().aggregate(agg, filter);
  }

  private AggregationResult getCaseData(String agg) {
    return WorkflowStats.current().caze().aggregate(agg);
  }

  private AggregationResult getCaseData(String agg, String filter) {
    return WorkflowStats.current().caze().aggregate(agg, filter);
  }

  @Override
  public Class<StatisticChart> getType() {
    return StatisticChart.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.STATISTIC_CHART.key;
  }
}
