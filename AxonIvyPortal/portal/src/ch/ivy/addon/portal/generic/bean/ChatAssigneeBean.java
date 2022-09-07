package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portal.chat.ChatGroupUtils;
import ch.ivy.addon.portal.chat.ChatReferencesContainer;
import ch.ivy.addon.portal.chat.CreateGroupChatStatus;
import ch.ivy.addon.portal.chat.GroupChat;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

@ManagedBean
@ViewScoped
public class ChatAssigneeBean implements Serializable {

  private static final String TASK_TEMPLATE_GROWL_ID = "task-template-growl";
  private static final String CHAT_ASSIGNEE_ERROR_MESSAGE_ID = "chat-assignee-selection-form:error-message";
  private static final String CONFIGURED_ROLES_SUB_PROCESS = "configureRolesForGroupChat(ch.ivyteam.ivy.workflow.ITask)";


  private static final long serialVersionUID = 4691697531600235758L;

  private boolean isAssignToUser = true;
  private UserDTO selectedUser;
  private RoleDTO selectedRole;
  private Set<SecurityMemberDTO> selectedAssignees = new HashSet<>();
  private boolean doesGroupChatExist;
  private String groupChatExistMessage;
  private GroupChat existedGroupChat;
  private boolean isShowCreateGroupChatDialog;
  private ITask task;

  @PostConstruct
  public void init() {
    selectedAssignees.add(SecurityMemberUtils.getCurrentSessionUserAsSecurityMemberDTO());
    task = Ivy.wfTask();
  }

  private void checkCaseHasGroupChat() {
    CaseQuery caseQuery = queryCaseHasGroupChat();
    doesGroupChatExist = Ivy.wf().getCaseQueryExecutor().getFirstResult(caseQuery) != null;
  }

  public void handleConfiguredRoleList() {
    List<IRole> configuredRoles = getConfiguredRoles();

    if (CollectionUtils.isEmpty(configuredRoles)) {
      isShowCreateGroupChatDialog = true;
    } else {
      List<IRole> memberRoles = hasRole(configuredRoles, Ivy.session().getSessionUser());
      if (CollectionUtils.isNotEmpty(memberRoles)) {
        isShowCreateGroupChatDialog = true;
        selectedAssignees.remove(SecurityMemberUtils.getCurrentSessionUserAsSecurityMemberDTO());
        selectedAssignees.addAll(SecurityMemberUtils.convertIRoleToSecurityMemberDTO(memberRoles));
      } else {
        selectedAssignees.addAll(SecurityMemberUtils.convertIRoleToSecurityMemberDTO(configuredRoles));
      }
    }
  }

  private List<IRole> hasRole(List<IRole> roles, IUser user) {
    List<IRole> memberRoles = new ArrayList<>();
    for (int i = 0; i < roles.size(); i++) {
      if (ChatGroupUtils.hasRole(roles.get(i), user)) {
        memberRoles.add(roles.get(i));
      }
    }
    return memberRoles;
  }

  public void changeAssigneeType() {
    if (isAssignToUser) {
      selectedRole = null;
    } else {
      selectedUser = null;
    }
  }

  public void addAssignee() {
    SecurityMemberDTO selectedAssignee = selectedUser != null ? SecurityMemberDTOMapper.mapFromUserDTO(selectedUser) :  SecurityMemberDTOMapper.mapFromRoleDTO(selectedRole);
    if (selectedAssignee == null || isSelectedAssigneeExist(selectedAssignee)) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/chat/errorSelectInvalidAssignee")));
      return;
    }

