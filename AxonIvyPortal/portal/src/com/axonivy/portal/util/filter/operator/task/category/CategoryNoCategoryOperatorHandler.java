package com.axonivy.portal.util.filter.operator.task.category;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CategoryNoCategoryOperatorHandler {
  private static CategoryNoCategoryOperatorHandler instance;

  public static CategoryNoCategoryOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CategoryNoCategoryOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery() {
    TaskQuery query = TaskQuery.create();
    return query.where().category().isNull().or().category().isEqual("");
  }
}