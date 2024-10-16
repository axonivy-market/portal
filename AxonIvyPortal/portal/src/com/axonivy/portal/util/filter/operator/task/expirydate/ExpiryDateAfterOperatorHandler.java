package com.axonivy.portal.util.filter.operator.task.expirydate;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class ExpiryDateAfterOperatorHandler {
  private static ExpiryDateAfterOperatorHandler instance;

  public static ExpiryDateAfterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ExpiryDateAfterOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery(DashboardFilter filter) {
    if (filter.getTo() == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    Date endDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getToDate()) : PortalDateUtils.getEndOfDate(filter.getToDate());
    query.where().expiryTimestamp().isGreaterThan(endDate);

    return query;
  }
}