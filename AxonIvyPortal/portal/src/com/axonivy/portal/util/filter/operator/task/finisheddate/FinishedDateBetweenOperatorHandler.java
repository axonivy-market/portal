package com.axonivy.portal.util.filter.operator.task.finisheddate;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class FinishedDateBetweenOperatorHandler {

  private static FinishedDateBetweenOperatorHandler instance;

  public static FinishedDateBetweenOperatorHandler getInstance() {
    if (instance == null) {
      instance = new FinishedDateBetweenOperatorHandler();
    }
    return instance;
  }

  
  public TaskQuery buildBetweenQuery(DashboardFilter filter) {
    Date from = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getToDate()) : PortalDateUtils.getEndOfDate(filter.getToDate());

    if (from == null && to == null) {
      return null;
    }

    TaskQuery subQuery = TaskQuery.create();
    if (from != null) {
      subQuery.where().endTimestamp().isGreaterOrEqualThan(from);
    }

    if (to != null) {
      subQuery.where().endTimestamp().isLowerOrEqualThan(to);
    }
    return subQuery;
  }

  public TaskQuery buildNotBetweenQuery(DashboardFilter filter) {
    Date from = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getToDate()) : PortalDateUtils.getEndOfDate(filter.getToDate());

    TaskQuery subQuery = TaskQuery.create();
    if (from != null && to != null) {
      subQuery.where().endTimestamp().isLowerThan(from).or().endTimestamp().isGreaterThan(to);
      return subQuery;
    }

    if (from != null) {
      subQuery.where().endTimestamp().isLowerThan(from);
      return subQuery;
    }

    if (to != null) {
      subQuery.where().endTimestamp().isGreaterThan(to);
      return subQuery;
    }

    return null;
  }
}