package com.axonivy.portal.util.filter.operator.caze.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTimestampYesterdayOperatorHandler {
  private static CustomTimestampYesterdayOperatorHandler instance;

  public static CustomTimestampYesterdayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampYesterdayOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery(DashboardFilter filter) {
    CaseQuery query = CaseQuery.create();
    Date from = PortalDateUtils.getStartOfYesterday();
    Date to = PortalDateUtils.getEndOfYesterday();
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
