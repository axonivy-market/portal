package com.axonivy.portal.util.filter.operator.task.createddate;

import java.util.Date;

import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CreatedDateYesterdayOperatorHandler {
  private static CreatedDateYesterdayOperatorHandler instance;

  public static CreatedDateYesterdayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateYesterdayOperatorHandler();
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
    subQuery.where().startTimestamp().isGreaterOrEqualThan(from);
    subQuery.where().startTimestamp().isLowerOrEqualThan(to);
    query.where().and(subQuery);
  }
}
