package com.axonivy.portal.util.filter.operator.task.description;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class DescriptionContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";

  private static DescriptionContainsOperatorHandler instance;

  public static DescriptionContainsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DescriptionContainsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().description().isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

  public TaskQuery buildNotContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().description().isNotLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}
