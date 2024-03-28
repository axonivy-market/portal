package com.axonivy.portal.util.filter.operator.caze.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTimestampNumberOfPeriodsOperatorHandler {
  private static CustomTimestampNumberOfPeriodsOperatorHandler instance;

  public static CustomTimestampNumberOfPeriodsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampNumberOfPeriodsOperatorHandler();
    }
    return instance;
  }

  
  public CaseQuery buildLastPeriodQuery(DashboardFilter filter) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter.getPeriodType(), numberOfPeriods, true, filter);
  }

  public CaseQuery buildNextPeriodQuery(DashboardFilter filter) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter.getPeriodType(), numberOfPeriods, false, filter);
  }

  private CaseQuery buildQuery(Date from, Date to, boolean isPast, DashboardFilter filter) {
    CaseQuery query = CaseQuery.create();
    if (isPast) {
      query.where().customField().timestampField(filter.getField())
          .isGreaterOrEqualThan(PortalDateUtils.getStartOfDate(from));
      query.where().customField().timestampField(filter.getField()).isLowerOrEqualThan(to);
    } else {
      query.where().customField().timestampField(filter.getField()).isGreaterOrEqualThan(to);
      query.where().customField().timestampField(filter.getField())
          .isLowerOrEqualThan(PortalDateUtils.getEndOfDate(from));
    }
    return query;
  }

  private CaseQuery queryCreatedDateByNUmberOfPeriod(FilterPeriodType dateFilterPeriodType, Long numberOfPeriods,
      boolean isPast, DashboardFilter filter) {
    Long absNumberOfPeriods = isPast ?  -Math.abs(numberOfPeriods) : Math.abs(numberOfPeriods);
    Date today = new Date();
    CaseQuery query = switch (dateFilterPeriodType) {
      case YEAR -> buildQuery(PortalDateUtils.getYearByPeriod(absNumberOfPeriods), today, isPast, filter);
      case MONTH -> buildQuery(PortalDateUtils.getMonthByPeriod(absNumberOfPeriods), today, isPast, filter);
      case WEEK -> buildQuery(PortalDateUtils.getWeekByPeriod(absNumberOfPeriods), today, isPast, filter);
      case DAY -> buildQuery(PortalDateUtils.getDayByPeriod(absNumberOfPeriods), today, isPast, filter);
      default -> CaseQuery.create();
    };
    return query;
  }
}
