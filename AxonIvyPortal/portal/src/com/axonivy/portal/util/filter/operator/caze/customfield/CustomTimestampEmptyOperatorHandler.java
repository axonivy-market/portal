package com.axonivy.portal.util.filter.operator.caze.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTimestampEmptyOperatorHandler {
  private static CustomTimestampEmptyOperatorHandler instance;

  public static CustomTimestampEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampEmptyOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildEmptyQuery(DashboardFilter filter) {
    return CaseQuery.create().where().customField().timestampField(filter.getField()).isNull();
  }

  public CaseQuery buildNotEmptyQuery(DashboardFilter filter) {
    return CaseQuery.create().where().customField().timestampField(filter.getField()).isNotNull();
  }
}
