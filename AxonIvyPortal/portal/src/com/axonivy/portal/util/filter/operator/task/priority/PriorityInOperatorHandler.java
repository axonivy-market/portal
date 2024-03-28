package com.axonivy.portal.util.filter.operator.task.priority;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class PriorityInOperatorHandler {

  private static PriorityInOperatorHandler instance;

  public static PriorityInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new PriorityInOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildPriorityInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();

    List<WorkflowPriority> priorityList = filter.getValues().stream()
        .map(text -> WorkflowPriority.valueOf(text))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    for (WorkflowPriority priority : priorityList) {
      filterQuery.or().priority().isEqual(priority);
    }
    return query;
  }


}
