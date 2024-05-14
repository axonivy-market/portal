package com.axonivy.portal.util.filter.operator.task.description;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class DescriptionEndWithOperatorHandler {

  private static final String END_WTIH_FORMAT = "%%%s";

  private static DescriptionEndWithOperatorHandler instance;

  public static DescriptionEndWithOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DescriptionEndWithOperatorHandler();
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
      subQuery.where().description().isLikeIgnoreCase(String.format(END_WTIH_FORMAT, text.toLowerCase()));
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
      subQuery.where().description().isNotLikeIgnoreCase(String.format(END_WTIH_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}
