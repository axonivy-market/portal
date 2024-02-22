package com.axonivy.portal.util.filter.operator.task.createddate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CreatedDateBeforeOperatorHandler {
  private static CreatedDateBeforeOperatorHandler instance;

  public static CreatedDateBeforeOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateBeforeOperatorHandler();
    }
    return instance;
  }

  
  public TaskQuery buildQuery(DashboardFilter filter) {
    if (filter.getFrom() == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().startTimestamp().isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));

    return query;
  }
}
