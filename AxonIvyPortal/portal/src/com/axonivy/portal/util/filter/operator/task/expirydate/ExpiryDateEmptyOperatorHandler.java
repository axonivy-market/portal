package com.axonivy.portal.util.filter.operator.task.expirydate;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class ExpiryDateEmptyOperatorHandler {
  private static ExpiryDateEmptyOperatorHandler instance;

  public static ExpiryDateEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ExpiryDateEmptyOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildEmptyQuery() {
    return TaskQuery.create().where().expiryTimestamp().isNull();
  }

  public TaskQuery buildNotEmptyQuery() {
    return TaskQuery.create().where().expiryTimestamp().isNotNull();
  }
}