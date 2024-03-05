package com.axonivy.portal.util.filter.operator.task.responsible;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class ResponsibleInOperatorHandler {
  private static ResponsibleInOperatorHandler instance;
  private static final String HASH = "#";

  public static ResponsibleInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ResponsibleInOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String activator : filter.getValues()) {
      filterQuery.or().activatorName().isEqual(activator.replace(HASH, ""));
    }

    return query;
  }

  public TaskQuery buildNotInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String activator : filter.getValues()) {
      filterQuery.and().activatorName().isNotEqual(activator.replace(HASH, ""));
    }

    return query;
  }
}
