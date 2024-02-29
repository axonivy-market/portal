package com.axonivy.portal.util.filter.operator.task.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTimestampAfterOperatorHandler {
  private static CustomTimestampAfterOperatorHandler instance;

  public static CustomTimestampAfterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampAfterOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery(DashboardFilter filter) {
    if (filter.getTo() == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().customField().timestampField(filter.getField())
        .isGreaterThan(PortalDateUtils.getEndOfDate(filter.getToDate()));

    return query;
  }

  public TaskQuery buildQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampAfterOperatorHandler
        .getInstance().buildQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
