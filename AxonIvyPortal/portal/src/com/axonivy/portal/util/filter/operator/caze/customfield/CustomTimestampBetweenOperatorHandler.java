package com.axonivy.portal.util.filter.operator.caze.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTimestampBetweenOperatorHandler {

  private static CustomTimestampBetweenOperatorHandler instance;

  public static CustomTimestampBetweenOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampBetweenOperatorHandler();
    }
    return instance;
  }

  
  public CaseQuery buildBetweenQuery(DashboardFilter filter) {
    Date from = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getToDate()) : PortalDateUtils.getEndOfDate(filter.getToDate());

    if (from == null && to == null) {
      return null;
    }

    CaseQuery subQuery = CaseQuery.create();
    if (from != null) {
      subQuery.where().customField().timestampField(filter.getField()).isGreaterOrEqualThan(from);
    }

    if (to != null) {
      subQuery.where().customField().timestampField(filter.getField()).isLowerOrEqualThan(to);
    }
    return subQuery;
  }

  public CaseQuery buildNotBetweenQuery(DashboardFilter filter) {
    Date from = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getToDate()) : PortalDateUtils.getEndOfDate(filter.getToDate());

    CaseQuery subQuery = CaseQuery.create();
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
}
