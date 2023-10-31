package com.axonivy.portal.util.filter.operator.caze.finisheddate;

import java.util.Date;

import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class FinishedDateTodayOperatorHandler {

  private static FinishedDateTodayOperatorHandler instance;

  public static FinishedDateTodayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new FinishedDateTodayOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery() {
    CaseQuery query = CaseQuery.create();
    Date from = PortalDateUtils.getStartOfToday();
    Date to = PortalDateUtils.getEndOfToday();
    buildQuery(query, from, to);
    return query;
  }

  private void buildQuery(CaseQuery query, Date from, Date to) {;
    CaseQuery subQuery = CaseQuery.create();
    subQuery.where().endTimestamp().isGreaterOrEqualThan(from);
    subQuery.where().endTimestamp().isLowerOrEqualThan(to);
    query.where().and(subQuery);
  }
}
