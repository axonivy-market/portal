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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.RemoteApplicationUser;
import ch.ivy.addon.portalkit.bo.RemoteSecurityMember;
import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivy.addon.portalkit.taskfilter.TaskInProgressByOthersFilter;
import ch.ivy.ws.addon.IvyUser;
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

  private static final String SELECTED_TASK_FILTER_SET = "SELECTED_TASK_FILTER_SET";
  private static final String SELECTED_TASK_FILTER = "SELECTED_TASK_FILTER";
  private static final String TASK_KEYWORD_FILTER = "TASK_KEYWORD_FILTER";
  private static final String TASK_IN_PROGRESS_FILTER = "TASK_IN_PROGRESS_FILTER";
  private static final String SELECTED_CASE_FILTER_SET = "SELECTED_CASE_FILTER_SET";
  private static final String SELECTED_CASE_FILTER = "SELECTED_CASE_FILTER";
  private static final String CASE_KEYWORD_FILTER = "CASE_KEYWORD_FILTER";

  /**
   * Get all users in current Ivy Server
   * 
   * @return List<IUser> : All users in current Ivy Server
   */
  public static List<IUser> getAllUsers() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<IUser>>() {
        @Override
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
        @Override
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
        @Override
        public Object call() throws Exception {
          IUser sessionUser = getIvySession().getSessionUser();
          Locale l = null;
          if (sessionUser.getEMailLanguage() != null) {
            l = sessionUser.getEMailLanguage();
          } else {
            // Application Default
            Locale defaultApplicationLocal = Ivy.request().getApplication().getDefaultEMailLanguage();
            l =
                new Locale(defaultApplicationLocal.getLanguage(), defaultApplicationLocal.getCountry(),
                    APPLICATION_DEFAULT);
          }
          getIvySession().setContentLocale(l);
          getIvySession().setFormattingLocale(l);
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
        @Override
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
        @Override
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

  /**
   * Filter list of ivy users by name based on provided query
   * 
   * @param users users need to be filtered
   * @param query provided query
   * @return Filtered and sorted list of ivy users
   */
  public static List<IvyUser> filterIvyUsers(List<IvyUser> users, String query) {
    if (StringUtils.isEmpty(query)) {
      return users;
    }

    List<IvyUser> filterUsers = new ArrayList<>();
    for (IvyUser user : users) {
      if (user.getDisplayName().toLowerCase().contains(query.toLowerCase())
          || user.getMemberName().toLowerCase().contains(query.toLowerCase())) {
        filterUsers.add(user);
      }
    }

    filterUsers.sort((first, second) -> first.getDisplayName().toLowerCase()
        .compareTo(second.getDisplayName().toLowerCase()));
    return filterUsers;
  }

  /**
   * Filter list of users by name based on provided query
   * 
   * @param users users need to be filtered
   * @param query provided query
   * @return Filtered and sorted list of ivy users
   */
  public static List<IUser> filterIUsers(List<IUser> users, String query) {
    if (StringUtils.isEmpty(query)) {
      return users;
    }

    List<IUser> filterUsers = new ArrayList<>();
    for (IUser user : users) {
      if (user.getDisplayName().toLowerCase().contains(query.toLowerCase())
          || user.getMemberName().toLowerCase().contains(query.toLowerCase())) {
        filterUsers.add(user);
      }
    }

    filterUsers.sort((first, second) -> first.getDisplayName().toLowerCase()
        .compareTo(second.getDisplayName().toLowerCase()));
    return filterUsers;
  }

  /**
   * Filter list of remote security member by name based on provided query
   * 
   * @param securityMembers security members need to be filtered
   * @param query provided query
   * @return Filtered and sorted list of remote security member
   */
  public static java.util.List<RemoteSecurityMember> filterSecurityMembers(
      java.util.List<RemoteSecurityMember> securityMembers, String query) {
    if (StringUtils.isEmpty(query)) {
      return securityMembers;
    }

    java.util.List<RemoteSecurityMember> result = new ArrayList<>();
    for (RemoteSecurityMember securityMember : securityMembers) {
      if (securityMember.getDisplayName().toLowerCase().contains(query.toLowerCase())
          || securityMember.getMemberName().toLowerCase().contains(query.toLowerCase())) {
        result.add(securityMember);
      }
    }

    result.sort((first, second) -> first.getDisplayName().toLowerCase()
        .compareTo(second.getDisplayName().toLowerCase()));
    return result;
  }

  public static void setSessionAttribute(String key, Object value) {
    Ivy.session().setAttribute(key, value);
  }

  public static void setSessionSelectedTaskFilterSetAttribute(TaskFilterData value) {
    setSessionAttribute(SELECTED_TASK_FILTER_SET, value);
  }

  public static void setSessionTaskAdvancedFilterAttribute(List<TaskFilter> value) {
    setSessionAttribute(SELECTED_TASK_FILTER, value);
  }

  public static void setSessionTaskKeywordFilterAttribute(String keyword) {
    setSessionAttribute(TASK_KEYWORD_FILTER, keyword);
  }

  public static void setSessionTaskInProgressFilterAttribute(TaskInProgressByOthersFilter filter) {
    setSessionAttribute(TASK_IN_PROGRESS_FILTER, filter);
  }

  public static TaskFilterData getSessionSelectedTaskFilterSetAttribute() {
    return (TaskFilterData) Ivy.session().getAttribute(SELECTED_TASK_FILTER_SET);
  }

  @SuppressWarnings("unchecked")
  public static List<TaskFilter> getSessionTaskAdvancedFilterAttribute() {
    List<TaskFilter> filters = (List<TaskFilter>) Ivy.session().getAttribute(SELECTED_TASK_FILTER);
    if (CollectionUtils.isEmpty(filters)) {
      return new ArrayList<>();
    }
    return filters;
  }

  public static String getSessionTaskKeywordFilterAttribute() {
    String keyword = (String) Ivy.session().getAttribute(TASK_KEYWORD_FILTER);
    if (StringUtils.isBlank(keyword)) {
      return "";
    }
    return keyword;
  }

  public static TaskInProgressByOthersFilter getSessionTaskInProgressFilterAttribute() {
    return (TaskInProgressByOthersFilter) Ivy.session().getAttribute(TASK_IN_PROGRESS_FILTER);
  }

  public static void setSessionSelectedCaseFilterSetAttribute(CaseFilterData value) {
    setSessionAttribute(SELECTED_CASE_FILTER_SET, value);
  }

  public static void setSessionCaseAdvancedFilterAttribute(List<CaseFilter> value) {
    setSessionAttribute(SELECTED_CASE_FILTER, value);
  }

  public static void setSessionCaseKeywordFilterAttribute(String keyword) {
    setSessionAttribute(CASE_KEYWORD_FILTER, keyword);
  }

  public static CaseFilterData getSessionSelectedCaseFilterSetAttribute() {
    return (CaseFilterData) Ivy.session().getAttribute(SELECTED_CASE_FILTER_SET);
  }

  @SuppressWarnings("unchecked")
  public static List<CaseFilter> getSessionCaseAdvancedFilterAttribute() {
    List<CaseFilter> filters = (List<CaseFilter>) Ivy.session().getAttribute(SELECTED_CASE_FILTER);
    if (CollectionUtils.isEmpty(filters)) {
      return new ArrayList<>();
    }
    return filters;
  }

  public static String getSessionCaseKeywordFilterAttribute() {
    String keyword = (String) Ivy.session().getAttribute(CASE_KEYWORD_FILTER);
    if (StringUtils.isBlank(keyword)) {
      return "";
    }
    return keyword;
  }

  public static String getIvySystemUserName() {
    return Ivy.session().getSecurityContext().getSystemUser().getName();
  }
}
