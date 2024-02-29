package com.axonivy.portal.util.filter.operator.task.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTimestampNumberOfPeriodsOperatorHandler {
  private static CustomTimestampNumberOfPeriodsOperatorHandler instance;

  public static CustomTimestampNumberOfPeriodsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampNumberOfPeriodsOperatorHandler();
    }
    return instance;
  }

  
  public TaskQuery buildLastPeriodQuery(DashboardFilter filter) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter.getPeriodType(), numberOfPeriods, true, filter);
  }

  public TaskQuery buildNextPeriodQuery(DashboardFilter filter) {
    Long numberOfPeriods = filter.getPeriods();
    if (filter.getPeriodType() == null || numberOfPeriods == null || numberOfPeriods <= 0) {
      return null;
    }

    return queryCreatedDateByNUmberOfPeriod(filter.getPeriodType(), numberOfPeriods, false, filter);
  }

  private TaskQuery buildQuery(Date from, Date to, boolean isPast, DashboardFilter filter) {
    TaskQuery query = TaskQuery.create();
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

  private TaskQuery queryCreatedDateByNUmberOfPeriod(FilterPeriodType dateFilterPeriodType, Long numberOfPeriods,
      boolean isPast, DashboardFilter filter) {
    Long absNumberOfPeriods = isPast ?  -Math.abs(numberOfPeriods) : Math.abs(numberOfPeriods);
    Date today = new Date();
    TaskQuery query = switch (dateFilterPeriodType) {
      case YEAR -> buildQuery(PortalDateUtils.getYearByPeriod(absNumberOfPeriods), today, isPast, filter);
      case MONTH -> buildQuery(PortalDateUtils.getMonthByPeriod(absNumberOfPeriods), today, isPast, filter);
      case WEEK -> buildQuery(PortalDateUtils.getWeekByPeriod(absNumberOfPeriods), today, isPast, filter);
      case DAY -> buildQuery(PortalDateUtils.getDayByPeriod(absNumberOfPeriods), today, isPast, filter);
      default -> TaskQuery.create();
    };
    return query;
  }

  public TaskQuery buildLastPeriodQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampNumberOfPeriodsOperatorHandler
        .getInstance().buildLastPeriodQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildNextPeriodQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampNumberOfPeriodsOperatorHandler
        .getInstance().buildNextPeriodQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;

  }
}
