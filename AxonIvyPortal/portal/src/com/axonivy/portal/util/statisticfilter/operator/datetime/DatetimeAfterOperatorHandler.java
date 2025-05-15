package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;

public class DatetimeAfterOperatorHandler {
  private static DatetimeAfterOperatorHandler instance;

  public static DatetimeAfterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DatetimeAfterOperatorHandler();
    }
    return instance;
  }

  public String buildFilter(DashboardFilter filter) {
    if (filter.getTo() == null) {
      return "";
    }
    
    StringBuilder sb = new StringBuilder();
    Date endDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getToDate()) : PortalDateUtils.getEndOfDate(filter.getToDate());
    sb.append(filter.isCustomDateField() ? StatisticConstants.CUSTOM_TIMESTAMP + filter.getField() : filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(endDate));
    return sb.toString();
  }
}
