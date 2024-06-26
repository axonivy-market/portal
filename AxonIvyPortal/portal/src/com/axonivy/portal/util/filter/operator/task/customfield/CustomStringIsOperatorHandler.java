package com.axonivy.portal.util.filter.operator.task.customfield;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomStringIsOperatorHandler {

  private static CustomStringIsOperatorHandler instance;

  public static CustomStringIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringIsOperatorHandler();
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
      subQuery.where().customField().stringField(filter.getField()).isEqualIgnoreCase(text.toLowerCase());
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
      subQuery.where().customField().stringField(filter.getField()).isNotEqualIgnoreCase(text.toLowerCase());
      query.where().and(subQuery);
    });
    return query;
  }

  public TaskQuery buildIsQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringIsOperatorHandler
        .getInstance().buildIsQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildIsNotQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringIsOperatorHandler
        .getInstance().buildIsNotQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}
