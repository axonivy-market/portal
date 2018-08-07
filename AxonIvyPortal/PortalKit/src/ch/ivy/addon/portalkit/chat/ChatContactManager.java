package ch.ivy.addon.portalkit.chat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;

public final class ChatContactManager {

  private static final String CONTACT_STATUS_CONTAINTER_PATH = System.getProperty("java.io.tmpdir")
      + "/contact-status/";
  private static final String CONTACT_STATUS_FILE_PATH = CONTACT_STATUS_CONTAINTER_PATH + "%s";

  private static final Map<String, Integer> ONLINE_INSTANCES = new HashMap<>();

  private ChatContactManager() {}

  public static List<ChatContact> loadOnlineContacts() {
    List<ChatContact> users = loadContacts();
    filterOnlineUser(users);
    sortByUserNameAndAvailableStatus(users);
    return users;
  }

  synchronized public static void setContactOnline(String contactName) throws IOException {
    Path path = Paths.get(String.format(CONTACT_STATUS_FILE_PATH, contactName));
    if (!Files.exists(path)) {
      Files.createDirectories(path.getParent());
      Files.createFile(path);
    }
    increaseNumberOfOnlineInstance(contactName);;
  }

  synchronized public static void setContactOffline(String contactName) throws IOException {
    if (ONLINE_INSTANCES.get(contactName) != null && ONLINE_INSTANCES.get(contactName) < 2) {
      Path path = Paths.get(String.format(CONTACT_STATUS_FILE_PATH, contactName));
      if (Files.exists(path)) {
        Files.delete(path);
      }
    }
    decreaseNumberOfOnlineUnstance(contactName);
  }

  public static List<String> getOnlineContacts() throws IOException {
    Path start = Paths.get(CONTACT_STATUS_CONTAINTER_PATH);
    List<String> onlineContacts = new ArrayList<>();
    if (Files.exists(start)) {
      onlineContacts =
          Files.walk(start, 1).filter(path -> !Files.isDirectory(path)).map(path -> path.getFileName().toString())
              .collect(Collectors.toList());
    }
    return onlineContacts;
  }

  public static int getNumberOfOnlineInstance(String contactName) {
    return ONLINE_INSTANCES.get(contactName);
  }

  private static void increaseNumberOfOnlineInstance(String contactName) {
    Integer current = ONLINE_INSTANCES.get(contactName);
    if (current != null) {
      ONLINE_INSTANCES.put(contactName, ++current);
    } else {
      ONLINE_INSTANCES.put(contactName, 1);
    }
  }

  private static void decreaseNumberOfOnlineUnstance(String contactName) {
    Integer current = ONLINE_INSTANCES.get(contactName);
    if (current != null) {
      ONLINE_INSTANCES.put(contactName, --current);
    } else {
      ONLINE_INSTANCES.put(contactName, 0);
    }
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
    try {
      List<String> onlineUsers = getOnlineContacts();
      users.stream().filter(user -> onlineUsers.contains(user.getName())).forEach(user -> user.setOnline(true));
    } catch (IOException e) {
      Ivy.log().warn("Could not get list of online users.", e);
    }
  }

  private static List<IUser> getContextUsers() {
    List<IUser> users = new ArrayList<>(Ivy.wf().getApplication().getSecurityContext().getUsers());
    users.sort((first, second) -> first.getName().compareToIgnoreCase(second.getName()));
    users.removeIf(user -> StringUtils.equals(user.getName(), ISecurityConstants.SYSTEM_USER_NAME));
    return users;
  }
}
