package com.axonivy.portal.util.filter.operator.task.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTimestampIsOperatorHandler {
  private static CustomTimestampIsOperatorHandler instance;

  public static CustomTimestampIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampIsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }
    Date fromDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date endDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getFromDate()) : PortalDateUtils.getEndOfDate(filter.getFromDate());

    return TaskQuery.create().where().customField().timestampField(filter.getField())
        .isGreaterOrEqualThan(fromDate)
        .and().customField().timestampField(filter.getField()).isLowerOrEqualThan(endDate);
  }

  public TaskQuery buildIsNotQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }
    Date fromDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date endDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getFromDate()) : PortalDateUtils.getEndOfDate(filter.getFromDate());

    return TaskQuery.create().where().customField().timestampField(filter.getField())
        .isGreaterThan(endDate)
        .or().customField().timestampField(filter.getField()).isLowerThan(fromDate);
  }

  public TaskQuery buildIsQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampIsOperatorHandler
        .getInstance().buildIsQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildIsNotQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampIsOperatorHandler
        .getInstance().buildIsNotQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
