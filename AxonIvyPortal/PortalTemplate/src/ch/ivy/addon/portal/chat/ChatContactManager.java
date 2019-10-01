package ch.ivy.addon.portal.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.IUser;

public final class ChatContactManager {

  private static final String UNKNOWN_USER = "Unknown User";
  private static final String PORTAL_CONNECTOR = "PortalConnector";
  
  private ChatContactManager() {}

  public static List<ChatContact> loadOnlineContacts() {
    List<ChatContact> users = loadContacts();
    filterOnlineUser(users);
    sortByUserNameAndAvailableStatus(users);
    return users;
  }

  public static List<String> getOnlineContacts() {
    return Ivy.wf().getSecurityContext().getSessions().stream().map(ISession::getSessionUserName)
        .filter(session -> !StringUtils.equals(session, Ivy.session().getSessionUserName()) && !StringUtils.contains(session, UNKNOWN_USER)).collect(Collectors.toList());
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
    List<IUser> users = new ArrayList<>(Ivy.wf().getApplication().getSecurityContext().getUsers());
    users.sort((first, second) -> first.getName().compareToIgnoreCase(second.getName()));
    users.removeIf(user -> StringUtils.equals(user.getName(), ISecurityConstants.SYSTEM_USER_NAME) || StringUtils.equals(user.getName(), PORTAL_CONNECTOR));
    return users;
  }
}
