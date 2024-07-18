package com.axonivy.portal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;
import com.axonivy.portal.util.filter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.process.NameColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.process.ProcessColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
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

    result.setFilters(new ArrayList<>());
    

    for (TaskColumnModel col : result.getColumns()) {
      DashboardStandardTaskColumn colEnum = DashboardStandardTaskColumn
          .findBy(col.getField());
      if (!DashboardStandardTaskColumn.AI_RESULT_COLUMNS.contains(colEnum)) {
        col.setVisible(false);
      }

      switch (colEnum) {
      case ID, ACTIONS -> col.setStyle(DEFAULT_MAX_WIDTH_STYLE);
      case NAME -> result.getFilters().add(initTaskNameFilter(name));
        case DESCRIPTION ->
        result.getFilters().add(initTaskDescriptionFilter(description));
      case PRIORITY ->
        result.getFilters().add(initTaskPriorityFilter(priority));
      case STATE -> result.getFilters().add(initTaskStateFilter(state));
        default -> {}
      }
    }

    result.buildFilterableColumns(columns);

    return result;
  }

  private static DashboardFilter initTaskNameFilter(String name) {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(DashboardStandardTaskColumn.NAME.getField());
    filter.setFilterType(DashboardColumnType.STANDARD);

    FilterField field = TaskFilterFieldFactory
        .findBy(DashboardStandardTaskColumn.NAME.getField());
    filter.setFilterField(field);
    field.addNewFilter(filter);
    filter.setValues(Arrays.asList(name));
    return filter;
  }
  
  private static DashboardFilter initTaskDescriptionFilter(String description) {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(DashboardStandardTaskColumn.DESCRIPTION.getField());
    filter.setFilterType(DashboardColumnType.STANDARD);

    FilterField field = TaskFilterFieldFactory
        .findBy(DashboardStandardTaskColumn.DESCRIPTION.getField());
    filter.setFilterField(field);
    field.addNewFilter(filter);
    filter.setValues(Arrays.asList(description));
    return filter;
  }

  private static DashboardFilter initTaskPriorityFilter(String priority) {
    WorkflowPriority priorityEnum = initPriority(priority);
    if (priorityEnum != null) {
      DashboardFilter filter = new DashboardFilter();
      filter.setField(DashboardStandardTaskColumn.PRIORITY.getField());
      filter.setFilterType(DashboardColumnType.STANDARD);

      FilterField field = TaskFilterFieldFactory
          .findBy(DashboardStandardTaskColumn.PRIORITY.getField());
      filter.setFilterField(field);
      field.addNewFilter(filter);
      filter.setValues(Arrays.asList(priorityEnum.name()));
      return filter;
    }

    return null;
  }

  private static DashboardFilter initTaskStateFilter(String state) {
    TaskBusinessState stateEnum = initTaskState(state);
    if (stateEnum != null) {
      DashboardFilter filter = new DashboardFilter();
      filter.setField(DashboardStandardTaskColumn.STATE.getField());
      filter.setFilterType(DashboardColumnType.STANDARD);

      FilterField field = TaskFilterFieldFactory
          .findBy(DashboardStandardTaskColumn.STATE.getField());
      filter.setFilterField(field);
      field.addNewFilter(filter);
      filter.setValues(Arrays.asList(stateEnum.name()));
      return filter;
    }

    return null;
  }

  public static CaseDashboardWidget convertIvyToolToCaseDashboardWidget(
      String name, String description, String state) {

    CaseDashboardWidget result = DashboardWidgetUtils
        .buildDefaultCaseWidget(DEFAULT_AI_WIDGET_ID, DEFAULT_AI_WIDGET_ID);
    List<CaseColumnModel> columns = new ArrayList<>();

    result.setFilters(new ArrayList<>());

    for (CaseColumnModel col : result.getColumns()) {
      DashboardStandardCaseColumn colEnum = DashboardStandardCaseColumn
          .findBy(col.getField());

      if (!DashboardStandardCaseColumn.AI_RESULT_COLUMNS.contains(colEnum)) {
        col.setVisible(false);
      }

      switch (colEnum) {
        case ID, ACTIONS -> col.setStyle(DEFAULT_MAX_WIDTH_STYLE);
        case NAME -> result.getFilters().add(initCaseNameFilter(name));
        case DESCRIPTION ->
          result.getFilters().add(initCaseDescriptionFilter(name));
        case STATE -> result.getFilters().add(initCaseStateFilter(state));
        default -> {}
      }
    }

    result.buildFilterableColumns(columns);

    return result;
  }
  
  public static ProcessDashboardWidget convertIvyToolToProcessDashboardWidget(
      String name, String description) {
    ProcessDashboardWidget result = DashboardWidgetUtils
        .buildDefaultProcessWidget(DEFAULT_AI_WIDGET_ID, DEFAULT_AI_WIDGET_ID);
    List<ProcessColumnModel> columns = new ArrayList<>();
    if (StringUtils.isNotBlank(name)) {
      NameColumnModel nameCol = new NameColumnModel();
      nameCol.setUserFilter(name);
      nameCol.setField("name");
      columns.add(nameCol);
    }

    result.buildFilterableColumns(columns);
    return result;
  }

  private static DashboardFilter initCaseNameFilter(String name) {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(DashboardStandardCaseColumn.NAME.getField());
    filter.setFilterType(DashboardColumnType.STANDARD);

    FilterField field = FilterFieldFactory
        .findBy(DashboardStandardCaseColumn.NAME.getField());
    filter.setFilterField(field);
    field.addNewFilter(filter);
    filter.setValues(Arrays.asList(name));
    return filter;
  }

  private static DashboardFilter initCaseDescriptionFilter(String description) {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(DashboardStandardCaseColumn.DESCRIPTION.getField());
    filter.setFilterType(DashboardColumnType.STANDARD);

    FilterField field = FilterFieldFactory
        .findBy(DashboardStandardCaseColumn.DESCRIPTION.getField());
    filter.setFilterField(field);
    field.addNewFilter(filter);
    filter.setValues(Arrays.asList(description));
    return filter;
  }

  private static DashboardFilter initCaseStateFilter(String state) {
    CaseBusinessState stateEnum = initCaseState(state);
    if (stateEnum != null) {
      DashboardFilter filter = new DashboardFilter();
      filter.setField(DashboardStandardCaseColumn.STATE.getField());
      filter.setFilterType(DashboardColumnType.STANDARD);

      FilterField field = FilterFieldFactory
          .findBy(DashboardStandardCaseColumn.STATE.getField());
      filter.setFilterField(field);
      field.addNewFilter(filter);
      filter.setValues(Arrays.asList(stateEnum.name()));
      return filter;
    }

    return null;
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
