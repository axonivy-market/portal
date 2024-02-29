package com.axonivy.portal.util.filter.operator.task.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTextIsEmptyOperatorHandler {

  private static CustomTextIsEmptyOperatorHandler instance;

  public static CustomTextIsEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTextIsEmptyOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsEmptyQuery(DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().customField().textField(filter.getField()).isNull().or().customField()
        .textField(filter.getField()).isLike("");
    return subQuery;
  }

  public TaskQuery buildNotEmptyQuery(DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().customField().textField(filter.getField()).isNotNull().and().customField()
        .textField(filter.getField()).isNotLike("");
    return subQuery;
  }

  public TaskQuery buildIsEmptyQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextIsEmptyOperatorHandler
        .getInstance().buildIsEmptyQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildNotEmptyQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextIsEmptyOperatorHandler
        .getInstance().buildNotEmptyQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
