package com.axonivy.portal.util.filter.operator.caze.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomNumberLessOperatorHandler {
  private static CustomNumberLessOperatorHandler instance;

  public static CustomNumberLessOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberLessOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildLessQuery(DashboardFilter filter) {
    if (filter.getNumber() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField())
      .isLowerThan(filter.getNumber());
    return query;
  }

  public CaseQuery buildLessOrEqualQuery(DashboardFilter filter) {
    if (filter.getNumber() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField())
      .isLowerOrEqualThan(filter.getNumber());
    return query;
  }
}
