package com.axonivy.portal.bean.dashboard.bulkaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.enums.BulkActionType;

import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class BulkDelegateBean implements Serializable {

  private static final long serialVersionUID = 10981333936325689L;

  private Map<String, List<ITask>> selectedTasksMap = new HashMap<>();

  public Map<String, List<ITask>> getSelectedTasksMap() {
    return selectedTasksMap;
  }

  public void setSelectedTasksMap(Map<String, List<ITask>> selectedTasksMap) {
    this.selectedTasksMap = selectedTasksMap;
  }

  public Map<String, Map<String, Boolean>> getTaskCheckboxSelectionMap() {
    Map<String, Map<String, Boolean>> computedMap = new HashMap<>();
    for (Map.Entry<String, List<ITask>> entry : selectedTasksMap.entrySet()) {
      Map<String, Boolean> innerMap = new HashMap<>();
      for (ITask task : entry.getValue()) {
        innerMap.put(task.uuid(), Boolean.TRUE);
      }
      computedMap.put(entry.getKey(), innerMap);
    }
    return computedMap;
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
      deselectAllDelegatedTasks(widget.getId());
      selectedTasksMap.remove(widget.getId());
    } else {
      widget.setBulkActionType(BulkActionType.DELEGATE);
      selectedTasksMap.put(widget.getId(), new ArrayList<>());
    }
  }

  public void resetTaskSelectionOnWidget(String widgetId) {
    deselectAllDelegatedTasks(widgetId);
    selectedTasksMap.remove(widgetId);
  }

  public void onSelectTask(BulkActionType bulkActionType, ITask task, TaskDashboardWidget widget) {
    Integer maxSelectedTasks = getMaximumSelectedTasks();

    if (selectedTasksMap == null) {
      selectedTasksMap = new HashMap<>();
    }
    List<ITask> selectedTasks = selectedTasksMap.computeIfAbsent(widget.getId(), k -> new ArrayList<>());
    boolean alreadySelected = selectedTasks.stream().anyMatch(t -> t.uuid().equals(task.uuid()));

    if (!alreadySelected) {
      if (maxSelectedTasks != null && maxSelectedTasks > 0 && selectedTasks.size() >= maxSelectedTasks) {
        return;
      }
      selectedTasks.add(task);
    } else {
      selectedTasks.removeIf(t -> t.uuid().equals(task.uuid()));
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

  public int getBulkSelectCount(String widgetId) {
    List<ITask> selectedTasks = selectedTasksMap.get(widgetId);
    return selectedTasks != null ? selectedTasks.size() : 0;
  }

  public void deselectAllDelegatedTasks(String widgetId) {
    if (selectedTasksMap.containsKey(widgetId)) {
      selectedTasksMap.get(widgetId).clear();
    }
  }

  private static Integer getMaximumSelectedTasks() {
    String maxTasksSetting = GlobalSettingService.getInstance()
        .findGlobalSettingValue(GlobalVariable.MAXIMUM_SELECTED_TASKS);
    try {
      Integer parsedValue = Integer.valueOf(maxTasksSetting.trim());
      return parsedValue > 0 ? parsedValue : null;
    } catch (NumberFormatException | NullPointerException e) {
      return null;
    }
  }

  public String generateMultipleTaskDelegateTitle(String widgetId) {
    if (getBulkSelectCount(widgetId) == 1) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskInformation/delegateTask");
    }
    return Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/MultipleTaskItemsDelegate/DelegateMultipleTaskTitle",
        List.of(getBulkSelectCount(widgetId)));
  }
}
