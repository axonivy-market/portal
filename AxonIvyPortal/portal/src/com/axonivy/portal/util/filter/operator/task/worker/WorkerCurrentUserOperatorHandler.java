package com.axonivy.portal.util.filter.operator.task.worker;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class WorkerCurrentUserOperatorHandler {
  private static WorkerCurrentUserOperatorHandler instance;

  public static WorkerCurrentUserOperatorHandler getInstance() {
    if (instance == null) {
      instance = new WorkerCurrentUserOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery() {
    TaskQuery query = TaskQuery.create();
    query.where().workerId()
        .isEqual(Ivy.session().getSessionUser().getSecurityMemberId());
    return query;
  }
}