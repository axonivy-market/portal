package com.axonivy.portal.util.filter.operator.task.description;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class DescriptionIsEmptyOperatorHandler {

  private static DescriptionIsEmptyOperatorHandler instance;

  public static DescriptionIsEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DescriptionIsEmptyOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsEmptyQuery(DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().description().isNull().or().description().isLike("");
    return subQuery;
  }

  public TaskQuery buildNotEmptyQuery(DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().description().isNotNull().and().description().isNotLike("");
    return subQuery;
  }
}
