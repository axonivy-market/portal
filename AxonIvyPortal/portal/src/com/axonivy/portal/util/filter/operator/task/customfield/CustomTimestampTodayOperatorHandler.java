package com.axonivy.portal.util.filter.operator.task.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTimestampTodayOperatorHandler {

  private static CustomTimestampTodayOperatorHandler instance;

  public static CustomTimestampTodayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampTodayOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery(DashboardFilter filter) {
    TaskQuery query = TaskQuery.create();
    Date from = PortalDateUtils.getStartOfToday();
    Date to = PortalDateUtils.getEndOfToday();
    buildQuery(query, from, to, filter);
    return query;
  }

  private void buildQuery(TaskQuery query, Date from, Date to, DashboardFilter filter) {
    TaskQuery subQuery = TaskQuery.create();
    subQuery.where().customField().timestampField(filter.getField()).isGreaterOrEqualThan(from);
    subQuery.where().customField().timestampField(filter.getField()).isLowerOrEqualThan(to);
    query.where().and(subQuery);
  }

  public TaskQuery buildQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampTodayOperatorHandler
        .getInstance().buildQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
