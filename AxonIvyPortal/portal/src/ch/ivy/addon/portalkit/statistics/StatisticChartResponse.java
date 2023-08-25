package ch.ivy.addon.portalkit.statistics;

import com.axonivy.portal.bo.StatisticData;

import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;

public class StatisticChartResponse {
  private AggregationResult result;
  private StatisticData chartConfig;

  public StatisticChartResponse(AggregationResult result, StatisticData config) {
    this.result = result;
    this.chartConfig = config;
  }

  public StatisticData getChartConfig() {
    return chartConfig;
  }

  public void setChartConfig(StatisticData chartConfig) {
    this.chartConfig = chartConfig;
  }

  public AggregationResult getResult() {
    return result;
  }

  public void setResult(AggregationResult result) {
    this.result = result;
  }

}
