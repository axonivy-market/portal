package com.axonivy.portal.util.filter.operator.caze.customfield;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomNumberEqualOperatorHandler {
  private static CustomNumberEqualOperatorHandler instance;

  public static CustomNumberEqualOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberEqualOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildEqualQuery(DashboardFilter filter) {
    if (StringUtils.isBlank(filter.getValue())) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField())
      .isEqual(BigDecimal.valueOf(NumberUtils.toDouble(filter.getValue())));
    return query;
  }

  public CaseQuery buildNotEqualQuery(DashboardFilter filter) {
    if (StringUtils.isBlank(filter.getValue())) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    query.where().customField().numberField(filter.getField())
      .isNotEqual(BigDecimal.valueOf(NumberUtils.toDouble(filter.getValue())));
    return query;
  }
}
