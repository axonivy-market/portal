package ch.ivy.addon.portalkit.statistics;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.dto.statistic.AggregationResultDTO;

public class StatisticResponse {

  private AggregationResultDTO result;
  private Statistic chartConfig;

  public StatisticResponse(AggregationResultDTO result, Statistic config) {
    this.result = result;
    this.chartConfig = config;
  }

  public Statistic getChartConfig() {
    return chartConfig;
  }

  public void setChartConfig(Statistic chartConfig) {
    this.chartConfig = chartConfig;
  }

  public AggregationResultDTO getResult() {
    return result;
  }

  public void setResult(AggregationResultDTO result) {
    this.result = result;
  }
}
