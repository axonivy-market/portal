package com.axonivy.portal.util.filter.operator.caze.createddate;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.util.PortalDateUtils;
import com.axonivy.portal.util.filter.operator.AbstractFilterOperatorHandler;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatedDateCurrentPeriodOperatorHandler extends AbstractFilterOperatorHandler {

  private static CreatedDateCurrentPeriodOperatorHandler instance;

  public static CreatedDateCurrentPeriodOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateCurrentPeriodOperatorHandler();
    }
    return instance;
  }

  
  public CaseQuery buildQuery(DashboardFilter filter) {
    if (filter.getPeriodType() == null) {
      return null;
    }
    return queryCreatedDateByPeriod(filter.getPeriodType());
  }

  private void buildQuery(CaseQuery query, Date from, Date to) {;
    query.where().startTimestamp().isGreaterOrEqualThan(from);
    query.where().startTimestamp().isLowerOrEqualThan(to);
  }

  private CaseQuery queryCreatedDateByPeriod(FilterPeriodType dateFilterPeriodType) {
    CaseQuery query = CaseQuery.create();
    switch (dateFilterPeriodType) {
      case YEAR -> buildQuery(query, PortalDateUtils.getStartOfCurrentYear(), PortalDateUtils.getEndOfCurrentYear());
      case QUARTER -> buildQuery(query, PortalDateUtils.getStartOfCurrentQuarter(), PortalDateUtils.getEndOfCurrentQuarter());
      case MONTH -> buildQuery(query, PortalDateUtils.getStartOfCurrentMonth(), PortalDateUtils.getEndOfCurrentMonth());
      case WEEK -> buildQuery(query, PortalDateUtils.getStartOfCurrentWeek(), PortalDateUtils.getEndOfCurrentWeek());
      case DAY -> buildQuery(query, PortalDateUtils.getStartOfToday(), PortalDateUtils.getEndOfToday());
    }
    return query;
  }
}
