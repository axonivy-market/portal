/**
 * 
 */
package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.RemoteApplicationUser;
import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

/**
 * Provide the utilities related to user
 * 
 * @author bolt
 */
public class UserUtils {

  private UserUtils() {

  }

  private static final String APPLICATION_DEFAULT = "APPLICATION_DEFAULT";
  /**
   * System user
   */
  private static final String SYSTEM_USER = "SYSTEM";
  /** default key to store PHONE number in additional field of User */
  public static final String PHONE = "PHONE";
  /** default key to store MOBILE number in additional field of User */
  public static final String MOBILE = "MOBILE";
  /** Property to get the hidden roles */
  private static final String HIDE_USERS_IN_DELEGATION = "HIDE_USERS_IN_DELEGATION";

  /**
   * Get all users in current Ivy Server
   * 
   * @return List<IUser> : All users in current Ivy Server
   */
  public static List<IUser> getAllUsers() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<IUser>>() {
        public List<IUser> call() throws Exception {
          ISecurityContext security = getIvySession().getSecurityContext();
          List<IUser> usersOut = new ArrayList<IUser>();
          for (IUser user : security.getUsers()) {
            if (user != null && !SYSTEM_USER.equals(user.getName())) {
              usersOut.add(user);
            }
          }
          return usersOut;
        }
      });
    } catch (PersistencyException e) {
      Ivy.log().error(e);
    } catch (EnvironmentNotAvailableException e) {
      Ivy.log().error(e);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return null;
  }

  /**
   * Get all users in current Ivy Server exclude some users has role hidden for delegate
   * 
   * @return List<IUser> : All users in current Ivy Server exclude some users has role hidden for delegate
   */
  public static List<IUser> getAllUsersForDelegate() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<IUser>>() {
        public List<IUser> call() throws Exception {
          ISecurityContext security = getIvySession().getSecurityContext();
          List<IUser> delegatedUsers = new ArrayList<IUser>();
          delegatedUsers.addAll(security.getUsers());
          for (IRole iRole : security.getRoles()) {
            if (iRole.getProperty(HIDE_USERS_IN_DELEGATION) != null) {
              delegatedUsers.removeAll(iRole.getUsers());
            }
          }

          for (Iterator<IUser> iter = delegatedUsers.listIterator(); iter.hasNext();) {
            IUser user = iter.next();
            if (SYSTEM_USER.equals(user.getName())) {
              iter.remove();
              break;
            }
          }

          // Sort the list
          Collections.sort(delegatedUsers, new Comparator<IUser>() {

            @Override
            public int compare(IUser o1, IUser o2) {

              if (o1 == null || o1.getDisplayName() == null) {
                return -1;
              }

              if (o2 == null) {
                return 1;
              }
              return o1.getDisplayName().compareTo(o2.getDisplayName());
            }

          });
          return delegatedUsers;
        }
      });
    } catch (PersistencyException e) {
      Ivy.log().error(e);
    } catch (EnvironmentNotAvailableException e) {
      Ivy.log().error(e);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return null;
  }

  /**
   * Set locale for session from user setting or application default
   * 
   */
  public static void setLanguague() {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Object>() {
        public Object call() throws Exception {
          IUser sessionUser = getIvySession().getSessionUser();

          if (sessionUser.getEMailLanguage() != null && sessionUser.getEMailLanguage() instanceof Locale) {
            Locale l = sessionUser.getEMailLanguage();
            getIvySession().setContentLocale(l);
            getIvySession().setFormattingLocale(l);
          } else {
            // Application Default
            Locale defaultApplicationLocal = Ivy.request().getApplication().getDefaultEMailLanguage();
            String language = defaultApplicationLocal.getLanguage();
            String country = defaultApplicationLocal.getCountry();
            Locale l = new Locale(language, country, APPLICATION_DEFAULT);
            getIvySession().setContentLocale(l);
            getIvySession().setFormattingLocale(l);
          }
          return null;
        }
      });
    } catch (PersistencyException e) {
      Ivy.log().error(e);
    } catch (EnvironmentNotAvailableException e) {
      Ivy.log().error(e);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  /**
   * Create Ivy user.
   * 
   * @param userName username of user will be created.
   * @param fullUserName full name
   * @param password password of user
   * @param eMailLanguage language used for email
   * @param eMailAddress email address
   * @param externalSecuritySystemName external security system name
   */
  public static void createIvyUser(final String userName, final String fullUserName, final String password,
      final Locale eMailLanguage, final String eMailAddress, final String externalSecuritySystemName) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Object>() {
        public Object call() throws Exception {
          Ivy.wf().getSecurityContext()
              .createUser(userName, fullUserName, password, eMailLanguage, eMailAddress, externalSecuritySystemName);
          Ivy.log().info("Created Ivy user: " + userName);
          return null;
        }
      });
    } catch (PersistencyException e) {
      Ivy.log().error(e);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  public static void deleteIvyUser(final String userName) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Object>() {
        public Object call() throws Exception {
          Ivy.wf().getSecurityContext().deleteUser(userName);
          Ivy.log().info("Deleted Ivy user: " + userName);
          return null;
        }
      });
    } catch (PersistencyException e) {
      Ivy.log().error(e);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  public static String getSessionUserName() {
    return getIvySession().getSessionUserName();
  }

  private static IWorkflowSession getIvySession() {
    return Ivy.session();
  }

  public static List<RemoteUser> filterUsers(List<RemoteUser> users, String query) {
    List<RemoteUser> filterUsers = new ArrayList<>();
    for (RemoteUser user : users) {
      if (user.getUsername().toLowerCase().contains(query.toLowerCase())
          || user.getName().toLowerCase().contains(query.toLowerCase())) {
        filterUsers.add(user);
      }
    }

    filterUsers.sort((first, second) -> getDisplayedName(first.getName().toLowerCase(),
        first.getUsername().toLowerCase()).compareTo(
        getDisplayedName(second.getName().toLowerCase(), second.getUsername().toLowerCase())));
    return filterUsers;
  }


  public static List<RemoteApplicationUser> sortApplicationUsers(List<RemoteApplicationUser> users) {
    List<RemoteApplicationUser> filterUsers = new ArrayList<>(users);
    filterUsers.sort((first, second) -> getDisplayedName(first.getDisplayName().toLowerCase(),
        first.getMemberName().toLowerCase()).compareTo(
        getDisplayedName(second.getDisplayName().toLowerCase(), second.getMemberName().toLowerCase())));
    return filterUsers;
  }

  public static String getDisplayedName(String fullname, String username) {
    if (StringUtils.isEmpty(fullname)) {
      return username;
    }
    return fullname + " (" + username + ")";
  }
}
