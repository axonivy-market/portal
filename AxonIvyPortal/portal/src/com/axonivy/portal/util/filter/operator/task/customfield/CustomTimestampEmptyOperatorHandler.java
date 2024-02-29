package com.axonivy.portal.util.filter.operator.task.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTimestampEmptyOperatorHandler {
  private static CustomTimestampEmptyOperatorHandler instance;

  public static CustomTimestampEmptyOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampEmptyOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildEmptyQuery(DashboardFilter filter) {
    return TaskQuery.create().where().customField().timestampField(filter.getField()).isNull();
  }

  public TaskQuery buildNotEmptyQuery(DashboardFilter filter) {
    return TaskQuery.create().where().customField().timestampField(filter.getField()).isNotNull();
  }

  public TaskQuery buildEmptyQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampEmptyOperatorHandler
        .getInstance().buildEmptyQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildNotEmptyQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampEmptyOperatorHandler
        .getInstance().buildNotEmptyQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
