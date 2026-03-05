package ch.ivy.addon.portalkit.statistics;

import java.util.HashMap;
import java.util.Map;

import com.axonivy.portal.bo.Statistic;

import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;

public class StatisticResponse {
  private AggregationResult result;
  private Statistic chartConfig;

  private Map<String, String> customFieldLocalizedValues = new HashMap<>();

  public StatisticResponse(AggregationResult result, Statistic config) {
    this.result = result;
    this.chartConfig = config;
  }

  public StatisticResponse(AggregationResult result, Statistic config, Map<String, String> customFieldLocalizedValues) {
    this.result = result;
    this.chartConfig = config;
    this.customFieldLocalizedValues = customFieldLocalizedValues;
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

  public Map<String, String> getCustomFieldLocalizedValues() {
    return customFieldLocalizedValues;
  }

  public void setCustomFieldLocalizedValues(Map<String, String> customFieldLocalizedValues) {
    this.customFieldLocalizedValues = customFieldLocalizedValues;
  }
}
