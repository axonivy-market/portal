package com.axonivy.portal.util.filter.operator.task.finisheddate;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class FinishedDateEmptyOperatorHandler {
  private static FinishedDateEmptyOperatorHandler instance;

  public static FinishedDateEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new FinishedDateEmptyOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildEmptyQuery() {
    return TaskQuery.create().where().endTimestamp().isNull();
  }

  public TaskQuery buildNotEmptyQuery() {
    return TaskQuery.create().where().endTimestamp().isNotNull();
  }
}