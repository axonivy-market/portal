package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortMeta;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetails;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

@ViewScoped
@ManagedBean
public class TaskDetailsBean extends AbstractConfigurableContentBean<TaskDetails> implements Serializable {

  private static final long serialVersionUID = 8566646437739271552L;
  private static final String RESET_TASK_FRIENDLY_REQUEST_PATH = "Start Processes/PortaStart/ResetTask.ivp";
  private static final String RESET_TASK_WARNING_CMS_URI = "/Dialogs/ch/ivy/addon/portalkit/component/TaskItemDetails/ResetTaskWarning";
  private static final String CANNOT_WORK_ON_TASK_WARNING_CMS_URI = "/Dialogs/ch/ivy/addon/portalkit/component/TaskItemDetails/CannotWorkOnTaskWarning";
  private static final String CANNOT_WORK_ON_DESTROYED_TASK_WARNING_CMS_URI = "/Dialogs/ch/ivy/addon/portalkit/component/TaskItemDetails/CannotWorkOnDestroyedTaskWarning";
  private static final String TASK_COMPLETED_BY_YOU_INFO_CMS_URI = "/Dialogs/ch/ivy/addon/portalkit/component/TaskItemDetails/TaskCompletedByYouInfo";
  private static final String TASK_COMPLETED_BY_OTHER_INFO_CMS_URI = "/Dialogs/ch/ivy/addon/portalkit/component/TaskItemDetails/TaskCompletedByOtherInfo";
  private static final String INVALID_STATE_INFO_CMS_URI = "/Dialogs/ch/ivy/addon/portalkit/component/TaskItemDetails/InvalidStateInfo";
  private static final String UUID = "uuid";
  private boolean hasShowDurationTime;
  private String taskDetailsDescription;
  private String taskDetailsUrl;
  private Boolean isShowShareButton;
  
