package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class DatetimeNumberOfPeriodsOperatorHandler {
  private static DatetimeNumberOfPeriodsOperatorHandler instance;

  public static DatetimeNumberOfPeriodsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DatetimeNumberOfPeriodsOperatorHandler();
    }
    return instance;
  }
  public String buildLastPeriodFilter(DashboardFilter filter) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter, numberOfPeriods, true);
  }

  public String buildNextPeriodFilter(DashboardFilter filter) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter, numberOfPeriods, false);
  }

  private void buildFilter(StringBuilder sb, DashboardFilter filter, Date from, Date to, boolean isPast) {
    String field = filter.isCustomDateField() ? StatisticConstants.CUSTOM_TIMESTAMP + filter.getField() : filter.getField();
    if (isPast) {
      sb.append(field).append(PortalConstants.COLON);
      sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
      sb.append(PortalConstants.COMMA).append(field).append(PortalConstants.COLON);
      sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
    } else {
      sb.append(field).append(PortalConstants.COLON);
      sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
      sb.append(PortalConstants.COMMA).append(field).append(PortalConstants.COLON);
      sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    }
  }

  private String queryCreatedDateByNUmberOfPeriod(DashboardFilter filter, Long numberOfPeriods,
      boolean isPast) {
    Long absNumberOfPeriods = isPast ? -Math.abs(numberOfPeriods) : Math.abs(numberOfPeriods);
    Date today = new Date();
    StringBuilder sb = new StringBuilder();
    switch (filter.getPeriodType()) {
      case YEAR -> buildFilter(sb, filter, PortalDateUtils.getYearByPeriod(absNumberOfPeriods), today, isPast);
      case MONTH -> buildFilter(sb, filter, PortalDateUtils.getMonthByPeriod(absNumberOfPeriods), today, isPast);
      case WEEK -> buildFilter(sb, filter, PortalDateUtils.getWeekByPeriod(absNumberOfPeriods), today, isPast);
      case DAY -> buildFilter(sb, filter, PortalDateUtils.getDayByPeriod(absNumberOfPeriods), today, isPast);
    }
    return sb.toString();
  }
}
