package com.axonivy.portal.util.statisticfilter.operator.datetime;

import java.util.Date;

import com.axonivy.portal.dto.statistic.StatisticFilter;
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
  public String buildLastPeriodQuery(StatisticFilter filter) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter, numberOfPeriods, true);
  }

  public String buildNextPeriodQuery(StatisticFilter filter) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter, numberOfPeriods, false);
  }

  private void buildQuery(StringBuilder sb, StatisticFilter filter, Date from, Date to, boolean isPast) {
    if (isPast) {
      sb.append(filter.getField()).append(PortalConstants.COLON);
      sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
      sb.append(PortalConstants.COMMA).append(filter.getField()).append(PortalConstants.COLON);
      sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
    } else {
      sb.append(filter.getField()).append(PortalConstants.COLON);
      sb.append(PortalConstants.GREATER_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(to));
      sb.append(PortalConstants.COMMA).append(filter.getField()).append(PortalConstants.COLON);
      sb.append(PortalConstants.LESS_THAN_OR_EQUAL).append(PortalDateUtils.toStringIso8601Format(from));
    }
  }

  private String queryCreatedDateByNUmberOfPeriod(StatisticFilter filter, Long numberOfPeriods,
      boolean isPast) {
    Long absNumberOfPeriods = isPast ? -Math.abs(numberOfPeriods) : Math.abs(numberOfPeriods);
    Date today = new Date();
    StringBuilder sb = new StringBuilder();
    switch (filter.getPeriodType()) {
      case YEAR -> buildQuery(sb, filter, PortalDateUtils.getYearByPeriod(absNumberOfPeriods), today, isPast);
      case MONTH -> buildQuery(sb, filter, PortalDateUtils.getMonthByPeriod(absNumberOfPeriods), today, isPast);
      case WEEK -> buildQuery(sb, filter, PortalDateUtils.getWeekByPeriod(absNumberOfPeriods), today, isPast);
      case DAY -> buildQuery(sb, filter, PortalDateUtils.getDayByPeriod(absNumberOfPeriods), today, isPast);
    }
    return sb.toString();
  }
}
