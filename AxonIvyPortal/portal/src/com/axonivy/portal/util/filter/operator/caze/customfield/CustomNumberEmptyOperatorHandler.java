package com.axonivy.portal.util.filter.operator.caze.customfield;

import static com.axonivy.portal.util.CaseQueryUtils.initCaseQuery;
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
    CaseQuery query = initCaseQuery(filter.getFilterType());
    query.where().customField().numberField(filter.getField()).isNull();
    return query;
  }

  public CaseQuery buildNotEmptyQuery(DashboardFilter filter) {
    CaseQuery query = initCaseQuery(filter.getFilterType());
    query.where().customField().numberField(filter.getField()).isNotNull();
    return query;
  }
}
