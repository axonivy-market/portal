package com.axonivy.portal.util.filter.operator.task.customfield;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTextIsOperatorHandler {

  private static CustomTextIsOperatorHandler instance;

  public static CustomTextIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTextIsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildIsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().customField().textField(filter.getField()).isEqualIgnoreCase(text.toLowerCase());
      query.where().or(subQuery);
    });
    return query;
  }

  public TaskQuery buildIsNotQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().customField().textField(filter.getField()).isNotEqualIgnoreCase(text.toLowerCase());
      query.where().and(subQuery);
    });
    return query;
  }

  public TaskQuery buildIsQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextIsOperatorHandler
        .getInstance().buildIsQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildIsNotQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextIsOperatorHandler
        .getInstance().buildIsNotQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}