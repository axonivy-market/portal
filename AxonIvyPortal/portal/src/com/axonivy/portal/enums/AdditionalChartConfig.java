package com.axonivy.portal.enums;

public enum AdditionalChartConfig {
  EMPTY_CHART_DATA_MESSAGE("emptyChartDataMessage"),
  FAIL_TO_RENDER_CHART_MESSAGE("failToRenderChartMessage"),
  MANIPULATE_BY("manipulateValueBy");

  private String key;

  private AdditionalChartConfig(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

}
