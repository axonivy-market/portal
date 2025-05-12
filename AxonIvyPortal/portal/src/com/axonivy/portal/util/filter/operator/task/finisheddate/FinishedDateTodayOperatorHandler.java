package com.axonivy.portal.util.filter.operator.task.finisheddate;

import java.util.Date;

import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class FinishedDateTodayOperatorHandler {

  private static FinishedDateTodayOperatorHandler instance;

  public static FinishedDateTodayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new FinishedDateTodayOperatorHandler();
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
    subQuery.where().endTimestamp().isGreaterOrEqualThan(from);
    subQuery.where().endTimestamp().isLowerOrEqualThan(to);
    query.where().and(subQuery);
  }
}
