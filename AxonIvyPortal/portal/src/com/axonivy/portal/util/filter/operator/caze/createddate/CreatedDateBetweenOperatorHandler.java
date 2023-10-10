package com.axonivy.portal.util.filter.operator.caze.createddate;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
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

  
  public CaseQuery buildQuery(DashboardFilter filter) {
    Date from = filter.getFrom();
    Date to = filter.getTo();
    if (from == null && to == null) {
      return null;
    }

    CaseQuery subQuery = CaseQuery.create();
    if (from != null) {
      subQuery.where().startTimestamp().isGreaterOrEqualThan(from);
    }

    if (to != null) {
      subQuery.where().startTimestamp().isLowerOrEqualThan(DateUtils.addDays(to, 1));
    }
    return subQuery;
  }
}
