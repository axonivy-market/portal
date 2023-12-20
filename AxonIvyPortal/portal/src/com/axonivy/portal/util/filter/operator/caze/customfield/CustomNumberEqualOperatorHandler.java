package com.axonivy.portal.util.filter.operator.caze.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomNumberEqualOperatorHandler {
  private static CustomNumberEqualOperatorHandler instance;

  public static CustomNumberEqualOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberEqualOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildEqualQuery(DashboardFilter filter) {
    if (filter.getNumber() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField())
      .isEqual(filter.getNumber());
    return query;
  }

  public CaseQuery buildNotEqualQuery(DashboardFilter filter) {
    if (filter.getNumber() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField())
      .isNotEqual(filter.getNumber());
    return query;
  }
}
