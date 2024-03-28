package com.axonivy.portal.util.filter.operator.task.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTimestampYesterdayOperatorHandler {
  private static CustomTimestampYesterdayOperatorHandler instance;

  public static CustomTimestampYesterdayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampYesterdayOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery(DashboardFilter filter) {
    TaskQuery query = TaskQuery.create();
    Date from = PortalDateUtils.getStartOfYesterday();
    Date to = PortalDateUtils.getEndOfYesterday();
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
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampYesterdayOperatorHandler
        .getInstance().buildQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
