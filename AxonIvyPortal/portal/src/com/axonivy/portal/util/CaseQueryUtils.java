package com.axonivy.portal.util;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseQueryUtils {

  public static CaseQuery initCaseQuery(DashboardColumnType type) {
    if (DashboardColumnType.CUSTOM_BUSINESS_CASE == type) {
      Ivy.log().info("CaseQueryUtils#initCaseQuery: filterType={0} -> CaseQuery.businessCases()", type);
      return CaseQuery.businessCases();
    }
    if (DashboardColumnType.CUSTOM_CASE == type) {
      Ivy.log().info("CaseQueryUtils#initCaseQuery: filterType={0} -> CaseQuery.subCases()", type);
      return CaseQuery.subCases();
    }
    // Case widgets (CUSTOM) and any unresolved type keep the neutral scope (business + sub case).
    Ivy.log().info("CaseQueryUtils#initCaseQuery: filterType={0} -> CaseQuery.create()", type);
    return CaseQuery.create();
  }
}
