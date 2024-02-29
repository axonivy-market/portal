package com.axonivy.portal.util.filter.operator.task.customfield;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomNumberBetweenOperatorHandler {
  private static CustomNumberBetweenOperatorHandler instance;

  public static CustomNumberBetweenOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomNumberBetweenOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildEqualQuery(DashboardFilter filter) {
    Number from = BigDecimal.valueOf(NumberUtils.toDouble(filter.getFrom()));
    Number to = BigDecimal.valueOf(NumberUtils.toDouble(filter.getTo()));

    TaskQuery query = TaskQuery.create();

    if (from != null && to != null) {
      query.where().customField().numberField(filter.getField()).isLowerOrEqualThan(to).and().customField()
        .numberField(filter.getField()).isGreaterOrEqualThan(from);
      return query;
    }

    if (from != null) {
      query.where().customField().numberField(filter.getField()).isGreaterOrEqualThan(from);
      return query;
    }

    if (to != null) {
      query.where().customField().numberField(filter.getField()).isLowerOrEqualThan(to);
      return query;
    }

    return null;
  }

  public TaskQuery buildNotEqualQuery(DashboardFilter filter) {
    Number from = BigDecimal.valueOf(NumberUtils.toDouble(filter.getFrom()));
    Number to = BigDecimal.valueOf(NumberUtils.toDouble(filter.getTo()));

    TaskQuery query = TaskQuery.create();

    if (from != null && to != null) {
      query.where().customField().numberField(filter.getField()).isLowerOrEqualThan(from).or().customField()
        .numberField(filter.getField()).isGreaterOrEqualThan(to);
      return query;
    }

    if (from != null) {
      query.where().customField().numberField(filter.getField()).isLowerOrEqualThan(from);
      return query;
    }

    if (to != null) {
      query.where().customField().numberField(filter.getField()).isGreaterOrEqualThan(to);
      return query;
    }

    return null;
  }

  public TaskQuery buildEqualQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberBetweenOperatorHandler
        .getInstance().buildEqualQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;

  }

  public TaskQuery buildNotEqualQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberBetweenOperatorHandler
        .getInstance().buildNotEqualQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
