package com.axonivy.portal.util;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseQueryUtils {

  public static CaseQuery initCaseQuery(DashboardColumnType type) {
    if (DashboardColumnType.CUSTOM_BUSINESS_CASE == type) {
      return CaseQuery.businessCases();
    } else if (DashboardColumnType.CUSTOM_CASE == type) {
      return CaseQuery.subCases();
    } else {
      return CaseQuery.create();
    }
  }
}
