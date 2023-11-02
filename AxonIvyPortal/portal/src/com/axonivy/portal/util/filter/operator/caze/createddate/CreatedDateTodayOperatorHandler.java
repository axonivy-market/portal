package com.axonivy.portal.util.filter.operator.caze.createddate;

import java.util.Date;

import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatedDateTodayOperatorHandler {

  private static CreatedDateTodayOperatorHandler instance;

  public static CreatedDateTodayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateTodayOperatorHandler();
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
    subQuery.where().startTimestamp().isGreaterOrEqualThan(from);
    subQuery.where().startTimestamp().isLowerOrEqualThan(to);
    query.where().and(subQuery);
  }
}
