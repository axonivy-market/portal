package com.axonivy.portal.util.filter.operator.task.responsible;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class ReponsibleContainsOperatorHandler {
  
  private static final String LIKE_FORMAT = "%%%s%%";

  private static ReponsibleContainsOperatorHandler instance;

  public static ReponsibleContainsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ReponsibleContainsOperatorHandler();
    }
    return instance;
  }
  
  public TaskQuery buildContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String responsible : filter.getValues()) {
      filterQuery.or().activatorDisplayName().isLikeIgnoreCase(String.format(LIKE_FORMAT, responsible.toLowerCase()));
    }

    return query;
  }
}
