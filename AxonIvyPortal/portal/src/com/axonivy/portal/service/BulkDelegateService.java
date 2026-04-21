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
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.enums.PortalCustomSignature;

import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.SearchScope;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.exec.Sudo;
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
      return findUsers(query);
    }
    if (selectedTasks == null || selectedTasks.isEmpty()) {
      return new ArrayList<>();
    }
    List<UserDTO> cacheAllUserDTOs = new ArrayList<>();
    List<UserDTO> intersectedUsers = null;
    for (ITask task : selectedTasks) {
      List<Map<String, Object>> result = callCustomDelegate(task);
      if (CollectionUtils.isEmpty(result)) {
        return new ArrayList<>();
      }
      List<UserDTO> customUsers = new ArrayList<>();
      for (Map<String, Object> map : (List<Map<String, Object>>) result) {
        if (!CustomProcessUtils.isSkipCustomProcess(map)) {
          Object usersObj = map.get("users");
          
          if (usersObj instanceof List) {
            List<UserDTO> usersList = (List<UserDTO>) usersObj;
            if (usersList.isEmpty()) {
              cacheAllUserDTOs = getCachedUsers(query, cacheAllUserDTOs);
              customUsers.addAll(cacheAllUserDTOs);
            } else {
              customUsers.addAll(usersList);
            }
          }
        } else {
          cacheAllUserDTOs = getCachedUsers(query, cacheAllUserDTOs);
          customUsers.addAll(cacheAllUserDTOs);
        }
      }
      intersectedUsers = getIntersectedUserIds(intersectedUsers, customUsers);
    }
    return intersectedUsers;
  }

  private List<UserDTO> getCachedUsers(String query, List<UserDTO> cacheAllUserDTOs) {
    return Sudo.get(() -> {
      if (cacheAllUserDTOs.isEmpty()) {
        cacheAllUserDTOs.addAll(findUsers(query));
      }
      return cacheAllUserDTOs;
    });
  }

  private List<UserDTO> findUsers(String query) {
    var userDtos = SecurityService.newInstance().findUsersWithRoles(query, 0, 101, null, null);
    return userDtos.getUsers();
  }

  @SuppressWarnings("unchecked")
  public List<RoleDTO> completeRoleForBulkDelegate(String query, List<ITask> selectedTasks) {
    boolean customDelegateAvailable = checkCustomDelegateAvailable();
    if (!customDelegateAvailable) {
      return RoleUtils.findRoles(null, null, query);
    }
    if (selectedTasks == null || selectedTasks.isEmpty()) {
      return new ArrayList<>();
    }
    List<RoleDTO> intersectedRoles = null;
    for (ITask task : selectedTasks) {
      List<Map<String, Object>> result = callCustomDelegate(task);
      if (CollectionUtils.isEmpty(result)) {
        return new ArrayList<>();
      }
      List<RoleDTO> customRoles = new ArrayList<>();
      List<RoleDTO> cacheAllRoleDTOs = new ArrayList<>();

      for (Map<String, Object> map : (List<Map<String, Object>>) result) {
        if (!CustomProcessUtils.isSkipCustomProcess(map)) {
          Object rolesObj = map.get("roles");
          if (rolesObj instanceof List) {
            List<RoleDTO> rolesList = (List<RoleDTO>) rolesObj;
            if (rolesList.isEmpty()) {
              cacheAllRoleDTOs = getCachedRoles(query, cacheAllRoleDTOs);
              customRoles.addAll(cacheAllRoleDTOs);
            } else {
              customRoles.addAll(rolesList);
            }

            customRoles.addAll((List<RoleDTO>) rolesObj);
          }
        } else {
          cacheAllRoleDTOs = getCachedRoles(query, cacheAllRoleDTOs);
          customRoles.addAll(cacheAllRoleDTOs);
        }
      }
      intersectedRoles = getIntersectedRoleIds(intersectedRoles, customRoles);
    }
    return intersectedRoles;
  }

  private List<RoleDTO> getCachedRoles(String query, List<RoleDTO> cacheAllRoleDTOs) {
    return Sudo.get(() -> {
      if (cacheAllRoleDTOs.isEmpty()) {
        cacheAllRoleDTOs.addAll(RoleUtils.findRoles(null, null, query));
      }
      return cacheAllRoleDTOs;
    });
  }
  
  private static boolean checkCustomDelegateAvailable() {
    var filter = SubProcessSearchFilter.create()
        .setSearchScope(SearchScope.SECURITY_CONTEXT)
        .setSignature(PortalCustomSignature.DELEGATE.getSignature()).toFilter();

    var subProcessStartList = SubProcessCallStartEvent.find(filter);
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

    private static List<RoleDTO> getIntersectedRoleIds(List<RoleDTO> intersectedRoles, List<RoleDTO> customRoles) {
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

  private static List<UserDTO> getIntersectedUserIds(List<UserDTO> intersectedUsers, List<UserDTO> customUsers) {
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
    if (selectedUser == null && selectedRole == null) {
      throw new PortalException("Either selectedUser or selectedRole must be provided");
    }
    for (ITask task : tasks) {
      // Reset task
      TaskUtils.resetTask(task);

      // Resolve the delegate target
      String newResponsibleName = "";
      ISecurityMember delegatedSecurityMember = null;

      if (selectedUser != null && selectedUser.getSecurityMemberId() != null) {
        newResponsibleName = selectedUser.getDisplayName();
        delegatedSecurityMember = SecurityMemberUtils.findISecurityMemberFromUserDTO(selectedUser);
      } else if (selectedRole != null && selectedRole.getSecurityMemberId() != null) {
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
