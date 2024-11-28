package com.axonivy.portal.util.filter.operator.task.name;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class NameIsEmptyOperatorHandler {

  private static NameIsEmptyOperatorHandler instance;

  public static NameIsEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new NameIsEmptyOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsEmptyQuery(@SuppressWarnings("unused") DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().name().isNull().or().name().isLike("");
    return subQuery;
  }

  public TaskQuery buildNotEmptyQuery(@SuppressWarnings("unused") DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().name().isNotNull().and().name().isNotLike("");
    return subQuery;
  }
}
