package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.TaskDashboardWidget;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskDashboardWidgetService {
  public TaskQuery loadTaskQuery(TaskDashboardWidget definition) {
    TaskQuery taskQuery = TaskQuery.create();
    
    if (definition.getName() == null) {
      return taskQuery;
    }

    // Name
    if (StringUtils.isNotBlank(definition.getTaskName())) {
      String containingKeyword = String.format("%%%s%%", definition.getTaskName());
      taskQuery.where().name().isLikeIgnoreCase(containingKeyword);
    }

    // Description
    if (StringUtils.isNotBlank(definition.getDescription())) {
      String containingKeyword = String.format("%%%s%%", definition.getDescription());
      taskQuery.where().description().isLikeIgnoreCase(containingKeyword);
    }

    // Priority
    TaskQuery prioritySubQuery = TaskQuery.create();
    List<WorkflowPriority> priorities = definition.getPriorities();
    if (CollectionUtils.isEmpty(priorities)) {
      priorities = Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH, WorkflowPriority.NORMAL, WorkflowPriority.LOW);
    }
    IFilterQuery priorityFilterQuery = prioritySubQuery.where();
    for (WorkflowPriority priority : priorities) {
      priorityFilterQuery.or().priority().isEqual(priority);
    }
    taskQuery.where().and(prioritySubQuery);

    // State
    TaskQuery stateSubQuery = TaskQuery.create();
    List<TaskState> states = definition.getStates();
    if (CollectionUtils.isEmpty(states)) {
      states = Arrays.asList(TaskState.CREATED, TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE);
    }
    IFilterQuery stateFilterQuery = stateSubQuery.where();
    for (TaskState state : states) {
      stateFilterQuery.or().state().isEqual(state);
    }
    taskQuery.where().and(stateSubQuery);

    // Responsible
    if (CollectionUtils.isNotEmpty(definition.getResponsibles())) {
      TaskQuery responsibleQuery = TaskQuery.create();
      if (CollectionUtils.isNotEmpty(definition.getResponsibles())) {
        definition.getResponsibles().forEach(r -> responsibleQuery.where().or().activatorName().isEqual(r));
      }
      taskQuery.where().and(responsibleQuery);
    }

    // Created date
    TaskQuery subTaskQueryForCreatedDate = TaskQuery.create();
    Date createdDateFrom = definition.getCreatedDateFrom();
    Date createdDateTo = definition.getCreatedDateTo();
    if (createdDateFrom != null || createdDateTo != null) {
      if (createdDateFrom != null) {
        subTaskQueryForCreatedDate.where().startTimestamp().isGreaterOrEqualThan(createdDateFrom);
      }
      if (createdDateTo != null) {
        subTaskQueryForCreatedDate.where().startTimestamp().isLowerOrEqualThan(createdDateTo);
      }
      taskQuery.where().and(subTaskQueryForCreatedDate);
    }

    // Expiry date
    TaskQuery subTaskQueryForExpiryDate = TaskQuery.create();
    Date expiryDateFrom = definition.getCreatedDateFrom();
    Date expiryDateTo = definition.getCreatedDateTo();
    if (expiryDateFrom != null || expiryDateTo != null) {
      if (expiryDateFrom != null) {
        subTaskQueryForExpiryDate.where().expiryTimestamp().isGreaterOrEqualThan(expiryDateFrom);
      }
      if (createdDateTo != null) {
        subTaskQueryForExpiryDate.where().expiryTimestamp().isLowerOrEqualThan(expiryDateTo);
      }
      taskQuery.where().and(subTaskQueryForExpiryDate);
    }
    
    return taskQuery;
  }

}