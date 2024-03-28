package com.axonivy.portal.util.filter.operator.task.expirydate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class ExpiryDateBeforeOperatorHandler {
  private static ExpiryDateBeforeOperatorHandler instance;

  public static ExpiryDateBeforeOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ExpiryDateBeforeOperatorHandler();
    }
    return instance;
  }

  
  public TaskQuery buildQuery(DashboardFilter filter) {
    if (filter.getFrom() == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().expiryTimestamp().isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));

    return query;
  }
}
