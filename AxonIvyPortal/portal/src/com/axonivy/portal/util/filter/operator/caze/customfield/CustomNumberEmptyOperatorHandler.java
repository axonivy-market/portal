package com.axonivy.portal.util.filter.operator.caze.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomNumberEmptyOperatorHandler {
  private static CustomNumberEmptyOperatorHandler instance;

  public static CustomNumberEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberEmptyOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildEmptyQuery(DashboardFilter filter) {
    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField()).isNull();
    return query;
  }

  public CaseQuery buildNotEmptyQuery(DashboardFilter filter) {
    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField()).isNotNull();
    return query;
  }
}
