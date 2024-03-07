package com.axonivy.portal.util.filter.operator.task.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTimestampEmptyOperatorHandler {
  private static CustomTimestampEmptyOperatorHandler instance;

  public static CustomTimestampEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampEmptyOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildEmptyQuery(DashboardFilter filter) {
    return TaskQuery.create().where().customField().timestampField(filter.getField()).isNull();
  }

  public TaskQuery buildNotEmptyQuery(DashboardFilter filter) {
    return TaskQuery.create().where().customField().timestampField(filter.getField()).isNotNull();
  }
}
