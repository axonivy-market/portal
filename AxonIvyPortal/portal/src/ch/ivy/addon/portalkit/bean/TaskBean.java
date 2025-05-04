package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
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
@ManagedBean(name = "taskBean")
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

  public String displayRelatedTaskToolTip(ITask task) {
    List<Object> params = new ArrayList<>();
    if (task != null) {
      ISecurityMember taskActivator = task.getActivator();
      String taskActivatorName = taskActivator != null? taskActivator.getDisplayName() : StringUtils.stripStart(task.getActivatorName(), "#");
      params = Arrays.asList(getTranslatedState(task.getBusinessState()), Objects.toString(taskActivatorName, ""));
    }
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateAndResponsible", params);
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
      if (col.getVisible()) {
        if (DashboardStandardTaskColumn.START.getField().equalsIgnoreCase(col.getField())) {
          displayTexts.add(cms("/ch.ivy.addon.portalkit.ui.jsf/dashboard/taskStart"));
        } else if (DashboardStandardTaskColumn.PRIORITY.getField().equalsIgnoreCase(col.getField())) {
          displayTexts.add(col.getHeaderText() + ": " + getPriority(task.getPriority()));
        } else if (DashboardStandardTaskColumn.STATE.getField().equalsIgnoreCase(col.getField())) {
          displayTexts.add(col.getHeaderText() + ": " + getTaskBusinessState(task.getBusinessState()));
        } else if (DashboardStandardTaskColumn.CREATED.getField().equalsIgnoreCase(col.getField())) {
          String createdDateString = new SimpleDateFormat(DateTimeGlobalSettingService.getInstance().getGlobalDateTimePattern()).format(task.getStartTimestamp());
          displayTexts.add(col.getHeaderText() + ": " + createdDateString);
        } else if (DashboardStandardTaskColumn.EXPIRY.getField().equalsIgnoreCase(col.getField())) {
          if (task.getExpiryTimestamp() != null) {
            String expiryDateString = new SimpleDateFormat(DateTimeGlobalSettingService.getInstance().getGlobalDateTimePattern()).format(task.getExpiryTimestamp());
            displayTexts.add(col.getHeaderText() + ": " + expiryDateString);
          }
        } else {
          Object displayObject = col.display(task);
          if (displayObject != null && StringUtils.isNotEmpty(displayObject.toString())) {
            displayTexts.add(col.getHeaderText() + ": " + displayObject.toString());
          }
        }
      }
    }
    return String.join(" - ", displayTexts);
  }
}
