package com.axonivy.portal.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class PieChartConfig {
  private List<String> backgroundColors;
  private List<ThresholdStatisticChart> thresholds;
  private String defaultBackgroundColor;
  private boolean conditionBasedColoringEnabled;

  public List<String> getBackgroundColors() {
    return backgroundColors;
  }

  public void setBackgroundColors(List<String> backgroundColors) {
    this.backgroundColors = backgroundColors;
  }

  public List<ThresholdStatisticChart> getThresholds() {
    return thresholds;
  }

  public void setThresholds(List<ThresholdStatisticChart> thresholds) {
    this.thresholds = thresholds;
  }

  public String getDefaultBackgroundColor() {
    return defaultBackgroundColor;
  }

  public void setDefaultBackgroundColor(String defaultBackgroundColor) {
    this.defaultBackgroundColor = defaultBackgroundColor;
  }

  public boolean isConditionBasedColoringEnabled() {
    return conditionBasedColoringEnabled;
  }

  public void setConditionBasedColoringEnabled(boolean conditionBasedColoringEnabled) {
    this.conditionBasedColoringEnabled = conditionBasedColoringEnabled;
  }
}
