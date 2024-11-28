package com.axonivy.portal.util.filter.operator.caze.finisheddate;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class FinishedDateAfterOperatorHandler {
  private static FinishedDateAfterOperatorHandler instance;

  public static FinishedDateAfterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new FinishedDateAfterOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery(DashboardFilter filter) {
    if (filter.getTo() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    Date endDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getToDate()) : PortalDateUtils.getEndOfDate(filter.getToDate());
    query.where().endTimestamp().isGreaterThan(endDate);

    return query;
  }
}