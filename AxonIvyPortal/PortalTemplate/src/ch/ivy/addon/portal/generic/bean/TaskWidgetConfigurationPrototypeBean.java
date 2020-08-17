package ch.ivy.addon.portal.generic.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.ToggleEvent;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.enums.TaskColumn;
import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@ManagedBean
@ViewScoped
public class TaskWidgetConfigurationPrototypeBean {
  private List<TaskState> filteredStates;
  private List<WorkflowPriority> priorities;
  private UserDTO selectedUser;
  private Map<String, Boolean> taskColumns;
  private List<SecurityMemberDTO> responsibles;

  @PostConstruct
  public void init() {
    this.setFilteredStates(new ArrayList<>());
    this.getFilteredStates().addAll(
        Arrays.asList(TaskState.CREATED, TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE));

    this.priorities =
        Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH, WorkflowPriority.NORMAL, WorkflowPriority.LOW);

    TaskDashboardWidget widget = Attrs.currentContext().get("cc.attrs.taskWidget");
    this.taskColumns = new HashMap<>();
    if (CollectionUtils
        .isNotEmpty(Optional.ofNullable(widget).map(TaskDashboardWidget::getTaskColumns).orElse(new ArrayList<>()))) {
      for (TaskColumn column : TaskColumn.values()) {
        taskColumns.put(column.name(), widget.getTaskColumns().contains(column.name()));
      }
    } else {
      for (TaskColumn column : TaskColumn.values()) {
        taskColumns.put(column.name(), true);
      }
    }

    this.responsibles =
        SecurityService.newInstance().findSecurityMembers("", Ivy.wf().getApplication(), 0, -1).getSecurityMembers();
  }
  
  public String formatName(SecurityMemberDTO responsible) {
    String responsibleName = "";
    if (responsible != null) {
      if (StringUtils.isBlank(responsible.getDisplayName())) {
        responsibleName = responsible.getName();
      } else {
        responsibleName = responsible.getDisplayName() + " (" + responsible.getName() + ")";
      }
      return responsible.isEnabled()? responsibleName : Ivy.cms().co("/Labels/disabledUserPrefix") + " " + responsibleName;
    }
    return responsibleName;
  }
  
  public List<SecurityMemberDTO> completeResponsibles(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
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
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/PARKED");
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

  public void clearTaskFilters(TaskDashboardWidget widget) {
    widget.setTaskName(null);
    widget.setCreatedDateFrom(null);
    widget.setCreatedDateTo(null);
    widget.setExpiryDateFrom(null);
    widget.setExpiryDateTo(null);
    widget.setCategory(null);
    widget.setDescription(null);
    widget.setPriorities(getPriorities());
    widget.setStates(getFilteredStates());
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

  public Map<String, Boolean> getTaskColumns() {
    return taskColumns;
  }

  public void setTaskColumns(Map<String, Boolean> taskColumns) {
    this.taskColumns = taskColumns;
  }

  public List<SecurityMemberDTO> getResponsibles() {
    return responsibles;
  }

  public void setResponsibles(List<SecurityMemberDTO> responsibles) {
    this.responsibles = responsibles;
  }
}
