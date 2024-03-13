package com.axonivy.portal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.ai.IvyToolResultType;
import com.axonivy.portal.components.service.impl.ProcessService;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class AssistantUtils {

  public static TaskDashboardWidget convertIvyToolToTaskDashboardWidget(
      String name, String description, String priority, String state) {

    TaskDashboardWidget result = DashboardWidgetUtils
        .buildDefaultTaskWidget("ai-result", "ai-result");
    List<TaskColumnModel> columns = new ArrayList<>();
    for (TaskColumnModel col : result.getColumns()) {
      DashboardStandardTaskColumn colEnum = DashboardStandardTaskColumn
          .findBy(col.getField());
      if (!DashboardStandardTaskColumn.AI_RESULT_COLUMNS.contains(colEnum)) {
        col.setVisible(false);
      }

      if (colEnum == DashboardStandardTaskColumn.ID) {
        col.setStyle("max-width: 150px;");
      }

      if (colEnum == DashboardStandardTaskColumn.ACTIONS) {
        col.setStyle("max-width: 150px");
      }

      if (StringUtils.isNotBlank(name)
          && colEnum == DashboardStandardTaskColumn.NAME) {
        col.setFilter(name);
      }
      if (StringUtils.isNotBlank(description)
          && colEnum == DashboardStandardTaskColumn.DESCRIPTION) {
        col.setFilter(description);
      }
      if (StringUtils.isNotBlank(priority)
          && colEnum == DashboardStandardTaskColumn.PRIORITY) {
        WorkflowPriority priorityEnum = initPriority(priority);
        if (priorityEnum == null) {
          if (result.getErrors() == null) {
            result.setErrors(new ArrayList<>());
            result.getErrors().add("Invalid value '" + priority
                + "'for priority. Priority only accept these values: 'low', 'normal', 'high', or 'exception'");
          }
        } else {
          col.setFilterList(Arrays.asList(priorityEnum.name()));
        }
      }
      if (StringUtils.isNotBlank(state)
          && colEnum == DashboardStandardTaskColumn.STATE) {
        TaskBusinessState stateEnum = initTaskState(state);
        col.setFilterList(Arrays.asList(stateEnum.name()));
      }
    }

    result.buildFilterableColumns(columns);
    result.buildPredefinedFilterData();

    return result;
  }

  public static CaseDashboardWidget convertIvyToolToCaseDashboardWidget(
      String name, String description, String state) {

    CaseDashboardWidget result = DashboardWidgetUtils
        .buildDefaultCaseWidget("ai-result", "ai-result");
    List<CaseColumnModel> columns = new ArrayList<>();
    for (CaseColumnModel col : result.getColumns()) {
      DashboardStandardCaseColumn colEnum = DashboardStandardCaseColumn
          .findBy(col.getField());
      if (!DashboardStandardCaseColumn.AI_RESULT_COLUMNS.contains(colEnum)) {
        col.setVisible(false);
      }

      if (colEnum == DashboardStandardCaseColumn.ID) {
        col.setStyle("max-width: 150px;");
      }

      if (colEnum == DashboardStandardCaseColumn.ACTIONS) {
        col.setStyle("max-width: 150px");
      }

      if (StringUtils.isNotBlank(name)
          && colEnum == DashboardStandardCaseColumn.NAME) {
        col.setFilter(name);
      }
      if (StringUtils.isNotBlank(description)
          && colEnum == DashboardStandardCaseColumn.DESCRIPTION) {
        col.setFilter(description);
      }
      if (StringUtils.isNotBlank(state)
          && colEnum == DashboardStandardCaseColumn.STATE) {
        CaseBusinessState stateEnum = initCaseState(state);
        col.setFilterList(Arrays.asList(stateEnum.name()));
      }
    }

    result.buildFilterableColumns(columns);
    result.buildPredefinedFilterData();

    return result;
  }

  private static WorkflowPriority initPriority(String priority) {
    return switch (priority) {
    case "low" -> WorkflowPriority.LOW;
    case "normal" -> WorkflowPriority.NORMAL;
    case "high" -> WorkflowPriority.HIGH;
    case "exception" -> WorkflowPriority.EXCEPTION;
    default -> null;
    };
  }

  private static TaskBusinessState initTaskState(String state) {
    return switch (state.toLowerCase()) {
    case "open" -> TaskBusinessState.OPEN;
    case "in progress" -> TaskBusinessState.IN_PROGRESS;
    case "done" -> TaskBusinessState.DONE;
    default -> null;
    };
  }

  private static CaseBusinessState initCaseState(String state) {
    return switch (state.toLowerCase()) {
    case "open" -> CaseBusinessState.OPEN;
    case "destroyed" -> CaseBusinessState.DESTROYED;
    case "done" -> CaseBusinessState.DONE;
    default -> null;
    };
  }

  public static String generateErrorResult(String error) {
    if (StringUtils.isBlank(error)) {
      return "";
    }
    return IvyToolResultType.ERROR.format(error);
  }

  public static String generateLinkToIvyProcess(String link,
      Map<String, String> params) {
    try {
      IWebStartable process = initWebStartable(link);
      return IvyToolResultType.IFRAME
          .format(process.getLink().queryParams(params).getRelative());
    } catch (Exception e) {
      return IvyToolResultType.ERROR.format(
          "Error happened when proceed your request. Please try again.");
    }
  }

  private static IWebStartable initWebStartable(String processPath) {
    if (StringUtils.isBlank(processPath)) {
      return null;
    }

    return ProcessService.getInstance().findAllProcesses().stream()
        .filter(process -> process.getId().contentEquals(processPath))
        .findFirst().orElse(null);
  }

  public static String validateTaskState(String stateStr) {
    if (StringUtils.isBlank(stateStr)) {
      return null;
    }

    TaskBusinessState stateEnum = initTaskState(stateStr);
    if (stateEnum == null) {
      return "Invalid task state. Valid task states are: open, done, in progress, or error.";
    }
    return null;
  }

  public static String validateTaskPriority(String priorityStr) {
    if (StringUtils.isBlank(priorityStr)) {
      return null;
    }

    WorkflowPriority priorityEnum = initPriority(priorityStr);
    if (priorityEnum == null) {
      return "Invalid task priority. Valid task priorities are: low, normal, high, or exception.";
    }
    return null;
  }

  public static String validateCaseState(String stateStr) {
    if (StringUtils.isBlank(stateStr)) {
      return null;
    }

    CaseBusinessState stateEnum = initCaseState(stateStr);
    if (stateEnum == null) {
      return "Invalid task state. Valid case states are: open, done, or destroyed.";
    }
    return null;
  }
}
