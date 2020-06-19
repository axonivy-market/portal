package ch.ivy.addon.portal.generic.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.ToggleEvent;

import com.fasterxml.jackson.core.JsonProcessingException;

import ch.ivy.addon.portalkit.dto.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.enums.TaskColumn;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@ManagedBean
@ViewScoped
public class TaskWidgetConfigurationPrototypeBean {
  private List<TaskState> filteredStates;
  private List<WorkflowPriority> priorities;
  private UserDTO selectedUser;
  private Map <String, Boolean> taskColumns;

  @PostConstruct
  public void init() {
      this.setFilteredStates(new ArrayList<>());
      this.getFilteredStates().addAll(Arrays.asList(TaskState.CREATED, TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE));

      this.priorities = Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH, WorkflowPriority.NORMAL, WorkflowPriority.LOW);

      TaskDashboardWidget widget = Attrs.currentContext().get("cc.attrs.taskWidget");
      this.taskColumns = new HashMap<>();
      if (CollectionUtils.isNotEmpty(Optional.ofNullable(widget).map(TaskDashboardWidget::getTaskColumns).orElse(new ArrayList<>()))) {
        for(TaskColumn column : TaskColumn.values()) {
          taskColumns.put(column.name(), widget.getTaskColumns().contains(column.name()));
        }
      } else {
        for(TaskColumn column : TaskColumn.values()) {
          taskColumns.put(column.name(), true);
        }
      }
  }

  public String getUserFriendlyTaskState(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    switch (state) {
      case SUSPENDED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/OPEN");
      case CREATED:
      case RESUMED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/RESUMED");
      case DONE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/DONE");
      case PARKED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/RESERVED");
      case DESTROYED:
      case ZOMBIE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/DESTROYED");
      default:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/SYSTEM");
    }
  }

  public String getUserFriendlyTaskPriority(WorkflowPriority priority) {
    if (priority == null) {
      return StringUtils.EMPTY;
    }
    switch (priority) {
      case LOW:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/LOW_LOWERCASE");
      case NORMAL:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/NORMAL_LOWERCASE");
      case HIGH:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/HIGH_LOWERCASE");
      case EXCEPTION:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/EXCEPTION_LOWERCASE");
      default:
        return StringUtils.EMPTY;
    }
  }

  public void onToggleColumns(ToggleEvent e) {
    TaskColumn toggledColumn = TaskColumn.values()[(Integer) e.getData()];
    this.taskColumns.put(toggledColumn.name(), !this.taskColumns.get(toggledColumn.name()).booleanValue());
  }

  public void saveTaskDashboardWidget(TaskDashboardWidget widget) throws JsonProcessingException {

    DashboardBean dashboardBean = ManagedBeans.get("dashboardBean");
    if (widget.getTaskColumns() == null) {
      widget.setTaskColumns(new ArrayList<>());
    }

    for (Entry<String, Boolean> entry : this.taskColumns.entrySet()) {
      if (entry.getValue() && widget.getTaskColumns().contains(entry.getKey())) {
        widget.getTaskColumns().add(entry.getKey());
      }
    }
    dashboardBean.saveWidget(widget);
  }
  
  public void createTaskDashboardWidget(TaskDashboardWidget widget) throws JsonProcessingException {
    DashboardBean dashboardBean = ManagedBeans.get("dashboardBean");
    widget.setTaskColumns(new ArrayList<>());
    if (this.taskColumns.isEmpty()) {
      widget.setTaskColumns(Arrays.asList(TaskColumn.PRIORITY.name(), TaskColumn.NAME.name(), TaskColumn.ACTIVATOR.name(), TaskColumn.STATE.name()));
    } else {
      for (Entry<String, Boolean> entry : this.taskColumns.entrySet()) {
        if (entry.getValue() && widget.getTaskColumns().contains(entry.getKey())) {
          widget.getTaskColumns().add(entry.getKey());
        }
      }
    }
    dashboardBean.saveWidget(widget);
  }

  public List<TaskState> getFilteredStates() {
      return filteredStates;
  }

  public void setFilteredStates(List<TaskState> filteredStates) {
      this.filteredStates = filteredStates;
  }

  public List<WorkflowPriority> getPriorities() {
      return priorities;
  }

  public void setPriorities(List<WorkflowPriority> priorities) {
      this.priorities = priorities;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO selectedUser) {
    this.selectedUser = selectedUser;
  }

  public Map <String, Boolean> getTaskColumns() {
    return taskColumns;
  }

  public void setTaskColumns(Map <String, Boolean> taskColumns) {
    this.taskColumns = taskColumns;
  }
}