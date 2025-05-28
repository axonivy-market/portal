package com.axonivy.portal.util.filter.operator.task.finisheddate;

import java.util.Date;

import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class FinishedDateYesterdayOperatorHandler {
  private static FinishedDateYesterdayOperatorHandler instance;

  public static FinishedDateYesterdayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new FinishedDateYesterdayOperatorHandler();
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

  private void buildQuery(TaskQuery query, Date from, Date to) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().endTimestamp().isGreaterOrEqualThan(from);
    subQuery.where().endTimestamp().isLowerOrEqualThan(to);
    query.where().and(subQuery);
  }
}