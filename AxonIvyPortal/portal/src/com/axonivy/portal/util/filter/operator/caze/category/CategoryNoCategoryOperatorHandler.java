package com.axonivy.portal.util.filter.operator.caze.category;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CategoryNoCategoryOperatorHandler {
  private static CategoryNoCategoryOperatorHandler instance;

  public static CategoryNoCategoryOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CategoryNoCategoryOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery() {
    CaseQuery query = CaseQuery.create();
    return query.where().category().isNull().or().category().isEqual("");
  }
}