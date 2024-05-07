package com.axonivy.portal.util.filter.operator.task.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomStringIsEmptyOperatorHandler {

  private static CustomStringIsEmptyOperatorHandler instance;

  public static CustomStringIsEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringIsEmptyOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsEmptyQuery(DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().customField().stringField(filter.getField()).isNull().or().customField()
        .stringField(filter.getField()).isLike("");
    return subQuery;
  }

  public TaskQuery buildNotEmptyQuery(DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().customField().stringField(filter.getField()).isNotNull().and().customField()
        .stringField(filter.getField()).isNotLike("");
    return subQuery;
  }

  public TaskQuery buildIsEmptyQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringIsEmptyOperatorHandler
        .getInstance().buildIsEmptyQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildNotEmptyQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringIsEmptyOperatorHandler
        .getInstance().buildNotEmptyQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
