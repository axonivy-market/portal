package ch.ivy.addon.portal.generic.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import ch.ivy.addon.portalkit.dto.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@ManagedBean
@ViewScoped
public class TaskWidgetConfigurationPrototypeBean {
  private List<TaskState> filteredStates;
  private List<WorkflowPriority> priorities;
  private List<String> portalDefaultColumns =
          Arrays.asList("PRIORITY", "NAME", "ACTIVATOR", "ID", "CREATION_TIME", "EXPIRY_TIME", "STATE", "DESCRIPTION", "CATEGORY");
  private UserDTO selectedUser;

  @PostConstruct
  public void init() {
      this.setFilteredStates(new ArrayList<>());
      this.getFilteredStates().addAll(Arrays.asList(TaskState.CREATED, TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE));

      this.priorities = Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH, WorkflowPriority.NORMAL, WorkflowPriority.LOW);

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

  public void saveTaskDashboardWidget() throws JsonProcessingException {
    DashboardBean dashboardBean = ManagedBeans.get("dashboardBean");
    dashboardBean.saveWidgets();

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

  public List<String> getPortalDefaultColumns() {
    return portalDefaultColumns;
  }

  public void setPortalDefaultColumns(List<String> portalDefaultColumns) {
    this.portalDefaultColumns = portalDefaultColumns;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO selectedUser) {
    this.selectedUser = selectedUser;
  }
}