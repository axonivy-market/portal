package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;

public class DatetimeIsOperatorHandler {
  private static DatetimeIsOperatorHandler instance;

  public static DatetimeIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DatetimeIsOperatorHandler();
    }
    return instance;
  }
  
  public String buildIsFilter(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    String field = filter.isCustomDateField() ? StatisticConstants.CUSTOM_TIMESTAMP + filter.getField() : filter.getField();
    Date from = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getFromDate()) : PortalDateUtils.getEndOfDate(filter.getFromDate());

    sb.append(field).append(PortalConstants.COLON);
    sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    sb.append(PortalConstants.COMMA).append(field).append(PortalConstants.COLON);
    sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
    return sb.toString();
  }
  
  public String buildIsNotFilter(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }
    
    StringBuilder sb = new StringBuilder();
    Date from = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getFromDate()) : PortalDateUtils.getEndOfDate(filter.getFromDate());
    String field = filter.isCustomDateField() ? StatisticConstants.CUSTOM_TIMESTAMP + filter.getField() : filter.getField();

    sb.append(field).append(PortalConstants.COLON);
    sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
    sb.append(PortalConstants.COMMA).append(field).append(PortalConstants.COLON);
    sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    return sb.toString();
  }
}
