package com.axonivy.portal.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum AdditionalChartConfig {
  EMPTY_CHART_DATA_MESSAGE("emptyChartDataMessage",
      "/ch.ivy.addon.portalkit.ui.jsf/dashboard/StatisticWidget/EmptyChartDataMessage"),
  NO_CATEGORY("noCategory", "/ch.ivy.addon.portalkit.ui.jsf/common/noCategory");

  private String key;
  private String cms;

  private AdditionalChartConfig(String key, String cms) {
    this.key = key;
    this.cms = cms;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getCms() {
    return Ivy.cms().co(cms);
  }

  public void setCms(String cms) {
    this.cms = cms;
  }
}
