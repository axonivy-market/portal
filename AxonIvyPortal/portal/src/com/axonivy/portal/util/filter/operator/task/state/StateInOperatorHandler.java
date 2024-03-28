package com.axonivy.portal.util.filter.operator.task.state;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class StateInOperatorHandler {
  
  private static StateInOperatorHandler instance;
  
  public static StateInOperatorHandler getInstance() {
    if(instance == null) {
      instance = new StateInOperatorHandler();
    }

    return instance;
  }
  
  public TaskQuery buildStateInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();

    List<TaskBusinessState> states = filter.getValues().stream()
        .map(text -> TaskBusinessState.valueOf(text))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    for (TaskBusinessState state : states) {
      filterQuery.or().businessState().isEqual(state);
    }
    return query;
  }
}