package com.axonivy.portal.util.filter.operator.caze.name;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class NameIsEmptyOperatorHandler {

  private static NameIsEmptyOperatorHandler instance;

  public static NameIsEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new NameIsEmptyOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsEmptyQuery(@SuppressWarnings("unused") DashboardFilter filter) {
    CaseQuery subQuery = CaseQuery.create();
    subQuery.where().name().isNull().or().name().isLike("");
    return subQuery;
  }

  public CaseQuery buildNotEmptyQuery(@SuppressWarnings("unused") DashboardFilter filter) {
    CaseQuery subQuery = CaseQuery.create();
    subQuery.where().name().isNotNull().and().name().isNotLike("");
    return subQuery;
  }
}
