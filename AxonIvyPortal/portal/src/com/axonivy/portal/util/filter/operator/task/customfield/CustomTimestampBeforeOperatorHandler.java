package com.axonivy.portal.util.filter.operator.task.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTimestampBeforeOperatorHandler {
  private static CustomTimestampBeforeOperatorHandler instance;

  public static CustomTimestampBeforeOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampBeforeOperatorHandler();
    }
    return instance;
  }

  
  public TaskQuery buildQuery(DashboardFilter filter) {
    if (filter.getFrom() == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().customField().timestampField(filter.getField())
        .isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));

    return query;
  }
}
