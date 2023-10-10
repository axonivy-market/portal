package com.axonivy.portal.util.filter.operator.caze.createddate;

import java.util.Date;

import com.axonivy.portal.util.PortalDateUtils;
import com.axonivy.portal.util.filter.operator.AbstractFilterOperatorHandler;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatedDateYesterdayOperatorHandler extends AbstractFilterOperatorHandler {
  private static CreatedDateYesterdayOperatorHandler instance;

  public static CreatedDateYesterdayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateYesterdayOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery() {
    CaseQuery query = CaseQuery.create();
    Date from = PortalDateUtils.getStartOfYesterday();
    Date to = PortalDateUtils.getEndOfYesterday();
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
