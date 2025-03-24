package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.dto.statistic.StatisticFilter;
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
  
  public String buildIsQuery(StatisticFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    Date from = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getFromDate()) : PortalDateUtils.getEndOfDate(filter.getFromDate());

    sb.append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    sb.append(PortalConstants.COMMA).append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
    return sb.toString();
  }
  
  public String buildIsNotQuery(StatisticFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }
    
    StringBuilder sb = new StringBuilder();
    Date from = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getFromDate()) : PortalDateUtils.getEndOfDate(filter.getFromDate());

    sb.append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
    sb.append(PortalConstants.COMMA).append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    return sb.toString();
  }
}