  public void init() {
    super.initConfig();
    isShowShareButton = PermissionUtils.hasShareTaskDetailsPermission();
    try {
      hasShowDurationTime = Boolean.parseBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.SHOW_TASK_DURATION_TIME));
      loadWidgets();
    } catch (Exception e) {
      Ivy.log().error("Exception at method init of Class TaskDetailsBean", e);
    }
  }

  @Override
  protected void loadWidgets() throws Exception {
    ITask selectedTask = getSelectedTaskFromData();
    if (selectedTask == null) {
      return;
    }
    setTaskDetailsUrl(PortalNavigatorAPI.buildUrlToPortalTaskDetailsPageByUUID(selectedTask.uuid()));
    super.loadWidgets();
  }

  protected ITask getSelectedTaskFromData() {
    return Attrs.currentContext().getAttribute("#{data.task}", ITask.class);
  }

  public void initTaskDescription(ITask taskDetails) {
    setTaskDetailsDescription(taskDetails.descriptions().current());
  }

  public void updateTaskDescription(ITask taskDetails) {
    if (taskDetails == null || taskDetailsDescription == null) {
      return;
    }
    taskDetails.descriptions().current(taskDetailsDescription);
  }

  public boolean isShowDurationTime() {
    return hasShowDurationTime;
  }

  public void setShowDurationTime(boolean hasShowDurationTime) {
    this.hasShowDurationTime = hasShowDurationTime;
  }

  public String getTaskDetailsDescription() {
    return taskDetailsDescription;
  }

  public void setTaskDetailsDescription(String taskDetailsDescription) {
    this.taskDetailsDescription = taskDetailsDescription;
  }

  public String getTaskDetailsUrl() {
    return taskDetailsUrl;
  }

  public void setTaskDetailsUrl(String taskDetailsUrl) {
    this.taskDetailsUrl = taskDetailsUrl;
  }

  public Boolean getIsShowShareButton() {
    return isShowShareButton;
  }

  public void setIsShowShareButton(Boolean isShowShareButton) {
    this.isShowShareButton = isShowShareButton;
  }

  @Override
  public String getVariableKey() {
    return PortalVariable.TASK_DETAIL.key;
  }

  @Override
  protected boolean findConfigByPredefinedFilters(List<TaskDetails> configurations) {
    ITask currentTask = getSelectedTaskFromData();
    for (TaskDetails config : configurations) {
      // found configuration for current task by predefined filters
      if (isFilterByCategories(currentTask.getCategoryPath(), config.getFilters())
          || isFilterByStates(currentTask.getState().name(), config.getFilters())) {
        configuration = config;
        return true;
      }
    }
    return false;
  }

  @Override
  protected Class<TaskDetails> getConfigurationType() {
    return TaskDetails.class;
  }

  @Override
  protected String getDefaultConfigId() {
    return "default-task-detail";
  }

  public void resetTask() {
    TaskUtils.resetTask(getSelectedTaskFromData());
  }

  public boolean showInfoBanner() {
    ITask selectedTask = getSelectedTaskFromData();
    EnumSet<TaskState> activeTaskStates = EnumSet.of(TaskState.DONE, TaskState.READY_FOR_JOIN, TaskState.JOINING, TaskState.JOIN_FAILED, TaskState.CREATED, TaskState.RESUMED, TaskState.PARKED, TaskState.DESTROYED);
    return isActivator() && activeTaskStates.contains(selectedTask.getState());
  }

  public String getInfoBannerSeverity() {
    ITask selectedTask = getSelectedTaskFromData();
    boolean validState = selectedTask.getState() == TaskState.RESUMED || selectedTask.getState() == TaskState.CREATED || selectedTask.getState() == TaskState.PARKED;
    boolean notCurrentSession = selectedTask.getWorkerSession() != ISession.current();
    return validState && notCurrentSession && isActivator() && currentIsWorkerUser() ? "warn" : "info";
  }

  public String getInfoBannerMessage() {
    ITask selectedTask = getSelectedTaskFromData();
    return switch (selectedTask.getState()) {
    case CREATED, RESUMED, PARKED -> {
      if (currentIsWorkerUser()) {
        yield Ivy.cms().co(RESET_TASK_WARNING_CMS_URI, Arrays.asList(buildResetTaskUrl(selectedTask)));
      } else {
        yield Ivy.cms().co(CANNOT_WORK_ON_TASK_WARNING_CMS_URI, Arrays.asList(getWorkerUser(selectedTask)));
      }
    }
    case DONE, READY_FOR_JOIN, JOINING, JOIN_FAILED -> {
      if (currentIsWorkerUser()) {
        yield Ivy.cms().co(TASK_COMPLETED_BY_YOU_INFO_CMS_URI, Arrays.asList(formatDate(selectedTask.getEndTimestamp())));
      } else {
        yield Ivy.cms().co(TASK_COMPLETED_BY_OTHER_INFO_CMS_URI, Arrays.asList(getWorkerUser(selectedTask), formatDate(selectedTask.getEndTimestamp())));
      }
    }
    case DESTROYED -> Ivy.cms().co(CANNOT_WORK_ON_DESTROYED_TASK_WARNING_CMS_URI);
    default -> Ivy.cms().co(INVALID_STATE_INFO_CMS_URI);
    };
  }

  private boolean isActivator() {
    ITask selectedTask = getSelectedTaskFromData();
    return selectedTask.getActivator() == null ? false : selectedTask.getActivator().isMember(ISession.current(), true);
  }

  private boolean currentIsWorkerUser() {
    ITask selectedTask = getSelectedTaskFromData();
    return selectedTask.getWorkerUser() == null ? false : selectedTask.getWorkerUser().equals(ISession.current().getSessionUser());
  }

  private String buildResetTaskUrl(ITask task) {
    Map<String, String> params = new HashMap<>();
    params.put(UUID, task.uuid());
    return PortalNavigator.buildUrlByKeyword("ResetTask.ivp", RESET_TASK_FRIENDLY_REQUEST_PATH, params);
  }

  private String formatDate(Date datetime) {
    return DateTimeGlobalSettingService.getInstance().getDefaultDateTimeFormatter().format(datetime);
  }

  private String getWorkerUser(ITask selectedTask) {
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(selectedTask.getWorkerUser(), selectedTask.getWorkerUser().getMemberName());
  }

  public SortMeta getSortByCreationTimestampTime() {
    return SortFieldUtil.buildSortMeta("creationTimestamp", true);
  }

  public SortMeta getSortByCreationTimestamp() {
    return SortFieldUtil.buildSortMeta("creation.timestamp", true);
  }
  
  public String getDestroyTaskMessage() {
    String taskName = "Unknown Task";
    String taskId = "Unknown ID";
    ITask task = getSelectedTaskFromData();
    if (task != null) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/destroyTaskMessage", List.of(task.names().current(), task.getId()));
    }
    else return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/destroyTaskMessage", List.of(taskName, taskId));
  }
}
