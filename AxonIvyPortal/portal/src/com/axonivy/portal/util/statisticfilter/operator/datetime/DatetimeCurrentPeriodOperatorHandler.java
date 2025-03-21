package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.dto.statistic.StatisticFilter;
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
  
  public String buildQuery(StatisticFilter filter) {
    if (filter.getPeriodType() == null) {
      return "";
    }
    return queryCreatedDateByPeriod(filter);
  }

  private void buildQuery(StringBuilder sb, StatisticFilter filter, Date from, Date to) {
    sb.append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    
    sb.append(PortalConstants.COMMA).append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
  }

  private String queryCreatedDateByPeriod(StatisticFilter filter) {
    StringBuilder sb = new StringBuilder();
    switch (filter.getPeriodType()) {
      case YEAR -> buildQuery(sb, filter, PortalDateUtils.getStartOfCurrentYear(), PortalDateUtils.getEndOfCurrentYear());
      case MONTH -> buildQuery(sb, filter, PortalDateUtils.getStartOfCurrentMonth(), PortalDateUtils.getEndOfCurrentMonth());
      case WEEK -> buildQuery(sb, filter, PortalDateUtils.getStartOfCurrentWeek(), PortalDateUtils.getEndOfCurrentWeek());
      case DAY -> buildQuery(sb, filter, PortalDateUtils.getStartOfToday(), PortalDateUtils.getEndOfToday());
    }
    return sb.toString();
  }
}
