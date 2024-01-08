package com.axonivy.portal.developerexamples.util;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public final class CaseUtils {

  private CaseUtils() {}

  public static CaseQuery createBusinessCaseQuery() {
    return CaseQuery.businessCases();
  }

}