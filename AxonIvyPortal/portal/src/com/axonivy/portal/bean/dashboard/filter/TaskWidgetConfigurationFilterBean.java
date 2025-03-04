package com.axonivy.portal.bean.dashboard.filter;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

@ManagedBean
@ViewScoped
public class TaskWidgetConfigurationFilterBean extends AbstractTaskWidgetFilterBean implements Serializable {

  private static final long serialVersionUID = -6820861008506479795L;

  private List<TaskBusinessState> states;
  private UserDTO selectedUser;
  private List<SecurityMemberDTO> owners;

  @PostConstruct
  public void init() {
    this.states = TaskUtils.getValidStates();
  }

  public String formatName(SecurityMemberDTO memberDTO) {
    String memberName = EMPTY;
    if (memberDTO != null) {
      if (StringUtils.isBlank(memberDTO.getDisplayName())) {
        memberName = memberDTO.getName();
      } else {
        memberName = String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/StringFormat/TextWithRoundBracket"), memberDTO.getDisplayName(), memberDTO.getName());
      }
      return memberDTO.isEnabled() ? memberName
          : String.format("%s %s", Ivy.cms().co("/Labels/disabledUserPrefix"), memberName);
    }
    return memberName;
  }

  public String getUserFriendlyTaskState(TaskBusinessState state) {
    if (state == null) {
      return EMPTY;
    }
    String displayState = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/businessTaskState/" + state.toString());
    return StringUtils.isBlank(displayState) ? state.name() : displayState;
  }

  public List<TaskBusinessState> getStates() {
    return states;
  }

  public void setStates(List<TaskBusinessState> states) {
    this.states = states;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO selectedUser) {
    this.selectedUser = selectedUser;
  }

  public List<SecurityMemberDTO> getOwners() {
    return owners;
  }

  public void setOwners(List<SecurityMemberDTO> owners) {
    this.owners = owners;
  }

  @Override
  public void addNewFilter(TaskDashboardWidget widget) {
    if (widget.getFilters() == null) {
      widget.setFilters(new ArrayList<>());
    }
    widget.getFilters().add(new DashboardFilter());
  }

  @Override
  public void removeFilter(TaskDashboardWidget widget, DashboardFilter filter) {
    widget.getFilters().remove(filter);
  }

  @Override
  public void resetTaskWidgetFilter(TaskDashboardWidget widget) {
    widget.setFilters(new ArrayList<>());
  }

  @Override
  public void resetCaseWidgetFilter(CaseDashboardWidget widget) {
    widget.setFilters(new ArrayList<>());
  }
}
