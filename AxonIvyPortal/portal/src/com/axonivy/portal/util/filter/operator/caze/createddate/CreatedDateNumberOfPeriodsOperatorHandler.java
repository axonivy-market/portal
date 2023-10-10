package com.axonivy.portal.util.filter.operator.caze.createddate;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatedDateNumberOfPeriodsOperatorHandler {
  private static CreatedDateNumberOfPeriodsOperatorHandler instance;

  public static CreatedDateNumberOfPeriodsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateNumberOfPeriodsOperatorHandler();
    }
    return instance;
  }

  
  public CaseQuery buildQuery(DashboardFilter filter, boolean isPast) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter.getPeriodType(), numberOfPeriods, isPast);
  }

  private void buildQuery(CaseQuery query, Date from, Date to) {;
    query.where().startTimestamp().isGreaterOrEqualThan(from);
    query.where().startTimestamp().isLowerOrEqualThan(to);
  }

  private CaseQuery queryCreatedDateByNUmberOfPeriod(FilterPeriodType dateFilterPeriodType, Long numberOfPeriods, boolean isPast) {
    Long realNumberOfPeriods = isPast ? -(Math.abs(numberOfPeriods)) : Math.abs(numberOfPeriods);
    Date today = new Date();
    CaseQuery query = CaseQuery.create();
    switch (dateFilterPeriodType) {
      case YEAR -> buildQuery(query, PortalDateUtils.getYearByPeriod(realNumberOfPeriods), today);
      case QUARTER -> buildQuery(query, PortalDateUtils.getQuarterByPeriod(realNumberOfPeriods), today);
      case MONTH -> buildQuery(query, PortalDateUtils.getMonthByPeriod(realNumberOfPeriods), today);
      case WEEK -> buildQuery(query, PortalDateUtils.getWeekByPeriod(realNumberOfPeriods), today);
      case DAY -> buildQuery(query, PortalDateUtils.getDayByPeriod(realNumberOfPeriods), today);
    }
    return query;
  }
}
