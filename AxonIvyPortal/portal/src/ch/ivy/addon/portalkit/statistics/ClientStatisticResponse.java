package ch.ivy.addon.portalkit.statistics;

import com.axonivy.portal.bo.ClientStatistic;

import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;

public class ClientStatisticResponse {
  private AggregationResult result;
  private ClientStatistic chartConfig;

  public ClientStatisticResponse(AggregationResult result, ClientStatistic config) {
    this.result = result;
    this.chartConfig = config;
  }

  public ClientStatistic getChartConfig() {
    return chartConfig;
  }

  public void setChartConfig(ClientStatistic chartConfig) {
    this.chartConfig = chartConfig;
  }

  public AggregationResult getResult() {
    return result;
  }

  public void setResult(AggregationResult result) {
    this.result = result;
  }

}
