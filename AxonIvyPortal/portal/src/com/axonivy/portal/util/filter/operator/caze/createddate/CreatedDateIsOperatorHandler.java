package com.axonivy.portal.util.filter.operator.caze.createddate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatedDateIsOperatorHandler {
  private static CreatedDateIsOperatorHandler instance;

  public static CreatedDateIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateIsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return CaseQuery.create().where().startTimestamp().isGreaterOrEqualThan(PortalDateUtils.getStartOfDate(filter.getFromDate()))
        .and().startTimestamp().isLowerOrEqualThan(PortalDateUtils.getEndOfDate(filter.getFromDate()));
  }

  public CaseQuery buildIsNotQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return CaseQuery.create().where().startTimestamp().isGreaterThan(PortalDateUtils.getEndOfDate(filter.getFromDate()))
        .or().startTimestamp().isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));
  }
}
