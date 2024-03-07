package com.axonivy.portal.util.filter.operator.task.createddate;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CreatedDateIsOperatorHandler {
  private static CreatedDateIsOperatorHandler instance;

  public static CreatedDateIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateIsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return TaskQuery.create().where().startTimestamp()
        .isGreaterOrEqualThan(PortalDateUtils.getStartOfDate(filter.getFromDate())).and().startTimestamp()
        .isLowerOrEqualThan(PortalDateUtils.getEndOfDate(filter.getFromDate()));
  }

  public TaskQuery buildIsNotQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return TaskQuery.create().where().startTimestamp().isGreaterThan(PortalDateUtils.getEndOfDate(filter.getFromDate()))
        .or().startTimestamp().isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));
  }
}
