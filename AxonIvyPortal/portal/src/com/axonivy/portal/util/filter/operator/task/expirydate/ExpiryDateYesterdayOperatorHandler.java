package com.axonivy.portal.util.filter.operator.task.expirydate;

import java.util.Date;

import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class ExpiryDateYesterdayOperatorHandler {
  private static ExpiryDateYesterdayOperatorHandler instance;

  public static ExpiryDateYesterdayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ExpiryDateYesterdayOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery() {
    TaskQuery query = TaskQuery.create();
    Date from = PortalDateUtils.getStartOfYesterday();
    Date to = PortalDateUtils.getEndOfYesterday();
    buildQuery(query, from, to);
    return query;
  }

  private void buildQuery(TaskQuery query, Date from, Date to) {;
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().expiryTimestamp().isGreaterOrEqualThan(from);
    subQuery.where().expiryTimestamp().isLowerOrEqualThan(to);
    query.where().and(subQuery);
  }
}