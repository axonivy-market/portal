package com.axonivy.portal.util.filter.operator.task.responsible;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class ResponsibleInOperatorHandler {
  private static ResponsibleInOperatorHandler instance;

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
    for (String responsible : filter.getValues()) {
      filterQuery.or().responsibleName().isEqual(responsible);
    }

    return query;
  }

  public TaskQuery buildNotInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String responsible : filter.getValues()) {
      filterQuery.and().responsibleName().isNotEqual(responsible);
    }

    return query;
  }
}
