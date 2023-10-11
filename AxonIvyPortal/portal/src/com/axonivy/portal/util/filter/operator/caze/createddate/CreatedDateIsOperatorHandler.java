package com.axonivy.portal.util.filter.operator.caze.createddate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;
import com.axonivy.portal.util.filter.operator.AbstractFilterOperatorHandler;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatedDateIsOperatorHandler extends AbstractFilterOperatorHandler {
  private static CreatedDateIsOperatorHandler instance;

  public static CreatedDateIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateIsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsQuery(DashboardFilter filter) {
    if (filter.getFrom() == null) {
      return null;
    }

    return CaseQuery.create().where().startTimestamp().isGreaterOrEqualThan(PortalDateUtils.getStartOfDate(filter.getFrom()))
        .and().startTimestamp().isLowerOrEqualThan(PortalDateUtils.getEndOfDate(filter.getFrom()));
  }

  public CaseQuery buildIsNotQuery(DashboardFilter filter) {
    if (filter.getFrom() == null) {
      return null;
    }

    return CaseQuery.create().where().startTimestamp().isGreaterThan(PortalDateUtils.getEndOfDate(filter.getFrom()))
        .or().startTimestamp().isLowerThan(PortalDateUtils.getStartOfDate(filter.getFrom()));
  }
}
