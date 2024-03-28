package com.axonivy.portal.util.filter.operator.task.category;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class CategoryContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";

  private static CategoryContainsOperatorHandler instance;


  public static CategoryContainsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CategoryContainsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildContainsQuery(DashboardFilter filter) {
    return buildQuery(filter, false);
  }

  public TaskQuery buildNotContainsQuery(DashboardFilter filter) {
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
        filterQuery.and().category().isNotLike(String.format(LIKE_FORMAT, category));
      } else {
        filterQuery.or().category().isLike(String.format(LIKE_FORMAT, category));
      }
    }

    return query;
  }
}