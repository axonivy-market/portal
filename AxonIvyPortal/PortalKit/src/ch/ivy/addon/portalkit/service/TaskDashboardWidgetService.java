package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.TaskDashboardWidget;
import ch.ivy.addon.portalkit.enums.TaskDashboardWidgetType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskDashboardWidgetService {
  public TaskQuery loadTaskQuery(TaskDashboardWidget definition) {
    if (definition.getName() == null) {
      return TaskQuery.create();
    }

    TaskQuery taskQuery = TaskQuery.create();

    // Name
    if (StringUtils.isNotBlank(definition.getTaskName())) {
      String containingKeyword = String.format("%%%s%%", definition.getTaskName());
      taskQuery.where().name().isLikeIgnoreCase(containingKeyword);
    }

    // Description
    if (StringUtils.isNotBlank(definition.getDescription())) {
      String containingKeyword = String.format("%%%s%%", definition.getTaskName());
      taskQuery.where().description().isLikeIgnoreCase(containingKeyword);
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
    if (definition.getTaskDashboardWidgetType() == TaskDashboardWidgetType.PERSONAL) {
      taskQuery.where().and().activatorUserId().isEqual(Ivy.session().getSessionUser().getId());
    } else if (CollectionUtils.isNotEmpty(definition.getResponsibles()) || CollectionUtils.isNotEmpty(definition.getRoles())) {
      TaskQuery responsibleQuery = TaskQuery.create();
      if (CollectionUtils.isNotEmpty(definition.getResponsibles())) {
        definition.getResponsibles().forEach(user -> responsibleQuery.where().or().activatorName().isEqual("#".concat(user)));
      }
      if (CollectionUtils.isNotEmpty(definition.getRoles())) {
        definition.getRoles().forEach(role -> responsibleQuery.where().or().activatorName().isEqual(role));
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
    
    // Category
    
    return taskQuery;
  }

}