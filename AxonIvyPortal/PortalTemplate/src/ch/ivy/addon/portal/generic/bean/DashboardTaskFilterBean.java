package ch.ivy.addon.portal.generic.bean;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@ManagedBean
@ViewScoped
public class DashboardTaskFilterBean {
  private List<TaskState> states;
  private List<WorkflowPriority> priorities;
  private UserDTO selectedUser;
  private List<SecurityMemberDTO> responsibles;
  private TaskDashboardWidget widget;
  
  @PostConstruct
  public void init() {
    this.states = TaskUtils.getValidStates();
    this.priorities = Arrays.asList(WorkflowPriority.values());
    this.responsibles = new ArrayList<>();
  }
  
  public void preRender(TaskDashboardWidget widget) {
    this.widget = widget;
    this.widget.setInConfiguration(true);
  }

  public String formatName(SecurityMemberDTO responsible) {
    String responsibleName = EMPTY;
    if (responsible != null) {
      if (StringUtils.isBlank(responsible.getDisplayName())) {
        responsibleName = responsible.getName();
      } else {
        responsibleName = String.format("%s (%s)", responsible.getDisplayName(), responsible.getName());
      }
      return responsible.isEnabled()? responsibleName : String.format("%s %s", Ivy.cms().co("/Labels/disabledUserPrefix"), responsibleName);
    }
    return responsibleName;
  }
  
  public List<SecurityMemberDTO> completeResponsibles(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
  }

  public String getUserFriendlyTaskState(TaskState state) {
    if (state == null) {
      return EMPTY;
    }
    String displayState = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + state.toString());
    return StringUtils.isBlank(displayState) ? state.name() : displayState;
  }

  public String getUserFriendlyTaskPriority(WorkflowPriority priority) {
    if (priority == null) {
      return EMPTY;
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
        return EMPTY;
    }
  }

  public boolean hasPredefinedFilter(TaskDashboardWidget widget) {
    return DashboardWidgetUtils.hasPredefinedFilter(widget);
  }

  public List<TaskState> getStates() {
    return states;
  }

  public void setStates(List<TaskState> states) {
    this.states = states;
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

  public List<SecurityMemberDTO> getResponsibles() {
    return responsibles;
  }

  public void setResponsibles(List<SecurityMemberDTO> responsibles) {
    this.responsibles = responsibles;
  }
}
