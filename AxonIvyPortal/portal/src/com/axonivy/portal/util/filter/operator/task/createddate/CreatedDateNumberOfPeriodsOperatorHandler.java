package com.axonivy.portal.util.filter.operator.task.createddate;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CreatedDateNumberOfPeriodsOperatorHandler {
  private static CreatedDateNumberOfPeriodsOperatorHandler instance;

  public static CreatedDateNumberOfPeriodsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateNumberOfPeriodsOperatorHandler();
    }
    return instance;
  }

  
  public TaskQuery buildLastPeriodQuery(DashboardFilter filter) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter.getPeriodType(), numberOfPeriods, true);
  }

  public TaskQuery buildNextPeriodQuery(DashboardFilter filter) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter.getPeriodType(), numberOfPeriods, false);
  }

  private void buildQuery(TaskQuery query, Date from, Date to, boolean isPast) {
    if (isPast) {
      query.where().startTimestamp().isGreaterOrEqualThan(PortalDateUtils.getStartOfDate(from));
      query.where().startTimestamp().isLowerOrEqualThan(to);
    } else {
      query.where().startTimestamp().isGreaterOrEqualThan(to);
      query.where().startTimestamp().isLowerOrEqualThan(PortalDateUtils.getEndOfDate(from));
    }
  }

  private TaskQuery queryCreatedDateByNUmberOfPeriod(FilterPeriodType dateFilterPeriodType, Long numberOfPeriods, boolean isPast) {
    Long absNumberOfPeriods = isPast ?  -Math.abs(numberOfPeriods) : Math.abs(numberOfPeriods);
    Date today = new Date();
    TaskQuery query = TaskQuery.create();
    switch (dateFilterPeriodType) {
      case YEAR -> buildQuery(query, PortalDateUtils.getYearByPeriod(absNumberOfPeriods), today, isPast);
      case MONTH -> buildQuery(query, PortalDateUtils.getMonthByPeriod(absNumberOfPeriods), today, isPast);
      case WEEK -> buildQuery(query, PortalDateUtils.getWeekByPeriod(absNumberOfPeriods), today, isPast);
      case DAY -> buildQuery(query, PortalDateUtils.getDayByPeriod(absNumberOfPeriods), today, isPast);
    }
    return query;
  }
}
