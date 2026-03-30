package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.axonivy.portal.components.util.HtmlUtils;
import com.axonivy.portal.enums.BulkActionType;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInTaskList;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.TaskEmptyMessage;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class DashboardTaskWidgetBean implements Serializable {

  private static final long serialVersionUID = 1098133393632524689L;

  private ITask selectedTask;
  private boolean isRunningTaskWhenClickingOnTaskInList;
  private TaskEmptyMessage noTasksMessage;
  private Map<String, List<ITask>> selectedTasksMap = new HashMap<>();
  private Map<String, Map<String, Boolean>> taskCheckboxSelectionMap = new HashMap<>();

  @PostConstruct
  public void init() {
    isRunningTaskWhenClickingOnTaskInList = GlobalSettingService.getInstance()
        .findGlobalSettingValue(
            GlobalVariable.DEFAULT_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST)
        .equals(BehaviourWhenClickingOnLineInTaskList.RUN_TASK.name());
  }

  public void resetAndOpenTask() throws IOException {
    TaskUtils.resetTask(selectedTask);
    FacesContext.getCurrentInstance().getExternalContext()
        .redirect(selectedTask.getStartLinkEmbedded().getRelative());
  }

  public void handleRowSelectEventOnTaskWidget(SelectEvent<Object> event)
      throws IOException {
    ITask task = ((ITask) event.getObject());
    handleSelectedTask(task);
  }

  private void handleSelectedTask(ITask task) throws IOException {
    if (isRunningTaskWhenClickingOnTaskInList) {
      handleStartTask(task);
    } else {
      navigateToSelectedTaskDetails(task);
    }
  }

  public void handleStartTask(ITask task) throws IOException {
    DashboardBean dashboardBean = ManagedBeans.get("dashboardBean");
    String selectedDashboardId =
        Optional.ofNullable(dashboardBean).map(DashboardBean::getSelectedDashboardId).orElse(null);
    selectedTask = task;
    TaskUtils.handleStartTask(task, PortalPage.HOME_PAGE,
        PortalConstants.RESET_TASK_CONFIRMATION_DIALOG, selectedDashboardId);
  }

  public void navigateToSelectedTaskDetails(SelectEvent<Object> event) {
    String uuid = ((ITask) event.getObject()).uuid();
    PortalNavigator.navigateToPortalTaskDetails(uuid);
  }

  public void navigateToSelectedTaskDetails(ITask task) {
    PortalNavigator.navigateToPortalTaskDetails(task.uuid());
  }

  public String createParseTextFromHtml(String text) {
    return HtmlUtils.parseTextFromHtml(text);
  }

  public TaskEmptyMessage getNoTasksMessage() {
    if (noTasksMessage == null) {
      List<TaskEmptyMessage> messages = Stream.of(TaskEmptyMessage.values())
          .collect(Collectors.toList());
      Random random = new Random();
      int index = random.ints(0, messages.size()).findFirst().getAsInt();
      noTasksMessage = messages.get(index);
    }
    return noTasksMessage;
  }

  public String createExtractedTextFromHtml(String text) {
    return HtmlParser.extractTextFromHtml(text);
  }

  public Map<String, List<ITask>> getSelectedTasksMap() {
    return selectedTasksMap;
  }

  public void setSelectedTasksMap(Map<String, List<ITask>> selectedTasksMap) {
    this.selectedTasksMap = selectedTasksMap;
  }

  public Map<String, Map<String, Boolean>> getTaskCheckboxSelectionMap() {
    return taskCheckboxSelectionMap;
  }

  public void setTaskCheckboxSelectionMap(Map<String, Map<String, Boolean>> taskCheckboxSelectionMap) {
    this.taskCheckboxSelectionMap = taskCheckboxSelectionMap;
  }

  public boolean isShowSelection(DashboardWidget widget) {
    if (widget == null || selectedTasksMap == null) {
      return false;
    }
    return selectedTasksMap.containsKey(widget.getId());
  }

  public void toggleDelegationColumn(TaskDashboardWidget widget) {
    if (isShowSelection(widget)) {
      widget.setBulkActionType(BulkActionType.NONE);
      deselectAllDelegatedTasks(widget);
      selectedTasksMap.remove(widget.getId());
      taskCheckboxSelectionMap.remove(widget.getId());
    } else {
      widget.setBulkActionType(BulkActionType.DELEGATE);
      selectedTasksMap.put(widget.getId(), new ArrayList<>());
      taskCheckboxSelectionMap.put(widget.getId(), new HashMap<>());
    }
  }

  public void onSelectTask(BulkActionType bulkActionType, ITask task, TaskDashboardWidget widget) {
    Integer maxSelectedTasks = getMaximumSelectedTasks();

    if (selectedTasksMap == null) {
      selectedTasksMap = new HashMap<>();
    }
    List<ITask> selectedTasks = selectedTasksMap.computeIfAbsent(widget.getId(), k -> new ArrayList<>());
    Map<String, Boolean> checkboxMap = taskCheckboxSelectionMap.computeIfAbsent(widget.getId(), k -> new HashMap<>());

    if (checkboxMap.getOrDefault(task.uuid(), false)) {        
      if (maxSelectedTasks != null && maxSelectedTasks > 0 && selectedTasks.size() >= maxSelectedTasks) {
        // Revert selection and optionally show a message
        checkboxMap.put(task.uuid(), false);
        return;
      }
      selectedTasks.add(task);
    } else {
      selectedTasks.remove(task);
    }
  }

  public boolean isReachMaxSelectionLimit(TaskDashboardWidget widget) {
    Integer maxSelectedTasks = getMaximumSelectedTasks();
    if (maxSelectedTasks == null || maxSelectedTasks <= 0) {
      return false;
    }
    if (selectedTasksMap == null || widget == null) {
      return false;
    }
    List<ITask> selectedTasks = selectedTasksMap.get(widget.getId());
    return selectedTasks != null && selectedTasks.size() >= maxSelectedTasks;
  }

  public Integer getMaxSelectedTasks() {
    return getMaximumSelectedTasks();
  }

  public boolean canShowBulkSelectDelegation(DashboardWidget widget) {
    if (!(widget instanceof TaskDashboardWidget)) {
      return false;
    }
    if (selectedTasksMap == null || widget == null) {
      return false;
    }
    List<ITask> selectedTasks = selectedTasksMap.get(widget.getId());
    return selectedTasks != null && !selectedTasks.isEmpty();
  }

  public int getBulkSelectCount(DashboardWidget widget) {
    if (!(widget instanceof TaskDashboardWidget) || selectedTasksMap == null) {
      return 0;
    }
    List<ITask> selectedTasks = selectedTasksMap.get(widget.getId());
    return selectedTasks != null ? selectedTasks.size() : 0;
  }

  public void deselectAllDelegatedTasks(DashboardWidget widget) {
    if (widget instanceof TaskDashboardWidget) {
      if (selectedTasksMap.containsKey(widget.getId())) {
        selectedTasksMap.get(widget.getId()).clear();
      }
      if (taskCheckboxSelectionMap.containsKey(widget.getId())) {
        taskCheckboxSelectionMap.get(widget.getId()).clear();
      }
    }
  }

  private static Integer getMaximumSelectedTasks() {
    String maxTasksSetting = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.MAXIMUM_SELECTED_TASKS);
    try {
      Integer parsedValue = Integer.valueOf(maxTasksSetting.trim());
      return parsedValue > 0 ? parsedValue : null;
    } catch (NumberFormatException | NullPointerException e) {
      return null;
    }
  }
}
