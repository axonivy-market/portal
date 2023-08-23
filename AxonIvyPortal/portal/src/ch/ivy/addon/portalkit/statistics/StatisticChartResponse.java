package ch.ivy.addon.portalkit.statistics;

import com.axonivy.portal.bo.BarChartConfig;
import com.axonivy.portal.bo.StatisticData;

import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;

public class StatisticChartResponse {
  private AggregationResult result;
  private String chartType;
  private String label;
  private BarChartConfig barChartConfig;

  public StatisticChartResponse(AggregationResult result, StatisticData config) {
    this.result = result;
    this.chartType = config.getChartType();
    this.label = config.getLabel();
    this.barChartConfig = config.getBarChartConfig();
  }

  public AggregationResult getResult() {
    return result;
  }

  public void setResult(AggregationResult result) {
    this.result = result;
  }

  public String getChartType() {
    return chartType;
  }

  public void setChartType(String chartType) {
    this.chartType = chartType;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public BarChartConfig getBarChartConfig() {
    return barChartConfig;
  }

  public void setBarChartConfig(BarChartConfig barChartConfig) {
    this.barChartConfig = barChartConfig;
  }

}
