package ch.ivy.addon.portalkit.service;

import org.primefaces.model.charts.donut.DonutChartModel;

import ch.ivy.addon.portalkit.bo.PriorityStatistic;
import ch.ivy.addon.portalkit.constant.DummyStatistic;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.statistics.StatisticChart;

public class DummyStatisticService {

  public static StatisticChart createDummyChartForGuide() {
    StatisticChart dummyChart = new StatisticChart();
    dummyChart.setId("Dummy");
    dummyChart.setName(DummyStatistic.CHART_NAME);
    dummyChart.setType(StatisticChartType.TASK_BY_PRIORITY);
    
    PriorityStatistic data = new PriorityStatistic();
    data.setNormal(1);
    StatisticService service = new StatisticService();
    DonutChartModel model = service.generateTaskByPriorityModel(data, true);
    dummyChart.setDonutChartModel(model);
    return dummyChart;
  }
  
}
