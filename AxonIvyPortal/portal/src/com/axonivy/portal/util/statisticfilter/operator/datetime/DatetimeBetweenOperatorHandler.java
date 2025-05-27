package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;

public class DatetimeBetweenOperatorHandler {
  private static DatetimeBetweenOperatorHandler instance;

  public static DatetimeBetweenOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DatetimeBetweenOperatorHandler();
    }
    return instance;
  }

  public String buildBetweenFilter(DashboardFilter filter) {
    Date from = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
    Date to = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getToDate()) : PortalDateUtils.getEndOfDate(filter.getToDate());

    if (from == null && to == null) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    String field = filter.isCustomDateField() ? StatisticConstants.CUSTOM_TIMESTAMP + filter.getField() : filter.getField();
    if (from != null) {
      sb.append(field).append(PortalConstants.COLON);
      sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    }

    if (to != null) {
      sb.append(PortalConstants.COMMA).append(field).append(PortalConstants.COLON);
      sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
    }
    return sb.toString();
  }

  public String buildNotBetweenFilter(DashboardFilter filter) {
  Date from = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getStartOfMinute(filter.getFromDate()) : PortalDateUtils.getStartOfDate(filter.getFromDate());
  Date to = DateTimeGlobalSettingService.getInstance().isDateFilterWithTime() ? PortalDateUtils.getEndOfMinute(filter.getToDate()) : PortalDateUtils.getEndOfDate(filter.getToDate());

    if (from == null && to == null) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    String field = filter.isCustomDateField() ? StatisticConstants.CUSTOM_TIMESTAMP + filter.getField() : filter.getField();
    if (from != null) {
      sb.append(field).append(PortalConstants.COLON);
      sb.append(PortalConstants.LESS_THAN).append(PortalDateUtils.toStringIso8601Format(from));
    }

    if (to != null) {
      sb.append(PortalConstants.COMMA).append(field).append(PortalConstants.COLON);
      sb.append(PortalConstants.GREATER_THAN).append(PortalDateUtils.toStringIso8601Format(to));
    }
    return sb.toString();
  }
}
