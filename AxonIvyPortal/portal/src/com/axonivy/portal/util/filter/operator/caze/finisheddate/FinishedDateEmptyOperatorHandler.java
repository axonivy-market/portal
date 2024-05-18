package com.axonivy.portal.util.filter.operator.caze.finisheddate;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class FinishedDateEmptyOperatorHandler {
  private static FinishedDateEmptyOperatorHandler instance;

  public static FinishedDateEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new FinishedDateEmptyOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildEmptyQuery() {
    return CaseQuery.create().where().endTimestamp().isNull();
  }

  public CaseQuery buildNotEmptyQuery() {
    return CaseQuery.create().where().endTimestamp().isNotNull();
  }
}