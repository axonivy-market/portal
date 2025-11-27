package com.axonivy.portal.userexamples.enums;

public enum PortalUserExampleVariable {
  SAMPLE_STATISTIC_KEY("Portal.User.Example.ProcurementStatistic"),
  SAMPLE_DASHBOARD_KEY("Portal.User.Example.ProcurementDashboard");

  public String key;

  private PortalUserExampleVariable(String key) {
    this.key = key;
  }
}
