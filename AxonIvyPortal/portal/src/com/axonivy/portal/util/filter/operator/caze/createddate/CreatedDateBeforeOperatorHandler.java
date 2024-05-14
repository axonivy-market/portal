package com.axonivy.portal.util.filter.operator.caze.createddate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatedDateBeforeOperatorHandler {
  private static CreatedDateBeforeOperatorHandler instance;

  public static CreatedDateBeforeOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateBeforeOperatorHandler();
    }
    return instance;
  }

  
  public CaseQuery buildQuery(DashboardFilter filter) {
    if (filter.getFrom() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().startTimestamp().isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));

    return query;
  }
}
