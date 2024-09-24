package com.axonivy.portal.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;
import com.axonivy.portal.util.filter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portalkit.bean.DashboardProcessBean;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.process.DescriptionColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.process.NameColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.process.ProcessColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class AiToolUtils {

  private static final String DEFAULT_AI_WIDGET_ID = "ai-result";
  private static final String DEFAULT_MAX_WIDTH_STYLE = "max-width: 150px;";
  private static final String DEFAULT_NAME_MIN_WITH_STYLE = "min-width: 200px;";

  public static TaskDashboardWidget convertIvyToolToTaskDashboardWidget(
      String name, String description, String priority, String state,
      String taskExpiryDateFrom, String taskExpiryDateTo, String onlyMyTask) {

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
      case NAME ->  { 
        if (StringUtils.isNotBlank(name)) {
          result.getFilters().add(initTaskNameFilter(name));
        }
        col.setStyle(DEFAULT_NAME_MIN_WITH_STYLE);
      }
      case DESCRIPTION -> {
        if (StringUtils.isNotBlank(description)) {
          result.getFilters().add(initTaskDescriptionFilter(description));
        }
      }
      case PRIORITY ->{
        if (StringUtils.isNotBlank(priority)) {
          result.getFilters().add(initTaskPriorityFilter(priority));
        }
      }
      case STATE -> {
        if (StringUtils.isNotBlank(state)) {
        result.getFilters().add(initTaskStateFilter(state));
        }
      }
      case EXPIRY -> {
        if (StringUtils.isNotBlank(taskExpiryDateFrom)
            || StringUtils.isNotBlank(taskExpiryDateTo)) {
          result.getFilters().add(
              initTaskExpiryDateFilter(taskExpiryDateFrom, taskExpiryDateTo));
        }
      }
      default -> {}
      }
    }
    
    if (StringUtils.isNotBlank(onlyMyTask)
        && BooleanUtils.toBoolean(onlyMyTask)) {
      DashboardFilter filter = new DashboardFilter();
      filter.setField(DashboardStandardTaskColumn.RESPONSIBLE.getField());
      filter.setFilterType(DashboardColumnType.STANDARD);

      FilterField field = TaskFilterFieldFactory
          .findBy(DashboardStandardTaskColumn.RESPONSIBLE.getField());
      filter.setFilterField(field);
      field.addNewFilter(filter);
      filter.setValues(Arrays.asList(Ivy.session().getSessionUserName()));
      result.getFilters().add(filter);
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
    Optional<List<String>> priorities = Optional
        .ofNullable(Arrays.asList(priority.split(",")));

    if (priorities.isEmpty()) {
      return null;
    }

    DashboardFilter filter = new DashboardFilter();
    filter.setField(DashboardStandardTaskColumn.PRIORITY.getField());
    filter.setFilterType(DashboardColumnType.STANDARD);

    FilterField field = TaskFilterFieldFactory
        .findBy(DashboardStandardTaskColumn.PRIORITY.getField());
    filter.setFilterField(field);
    field.addNewFilter(filter);
    filter.setValues(new ArrayList<>());

    for (String priorityStr : priorities.get()) {
      WorkflowPriority priorityEnum = initPriority(priorityStr);
      if (priorityEnum != null) {
        filter.getValues().add(priorityEnum.name());
      }
    }

    return filter;
  }

  private static DashboardFilter initTaskStateFilter(String state) {
    Optional<List<String>> states = Optional
        .ofNullable(Arrays.asList(state.split(",")));

    if (states.isEmpty()) {
      return null;
    }

    DashboardFilter filter = new DashboardFilter();
    filter.setField(DashboardStandardTaskColumn.STATE.getField());
    filter.setFilterType(DashboardColumnType.STANDARD);

    FilterField field = TaskFilterFieldFactory
        .findBy(DashboardStandardTaskColumn.STATE.getField());
    filter.setFilterField(field);
    field.addNewFilter(filter);
    filter.setValues(new ArrayList<>());

    for (String stateStr : states.get()) {
      TaskBusinessState stateEnum = initTaskState(stateStr);
      if (stateEnum != null) {
        filter.getValues().add(stateEnum.name());
      }
    }

  return filter;
  }

  private static DashboardFilter initTaskExpiryDateFilter(String expiryDateFrom,
      String expiryDateTo) {
    Date fromDate = null;
    Date toDate = null;
    try {
      fromDate = DateTimeGlobalSettingService.getInstance()
          .getDefaultDateFormatter().parse(expiryDateFrom);
      toDate = DateTimeGlobalSettingService.getInstance()
          .getDefaultDateFormatter().parse(expiryDateTo);
    } catch (ParseException e) {
      return null;
    }

    DashboardFilter filter = new DashboardFilter();
    filter.setField(DashboardStandardTaskColumn.EXPIRY.getField());
    filter.setFilterType(DashboardColumnType.STANDARD);

    FilterField field = TaskFilterFieldFactory
        .findBy(DashboardStandardTaskColumn.EXPIRY.getField());
    filter.setFilterField(field);
    field.addNewFilter(filter);

    filter.setFrom(expiryDateFrom);
    filter.setFromDate(fromDate);
    filter.setTo(expiryDateTo);
    filter.setToDate(toDate);
    filter.setOperator(FilterOperator.BETWEEN);
    return filter;
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
        case NAME -> {
          if (StringUtils.isNotBlank(name)) {
            result.getFilters().add(initCaseNameFilter(name));
          }
        }
        case DESCRIPTION -> {
          if (StringUtils.isNotBlank(description)) {
            result.getFilters().add(initCaseDescriptionFilter(name));
          }
        }
        case STATE -> {
          if (StringUtils.isNotBlank(state)) {
            result.getFilters().add(initCaseStateFilter(state));
          }
        }
        default -> {}
      }
    }

    result.buildFilterableColumns(columns);

    return result;
  }
  
  public static ProcessDashboardWidget convertIvyToolToProcessDashboardWidget(
      String name, String description) {
    CompactProcessDashboardWidget result = (CompactProcessDashboardWidget) DashboardWidgetUtils
        .buildDefaultProcessWidget(DEFAULT_AI_WIDGET_ID, DEFAULT_AI_WIDGET_ID);
    List<ProcessColumnModel> columns = new ArrayList<>();

    if (StringUtils.isNotBlank(name)) {
      NameColumnModel nameCol = new NameColumnModel();
      nameCol.setUserFilter(name);
      nameCol.setField(DashboardStandardProcessColumn.NAME.getField());
      columns.add(nameCol);
    }

    if (StringUtils.isNotBlank(description)) {
      DescriptionColumnModel descriptionCol = new DescriptionColumnModel();
      descriptionCol.setUserFilter(description);
      descriptionCol
          .setField(DashboardStandardProcessColumn.DESCRIPTION.getField());
      columns.add(descriptionCol);
    }

    result.buildFilterableColumns(columns);

    // Init processes
    DashboardProcessBean bean = new DashboardProcessBean();
    bean.init();
    result.setOriginalDisplayProcesses(bean.getPortalDashboardProcesses());
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
    Optional<List<String>> states = Optional
        .ofNullable(Arrays.asList(state.split(",")));

    if (states.isEmpty()) {
      return null;
    }

    DashboardFilter filter = new DashboardFilter();
    filter.setField(DashboardStandardCaseColumn.STATE.getField());
    filter.setFilterType(DashboardColumnType.STANDARD);

    FilterField field = FilterFieldFactory
        .findBy(DashboardStandardCaseColumn.STATE.getField());
    filter.setFilterField(field);
    field.addNewFilter(filter);
    filter.setValues(new ArrayList<>());

    for (String stateStr : states.get()) {
      CaseBusinessState stateEnum = initCaseState(stateStr);
      if (stateEnum != null) {
        filter.getValues().add(stateEnum.name());
      }
    }

    return filter;
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

    List<String> states = Optional
        .ofNullable(Arrays.asList(stateStr.split(",")))
        .orElse(Collections.emptyList());

    for (String state : states) {
      TaskBusinessState stateEnum = initTaskState(state);
      if (stateEnum == null) {
        return Ivy.cms().co("/Labels/AI/Error/InvalidTaskState");
      }
    }

    return null;
  }

  public static String validateTaskPriority(String priorityStr) {
    if (StringUtils.isBlank(priorityStr)) {
      return null;
    }

    List<String> priorities = Optional
        .ofNullable(Arrays.asList(priorityStr.split(",")))
        .orElse(Collections.emptyList());

    for (String priority : priorities) {
      WorkflowPriority priorityEnum = initPriority(priority);
      if (priorityEnum == null) {
        return Ivy.cms().co("/Labels/AI/Error/InvalidTaskPriority");
      }
    }

    return null;
  }

  public static String validateCaseState(String stateStr) {
    if (StringUtils.isBlank(stateStr)) {
      return null;
    }

    List<String> states = Optional
        .ofNullable(Arrays.asList(stateStr.split(",")))
        .orElse(Collections.emptyList());

    for (String state : states) {
      CaseBusinessState stateEnum = initCaseState(state);
      if (stateEnum == null) {
        return Ivy.cms().co("/Labels/AI/Error/InvalidTaskState");
      }
    }
    return null;
  }
}
