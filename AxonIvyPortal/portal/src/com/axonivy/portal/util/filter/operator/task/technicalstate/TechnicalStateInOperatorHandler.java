package com.axonivy.portal.util.filter.operator.task.technicalstate;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TechnicalStateInOperatorHandler {

  private static TechnicalStateInOperatorHandler instance;

  public static TechnicalStateInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new TechnicalStateInOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildStateInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();

    List<TaskState> states = filter.getValues().stream()
        .map(TaskState::valueOf)
        .toList();

    for (TaskState state : states) {
      filterQuery.or().state().isEqual(state);
    }
    return query;
  }
}
