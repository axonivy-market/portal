package com.axonivy.portal.util.filter.operator.task.customfield;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTextEndWithOperatorHandler {

  private static final String END_WTIH_FORMAT = "%%%s";

  private static CustomTextEndWithOperatorHandler instance;

  public static CustomTextEndWithOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTextEndWithOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildEndWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().customField().textField(filter.getField())
          .isLikeIgnoreCase(String.format(END_WTIH_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

  public TaskQuery buildNotEndWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().customField().textField(filter.getField())
          .isNotLikeIgnoreCase(String.format(END_WTIH_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }

  public TaskQuery buildEndWithQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextEndWithOperatorHandler
        .getInstance().buildEndWithQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildNotEndWithQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextEndWithOperatorHandler
        .getInstance().buildNotEndWithQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}