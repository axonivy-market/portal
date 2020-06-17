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

public class TaskDashboardWidgetService {
  public TaskQuery loadTaskQuery(TaskDashboardWidget definition) {
    if (definition.getTaskName() == null) {
      return TaskQuery.create();
    }

    TaskQuery taskQuery = TaskQuery.create();

    // Name
    if (StringUtils.isNotBlank(definition.getTaskName())) {
      taskQuery.where().and().name().isLike(definition.getTaskName());
    }

    // Description
    if (StringUtils.isNotBlank(definition.getDescription())) {
      taskQuery.where().and().description().isLike(definition.getDescription());
    }

    // Priority
    TaskQuery prioritySubQuery = TaskQuery.create();
    if (CollectionUtils.isNotEmpty(definition.getPriorities())) {
      definition.getPriorities().forEach(priority -> prioritySubQuery.where().or().priority().isEqual(priority));
    } else {
      List<WorkflowPriority> priorities = Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH, WorkflowPriority.NORMAL, WorkflowPriority.LOW);
      priorities.forEach(priority -> prioritySubQuery.where().and().priority().isNotEqual(priority));
    }
    taskQuery.where().and(prioritySubQuery);

    // State
    TaskQuery stateSubQuery = TaskQuery.create();
    if (CollectionUtils.isNotEmpty(definition.getStates())) {
      definition.getStates().forEach(state -> stateSubQuery.where().or().state().isEqual(state));
    } else {
      List<TaskState> states = Arrays.asList(TaskState.CREATED, TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE);
      states.forEach(state -> stateSubQuery.where().and().state().isNotEqual(state));
    }
    taskQuery.where().and(stateSubQuery);

    // Responsible
    if (StringUtils.isNotBlank(definition.getResponsible())) {
      taskQuery.where().and().activatorName().isEqual(definition.getResponsible());
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
    
    // Category
    
    return taskQuery;
  }

}