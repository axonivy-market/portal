package com.axonivy.portal.util.filter.operator.task.expirydate;

import java.util.Date;

import com.axonivy.portal.components.service.DateTimeGlobalSettingService;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class ExpiryDateIsOperatorHandler {
  private static ExpiryDateIsOperatorHandler instance;

  public static ExpiryDateIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ExpiryDateIsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsQuery(DashboardFilter filter) {
    
    if (filter.getFromDate() == null) {
      return null;
    }

    Date fromDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date endDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getFromDate()) : PortalDateUtils.getEndOfDate(filter.getFromDate());

    return TaskQuery.create().where().expiryTimestamp().isGreaterOrEqualThan(fromDate)
        .and().expiryTimestamp().isLowerOrEqualThan(endDate);
  }

  public TaskQuery buildIsNotQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }
    
    Date fromDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date endDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getFromDate()) : PortalDateUtils.getEndOfDate(filter.getFromDate());

    return TaskQuery.create().where().expiryTimestamp().isGreaterThan(endDate)
        .or().expiryTimestamp().isLowerThan(fromDate);
  }
}