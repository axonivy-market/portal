package com.axonivy.portal.util.filter.operator.task.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomNumberEmptyOperatorHandler {
  private static CustomNumberEmptyOperatorHandler instance;

  public static CustomNumberEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberEmptyOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildEmptyQuery(DashboardFilter filter) {
    TaskQuery query = TaskQuery.create();
    query.where().customField().numberField(filter.getField()).isNull();
    return query;
  }

  public TaskQuery buildNotEmptyQuery(DashboardFilter filter) {
    TaskQuery query = TaskQuery.create();
    query.where().customField().numberField(filter.getField()).isNotNull();
    return query;
  }

  public TaskQuery buildEmptyQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberEmptyOperatorHandler
        .getInstance().buildEmptyQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildNotEmptyQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberEmptyOperatorHandler
        .getInstance().buildNotEmptyQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
