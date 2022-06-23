package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portal.chat.ChatReferencesContainer.getApplication;
import static ch.ivy.addon.portal.chat.ChatReferencesContainer.log;
import static ch.ivy.addon.portal.chat.ChatReferencesContainer.wf;

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
import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;

public class ChatGroupUtils {

  private static final List<String> SYSTEM_USERS = Arrays.asList("SYSTEM");
  private static final String USER_IDENTIFIER = "#";

  private ChatGroupUtils() {}

  public static Set<String> getUserNamesFromGroup(long caseId) {
    return getAllUsersFromAssigneeNames(getAssigneesFromGroup(caseId));
  }

  public static Map<String, Set<String>> getParticipantsFromGroup(long caseId) {
    Set<String> assignees = ChatGroupUtils.getAssigneesFromGroup(caseId);
    Map<String, Set<String>> participants = new HashMap<>();
    Set<String> users = new HashSet<>();
    for (String assignee : assignees) {
      if (assignee.startsWith(USER_IDENTIFIER)) {
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
    IRole role = getApplication().getSecurityContext().roles().find(roleName);
    if (role != null) {
      userNames.addAll(role.users().allPaged().stream().map(IUser::getName).collect(Collectors.toSet()));
    }
    userNames.removeAll(SYSTEM_USERS);
    return userNames;
  }

  public static boolean hasRole(ISecurityMember securityRole, IUser user) {
    IRole role = getApplication().getSecurityContext().roles().find(securityRole.getName());
    return user.getUserToken().hasRole(role, false);
  }

  public static Set<String> getAllUsersFromAssigneeNames(Set<String> assigneeNames) {
    Set<String> userNames = new HashSet<>();
    Set<String> userNamesOnly = assigneeNames.stream().filter(name -> name.startsWith(USER_IDENTIFIER))
        .map(name -> name.substring(1)).collect(Collectors.toSet());
    userNames.addAll(userNamesOnly);
    Set<String> roleNamesOnly =
        assigneeNames.stream().filter(name -> !name.startsWith(USER_IDENTIFIER)).collect(Collectors.toSet());
    userNames.addAll(getAllUsersFromRoles(roleNamesOnly));
    return userNames;
  }

  public static Set<String> getAllUsersFromUserIdsAndRoleNames(Set<String> assigneeNames) {
    Set<String> userNames = new HashSet<>();
    List<String> userNamesOnly = assigneeNames.stream().filter(name -> name.startsWith(USER_IDENTIFIER))
        .map(name -> wf().getSecurityContext().users().find(Long.parseLong(name.substring(1))).getName())
        .collect(Collectors.toList());

    Set<String> roleNamesOnly =
        assigneeNames.stream().filter(name -> !name.startsWith(USER_IDENTIFIER)).collect(Collectors.toSet());
    userNames.addAll(getAllUsersFromRoles(roleNamesOnly));
    userNames.addAll(userNamesOnly);
    return userNames;
  }

  public static IUser findUserByUserId(Long userId) {
    return wf().getSecurityContext().users().find(userId);
  }

  public static IUser findUserByUsername(String username) {
    return wf().getSecurityContext().users().find(username);
  }

  private static Set<String> getAssigneesFromGroup(long caseId) {
    Set<String> assignees = new HashSet<>();
    ICase iCase = wf().findCase(caseId);
    if (iCase == null) {
      return assignees;
    }
    ObjectMapper mapper = new ObjectMapper();
    String groupChatJson = iCase.customFields().stringField(AdditionalProperty.PORTAL_GROUP_CHAT_INFO.toString()).get()
        .orElse(StringUtils.EMPTY);
    try {
      GroupChat groupChat = mapper.readValue(groupChatJson, GroupChat.class);
      Set<String> originalAssignees = Optional.ofNullable(groupChat.getAssigneeNames()).orElse(new HashSet<>());
      originalAssignees.forEach(assigneeName -> {
        if (assigneeName.startsWith(USER_IDENTIFIER) && NumberUtils.isParsable(assigneeName.substring(1))) {
          IUser user = findUserByUserId(Long.parseLong(assigneeName.substring(1)));
          if (user != null) {
            assignees.add(user.getMemberName());
          }
        } else {
          assignees.add(assigneeName);
        }
      });
    } catch (IOException e) {
      log().error("Failed to parse asignees in group chat for case {0}, json: {1}", e, iCase.getId(), groupChatJson);
    }
    return assignees;
  }

  private static Set<String> getAllUsersFromRoles(Set<String> roleNames) {
    Set<String> userNames = new HashSet<>();
    for (String roleName : roleNames) {
      IRole role = getApplication().getSecurityContext().roles().find(roleName);
      if (role != null) {
        userNames.addAll(role.users().allPaged().stream().map(IUser::getName).collect(Collectors.toSet()));
      }
    }
    userNames.removeAll(SYSTEM_USERS);
    return userNames;
  }

}
