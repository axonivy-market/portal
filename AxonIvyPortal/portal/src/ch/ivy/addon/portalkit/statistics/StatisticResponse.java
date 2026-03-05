package ch.ivy.addon.portalkit.statistics;

import java.util.HashMap;
import java.util.Map;

import com.axonivy.portal.bo.Statistic;

import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;

public class StatisticResponse {
  private AggregationResult result;
  private Statistic chartConfig;

  private Map<String, String> localizedValues = new HashMap<>();

  public StatisticResponse(AggregationResult result, Statistic config) {
    this.result = result;
    this.chartConfig = config;
  }

  public StatisticResponse(AggregationResult result, Statistic config, Map<String, String> localizedValues) {
    this.result = result;
    this.chartConfig = config;
    this.localizedValues = localizedValues;
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

  public Map<String, String> getLocalizedValues() {
    return localizedValues;
  }

  public void setLocalizedValues(Map<String, String> localizedValues) {
    this.localizedValues = localizedValues;
  }
}
