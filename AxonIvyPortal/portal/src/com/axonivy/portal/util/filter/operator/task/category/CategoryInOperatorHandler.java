package com.axonivy.portal.util.filter.operator.task.category;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class CategoryInOperatorHandler {
  private static CategoryInOperatorHandler instance;

  public static CategoryInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CategoryInOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildInQuery(DashboardFilter filter) {
    return buildQuery(filter, false);
  }

  public TaskQuery buildNotInQuery(DashboardFilter filter) {
    return buildQuery(filter, true);
  }

  public TaskQuery buildQuery(DashboardFilter filter, boolean isNot) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String category : filter.getValues()) {
      if (isNot) {
        filterQuery.and().category().isNotEqual(category);
      } else {
        filterQuery.or().category().isEqual(category);
      }
    }

    return query;
  }
}