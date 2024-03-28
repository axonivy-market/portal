package com.axonivy.portal.util.filter.operator.caze.customfield;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomNumberGreaterOperatorHandler {
  private static CustomNumberGreaterOperatorHandler instance;

  public static CustomNumberGreaterOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberGreaterOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildGreaterQuery(DashboardFilter filter) {
    if (StringUtils.isBlank(filter.getValue())) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField())
      .isGreaterThan(BigDecimal.valueOf(NumberUtils.toDouble(filter.getValue())));
    return query;
  }

  public CaseQuery buildGreaterOrEqualQuery(DashboardFilter filter) {
    if (StringUtils.isBlank(filter.getValue())) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField())
      .isGreaterOrEqualThan(BigDecimal.valueOf(NumberUtils.toDouble(filter.getValue())));
    return query;
  }
}
