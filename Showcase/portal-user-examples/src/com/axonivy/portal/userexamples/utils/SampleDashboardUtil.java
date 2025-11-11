package com.axonivy.portal.userexamples.utils;

import com.axonivy.portal.userexamples.enums.PortalUserExampleVariable;

import ch.ivyteam.ivy.environment.Ivy;

public class SampleDashboardUtil {
  private static final String SAMPLE_KPI_DASHBOARD_KEY = PortalUserExampleVariable.SAMPLE_KPI_DASHBOARD_KEY.key;

  public static String getSampleDashboardsJson() {
    return Ivy.var().get(SAMPLE_KPI_DASHBOARD_KEY);
  }
}
