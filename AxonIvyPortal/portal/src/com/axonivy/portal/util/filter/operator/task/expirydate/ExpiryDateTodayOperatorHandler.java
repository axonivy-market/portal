package com.axonivy.portal.util.filter.operator.task.expirydate;

import java.util.Date;

import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class ExpiryDateTodayOperatorHandler {

  private static ExpiryDateTodayOperatorHandler instance;

  public static ExpiryDateTodayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ExpiryDateTodayOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery() {
    TaskQuery query = TaskQuery.create();
    Date from = PortalDateUtils.getStartOfToday();
    Date to = PortalDateUtils.getEndOfToday();
    buildQuery(query, from, to);
    return query;
  }

  private void buildQuery(TaskQuery query, Date from, Date to) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().expiryTimestamp().isGreaterOrEqualThan(from);
    subQuery.where().expiryTimestamp().isLowerOrEqualThan(to);
    query.where().and(subQuery);
  }
}
