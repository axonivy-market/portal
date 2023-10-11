package com.axonivy.portal.util.filter.operator.caze.createddate;

import java.util.Date;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;
import com.axonivy.portal.util.filter.operator.AbstractFilterOperatorHandler;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CreatedDateBetweenOperatorHandler extends AbstractFilterOperatorHandler {

  private static CreatedDateBetweenOperatorHandler instance;

  public static CreatedDateBetweenOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatedDateBetweenOperatorHandler();
    }
    return instance;
  }

  
  public CaseQuery buildBetweenQuery(DashboardFilter filter) {
    Date from = PortalDateUtils.getStartOfDate(filter.getFrom());
    Date to = PortalDateUtils.getEndOfDate(filter.getTo());

    if (from == null && to == null) {
      return null;
    }

    CaseQuery subQuery = CaseQuery.create();
    if (from != null) {
      subQuery.where().startTimestamp().isGreaterOrEqualThan(from);
    }

    if (to != null) {
      subQuery.where().startTimestamp().isLowerOrEqualThan(to);
    }
    return subQuery;
  }

  public CaseQuery buildNotBetweenQuery(DashboardFilter filter) {
    Date from = PortalDateUtils.getStartOfDate(filter.getFrom());
    Date to = PortalDateUtils.getEndOfDate(filter.getTo());

    CaseQuery subQuery = CaseQuery.create();
    if (from != null && to != null) {
      subQuery.where().startTimestamp().isLowerThan(from).or().startTimestamp().isGreaterThan(to);
      return subQuery;
    }

    if (from != null) {
      subQuery.where().startTimestamp().isLowerThan(from);
      return subQuery;
    }

    if (to != null) {
      subQuery.where().startTimestamp().isGreaterThan(to);
      return subQuery;
    }

    return null;
  }
}
