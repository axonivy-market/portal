package com.axonivy.portal.util.filter.operator.caze.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTimestampAfterOperatorHandler {
  private static CustomTimestampAfterOperatorHandler instance;

  public static CustomTimestampAfterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampAfterOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery(DashboardFilter filter) {
    if (filter.getTo() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().timestampField(filter.getField())
        .isGreaterThan(PortalDateUtils.getEndOfDate(filter.getToDate()));

    return query;
  }
}
