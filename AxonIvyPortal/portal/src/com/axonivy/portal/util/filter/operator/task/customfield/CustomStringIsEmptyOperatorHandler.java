package com.axonivy.portal.util.filter.operator.task.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomStringIsEmptyOperatorHandler {

  private static CustomStringIsEmptyOperatorHandler instance;

  public static CustomStringIsEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringIsEmptyOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsEmptyQuery(DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().customField().stringField(filter.getField()).isNull().or().customField()
        .stringField(filter.getField()).isLike("");
    return subQuery;
  }

  public TaskQuery buildNotEmptyQuery(DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().customField().stringField(filter.getField()).isNotNull().and().customField()
        .stringField(filter.getField()).isNotLike("");
    return subQuery;
  }
}
