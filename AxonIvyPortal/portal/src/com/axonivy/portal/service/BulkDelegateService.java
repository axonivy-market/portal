package com.axonivy.portal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.components.util.CustomProcessUtils;
import com.axonivy.portal.enums.PortalCustomSignature;

import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.SearchScope;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ITask;

public class BulkDelegateService {
  private static BulkDelegateService instance;

  public static BulkDelegateService getInstance() {
    if (instance == null) {
      instance = new BulkDelegateService();
    }
    return BulkDelegateService.instance;
  }

  @SuppressWarnings("unchecked")
  public List<UserDTO> completeUserForBulkDelegate(String query, List<ITask> selectedTasks) {
    boolean customDelegateAvailable = checkCustomDelegateAvailable();
    if (!customDelegateAvailable) {
      Ivy.log().info("No custom delegate found, using default user search for query: " + query);
      var dto = SecurityService.newInstance().findUsersWithRoles(query, 0, 101, null, null);
      return dto.getUsers();
    }
    if (selectedTasks == null || selectedTasks.isEmpty()) {
      return new ArrayList<>();
    }
    Ivy.log().info("Starting bulk delegate user autocomplete for query: '" + query + "' with " + selectedTasks.size() + " selected tasks.");
    List<UserDTO> intersectedUsers = new ArrayList<>();
    for (ITask task : selectedTasks) {
      List<Map<String, Object>> result = callCustomDelegate(task);
      List<UserDTO> customUsers = new ArrayList<>();
      if (CollectionUtils.isEmpty(result)) {
        Ivy.log().info("No custom delegate result for task '" + task.getName() + "'. Skipping.");
        continue;
      }
      for (Map<String, Object> map : (List<Map<String, Object>>) result) {
        if (!CustomProcessUtils.isSkipCustomProcess(map)) {
          Object usersObj = map.get("users");
          if (usersObj instanceof List) {
            customUsers.addAll((List<UserDTO>) usersObj);
          }
        }
      }
      intersectedUsers = getUniqUserIds(intersectedUsers, customUsers);
    }
    return intersectedUsers;
  }

  public List<RoleDTO> completeRoleForBulkDelegate(String query, List<ITask> selectedTasks) {
    boolean customDelegateAvailable = checkCustomDelegateAvailable();
    if (!customDelegateAvailable) {
      Ivy.log().info("No custom delegate found, using default role search for query: " + query);
      var dto = SecurityService.newInstance().findUsersWithRoles(query, 0, 101, null, null);
      return dto.getRoleDTOs();
    }
    if (selectedTasks == null || selectedTasks.isEmpty()) {
      return new ArrayList<>();
    }
    List<RoleDTO> intersectedRoles = new ArrayList<>();
    for (ITask task : selectedTasks) {
      List<Map<String, Object>> result = callCustomDelegate(task);
      List<RoleDTO> customRoles = new ArrayList<>();

      for (Map<String, Object> map : (List<Map<String, Object>>) result) {
        if (!CustomProcessUtils.isSkipCustomProcess(map)) {
          Object rolesObj = map.get("roles");
          if (rolesObj instanceof List) {
            customRoles.addAll((List<RoleDTO>) rolesObj);
          }
        }
      }
      intersectedRoles = getUniqRoleIds(intersectedRoles, customRoles);
    }
    return intersectedRoles;
  }
  
  private static boolean checkCustomDelegateAvailable() {
    var filter = SubProcessSearchFilter.create()
        .setSearchScope(SearchScope.SECURITY_CONTEXT)
        .setSignature(PortalCustomSignature.DELEGATE.getSignature()).toFilter();

    var subProcessStartList = SubProcessCallStartEvent.find(filter);
    Ivy.log().info(subProcessStartList);
    return CollectionUtils.isNotEmpty(subProcessStartList);
  }

  private List<Map<String, Object>> callCustomDelegate(ITask task) {
    Map<String, Object> params = new HashMap<>();
    params.put("roles", null);
    params.put("users", null);
    params.put("currentUser", SecurityMemberUtils.getCurrentSessionUserAsSecurityMemberDTO());
    params.put("task", task);
    List<Map<String, Object>> result = IvyAdapterService
        .startSubProcessesInSecurityContext(PortalCustomSignature.DELEGATE.getSignature(), params);
    return result;
  }

    private static List<RoleDTO> getUniqRoleIds(List<RoleDTO> intersectedRoles, List<RoleDTO> customRoles) {
    Set<String> uniqRoleIds = customRoles.stream()
        .map(RoleDTO::getSecurityMemberId)
        .collect(java.util.stream.Collectors.toSet());
    if (intersectedRoles == null) {
      intersectedRoles = new ArrayList<>(customRoles);
    } else {
      intersectedRoles.removeIf(r -> !uniqRoleIds.contains(r.getSecurityMemberId()));
    }
    return intersectedRoles;
  }

  private static List<UserDTO> getUniqUserIds(List<UserDTO> intersectedUsers, List<UserDTO> customUsers) {
    Set<String> uniqUserIds = customUsers.stream()
        .map(UserDTO::getSecurityMemberId)
        .collect(java.util.stream.Collectors.toSet());

    if (intersectedUsers == null) {
      intersectedUsers = new ArrayList<>(customUsers);
    } else {
      // Keep ONLY users that currently exist in both sets
      intersectedUsers.removeIf(u -> !uniqUserIds.contains(u.getSecurityMemberId()));
    }
    return intersectedUsers;
  }

  public void delegateTasks(List<ITask> tasks, UserDTO selectedUser, RoleDTO selectedRole,
      String taskDelegationComment) {
    for (ITask task : tasks) {
      // Reset task
      TaskUtils.resetTask(task);

      // Resolve the delegate target
      String newResponsibleName = "";
      ISecurityMember delegatedSecurityMember = null;

      if (selectedUser != null) {
        newResponsibleName = selectedUser.getDisplayName();
        delegatedSecurityMember = SecurityMemberUtils.findISecurityMemberFromUserDTO(selectedUser);
      } else if (selectedRole != null) {
        newResponsibleName = selectedRole.getDisplayName();
        delegatedSecurityMember = SecurityMemberUtils.findISecurityMemberFromRoleDTO(selectedRole);
      }

      // Create note
      String oldResponsibleName = TaskUtils.toDisplayNameResponsible(task.responsibles());

      String delegateComment;
      if (StringUtils.isBlank(taskDelegationComment)) {
        delegateComment = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskDelegate/delegateComment",
            Arrays.asList(task.getId(), oldResponsibleName, newResponsibleName));
      } else {
        delegateComment = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskDelegate/delegateReasonIncludedComment",
            Arrays.asList(task.getId(), oldResponsibleName, newResponsibleName, taskDelegationComment.trim()));
      }

      // Delegate task
      TaskUtils.delegateTask(task, delegatedSecurityMember);

      // Add note
      task.getCase().createNote(Ivy.session(), delegateComment);
    }
  }
}
