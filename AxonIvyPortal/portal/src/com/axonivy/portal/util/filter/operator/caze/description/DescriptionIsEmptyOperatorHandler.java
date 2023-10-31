package com.axonivy.portal.util.filter.operator.caze.description;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class DescriptionIsEmptyOperatorHandler {

  private static DescriptionIsEmptyOperatorHandler instance;

  public static DescriptionIsEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DescriptionIsEmptyOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsEmptyQuery(DashboardFilter filter) {
    CaseQuery subQuery = CaseQuery.create();
    subQuery.where().description().isNull();
    return subQuery;
  }

  public CaseQuery buildNotEmptyQuery(DashboardFilter filter) {
    CaseQuery subQuery = CaseQuery.create();
    subQuery.where().description().isNotNull();
    return subQuery;
  }
}
