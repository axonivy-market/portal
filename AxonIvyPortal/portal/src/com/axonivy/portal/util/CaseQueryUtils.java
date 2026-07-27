package com.axonivy.portal.util;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.CaseQueryStrategy;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public final class CaseQueryUtils {

  private static final String RELATED_CASE_QUERY_STRATEGY_VAR = "Portal.Tasks.RelatedCaseQueryStrategy";

  private CaseQueryUtils() {
  }

  public static CaseQuery initCaseQuery(DashboardColumnType type) {
    if (currentStrategy() == CaseQueryStrategy.LEGACY) {
      return CaseQuery.create();
    }
    if (DashboardColumnType.CUSTOM_BUSINESS_CASE == type) {
      return CaseQuery.businessCases();
    } else if (DashboardColumnType.CUSTOM_CASE == type) {
      return CaseQuery.subCases();
    }
    return CaseQuery.create();
  }

  private static CaseQueryStrategy currentStrategy() {
    String strategy = Ivy.var().get(RELATED_CASE_QUERY_STRATEGY_VAR);
    return StringUtils.isBlank(strategy) ? CaseQueryStrategy.LEGACY : CaseQueryStrategy.valueOf(strategy);
  }
}
