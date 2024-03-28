package com.axonivy.portal.util.filter.operator.caze.customfield;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTimestampCurrentPeriodOperatorHandler {

  private static CustomTimestampCurrentPeriodOperatorHandler instance;

  public static CustomTimestampCurrentPeriodOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampCurrentPeriodOperatorHandler();
    }
    return instance;
  }


  public CaseQuery buildQuery(DashboardFilter filter) {
    if (filter.getPeriodType() == null) {
      return null;
    }
    return queryCreatedDateByPeriod(filter.getPeriodType(), filter);
  }

  private CaseQuery buildQuery(Date from, Date to, DashboardFilter filter) {
    CaseQuery query = CaseQuery.create();
    query.where().customField().timestampField(filter.getField()).isGreaterOrEqualThan(from);
    query.where().customField().timestampField(filter.getField()).isLowerOrEqualThan(to);
    return query;
  }

  private CaseQuery queryCreatedDateByPeriod(FilterPeriodType dateFilterPeriodType, DashboardFilter filter) {
    CaseQuery query = switch (dateFilterPeriodType) {
      case YEAR -> buildQuery(PortalDateUtils.getStartOfCurrentYear(), PortalDateUtils.getEndOfCurrentYear(), filter);
      case MONTH -> buildQuery(PortalDateUtils.getStartOfCurrentMonth(), PortalDateUtils.getEndOfCurrentMonth(),
          filter);
      case WEEK -> buildQuery(PortalDateUtils.getStartOfCurrentWeek(), PortalDateUtils.getEndOfCurrentWeek(), filter);
      case DAY -> buildQuery(PortalDateUtils.getStartOfToday(), PortalDateUtils.getEndOfToday(), filter);
    };
    return query;
  }
}
