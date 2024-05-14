package com.axonivy.portal.util.filter.operator.task.createddate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CreatedDateAfterOperatorHandler {
  private static CreatedDateAfterOperatorHandler instance;

  public static CreatedDateAfterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateAfterOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery(DashboardFilter filter) {
    if (filter.getTo() == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().startTimestamp().isGreaterThan(PortalDateUtils.getEndOfDate(filter.getToDate()));

    return query;
  }
}
