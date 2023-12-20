package com.axonivy.portal.util.filter.operator.caze.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomNumberGreaterOperatorHandler {
  private static CustomNumberGreaterOperatorHandler instance;

  public static CustomNumberGreaterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberGreaterOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildGreaterQuery(DashboardFilter filter) {
    if (filter.getNumber() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField())
      .isGreaterThan(filter.getNumber());
    return query;
  }

  public CaseQuery buildGreaterOrEqualQuery(DashboardFilter filter) {
    if (filter.getNumber() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField())
      .isGreaterOrEqualThan(filter.getNumber());
    return query;
  }
}
