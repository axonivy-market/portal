package com.axonivy.portal.util.filter.operator.caze.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomNumberBetweenOperatorHandler {
  private static CustomNumberBetweenOperatorHandler instance;

  public static CustomNumberBetweenOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberBetweenOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildEqualQuery(DashboardFilter filter) {
    Number from = filter.getFromNumber();
    Number to = filter.getToNumber();

    CaseQuery query = CaseQuery.create();

    if (from != null && to != null) {
      query.where().customField().numberField(filter.getField()).isLowerOrEqualThan(to)
        .and().customField().numberField(filter.getField()).isGreaterOrEqualThan(from);
      return query;
    }

    if (from != null) {
      query.where().customField().numberField(filter.getField()).isGreaterOrEqualThan(from);
      return query;
    }

    if (to != null) {
      query.where().customField().numberField(filter.getField()).isLowerOrEqualThan(to);
      return query;
    }

    return null;
  }

  public CaseQuery buildNotEqualQuery(DashboardFilter filter) {
    Number from = filter.getFromNumber();
    Number to = filter.getToNumber();

    CaseQuery query = CaseQuery.create();

    if (from != null && to != null) {
      query.where().customField().numberField(filter.getField()).isLowerOrEqualThan(from)
        .or().customField().numberField(filter.getField()).isGreaterOrEqualThan(to);
      return query;
    }

    if (from != null) {
      query.where().customField().numberField(filter.getField()).isLowerOrEqualThan(from);
      return query;
    }

    if (to != null) {
      query.where().customField().numberField(filter.getField()).isGreaterOrEqualThan(to);
      return query;
    }

    return null;
  }
}
