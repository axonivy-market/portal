package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.dto.statistic.StatisticFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class DatetimeIsOperatorHandler {
  private static DatetimeIsOperatorHandler instance;

  public static DatetimeIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DatetimeIsOperatorHandler();
    }
    return instance;
  }
  
  public String buildIsQuery(StatisticFilter filter) {
    StringBuilder sb = new StringBuilder();
    Date from = PortalDateUtils.getStartOfToday();
    Date to = PortalDateUtils.getEndOfToday();

    sb.append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    sb.append(PortalConstants.COMMA).append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));

    return sb.toString();
  }
}
