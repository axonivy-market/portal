package com.axonivy.portal.util.filter.operator.task.expirydate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class ExpiryDateIsOperatorHandler {
  private static ExpiryDateIsOperatorHandler instance;

  public static ExpiryDateIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ExpiryDateIsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return TaskQuery.create().where().expiryTimestamp().isGreaterOrEqualThan(PortalDateUtils.getStartOfDate(filter.getFromDate()))
        .and().expiryTimestamp().isLowerOrEqualThan(PortalDateUtils.getEndOfDate(filter.getFromDate()));
  }

  public TaskQuery buildIsNotQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return TaskQuery.create().where().expiryTimestamp().isGreaterThan(PortalDateUtils.getEndOfDate(filter.getFromDate()))
        .or().expiryTimestamp().isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));
  }
}