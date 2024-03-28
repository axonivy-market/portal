package com.axonivy.portal.util.filter.operator.caze.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTimestampBeforeOperatorHandler {
  private static CustomTimestampBeforeOperatorHandler instance;

  public static CustomTimestampBeforeOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampBeforeOperatorHandler();
    }
    return instance;
  }

  
  public CaseQuery buildQuery(DashboardFilter filter) {
    if (filter.getFrom() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().timestampField(filter.getField())
        .isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));

    return query;
  }
}
