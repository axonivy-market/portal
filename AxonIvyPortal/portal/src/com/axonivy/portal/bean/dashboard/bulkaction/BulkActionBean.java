package com.axonivy.portal.bean.dashboard.bulkaction;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.bulkaction.TaskSelectionDto;
import com.axonivy.portal.enums.BulkActionType;

import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class BulkActionBean implements Serializable {

  private static final long serialVersionUID = 10981333936325689L;

  private Map<String, TaskSelectionDto> taskSelectionMap = new HashMap<>();

  public boolean isInBulkMode(DashboardWidget widget) {
    if (widget == null) {
      return false;
    }
    return taskSelectionMap.containsKey(widget.getId());
  }

  public BulkActionType getActionType(String widgetId) {
    TaskSelectionDto selectionDto = taskSelectionMap.get(widgetId);
    return selectionDto != null ? selectionDto.getType() : BulkActionType.NONE;
  }

  public void startBulkDelegation(TaskDashboardWidget widget) {
    taskSelectionMap.put(widget.getId(), TaskSelectionDto.delegate());
  }

  public void cancelBulkAction(TaskDashboardWidget widget) {
    if (isInBulkMode(widget)) {
      taskSelectionMap.remove(widget.getId());
    }
  }

  public void resetTaskSelectionOnWidget(String widgetId) {
    taskSelectionMap.remove(widgetId);
  }

  public void onSelectTask(ITask task, TaskDashboardWidget widget) {
    Integer maxSelectedTasks = getMaximumSelectedTasks();
    TaskSelectionDto selectionDto = taskSelectionMap.get(widget.getId());
    if (selectionDto == null) {
      return;
    }

    if (selectionDto.isSelected(task)) {
      selectionDto.removeTask(task);
    } else {
      if (maxSelectedTasks != null && maxSelectedTasks > 0
          && selectionDto.getSelectedCount() >= maxSelectedTasks) {
        return;
      }
      selectionDto.addTask(task);
    }
  }

  public boolean isReachMaxSelectionLimit(TaskDashboardWidget widget) {
    Integer maxSelectedTasks = getMaximumSelectedTasks();
    if (maxSelectedTasks == null || maxSelectedTasks <= 0) {
      return false;
    }
    if (widget == null) {
      return false;
    }
    TaskSelectionDto selectionDto = taskSelectionMap.get(widget.getId());
    return selectionDto != null && selectionDto.getSelectedCount() >= maxSelectedTasks;
  }

  public int getBulkSelectCount(String widgetId) {
    TaskSelectionDto selectionDto = taskSelectionMap.get(widgetId);
    return selectionDto != null ? selectionDto.getSelectedCount() : 0;
  }

  public List<ITask> getSelectedTasks(String widgetId) {
    TaskSelectionDto selectionDto = taskSelectionMap.get(widgetId);
    return selectionDto != null ? selectionDto.getSelectedTasks() : List.of();
  }

  public Map<String, Map<String, Boolean>> getTaskCheckboxSelectionMap() {
    Map<String, Map<String, Boolean>> computedMap = new HashMap<>();
    for (Map.Entry<String, TaskSelectionDto> entry : taskSelectionMap.entrySet()) {
      Map<String, Boolean> innerMap = new HashMap<>();
      for (ITask task : entry.getValue().getSelectedTasks()) {
        innerMap.put(task.uuid(), Boolean.TRUE);
      }
      computedMap.put(entry.getKey(), innerMap);
    }
    return computedMap;
  }

  public Integer getMaximumSelectedTasks() {
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

  public boolean isRenderBulkDelegateToggle(TaskDashboardWidget widget) {
    return widget.isShowBulkDelegateToggle()
        && PermissionUtils.hasPortalPermission(PortalPermission.TASK_DISPLAY_DELEGATE_ACTION);
  }
}
