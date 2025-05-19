package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
import com.axonivy.portal.components.util.TaskUtils;
import com.axonivy.portal.components.util.UserUtils;
import com.axonivy.portal.enums.PortalCustomSignature;

import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.environment.Ivy;
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
  private List<UserDTO> approvers;
  private SideStepType selectedStepType;
  private List<SideStepType> stepTypes;
  private String comment;
  private SideStepDTO sideStepInfo;
  
  @PostConstruct
  public void init() {
    setApprovers(UserUtils.findUsers("", 0, -1, Collections.emptyList(), Collections.emptyList()));
  }

  public List<SideStepProcessDTO> getProcesses() {
    return processes;
  }


  public void setProcesses(List<SideStepProcessDTO> processes) {
    this.processes = processes;
  }


  public SideStepProcessDTO getSelectedProcess() {
    return selectedProcess;
  }

  public void setSelectedProcess(SideStepProcessDTO selectedProcess) {
    this.selectedProcess = selectedProcess;
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
            SideStepDTO sideStepInfo = BusinessEntityConverter.jsonValueToEntity(sideStepString,
                SideStepDTO.class);
            if (sideStepInfo.getParallel()) {
              this.selectedStepType = SideStepType.PARALLEL;
            } else if (!sideStepInfo.getParallel()) {
              this.selectedStepType = SideStepType.SWITCH;
            } else {
              setStepTypes(Arrays.asList(SideStepType.class.getEnumConstants()));
            }
            processes = sideStepInfo.getProcesses();
          }
        }
      } catch (NullPointerException ex) {
      }
    }
    return processes;
  }

  public void handleSelectProcess() {
    if (task != null && selectedProcess != null) {
      if (selectedProcess.getOriginalTaskId() == null) {
        selectedProcess.setOriginalTaskId(task.uuid());
      }
      

      String callableUsers = selectedProcess.getUsersCallable();
      if (StringUtils.isNotBlank(callableUsers)) {
        Map<String, Object> responseCallableUsers =  IvyAdapterService.startSubProcessInSecurityContext(callableUsers, null);
      }
      SideStepProcessParam param = new SideStepProcessParam(selectedProcess, assignee, selectedStepType, comment);
      String jsonSerializedPayload = BusinessEntityConverter.entityToJsonValue(param);
      Ivy.wf().signals().create().data(jsonSerializedPayload).send(selectedProcess.getSignal());
      if (selectedStepType == SideStepType.SWITCH) {
        TaskUtils.parkTask(task);
        TaskAPI.setHidePropertyToHideInPortal(task); 
        PortalNavigatorInFrameAPI.navigateToPortalHome();
      }

    }
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

  public List<UserDTO> getApprovers() {
    return approvers;
  }

  public void setApprovers(List<UserDTO> approvers) {
    this.approvers = approvers;
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

}
