package com.axonivy.portal.util.filter.operator.task.customfield;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomNumberLessOperatorHandler {
  private static CustomNumberLessOperatorHandler instance;

  public static CustomNumberLessOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberLessOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildLessQuery(DashboardFilter filter) {
    if (StringUtils.isBlank(filter.getValue())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().customField().numberField(filter.getField())
      .isLowerThan(BigDecimal.valueOf(NumberUtils.toDouble(filter.getValue())));
    return query;
  }

  public TaskQuery buildLessOrEqualQuery(DashboardFilter filter) {
    if (StringUtils.isBlank(filter.getValue())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().customField().numberField(filter.getField())
      .isLowerOrEqualThan(BigDecimal.valueOf(NumberUtils.toDouble(filter.getValue())));
    return query;
  }
}
