package com.axonivy.portal.util.filter.operator.caze.finisheddate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class FinishedDateIsOperatorHandler {
  private static FinishedDateIsOperatorHandler instance;

  public static FinishedDateIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new FinishedDateIsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return CaseQuery.create().where().endTimestamp().isGreaterOrEqualThan(PortalDateUtils.getStartOfDate(filter.getFromDate()))
        .and().endTimestamp().isLowerOrEqualThan(PortalDateUtils.getEndOfDate(filter.getFromDate()));
  }

  public CaseQuery buildIsNotQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return CaseQuery.create().where().endTimestamp().isGreaterThan(PortalDateUtils.getEndOfDate(filter.getFromDate()))
        .or().endTimestamp().isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));
  }
}