package com.axonivy.portal.util.filter.operator.caze.finisheddate;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class FinishedDateBeforeOperatorHandler {
  private static FinishedDateBeforeOperatorHandler instance;

  public static FinishedDateBeforeOperatorHandler getInstance() {
    if (instance == null) {
      instance = new FinishedDateBeforeOperatorHandler();
    }
    return instance;
  }

  
  public CaseQuery buildQuery(DashboardFilter filter) {
    if (filter.getFrom() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    Date fromDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    query.where().endTimestamp().isLowerThan(fromDate);

    return query;
  }
}
