package ch.ivy.addon.portal.chat;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatGroupUtils {

  private static final List<String> SYSTEM_USERS = Arrays.asList("SYSTEM", "PortalConnector");

  public static Set<String> getUserNamesFromGroup(long caseId) {
    return getAllUsersFromAssigneeNames(getAssigneesFromGroup(caseId));
  }

  public static Map<String, Set<String>> getParticipantsFromGroup(long caseId) {
    Set<String> assignees = ChatGroupUtils.getAssigneesFromGroup(caseId);
    Map<String, Set<String>> participants = new HashMap<>();
    Set<String> users = new HashSet<>();
    for (String assignee : assignees) {
      if (assignee.startsWith("#")) {
        users.add(assignee.substring(1));
      } else {
        participants.put(assignee, getAllUsersFromRole(assignee));
      }
    }
    if (CollectionUtils.isNotEmpty(users)) {
      participants.put("users", users);
    }
    return participants;
  }

  public static Set<String> getAllUsersFromRole(String roleName) {
    Set<String> userNames = new HashSet<>();
    IRole role = Ivy.wf().getApplication().getSecurityContext().findRole(roleName);
    if (role != null) {
      userNames.addAll(role.getAllUsers().stream().map(user -> user.getName()).collect(Collectors.toSet()));
    }
    userNames.removeAll(SYSTEM_USERS);
    return userNames;
  }
  
  public static boolean hasRole(ISecurityMember securityRole, IUser user) {
    IRole role = Ivy.wf().getApplication().getSecurityContext().findRole(securityRole.getName());
    return user.getUserToken().hasRole(role, false); 
  }

  public static Set<String> getAllUsersFromAssigneeNames(Set<String> assigneeNames) {
    Set<String> userNames = new HashSet<>();
    Set<String> userNamesOnly =
        assigneeNames.stream().filter(name -> name.startsWith("#")).map(name -> name.substring(1))
            .collect(Collectors.toSet());
    userNames.addAll(userNamesOnly);
    Set<String> roleNamesOnly =
        assigneeNames.stream().filter(name -> !name.startsWith("#")).collect(Collectors.toSet());
    userNames.addAll(getAllUsersFromRoles(roleNamesOnly));
    return userNames;
  }

  private static Set<String> getAssigneesFromGroup(long caseId) {
    Set<String> assignees = new HashSet<>();
    ICase iCase = Ivy.wf().findCase(caseId);
    if (iCase == null) {
      return assignees;
    }
    ObjectMapper mapper = new ObjectMapper();
    String groupChatJson = Optional.ofNullable(iCase.getCustomVarCharField5()).orElse(StringUtils.EMPTY);
    try {
      GroupChat groupChat = mapper.readValue(groupChatJson, GroupChat.class);
      assignees = groupChat.getAssigneeNames();
    } catch (IOException e) {
      Ivy.log()
          .error("Failed to parse asignees in group chat for case {0}, json: {1}", e, iCase.getId(), groupChatJson);
    }
    return assignees;
  }

  private static Set<String> getAllUsersFromRoles(Set<String> roleNames) {
    Set<String> userNames = new HashSet<>();
    for (String roleName : roleNames) {
      IRole role = Ivy.wf().getApplication().getSecurityContext().findRole(roleName);
      if (role != null) {
        userNames.addAll(role.getAllUsers().stream().map(user -> user.getName()).collect(Collectors.toSet()));
      }
    }
    userNames.removeAll(SYSTEM_USERS);
    return userNames;
  }
}
