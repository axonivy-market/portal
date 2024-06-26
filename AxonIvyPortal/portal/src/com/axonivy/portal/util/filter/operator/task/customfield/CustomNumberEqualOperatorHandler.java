package com.axonivy.portal.util.filter.operator.task.customfield;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomNumberEqualOperatorHandler {
  private static CustomNumberEqualOperatorHandler instance;

  public static CustomNumberEqualOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberEqualOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildEqualQuery(DashboardFilter filter) {
    if (StringUtils.isBlank(filter.getValue())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().customField().numberField(filter.getField())
      .isEqual(BigDecimal.valueOf(NumberUtils.toDouble(filter.getValue())));
    return query;
  }

  public TaskQuery buildNotEqualQuery(DashboardFilter filter) {
    if (StringUtils.isBlank(filter.getValue())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().customField().numberField(filter.getField())
      .isNotEqual(BigDecimal.valueOf(NumberUtils.toDouble(filter.getValue())));
    return query;
  }

  public TaskQuery buildEqualQueryByCase(DashboardFilter filter) {
    if (StringUtils.isBlank(filter.getValue())) {
      return null;
    }

    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberEqualOperatorHandler
        .getInstance().buildEqualQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildNotEqualQueryByCase(DashboardFilter filter) {
    if (StringUtils.isBlank(filter.getValue())) {
      return null;
    }

    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberEqualOperatorHandler
        .getInstance().buildNotEqualQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}

