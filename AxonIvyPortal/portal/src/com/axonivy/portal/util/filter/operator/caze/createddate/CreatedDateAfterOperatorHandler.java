package com.axonivy.portal.util.filter.operator.caze.createddate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;
import com.axonivy.portal.util.filter.operator.AbstractFilterOperatorHandler;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatedDateAfterOperatorHandler extends AbstractFilterOperatorHandler {
  private static CreatedDateAfterOperatorHandler instance;

  public static CreatedDateAfterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateAfterOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery(DashboardFilter filter) {
    if (filter.getTo() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().startTimestamp().isGreaterThan(PortalDateUtils.getEndOfDate(filter.getToDate()));

    return query;
  }
}
