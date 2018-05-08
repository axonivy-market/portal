package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivy.addon.portalkit.persistence.domain.User;

public class ApplicationUserCacheUtils {

  private ApplicationUserCacheUtils() {}

  /**
   * Method to convert list of object to list of entities.
   * 
   * @param remoteUsers list of remote user need to convert to entities.
   * @return list of entities.
   */
  public static List<User> convertToEntity(List<RemoteUser> remoteUsers) {
    List<User> users = new ArrayList<>();

    if (remoteUsers != null && !remoteUsers.isEmpty()) {
      for (RemoteUser remoteUser : remoteUsers) {

        User user = new User();
        String username = remoteUser.getUsername();
        if (username != null) {
          username = username.replaceFirst("#", "");
        }
        user.setUserName(username);
        user.setFullUserName(remoteUser.getName());
        user.setServerId(remoteUser.getServerId());
        user.setApplicationName(remoteUser.getAppName());

        users.add(user);
      }
    }

    return users;
  }
}
