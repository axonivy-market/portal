package com.axonivy.portal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.ai.IvyToolResultType;
import com.axonivy.portal.components.service.impl.ProcessService;

import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class AssistantUtils {

  public static TaskDashboardWidget convertIvyToolToTaskDashboardWidget(String name, String description,
      String priority, String state) {

    TaskDashboardWidget result = DashboardWidgetUtils.buildDefaultTaskWidget("ai-temp", "ai-temp");
    List<TaskColumnModel> columns = new ArrayList<>();
    for (TaskColumnModel col : result.getColumns()) {

      if (StringUtils.isNotBlank(name) && col.getField().contentEquals(DashboardStandardTaskColumn.NAME.getField())) {
        col.setFilter(name);
      }
      if (StringUtils.isNotBlank(description)
          && col.getField().contentEquals(DashboardStandardTaskColumn.DESCRIPTION.getField())) {
        col.setFilter(description);
      }
      if (StringUtils.isNotBlank(priority)
          && col.getField().contentEquals(DashboardStandardTaskColumn.PRIORITY.getField())) {
        WorkflowPriority priorityEnum = initPriority(priority);
        if (priorityEnum == null) {
          if (result.getErrors() == null) {
            result.setErrors(new ArrayList<>());
            result.getErrors().add("Invalid value '" + priority + "'for priority. Priority only accept these values: 'low', 'normal', 'high', or 'exception'");
          }
        } else {
          col.setFilterList(Arrays.asList(priorityEnum.name()));
        }
      }
      if (StringUtils.isNotBlank(state) && col.getField().contentEquals(DashboardStandardTaskColumn.STATE.getField())) {
        TaskBusinessState stateEnum = initState(state);
        if (stateEnum == null) {
          if (result.getErrors() == null) {
                result.setErrors(new ArrayList<>());
                result.getErrors().add("Invalid value '" + state + "'for state. State only accept these values: 'open', 'in progress', or 'done'");
              }
        } else {
          col.setFilterList(Arrays.asList(stateEnum.name()));
        }
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

  private static TaskBusinessState initState(String state) {
    return switch (state) {
    case "open" -> TaskBusinessState.OPEN;
    case "in progress" -> TaskBusinessState.IN_PROGRESS;
    case "done" -> TaskBusinessState.DONE;
    default -> null;
    };
  }

  public static String generateErrorResult(String error) {
    if (StringUtils.isBlank(error)) {
      return "";
    }
    return IvyToolResultType.ERROR.format(error);
  }

  public static String generateLinkToIvyProcess(String link, Map<String, String> params) {
    try {
      IWebStartable process = initWebStartable(link);
      return IvyToolResultType.IFRAME.format(process.getLink().queryParams(params).getRelative());
    } catch (Exception e) {
      return IvyToolResultType.ERROR.format("Error happened when proceed your request. Please try again.");
    }
  }
  
  private static IWebStartable initWebStartable(String processPath) {
    if (StringUtils.isBlank(processPath)) {
      return null;
    }

    return ProcessService.getInstance().findProcesses().stream()
        .filter(process -> process.getId().contentEquals(processPath)).findFirst().orElse(null);
  }

  public static String validateTaskState(String stateStr) {
    if (StringUtils.isBlank(stateStr)) {
      return null;
    }

    TaskBusinessState stateEnum = initState(stateStr);
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
}
