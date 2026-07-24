package com.axonivy.portal.util;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseQueryUtils {

  public static CaseQuery initCaseQuery(DashboardColumnType type) {
    // return CaseQuery.create();
    if (DashboardColumnType.CUSTOM_CASE == type) {
      return CaseQuery.subCases();
    } else { // default is business case
      return CaseQuery.businessCases();
    }
  }
}
