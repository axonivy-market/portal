package com.axonivy.portal.util.filter.operator.caze.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTimestampTodayOperatorHandler {

  private static CustomTimestampTodayOperatorHandler instance;

  public static CustomTimestampTodayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampTodayOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery(DashboardFilter filter) {
    CaseQuery query = CaseQuery.create();
    Date from = PortalDateUtils.getStartOfToday();
    Date to = PortalDateUtils.getEndOfToday();
    buildQuery(query, from, to, filter);
    return query;
  }

  private void buildQuery(CaseQuery query, Date from, Date to, DashboardFilter filter) {
    CaseQuery subQuery = CaseQuery.create();
    subQuery.where().customField().timestampField(filter.getField()).isGreaterOrEqualThan(from);
    subQuery.where().customField().timestampField(filter.getField()).isLowerOrEqualThan(to);
    query.where().and(subQuery);
  }
}
