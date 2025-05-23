package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.constant.CustomFields;
import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SideStepDTO;
import com.axonivy.portal.components.dto.SideStepProcessDTO;
import com.axonivy.portal.components.dto.SideStepProcessParam;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.enums.SideStepType;
import com.axonivy.portal.components.publicapi.PortalNavigatorInFrameAPI;
import com.axonivy.portal.components.publicapi.TaskAPI;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.components.util.TaskUtils;
import com.axonivy.portal.components.util.UserUtils;

import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class SideStepProcessBean implements Serializable {

  private static final long serialVersionUID = 4611697531600235758L;

  private ITask task;
  private SideStepProcessDTO selectedProcess;
  private String processId;
  private List<SideStepProcessDTO> processes;
  private UserDTO assignee;
  private RoleDTO assignedRole;
  private List<UserDTO> users;
  private List<RoleDTO> roles;
  private SideStepType selectedStepType;
  private List<SideStepType> stepTypes;
  private String comment;
  private SideStepDTO sideStepInfo;
  private boolean isUserDelegated;

  public List<SideStepProcessDTO> getProcesses() {
    return processes;
  }

  public void setProcesses(List<SideStepProcessDTO> processes) {
    this.processes = processes;
  }

  public SideStepProcessDTO getSelectedProcess() {
    return selectedProcess;
  }

  @SuppressWarnings("unchecked")
  public void setSelectedProcess(SideStepProcessDTO selectedProcess) {
    this.selectedProcess = selectedProcess;
    this.isUserDelegated = true;
    if (task != null && selectedProcess != null) {
      if (selectedProcess.getOriginalTaskId() == null) {
        selectedProcess.setOriginalTaskId(task.uuid());
      }

      String securityMembersCallable = selectedProcess.getSecurityMembersCallable();

      if (StringUtils.isNotBlank(securityMembersCallable)) {
        Map<String, Object> responseCallable = IvyAdapterService
            .startSubProcessInSecurityContext(securityMembersCallable, null);
        List<UserDTO> users = (List<UserDTO>) responseCallable.get("usersToDelegate");
        List<RoleDTO> rolesDTO = (List<RoleDTO>) responseCallable.get("rolesToDelegate");
        this.users = users;
        this.roles = rolesDTO;
      }

      if (StringUtils.isBlank(securityMembersCallable)) {
        this.users = UserUtils.findUsers("", 0, -1, Collections.emptyList(), Collections.emptyList());
        this.roles = RoleUtils.findRoles(Collections.emptyList(), Collections.emptyList(), "");
      }

    }
  }

  private String selectedProcessId;

  public boolean isRendered(ITask task) {
    return CollectionUtils.isNotEmpty(getSideStepProcesses(task));
  }

  public List<SideStepProcessDTO> getSideStepProcesses(ITask task) {
    if (processes == null) {
      try {
        if (task != null) {
          this.task = task;
          String sideStepString = task.getCase().customFields().textField(CustomFields.SIDE_STEPS_PROCESS).getOrNull();
          if (StringUtils.isBlank(sideStepString)) {
            sideStepString = task.customFields().textField(CustomFields.SIDE_STEPS_TASK).getOrNull();
          }
          if (StringUtils.isNotBlank(sideStepString)) {
            sideStepInfo = BusinessEntityConverter.jsonValueToEntity(sideStepString, SideStepDTO.class);
            if (sideStepInfo.getParallel() == null) {
              setStepTypes(Arrays.asList(SideStepType.class.getEnumConstants()));
            } else if (sideStepInfo.getParallel()) {
              this.selectedStepType = SideStepType.PARALLEL;
            } else {
              this.selectedStepType = SideStepType.SWITCH;
            }
            processes = sideStepInfo.getProcesses();
          }
        }
      } catch (NullPointerException ex) {
      }
    }
    return processes;
  }

  public String getStepTypeTitle(SideStepType type) {
    if (SideStepType.PARALLEL == type) {
      return StringUtils.defaultIfBlank(sideStepInfo.getStepTypeParallelTitle(),
          Ivy.cms().co("/Dialogs/com/axonivy/portal/components/SideStepType/" + SideStepType.PARALLEL.name()));
    } else if (SideStepType.SWITCH == type) {
      return StringUtils.defaultIfBlank(sideStepInfo.getStepTypeSwitchTitle(),
          Ivy.cms().co("/Dialogs/com/axonivy/portal/components/SideStepType/" + SideStepType.SWITCH.name()));
    }
    return null;
  }

  public void handleSelectProcess() {
    if (task != null && selectedProcess != null) {
      ISecurityMember delegatedSecurityMember;
      if (assignee != null) {
        delegatedSecurityMember = SecurityMemberUtils.findISecurityMemberFromUserDTO(assignee);
      } else {
        delegatedSecurityMember = SecurityMemberUtils.findISecurityMemberFromRoleDTO(assignedRole);
      }
      String memberName = delegatedSecurityMember.getMemberName();
      SideStepProcessParam param = new SideStepProcessParam(selectedProcess, memberName, selectedStepType, comment);
      String jsonSerializedPayload = BusinessEntityConverter.entityToJsonValue(param);
      Ivy.wf().signals().create().data(jsonSerializedPayload).send(selectedProcess.getSignal());
      if (selectedStepType == SideStepType.SWITCH) {
        TaskUtils.parkTask(task);
        TaskAPI.setHidePropertyToHideInPortal(task);
        PortalNavigatorInFrameAPI.navigateToPortalHome();
      }

    }
  }

  public void changeAssignType() {
    this.assignee = null;
    this.assignedRole = null;
  }

  public boolean isCompletedSideStepProcess() {
    if (selectedProcess != null) {

    }
    return false;
  }

  public ITask getTask() {
    return task;
  }

  public void setTask(ITask task) {
    this.task = task;
  }

  public String getSelectedProcessId() {
    return selectedProcessId;
  }

  public void setSelectedProcessId(String selectedProcessId) {
    this.selectedProcessId = selectedProcessId;
  }

  public String getProcessId() {
    return processId;
  }

  public void setProcessId(String processId) {
    this.processId = processId;
  }

  public UserDTO getAssignee() {
    return assignee;
  }

  public void setAssignee(UserDTO assignee) {
    this.assignee = assignee;
  }

  public List<SideStepType> getStepTypes() {
    return stepTypes;
  }

  public void setStepTypes(List<SideStepType> stepTypes) {
    this.stepTypes = stepTypes;
  }

  public SideStepType getSelectedStepType() {
    return selectedStepType;
  }

  public void setSelectedStepType(SideStepType selectedStepType) {
    this.selectedStepType = selectedStepType;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public SideStepDTO getSideStepInfo() {
    return sideStepInfo;
  }

  public void setSideStepInfo(SideStepDTO sideStepInfo) {
    this.sideStepInfo = sideStepInfo;
  }

  public RoleDTO getAssignedRole() {
    return assignedRole;
  }

  public void setAssignedRole(RoleDTO assignedRole) {
    this.assignedRole = assignedRole;
  }

  public List<RoleDTO> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDTO> roles) {
    this.roles = roles;
  }

  public List<UserDTO> getUsers() {
    return users;
  }

  public void setUsers(List<UserDTO> users) {
    this.users = users;
  }

  public boolean isUserDelegated() {
    return isUserDelegated;
  }

  public void setUserDelegated(boolean isUserDelegated) {
    this.isUserDelegated = isUserDelegated;
  }

}
