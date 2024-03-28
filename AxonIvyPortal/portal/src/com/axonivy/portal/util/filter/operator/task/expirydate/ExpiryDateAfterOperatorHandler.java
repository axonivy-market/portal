package com.axonivy.portal.util.filter.operator.task.expirydate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class ExpiryDateAfterOperatorHandler {
  private static ExpiryDateAfterOperatorHandler instance;

  public static ExpiryDateAfterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ExpiryDateAfterOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery(DashboardFilter filter) {
    if (filter.getTo() == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().expiryTimestamp().isGreaterThan(PortalDateUtils.getEndOfDate(filter.getToDate()));

    return query;
  }
}