    selectedAssignees.add(selectedAssignee);
  }

  private boolean isSelectedAssigneeExist(SecurityMemberDTO selectedAssignee) {
    for (SecurityMemberDTO securityMemberDTO : selectedAssignees) {
      if (securityMemberDTO.getName().equals(selectedAssignee.getName())) {
        return true;
      }
    }
    return false;
  }

  public void removeAssignee(SecurityMemberDTO assignee) {
    selectedAssignees.remove(assignee);
  }

  public String getGroupChatExistMessage() {
    if (StringUtils.isBlank(groupChatExistMessage)) {
      CaseQuery caseQuery = queryCaseHasGroupChat();
      ICase iCase = Ivy.wf().getCaseQueryExecutor().getFirstResult(caseQuery);
      existedGroupChat = mapFromCustomField(iCase);
      groupChatExistMessage = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/chat/processChatWasCreated",
          Arrays.asList(getGroupChatName(existedGroupChat)));
    }
    return groupChatExistMessage;
  }

  public boolean doesGroupChatExist() {
    return doesGroupChatExist;
  }

  public void joinGroupChat() {
    if (existedGroupChat != null) {
      Set<String> assigneeNames = existedGroupChat.getAssigneeNames();
      if (CollectionUtils.isNotEmpty(assigneeNames)) {
        assigneeNames.add("#".concat(Long.toString(Ivy.session().getSessionUser().getId())));
        String groupChatName = getGroupChatName(existedGroupChat);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
            Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/chat/joinedProcessChat",
                Arrays.asList(groupChatName)), null);
        CreateGroupChatStatus createGroupChatStatus = CreateGroupChatStatus.FAIL;
        boolean joinGroup = true;
        try {
          createGroupChatStatus = saveGroupChat(existedGroupChat, true);
        } catch (JsonProcessingException ex) {
          Ivy.log().error("Failure to process json {0}", ex, existedGroupChat.toString());
          joinGroup = false;
        }
        if (createGroupChatStatus == CreateGroupChatStatus.FAIL
            || createGroupChatStatus == CreateGroupChatStatus.JSON_TOO_LONG) {
          message = generateErrorMessageWhenJoinGroupChat();
          joinGroup = false;
        }
        FacesContext.getCurrentInstance().addMessage(TASK_TEMPLATE_GROWL_ID, message);
        PrimeFaces.current().ajax().update(TASK_TEMPLATE_GROWL_ID);
        PrimeFaces.current().executeScript("PF('chat-assignee-dialog').hide()");
        if (joinGroup) {
          openChatGroup(groupChatName);
        }
      }
    }
  }

  private FacesMessage generateErrorMessageWhenJoinGroupChat() {
    return new FacesMessage(FacesMessage.SEVERITY_ERROR,
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/chat/failureToJoinProcessChat"), null);
  }

  private GroupChat mapFromCustomField(ICase iCase) {
    String groupChatJson = iCase.customFields().stringField(AdditionalProperty.PORTAL_GROUP_CHAT_INFO.toString()).get()
        .orElse(StringUtils.EMPTY);
    try {
      ObjectMapper mapper = new ObjectMapper();
      GroupChat result = mapper.readValue(groupChatJson, GroupChat.class);
      result.getAssignees();
      return result;
    } catch (IOException e) {
      Ivy.log().error("Failed to parse group chat for case {0}, json: {1}", e, iCase.getId(), groupChatJson);
      return null;
    }
  }

  private CaseQuery queryCaseHasGroupChat() {
    CaseQuery caseQuery = CaseUtils.createBusinessCaseQuery();
    caseQuery.where().caseId().isEqual(task.getCase().getBusinessCase().getId()).and().customField()
        .stringField(AdditionalProperty.PORTAL_GROUP_CHAT_INFO.toString()).isNotNull();
    return caseQuery;
  }

  public void createGroupChatForConfiguredRoleList(ITask task) {
    this.task = task;
    checkCaseHasGroupChat();
    handleConfiguredRoleList();

    if (isShowCreateGroupChatDialog) {
      PrimeFaces.current().executeScript("PF('chat-assignee-dialog').show()");
    } else if (!doesGroupChatExist) {
      createGroupChat();
    }

    if (doesGroupChatExist) {
      PrimeFaces.current().executeScript("PF('chat-assignee-dialog').show()");
    }
  }

  public void createGroupChat() {
    if (checkNoAssignees() || checkGroupChatExist()) {
      return;
    }

    ICase iCase = task.getCase().getBusinessCase();
    GroupChat group = initGroupChat(iCase);
    String groupChatName = getGroupChatName(group);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, Ivy.cms()
        .co("/ch.ivy.addon.portalkit.ui.jsf/chat/processChatIsCreated", Arrays.asList(groupChatName)), null);
    boolean isCreated = true;

    try {
      CreateGroupChatStatus createGroupChatStatus = saveGroupChat(group, false);
      if (createGroupChatStatus == CreateGroupChatStatus.ALREADY_EXIST) {
        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, getGroupChatExistMessage(), null);
      } else if (createGroupChatStatus == CreateGroupChatStatus.SUSCCESS) {
        if (ChatReferencesContainer.getChatService() != null) {
          ChatReferencesContainer.getChatService().updateGroupList(group);
        }
      } else {
        message = generateErrorMessageWhenCreateGroupChat();
        isCreated = false;
      }
    } catch (JsonProcessingException ex) {
      Ivy.log().error("Failure to process json {0}", ex, group.toString());
      message = generateErrorMessageWhenCreateGroupChat();
      isCreated = false;
    }
    FacesContext.getCurrentInstance().addMessage(TASK_TEMPLATE_GROWL_ID, message);
    PrimeFaces.current().ajax().update(TASK_TEMPLATE_GROWL_ID);
    PrimeFaces.current().executeScript("PF('chat-assignee-dialog').hide()");
    if (isCreated) {
      openChatGroup(groupChatName);
    }
  }

  private void openChatGroup(String groupChatName) {
    String function = "$('#toggle-chat-panel-command').click();" + 
        "var checkExist = setInterval(function() { " + 
        "   if ($('span[title=\"" + groupChatName + "\"]').length) {" + 
        "      $('span[title=\"" + groupChatName + "\"]').click();" + 
        "      clearInterval(checkExist);" + 
        "   }" + 
        "}, 100);";
    PrimeFaces.current().executeScript(function);
  }

  private FacesMessage generateErrorMessageWhenCreateGroupChat() {
    return new FacesMessage(FacesMessage.SEVERITY_ERROR,
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/chat/failureToCreateProcessChat"), null);
  }

  @SuppressWarnings("unchecked")
  private String getGroupChatName(GroupChat group) {
    Map<String, Object> response = IvyAdapterService.startSubProcess("setGroupChatName()", null,
        Arrays.asList(PortalLibrary.PORTAL.getValue()));
    String groupChatName = response.get("name").toString();
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> mappedObject = objectMapper.convertValue(group, Map.class);
    for (Map.Entry<String, Object> entry : mappedObject.entrySet()) {
      if (StringUtils.equals("params", entry.getKey())) {
        Map<String, String> params = (Map<String, String>) entry.getValue();
        for (Map.Entry<String, String> param : params.entrySet()) {
          groupChatName = groupChatName.replace("{" + param.getKey() + "}", param.getValue());
        }
      } else {
        groupChatName = groupChatName.replace("{" + entry.getKey() + "}", entry.getValue().toString());
      }
    }
    return groupChatName;
  }

  private CreateGroupChatStatus saveGroupChat(GroupChat group, boolean isUpdate) throws JsonProcessingException {
    IBusinessCase iCase = task.getCase().getBusinessCase();
    String portalGroupChatInfo = iCase.customFields().stringField(AdditionalProperty.PORTAL_GROUP_CHAT_INFO.toString())
        .get().orElse(StringUtils.EMPTY);
    if (StringUtils.isBlank(portalGroupChatInfo) || isUpdate) {
      String json = new ObjectMapper().writeValueAsString(group);
      if (StringUtils.length(json) > PortalConstants.CUSTOM_STRING_FIELD_MAX_LENGTH) {
        return CreateGroupChatStatus.JSON_TOO_LONG;
      } else {
        iCase.customFields().stringField(AdditionalProperty.PORTAL_GROUP_CHAT_INFO.toString()).set(json);
        return CreateGroupChatStatus.SUSCCESS;
      }
    }
    return CreateGroupChatStatus.ALREADY_EXIST;
  }

  private GroupChat initGroupChat(ICase iCase) {
    GroupChat group = new GroupChat();
    group.setCaseId(iCase.getId());
    group.setCaseName(iCase.getName());
    group.setApplicationName(task.getApplication().getName());
    group.setCreator(Ivy.session().getSessionUserName());
    group.setAssignees(selectedAssignees);
    Map<String, Object> response = IvyAdapterService.startSubProcess("getGroupChatParams()", null,
        Arrays.asList(PortalLibrary.PORTAL.getValue()));
    @SuppressWarnings("unchecked")
    Map<String, String> params = (Map<String, String>) response.get("params");
    group.setParams(params);
    return group;
  }

  private boolean checkGroupChatExist() {
    if (doesGroupChatExist()) {
      FacesContext.getCurrentInstance().addMessage(CHAT_ASSIGNEE_ERROR_MESSAGE_ID,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getGroupChatExistMessage()));
      PrimeFaces.current().ajax().update(CHAT_ASSIGNEE_ERROR_MESSAGE_ID);
      return true;
    }
    return false;
  }

  private boolean checkNoAssignees() {
    if (CollectionUtils.isEmpty(selectedAssignees)) {
      FacesContext.getCurrentInstance().addMessage(CHAT_ASSIGNEE_ERROR_MESSAGE_ID, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "", Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/chat/noAssignees")));
      PrimeFaces.current().ajax().update(CHAT_ASSIGNEE_ERROR_MESSAGE_ID);
      return true;
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  private List<IRole> getConfiguredRoles() {
    Map<String, Object> params = new HashMap<>();
    params.put("task", task);
    Map<String, Object> response = IvyAdapterService.startSubProcess(CONFIGURED_ROLES_SUB_PROCESS, params,
        Arrays.asList(PortalLibrary.PORTAL.getValue()));
    List<IRole> roles = (List<IRole>) response.get("roles");
    return roles.stream().filter(role -> !Objects.isNull(role)).collect(Collectors.toList());
  }

  public boolean getIsAssignToUser() {
    return isAssignToUser;
  }

  public void setIsAssignToUser(boolean isAssignToUser) {
    this.isAssignToUser = isAssignToUser;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO selectedUser) {
    this.selectedUser = selectedUser;
  }

  public RoleDTO getSelectedRole() {
    return selectedRole;
  }

  public void setSelectedRole(RoleDTO selectedRole) {
    this.selectedRole = selectedRole;
  }

  public Set<SecurityMemberDTO> getSelectedAssignees() {
    return selectedAssignees;
  }

  public void setSelectedAssignees(Set<SecurityMemberDTO> selectedAssignees) {
    this.selectedAssignees = selectedAssignees;
  }

  public boolean isShowCreateGroupChatDialog() {
    return isShowCreateGroupChatDialog;
  }
}
