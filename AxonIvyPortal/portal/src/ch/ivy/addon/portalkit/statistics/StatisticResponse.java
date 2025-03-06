package ch.ivy.addon.portalkit.statistics;

import com.axonivy.portal.bo.Statistic;

import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;

public class StatisticResponse {
  private AggregationResult result;
  private Statistic chartConfig;

  public StatisticResponse(AggregationResult result, Statistic config) {
    this.result = result;
    this.chartConfig = config;
  }

  public Statistic getChartConfig() {
    return chartConfig;
  }

  public void setChartConfig(Statistic chartConfig) {
    this.chartConfig = chartConfig;
  }

  public AggregationResult getResult() {
    return result;
  }

  public void setResult(AggregationResult result) {
    this.result = result;
  }

}
