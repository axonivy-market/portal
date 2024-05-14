package com.axonivy.portal.util.filter.operator.caze.customfield;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.PortalDateUtils;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTimestampIsOperatorHandler {
  private static CustomTimestampIsOperatorHandler instance;

  public static CustomTimestampIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTimestampIsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return CaseQuery.create().where().customField().timestampField(filter.getField())
        .isGreaterOrEqualThan(PortalDateUtils.getStartOfDate(filter.getFromDate()))
        .and().customField().timestampField(filter.getField()).isLowerOrEqualThan(PortalDateUtils.getEndOfDate(filter.getFromDate()));
  }

  public CaseQuery buildIsNotQuery(DashboardFilter filter) {
    if (filter.getFromDate() == null) {
      return null;
    }

    return CaseQuery.create().where().customField().timestampField(filter.getField())
        .isGreaterThan(PortalDateUtils.getEndOfDate(filter.getFromDate()))
        .or().customField().timestampField(filter.getField()).isLowerThan(PortalDateUtils.getStartOfDate(filter.getFromDate()));
  }
}
