package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portal.chat.ChatReferencesContainer.wf;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.SessionInfo;

public final class ChatContactManager {

  private static final String UNKNOWN_USER = "Unknown User";

  private ChatContactManager() {}

  public static List<ChatContact> loadOnlineContacts() {
    List<ChatContact> users = loadContacts();
    filterOnlineUser(users);
    sortByUserNameAndAvailableStatus(users);
    return users;
  }

  private static List<String> getOnlineContacts() {
    Stream<String> onlineUsernames;
    if (ChatService.IS_STANDARD_MODE) {
      onlineUsernames = securityContext().sessions().all().stream().map(ISession::getSessionUserName);
    } else {
      onlineUsernames = securityContext().sessions().clusterSnapshot().getSessionInfos().stream()
          .map(SessionInfo::getSessionUserName).distinct();
    }
    return onlineUsernames.filter(session -> !StringUtils.equals(session, Ivy.session().getSessionUserName())
        && !StringUtils.contains(session, UNKNOWN_USER)).collect(Collectors.toList());
  }

  private static ISecurityContext securityContext() {
    return wf().getSecurityContext();
  }

  private static List<ChatContact> loadContacts() {
    String currentUserName = Ivy.session().getSessionUserName();
    List<ChatContact> users =
        getContextUsers().stream().map(user -> new ChatContact(user.getName())).collect(Collectors.toList());
    users.removeIf(user -> currentUserName.equals(user.getName()));
    return users;
  }

  private static void sortByUserNameAndAvailableStatus(List<ChatContact> users) {
    users.sort((first, second) -> {
      if ((first.isOnline() && second.isOnline()) || (!first.isOnline() && !second.isOnline())) {
        return first.getName().compareTo(second.getName());
      } else if (first.isOnline()) {
        return -1;
      } else {
        return 1;
      }
    });
  }

  private static void filterOnlineUser(List<ChatContact> users) {
    List<String> onlineUsers = getOnlineContacts();
    users.stream().filter(user -> onlineUsers.contains(user.getName())).forEach(user -> user.setOnline(true));
  }

  private static List<IUser> getContextUsers() {
    return wf().getSecurityContext().users().paged().stream()
        .filter(user -> !StringUtils.equals(user.getName(), ISecurityConstants.SYSTEM_USER_NAME))
        .sorted((first, second) -> first.getName().compareToIgnoreCase(second.getName())).collect(Collectors.toList());
  }
}
