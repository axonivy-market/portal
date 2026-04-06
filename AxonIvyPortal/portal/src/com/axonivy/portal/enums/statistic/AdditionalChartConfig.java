package com.axonivy.portal.enums.statistic;

public enum AdditionalChartConfig {
  EMPTY_CHART_DATA_MESSAGE("emptyChartDataMessage"),
  FAIL_TO_RENDER_CHART_MESSAGE("failToRenderChartMessage"),
  MANIPULATE_BY("manipulateValueBy"),
  TOOLTIP_TOTAL_LABEL("tooltipTotalLabel"),
  TOOLTIP_KPI_LABEL("tooltipKpiLabel"),
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
