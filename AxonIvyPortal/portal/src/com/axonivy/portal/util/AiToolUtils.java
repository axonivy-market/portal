package com.axonivy.portal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class AiToolUtils {

  private static final String DEFAULT_AI_WIDGET_ID = "ai-result";
  private static final String DEFAULT_MAX_WIDTH_STYLE = "max-width: 150px;";

  public static TaskDashboardWidget convertIvyToolToTaskDashboardWidget(
      String name, String description, String priority, String state) {

    TaskDashboardWidget result = DashboardWidgetUtils
        .buildDefaultTaskWidget(DEFAULT_AI_WIDGET_ID, DEFAULT_AI_WIDGET_ID);
    List<TaskColumnModel> columns = new ArrayList<>();

    for (TaskColumnModel col : result.getColumns()) {
      DashboardStandardTaskColumn colEnum = DashboardStandardTaskColumn
          .findBy(col.getField());
      if (!DashboardStandardTaskColumn.AI_RESULT_COLUMNS.contains(colEnum)) {
        col.setVisible(false);
      }

      switch (colEnum) {
        case ID, ACTIONS -> col.setStyle(DEFAULT_MAX_WIDTH_STYLE);
        case NAME -> col.setFilter(name);
        case DESCRIPTION -> col.setFilter(description);
        case PRIORITY -> {
          WorkflowPriority priorityEnum = initPriority(priority);
          if (priorityEnum != null) {
            col.setFilterList(Arrays.asList(priorityEnum.name()));
          }
        }
        case STATE -> {
          TaskBusinessState stateEnum = initTaskState(state);
          if (stateEnum != null) {
            col.setFilterList(Arrays.asList(stateEnum.name()));
          }
        }
        default -> {}
      }
    }

    result.buildFilterableColumns(columns);
    result.buildPredefinedFilterData();

    return result;
  }

  public static CaseDashboardWidget convertIvyToolToCaseDashboardWidget(
      String name, String description, String state) {

    CaseDashboardWidget result = DashboardWidgetUtils
        .buildDefaultCaseWidget(DEFAULT_AI_WIDGET_ID, DEFAULT_AI_WIDGET_ID);
    List<CaseColumnModel> columns = new ArrayList<>();

    for (CaseColumnModel col : result.getColumns()) {
      DashboardStandardCaseColumn colEnum = DashboardStandardCaseColumn
          .findBy(col.getField());

      if (!DashboardStandardCaseColumn.AI_RESULT_COLUMNS.contains(colEnum)) {
        col.setVisible(false);
      }

      switch (colEnum) {
        case ID, ACTIONS -> col.setStyle(DEFAULT_MAX_WIDTH_STYLE);
        case NAME -> col.setFilter(name);
        case DESCRIPTION -> col.setFilter(description);
        case STATE -> {
          CaseBusinessState stateEnum = initCaseState(state);
          col.setFilterList(Arrays.asList(stateEnum.name()));
        }
        default -> {}
      }
    }

    result.buildFilterableColumns(columns);
    result.buildPredefinedFilterData();

    return result;
  }

  private static WorkflowPriority initPriority(String priority) {
    for (var prioEnum : WorkflowPriority.values()) {
      if (prioEnum.name().toLowerCase().contentEquals(
          Optional.ofNullable(priority).orElse("").toLowerCase())) {
        return prioEnum;
      }
    }
    return null;
  }

  private static TaskBusinessState initTaskState(String state) {
    for (var stateEnum : TaskBusinessState.values()) {
      if (stateEnum.name().toLowerCase()
          .contentEquals(Optional.ofNullable(state).orElse("").toLowerCase())) {
        return stateEnum;
      }
    }
    return null;
  }

  private static CaseBusinessState initCaseState(String state) {
    for (var stateEnum : CaseBusinessState.values()) {
      if (stateEnum.name().toLowerCase()
          .contentEquals(Optional.ofNullable(state).orElse("").toLowerCase())) {
        return stateEnum;
      }
    }
    return null;
  }

  public static String validateTaskState(String stateStr) {
    if (StringUtils.isBlank(stateStr)) {
      return null;
    }

    TaskBusinessState stateEnum = initTaskState(stateStr);
    if (stateEnum == null) {
      return Ivy.cms().co("/Labels/AI/Error/InvalidTaskState");
    }
    return null;
  }

  public static String validateTaskPriority(String priorityStr) {
    if (StringUtils.isBlank(priorityStr)) {
      return null;
    }

    WorkflowPriority priorityEnum = initPriority(priorityStr);
    if (priorityEnum == null) {
      return Ivy.cms().co("/Labels/AI/Error/InvalidTaskPriority");
    }
    return null;
  }

  public static String validateCaseState(String stateStr) {
    if (StringUtils.isBlank(stateStr)) {
      return null;
    }

    CaseBusinessState stateEnum = initCaseState(stateStr);
    if (stateEnum == null) {
      return Ivy.cms().co("/Labels/AI/Error/InvalidTaskState");
    }
    return null;
  }
}
