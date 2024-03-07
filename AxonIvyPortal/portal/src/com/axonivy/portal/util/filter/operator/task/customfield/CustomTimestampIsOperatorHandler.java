package com.axonivy.portal.util.filter.operator.task.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTimestampIsOperatorHandler {
  private static CustomTimestampIsOperatorHandler instance;

  public static CustomTimestampIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampIsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return TaskQuery.create().where().customField().timestampField(filter.getField())
        .isGreaterOrEqualThan(PortalDateUtils.getStartOfDate(filter.getFromDate()))
        .and().customField().timestampField(filter.getField()).isLowerOrEqualThan(PortalDateUtils.getEndOfDate(filter.getFromDate()));
  }

  public TaskQuery buildIsNotQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return TaskQuery.create().where().customField().timestampField(filter.getField())
        .isGreaterThan(PortalDateUtils.getEndOfDate(filter.getFromDate()))
        .or().customField().timestampField(filter.getField()).isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));
  }
}
