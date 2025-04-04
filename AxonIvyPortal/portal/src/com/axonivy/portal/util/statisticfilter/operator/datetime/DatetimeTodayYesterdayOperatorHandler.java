package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.dto.statistic.StatisticFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class DatetimeTodayYesterdayOperatorHandler {
  private static DatetimeTodayYesterdayOperatorHandler instance;

  public static DatetimeTodayYesterdayOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DatetimeTodayYesterdayOperatorHandler();
    }
    return instance;
  }
  
  public String buildTodayQuery(StatisticFilter filter) {
    StringBuilder sb = new StringBuilder();
    Date from = PortalDateUtils.getStartOfToday();
    Date to = PortalDateUtils.getEndOfToday();

    sb.append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    sb.append(PortalConstants.COMMA).append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));

    return sb.toString();
  }
  
  public String buildYesterdayQuery(StatisticFilter filter) {
    StringBuilder sb = new StringBuilder();
    Date from = PortalDateUtils.getStartOfYesterday();
    Date to = PortalDateUtils.getEndOfYesterday();

    sb.append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    sb.append(PortalConstants.COMMA).append(filter.getField()).append(PortalConstants.COLON);
    sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));

    return sb.toString();
  }
}
