package com.axonivy.portal.util.filter.operator.caze.creator;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatorCurrentUserOperatorHandler {
  private static CreatorCurrentUserOperatorHandler instance;

  public static CreatorCurrentUserOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatorCurrentUserOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery() {
    CaseQuery query = CaseQuery.create();
    query.where().creatorUserName().isEqual(Ivy.session().getSessionUser().getName());
    return query;
  }
}