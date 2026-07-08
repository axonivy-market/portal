package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import jakarta.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortMeta;

import com.axonivy.portal.components.service.DateTimeGlobalSettingService;

import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;


/**
 * Provide the utilities related to Task.
 * 
 * @author bolt
 *
 */
@Named("taskBean")
public class TaskBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String TASK_BUSINESS_STATE_CMS_PATH = "/ch.ivy.addon.portalkit.ui.jsf/taskBusinessState/";

  public String getPriority(WorkflowPriority priority) {
    return cms("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + priority.name());
  }

  public boolean isNotDone(ITask task) {
    if (task == null) {
      return false;
    }
    EnumSet<TaskBusinessState> taskStages = EnumSet.of(TaskBusinessState.OPEN, TaskBusinessState.IN_PROGRESS);
    return taskStages.contains(task.getBusinessState());
  }

  public boolean isDone(ITask task) {
    return !isNotDone(task);
  }

  /**
   * Get the state of task
   * 
   * @param state
   * @return the state of task
   */
  public String getTranslatedState(TaskBusinessState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    return cms(TASK_BUSINESS_STATE_CMS_PATH + state);
  }

  private String cms(String uri) {
    return Ivy.cms().co(uri);
  }

  public String getTaskBusinessState(TaskBusinessState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    return cms(TASK_BUSINESS_STATE_CMS_PATH + state);
  }

  public String displayCaseName(ITask task) {
    ICase iCase = task.getCase().getBusinessCase();
    String caseName = iCase.names().current();
    return StringUtils.isNotEmpty(caseName) ? caseName : "#" + iCase.getId();
  }

  public String getTechnicalCaseDisplayName(ITask task) {
    ICase technicalCase = task.getCase();
    if (technicalCase != null) {
      if (StringUtils.isNotBlank(technicalCase.names().current())) {
        return technicalCase.names().current();
      }
      return String.valueOf(technicalCase.getId());
    }
    return StringUtils.EMPTY;
  }
  
  /**
   * Get failed reason of task FAILED or JOIN_FAILED
   * @param task
   * @return failedReason see more {@link ITask#getFailReason()}
   */
  public String getTaskFailedReason(ITask task) {
    if ((task.getBusinessState() == TaskBusinessState.ERROR)
         && StringUtils.isNotEmpty(task.getFailReason())) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/taskFailReason", Arrays.asList(task.getFailReason()));
    }
    return StringUtils.EMPTY;
  }

  public SortMeta getTaskWorkflowEventSortByTimestamp() {
    return SortFieldUtil.buildSortMeta("timestamp", true);
  }
  
  public String getAriaLabel(ITask task, List<TaskColumnModel> columns) {
    List<String> displayTexts = new ArrayList<>();
    for (TaskColumnModel col : columns) {
      if (!col.getVisible()) {
        continue;
      }
      String text = ariaLabelFor(task, col);
      if (text != null) {
        displayTexts.add(text);
      }
    }
    return String.join(" - ", displayTexts);
  }

  private String ariaLabelFor(ITask task, TaskColumnModel col) {
    String field = col.getField();
    if (DashboardStandardTaskColumn.START.getField().equalsIgnoreCase(field)) {
      return cms("/ch.ivy.addon.portalkit.ui.jsf/dashboard/taskStart");
    }
    if (DashboardStandardTaskColumn.PRIORITY.getField().equalsIgnoreCase(field)) {
      return col.getHeaderText() + ": " + getPriority(task.getPriority());
    }
    if (DashboardStandardTaskColumn.STATE.getField().equalsIgnoreCase(field)) {
      return col.getHeaderText() + ": " + getTaskBusinessState(task.getBusinessState());
    }
    if (DashboardStandardTaskColumn.CREATED.getField().equalsIgnoreCase(field)) {
      return col.getHeaderText() + ": " + formatDateTime(task.getStartTimestamp());
    }
    if (DashboardStandardTaskColumn.COMPLETED.getField().equalsIgnoreCase(field)) {
      return task.getEndTimestamp() == null ? null
          : col.getHeaderText() + ": " + formatDateTime(task.getEndTimestamp());
    }
    if (DashboardStandardTaskColumn.EXPIRY.getField().equalsIgnoreCase(field)) {
      return task.getExpiryTimestamp() == null ? null
          : col.getHeaderText() + ": " + formatDateTime(task.getExpiryTimestamp());
    }
    Object displayObject = col.display(task);
    if (displayObject == null) {
      return null;
    }
    String displayText = displayObject.toString();
    return StringUtils.isEmpty(displayText) ? null
        : col.getHeaderText() + ": " + displayText;
  }

  private static String formatDateTime(java.util.Date timestamp) {
    return new SimpleDateFormat(
        DateTimeGlobalSettingService.getInstance().getGlobalDateTimePattern()).format(timestamp);
  }

  public boolean isPinnedTask(ITask task) {
    return TaskUtils.isPinnedTask(task);
  }
  
  public void clickOnPinTask(ITask task) {
    if (task == null)
      return;

    String taskUuid = task.uuid();
    Set<String> pinnedTaskUuids = TaskUtils.getPinnedTaskUuids();

    if (!pinnedTaskUuids.remove(taskUuid)) {
      pinnedTaskUuids.add(taskUuid);
    }

    TaskUtils.savePinnedTaskUuids(pinnedTaskUuids);
  }

}
