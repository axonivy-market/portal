package com.axonivy.portal.util.filter.operator.task.description;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class DescriptionStartWithOperatorHandler {

  private static final String START_WTIH_FORMAT = "%s%%";

  private static DescriptionStartWithOperatorHandler instance;

  public static DescriptionStartWithOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DescriptionStartWithOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildStartWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().description().isLikeIgnoreCase(String.format(START_WTIH_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

  public TaskQuery buildNotStartWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().description().isNotLikeIgnoreCase(String.format(START_WTIH_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}
