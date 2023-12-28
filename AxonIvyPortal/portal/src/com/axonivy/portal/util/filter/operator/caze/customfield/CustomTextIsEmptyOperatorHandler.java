package com.axonivy.portal.util.filter.operator.caze.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTextIsEmptyOperatorHandler {

  private static CustomTextIsEmptyOperatorHandler instance;

  public static CustomTextIsEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTextIsEmptyOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsEmptyQuery(DashboardFilter filter) {
    CaseQuery subQuery = CaseQuery.create();
    subQuery.where().customField().textField(filter.getField()).isNull().or().customField()
        .textField(filter.getField()).isLike("");
    return subQuery;
  }

  public CaseQuery buildNotEmptyQuery(DashboardFilter filter) {
    CaseQuery subQuery = CaseQuery.create();
    subQuery.where().customField().textField(filter.getField()).isNotNull().and().customField()
        .textField(filter.getField()).isNotLike("");
    return subQuery;
  }
}
