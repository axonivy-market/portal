package com.axonivy.portal.userexamples.enums;

public enum PortalUserExamplesVariable {
  SAMPLE_STATISTIC_KEY("Portal.UserExamples.SampleStatistic"),
  SAMPLE_DASHBOARD_KEY("Portal.UserExamples.SampleDashboard");

  public String key;

  private PortalUserExamplesVariable(String key) {
    this.key = key;
  }
}