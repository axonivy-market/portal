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
  
  public String getAriaLabel(ITask task) {
    String ariaLabel = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/taskStart");
    ariaLabel += " - " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/taskName") + ": " + task.getName();
    ariaLabel += " - " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/PRIORITY") + ": "
        + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + task.getPriority().name());
    ariaLabel += " - " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/STATE") + ": "
        + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + task.getState().name());

    if (task.getExpiryTimestamp() != null) {
      String expiryDateString = new SimpleDateFormat(
          DateTimeGlobalSettingService.getInstance().getGlobalDateTimePattern()).format(task.getExpiryTimestamp());
      ariaLabel += " - " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/EXPIRY_TIME") + ": "
          + expiryDateString;
    }
    return ariaLabel;
  }
}
