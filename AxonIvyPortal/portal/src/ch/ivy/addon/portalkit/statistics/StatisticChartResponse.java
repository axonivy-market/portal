package ch.ivy.addon.portalkit.statistics;

import com.axonivy.portal.bo.StatisticChartData;

import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;

public class StatisticChartResponse {
  private AggregationResult result;
  private StatisticChartData chartConfig;

  public StatisticChartResponse(AggregationResult result, StatisticChartData config) {
    this.result = result;
    this.chartConfig = config;
  }

  public StatisticChartData getChartConfig() {
    return chartConfig;
  }

  public void setChartConfig(StatisticChartData chartConfig) {
    this.chartConfig = chartConfig;
  }

  public AggregationResult getResult() {
    return result;
  }

  public void setResult(AggregationResult result) {
    this.result = result;
  }

}
