package com.axonivy.portal.util.filter.operator.task.description;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class DescriptionIsOperatorHandler {

  private static final String EQUALS_FORMAT = "%s";

  private static DescriptionIsOperatorHandler instance;

  public static DescriptionIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DescriptionIsOperatorHandler();
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
      subQuery.where().description().isLikeIgnoreCase(String.format(EQUALS_FORMAT, text.toLowerCase()));
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
      subQuery.where().description().isNotLikeIgnoreCase(String.format(EQUALS_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}
