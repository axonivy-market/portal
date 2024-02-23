package com.axonivy.portal.util.filter.operator.task.createddate;

import java.util.Date;

import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CreatedDateTodayOperatorHandler {

  private static CreatedDateTodayOperatorHandler instance;

  public static CreatedDateTodayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateTodayOperatorHandler();
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
    subQuery.where().startTimestamp().isGreaterOrEqualThan(from);
    subQuery.where().startTimestamp().isLowerOrEqualThan(to);
    query.where().and(subQuery);
  }
}
