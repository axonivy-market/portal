package com.axonivy.portal.util.filter.operator.task.responsible;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class ReponsibleCurrentUserOperatorHandler {
  private static ReponsibleCurrentUserOperatorHandler instance;

  public static ReponsibleCurrentUserOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ReponsibleCurrentUserOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery() {
    TaskQuery query = TaskQuery.create();
    query.where().responsibleName()
        .isEqual(Ivy.session().getSessionUser().getMemberName());
    return query;
  }
}