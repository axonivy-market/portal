package com.axonivy.portal.userexamples.utils;

import com.axonivy.portal.userexamples.enums.PortalUserExampleVariable;

import ch.ivyteam.ivy.environment.Ivy;

public class SampleStatisticUtil {
  private static final String SAMPLE_KPI_STATISTIC_KEY = PortalUserExampleVariable.SAMPLE_KPI_STATISTIC_KEY.key;

  public static String getSampleStatisticsJson() {
    return Ivy.var().get(SAMPLE_KPI_STATISTIC_KEY);
  }
}
