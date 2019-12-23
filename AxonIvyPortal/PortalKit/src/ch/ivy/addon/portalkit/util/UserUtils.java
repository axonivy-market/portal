package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivy.addon.portalkit.taskfilter.TaskInProgressByOthersFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserAbsence;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class UserUtils {

  private static final String APPLICATION_DEFAULT = "APPLICATION_DEFAULT";
  private static final String SELECTED_TASK_FILTER_SET = "SELECTED_TASK_FILTER_SET";
  private static final String SELECTED_TASK_FILTER = "SELECTED_TASK_FILTER";
  private static final String TASK_KEYWORD_FILTER = "TASK_KEYWORD_FILTER";
  private static final String TASK_IN_PROGRESS_FILTER = "TASK_IN_PROGRESS_FILTER";
  private static final String SELECTED_CASE_FILTER_SET = "SELECTED_CASE_FILTER_SET";
  private static final String SELECTED_CASE_FILTER = "SELECTED_CASE_FILTER";
  private static final String CASE_KEYWORD_FILTER = "CASE_KEYWORD_FILTER";

  private UserUtils() {
  }

  /**
   * Set locale for session from user setting or application default
   */
  public static void setLanguague() {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
          IUser sessionUser = getIvySession().getSessionUser();
          Locale l = null;
          if (sessionUser.getEMailLanguage() != null) {
            l = sessionUser.getEMailLanguage();
          } else {
            // Application Default
            Locale defaultApplicationLocal = Ivy.request().getApplication().getDefaultEMailLanguage();
            l = new Locale(defaultApplicationLocal.getLanguage(), defaultApplicationLocal.getCountry(), APPLICATION_DEFAULT);
          }
          getIvySession().setContentLocale(l);
          getIvySession().setFormattingLocale(l);
          return null;
      });
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

  public static String getDisplayedName(String fullname, String username) {
    if (StringUtils.isEmpty(fullname)) {
      return username;
    }
    return String.format("%s (%s)", fullname, username);
  }
  
  /**
   * Filter list of users by name based on provided query
   * 
   * @param users users need to be filtered
   * @param query provided query
   * @return Filtered list of ivy users
   */
  public static List<IUser> filterUsers(List<IUser> users, String query) {
    if (StringUtils.isEmpty(query)) {
      return users;
    }

    return IvyExecutor.executeAsSystem(() -> {
      List<IUser> filterUsers = new ArrayList<>();
      for (IUser user : users) {
        if (StringUtils.containsIgnoreCase(user.getDisplayName(), query) || StringUtils.containsIgnoreCase(user.getMemberName(), query)) {
          filterUsers.add(user);
        }
      }
  
      return filterUsers.stream().sorted((first, second) -> StringUtils.compareIgnoreCase(first.getDisplayName(), second.getDisplayName())).collect(Collectors.toList());
    });
  }
  
  public static List<IUserAbsence> findAbsenceOfUser(IUser iUser) {
    return IvyExecutor.executeAsSystem(() -> {
      return iUser.getAbsences();
    });
  }
  
  public static List<UserDTO> filterUsersDTO(List<UserDTO> users, String query) {
    List<UserDTO> filterUsers = new ArrayList<>();
    
    return IvyExecutor.executeAsSystem(() -> {
      if (StringUtils.isEmpty(query)) {
          return users;
      }

      for (UserDTO user : users) {
        if (StringUtils.containsIgnoreCase(user.getDisplayName(), query) || StringUtils.containsIgnoreCase(user.getMemberName(), query)) {
          filterUsers.add(user);
        }
      }

      return filterUsers;
    });
  }
  
  /**
   * Gets non-duplicated all of users from map usersByApp 
   * 
   * @param usersByApp
   * @return non-duplicated list of ivy users
   */
  public static List<UserDTO> getNonDuplicatedUsers(Map<String, List<UserDTO>> usersByApp) {
    if (usersByApp == null || usersByApp.isEmpty()) {
      return new ArrayList<>();
    }

    return IvyExecutor.executeAsSystem(() ->
      usersByApp.values()
        .stream()
        .flatMap(List::stream)
        .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(UserDTO::getName, String.CASE_INSENSITIVE_ORDER))), ArrayList::new))
    );
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
    return StringUtils.defaultIfBlank((String) Ivy.session().getAttribute(TASK_KEYWORD_FILTER), "");
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
    return StringUtils.defaultIfBlank((String)Ivy.session().getAttribute(CASE_KEYWORD_FILTER), "");
  }

  public static List<UserDTO> findAllUserDTOInCurrentApplication() {
    List<UserDTO> users =  ServiceUtilities.findAllUserDTOByApplication(Ivy.request().getApplication());
    Collections.sort(users, (first, second) -> StringUtils.compareIgnoreCase(first.getDisplayName(), second.getDisplayName()));
    return users;
  }
  
  public static List<UserDTO> findAllUserDTOByApplication() {
    List<UserDTO> users = findUsersByCallableProcess();
    Collections.sort(users, (first, second) -> StringUtils.compareIgnoreCase(first.getDisplayName(), second.getDisplayName()));
    return users;
  }
  
  @SuppressWarnings("unchecked")
  private static List<UserDTO> findUsersByCallableProcess() {
      return IvyExecutor.executeAsSystem(() -> {
      if (Ivy.request().getApplication().getName().equals(PortalConstants.PORTAL_APPLICATION_NAME)) {
        Map<String, List<UserDTO>> usersByApp = SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
            .withStartName("findUsersOverAllApplications")
            .call(Ivy.session().getSessionUserName())
            .get("usersByApp", Map.class);
        return usersByApp.values().stream().flatMap(List::stream)
            .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(UserDTO::getName))), ArrayList::new));
      }
      
      return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
          .withStartName("findUsers")
          .call(Ivy.request().getApplication())
          .get("users", List.class);
      });
  }
  
  public static String getUserName(IUser user) {
    return IvyExecutor.executeAsSystem(() -> {
      return user.getName();
    });
  }

  public static String getFullName(IUser user) {
    return IvyExecutor.executeAsSystem(() -> {
      return user.getFullName();
    });
  }

  public static UserDTO getCurrentSessionUserAsUserDTO() {
    return IvyExecutor.executeAsSystem(() -> {
      return new UserDTO(getIvySession().getSessionUser());
    });
  }
  
}
