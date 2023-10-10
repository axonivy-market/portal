package com.axonivy.portal.util.filter.operator.caze.createddate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.operator.AbstractFilterOperatorHandler;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatedDateBeforeOperatorHandler extends AbstractFilterOperatorHandler {
  private static CreatedDateBeforeOperatorHandler instance;

  public static CreatedDateBeforeOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateBeforeOperatorHandler();
    }
    return instance;
  }

  
  public CaseQuery buildQuery(DashboardFilter filter) {
    if (filter.getTo() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().startTimestamp().isLowerOrEqualThan(filter.getTo());

    return query;
  }
}
