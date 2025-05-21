package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class DatetimeCurrentPeriodOperatorHandler {
  private static DatetimeCurrentPeriodOperatorHandler instance;

  public static DatetimeCurrentPeriodOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DatetimeCurrentPeriodOperatorHandler();
    }
    return instance;
  }
  
  public String buildFilter(DashboardFilter filter) {
    if (filter.getPeriodType() == null) {
      return "";
    }
    return queryCreatedDateByPeriod(filter);
  }

  private void buildFilter(StringBuilder sb, DashboardFilter filter, Date from, Date to) {
    String field = filter.isCustomDateField() ? StatisticConstants.CUSTOM_TIMESTAMP + filter.getField() : filter.getField();
    sb.append(field).append(PortalConstants.COLON);
    sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    
    sb.append(PortalConstants.COMMA).append(field).append(PortalConstants.COLON);
    sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
  }

  private String queryCreatedDateByPeriod(DashboardFilter filter) {
    StringBuilder sb = new StringBuilder();
    switch (filter.getPeriodType()) {
      case YEAR -> buildFilter(sb, filter, PortalDateUtils.getStartOfCurrentYear(), PortalDateUtils.getEndOfCurrentYear());
      case MONTH -> buildFilter(sb, filter, PortalDateUtils.getStartOfCurrentMonth(), PortalDateUtils.getEndOfCurrentMonth());
      case WEEK -> buildFilter(sb, filter, PortalDateUtils.getStartOfCurrentWeek(), PortalDateUtils.getEndOfCurrentWeek());
      case DAY -> buildFilter(sb, filter, PortalDateUtils.getStartOfToday(), PortalDateUtils.getEndOfToday());
    }
    return sb.toString();
  }
}
