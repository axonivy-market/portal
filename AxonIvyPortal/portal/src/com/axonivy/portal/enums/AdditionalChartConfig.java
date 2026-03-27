package com.axonivy.portal.enums;

public enum AdditionalChartConfig {
  EMPTY_CHART_DATA_MESSAGE("emptyChartDataMessage"),
  MANIPULATE_BY("manipulateValueBy"),
  EXPAND_LABEL_TEMPLATE("expandLabelTemplate"),
  COLLAPSE_LABEL_TEMPLATE("collapseLabelTemplate"),
  INFO_LABEL_TEMPLATE("infoLabelTemplate");

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
