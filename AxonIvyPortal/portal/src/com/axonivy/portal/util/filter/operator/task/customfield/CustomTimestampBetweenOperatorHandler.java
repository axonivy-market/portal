package com.axonivy.portal.util.filter.operator.task.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTimestampBetweenOperatorHandler {

  private static CustomTimestampBetweenOperatorHandler instance;

  public static CustomTimestampBetweenOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampBetweenOperatorHandler();
    }
    return instance;
  }

  
  public TaskQuery buildBetweenQuery(DashboardFilter filter) {
    Date from = PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = PortalDateUtils.getEndOfDate(filter.getToDate());

    if (from == null && to == null) {
      return null;
    }

    TaskQuery subQuery = TaskQuery.create();
    if (from != null) {
      subQuery.where().customField().timestampField(filter.getField()).isGreaterOrEqualThan(from);
    }

    if (to != null) {
      subQuery.where().customField().timestampField(filter.getField()).isLowerOrEqualThan(to);
    }
    return subQuery;
  }

  public TaskQuery buildNotBetweenQuery(DashboardFilter filter) {
    Date from = PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = PortalDateUtils.getEndOfDate(filter.getToDate());

    TaskQuery subQuery = TaskQuery.create();
    if (from != null && to != null) {
      subQuery.where().customField().timestampField(filter.getField()).isLowerThan(from).or().customField()
          .timestampField(filter.getField()).isGreaterThan(to);
      return subQuery;
    }

    if (from != null) {
      subQuery.where().customField().timestampField(filter.getField()).isLowerThan(from);
      return subQuery;
    }

    if (to != null) {
      subQuery.where().customField().timestampField(filter.getField()).isGreaterThan(to);
      return subQuery;
    }

    return null;
  }

  public TaskQuery buildBetweenQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampBetweenOperatorHandler
        .getInstance().buildBetweenQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildNotBetweenQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampBetweenOperatorHandler
        .getInstance().buildNotBetweenQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
