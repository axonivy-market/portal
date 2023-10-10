package com.axonivy.portal.util.filter.operator.caze.name;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.operator.AbstractFilterOperatorHandler;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class NameIsEmptyOperatorHandler extends AbstractFilterOperatorHandler {

  private static NameIsEmptyOperatorHandler instance;

  public static NameIsEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new NameIsEmptyOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsEmptyQuery(DashboardFilter filter) {
    CaseQuery subQuery = CaseQuery.create();
    subQuery.where().name().isNotNull();
    return subQuery;
  }

  public CaseQuery buildNotEmptyQuery(DashboardFilter filter) {
    CaseQuery subQuery = CaseQuery.create();
    subQuery.where().name().isNotNull();
    return subQuery;
  }
}
