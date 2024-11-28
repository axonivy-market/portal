package com.axonivy.portal.util.filter.operator.caze.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTimestampAfterOperatorHandler {
  private static CustomTimestampAfterOperatorHandler instance;

  public static CustomTimestampAfterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampAfterOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery(DashboardFilter filter) {
    if (filter.getTo() == null) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    Date endDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getToDate()) : PortalDateUtils.getEndOfDate(filter.getToDate());
    query.where().customField().timestampField(filter.getField())
        .isGreaterThan(endDate);

    return query;
  }
}
