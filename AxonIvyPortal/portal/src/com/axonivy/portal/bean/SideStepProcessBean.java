package com.axonivy.portal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.components.constant.CustomFields;
import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SideStepConfigurationDTO;
import com.axonivy.portal.components.dto.SideStepProcessDTO;
import com.axonivy.portal.components.dto.SideStepProcessParamDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.enums.SideStepType;
import com.axonivy.portal.components.publicapi.PortalNavigatorInFrameAPI;
import com.axonivy.portal.components.publicapi.TaskAPI;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.components.util.FacesMessageUtils;
import com.axonivy.portal.components.util.TaskUtils;

import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.GrowlMessageService;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
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
  private List<String> userRoles;
  private List<String> roles;
  private SideStepType selectedStepType;
  private List<SideStepType> stepTypes;
  private String comment;
  private SideStepConfigurationDTO sideStepConfigurationInfo;
  private boolean isUserDelegated = true;

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
    this.userRoles = new ArrayList<>();
    this.roles = new ArrayList<>();
    this.selectedProcess = selectedProcess;
    this.isUserDelegated = true;
    if (task != null && selectedProcess != null) {
      if (selectedProcess.getParams() != null) {
        selectedProcess.getParams().put("taskUuid", task.uuid());
      }
      String securityMembersCallable = selectedProcess.getCustomSecurityMemberCallable();

      List<IRole> customUserRoles = new ArrayList<>();
      List<IRole> customRoles = new ArrayList<>();
      if (StringUtils.isNotBlank(securityMembersCallable)) {
        Map<String, Object> responseCallable = IvyAdapterService.startSubProcessInSecurityContext(securityMembersCallable, null);
        customUserRoles = (List<IRole>) responseCallable.get("userRolesToDelegate");
        customRoles = (List<IRole>) responseCallable.get("rolesToDelegate");
        this.userRoles = customUserRoles.stream().map(IRole::getName).collect(Collectors.toList());
        this.roles = customRoles.stream().map(IRole::getName).collect(Collectors.toList());
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
          String sideStepString = task.getCase().customFields().textField(CustomFields.SIDE_STEP_CASE).getOrNull();
          if (StringUtils.isBlank(sideStepString)) {
            sideStepString = task.customFields().textField(CustomFields.SIDE_STEPS_TASK).getOrNull();
          }
          if (StringUtils.isNotBlank(sideStepString)) {
            sideStepConfigurationInfo = BusinessEntityConverter.jsonValueToEntity(sideStepString, SideStepConfigurationDTO.class);
            if (sideStepConfigurationInfo.getIsParallelSideStep() == null) {
              setStepTypes(Arrays.asList(SideStepType.class.getEnumConstants()));
            } else if (sideStepConfigurationInfo.getIsParallelSideStep()) {
              this.selectedStepType = SideStepType.PARALLEL;
            } else {
              this.selectedStepType = SideStepType.SWITCH;
            }
            processes = sideStepConfigurationInfo.getProcesses();
          }
        }
      } catch (NullPointerException ex) {
      }
    }
    return processes;
  }

  public String getStepTypeTitle(SideStepType type) {
    return switch (type) {
      case SideStepType.PARALLEL -> StringUtils.defaultIfBlank((getCustomParallelTitle(sideStepConfigurationInfo)), SideStepType.PARALLEL.getLabel());
      case SideStepType.SWITCH -> StringUtils.defaultIfBlank(getCustomSwitchTitle(sideStepConfigurationInfo), SideStepType.SWITCH.getLabel());
      default -> "";
    };
  }

  public void handleSelectProcess() {
    if (task != null && selectedProcess != null) {
      ISecurityMember delegatedSecurityMember;
      if (assignee != null) {
        delegatedSecurityMember = SecurityMemberUtils.findISecurityMemberFromUserDTO(assignee);
      } else {
        delegatedSecurityMember = SecurityMemberUtils.findISecurityMemberFromRoleDTO(assignedRole);
      }
      String securityMemberId = delegatedSecurityMember != null ? delegatedSecurityMember.getSecurityMemberId() : "";
      SideStepProcessParamDTO param = new SideStepProcessParamDTO(selectedProcess, securityMemberId, selectedStepType == SideStepType.PARALLEL, comment);
      String jsonSerializedPayload = BusinessEntityConverter.entityToJsonValue(param);
      Ivy.wf().signals().create().data(jsonSerializedPayload).send(selectedProcess.getSignal());
      if (selectedStepType == SideStepType.SWITCH) {
        TaskUtils.parkTask(task);
        TaskAPI.setHidePropertyToHideInPortal(task);
        PortalNavigatorInFrameAPI.navigateToPortalHome();
      }
      String message = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/SideStep/NewSideStepMessage");
      FacesContext.getCurrentInstance().addMessage(GrowlMessageService.PORTAL_GLOBAL_GROWL_MESSAGE, FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_INFO, message, null));
      PrimeFaces.current().ajax().update(GrowlMessageService.PORTAL_GLOBAL_GROWL);
    }
  }
  
  public void resetData() {
    this.selectedProcess = null;
    this.selectedStepType = null;
    this.assignedRole = null;
    this.assignee = null;
    this.isUserDelegated = true;
    this.comment = "";
  }

  public void changeAssignType() {
    this.assignee = null;
    this.assignedRole = null;
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

  public SideStepConfigurationDTO getSideStepInfo() {
    return sideStepConfigurationInfo;
  }

  public void setSideStepInfo(SideStepConfigurationDTO sideStepInfo) {
    this.sideStepConfigurationInfo = sideStepInfo;
  }

  public RoleDTO getAssignedRole() {
    return assignedRole;
  }

  public void setAssignedRole(RoleDTO assignedRole) {
    this.assignedRole = assignedRole;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public boolean isUserDelegated() {
    return isUserDelegated;
  }

  public void setUserDelegated(boolean isUserDelegated) {
    this.isUserDelegated = isUserDelegated;
  }

  public List<String> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<String> userRoles) {
    this.userRoles = userRoles;
  }
  
  public String buildRequireMessage(String fieldName) {
    return String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/StringFormat/TextWithColon"), 
        fieldName, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/requiredFieldMessage"));
  }
  
  public String getProcessName(SideStepProcessDTO dto) {
    if (dto.getProcessNames() != null) {
      return dto.getProcessNames().get(Ivy.session().getContentLocale().toString());
    }
    return "";
  }
  
  public String getCustomParallelTitle(SideStepConfigurationDTO dto) {
    if (dto.getCustomParallelSideStepTitles() != null) {
      return dto.getCustomParallelSideStepTitles().get(Ivy.session().getContentLocale().toString());
    }
    return "";
  }
  
  public String getCustomSwitchTitle(SideStepConfigurationDTO dto) {
    if (dto.getCustomSwitchSideStepTitles() != null) {
      return dto.getCustomSwitchSideStepTitles().get(Ivy.session().getContentLocale().toString());
    }
    return "";
  }
  
}
