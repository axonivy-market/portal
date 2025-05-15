package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;

public class DatetimeBeforeOperatorHandler {
  private static DatetimeBeforeOperatorHandler instance;

  public static DatetimeBeforeOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DatetimeBeforeOperatorHandler();
    }
    return instance;
  }

  public String buildFilter(DashboardFilter filter) {
    if (filter.getFrom() == null) {
      return "";
    }
    
    StringBuilder sb = new StringBuilder();
    Date fromDate = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    sb.append(filter.isCustomDateField() ? StatisticConstants.CUSTOM_TIMESTAMP + filter.getField() : filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.LESS_THAN).append(PortalDateUtils.toStringIso8601Format(fromDate));
    return sb.toString();
  }

}
