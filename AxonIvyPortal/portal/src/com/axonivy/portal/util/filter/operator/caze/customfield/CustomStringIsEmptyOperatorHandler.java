package com.axonivy.portal.util.filter.operator.caze.customfield;

import static com.axonivy.portal.util.CaseQueryUtils.initCaseQuery;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomStringIsEmptyOperatorHandler {

  private static CustomStringIsEmptyOperatorHandler instance;

  public static CustomStringIsEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringIsEmptyOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsEmptyQuery(DashboardFilter filter) {
    CaseQuery subQuery = initCaseQuery(filter.getFilterType());
    subQuery.where().customField().stringField(filter.getField()).isNull().or().customField()
        .stringField(filter.getField()).isLike("");
    return subQuery;
  }

  public CaseQuery buildNotEmptyQuery(DashboardFilter filter) {
    CaseQuery subQuery = initCaseQuery(filter.getFilterType());
    subQuery.where().customField().stringField(filter.getField()).isNotNull().and().customField()
        .stringField(filter.getField()).isNotLike("");
    return subQuery;
  }
}
