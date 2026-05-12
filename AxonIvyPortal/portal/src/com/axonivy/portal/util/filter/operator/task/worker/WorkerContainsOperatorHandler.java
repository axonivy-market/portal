package com.axonivy.portal.util.filter.operator.task.worker;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class WorkerContainsOperatorHandler {
  
  private static final String LIKE_FORMAT = "%%%s%%";

  private static WorkerContainsOperatorHandler instance;

  public static WorkerContainsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new WorkerContainsOperatorHandler();
    }
    return instance;
  }
  
  public TaskQuery buildContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String worker : filter.getValues()) {
      filterQuery.or().workerUserDisplayName().isLikeIgnoreCase(String.format(LIKE_FORMAT, StringUtils.lowerCase(worker)));
    }

    return query;
  }
}
