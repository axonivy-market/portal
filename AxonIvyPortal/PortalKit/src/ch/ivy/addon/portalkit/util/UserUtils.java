package ch.ivy.addon.portalkit.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserAbsence;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

@SuppressWarnings("deprecation")
public class UserUtils {

  private static final String APPLICATION_DEFAULT = "APPLICATION_DEFAULT";
  private static final String SELECTED_TASK_FILTER_SET = "SELECTED_TASK_FILTER_SET";
  private static final String SELECTED_TASK_FILTER = "SELECTED_TASK_FILTER";
  private static final String TASK_KEYWORD_FILTER = "TASK_KEYWORD_FILTER";
  private static final String SELECTED_CASE_FILTER_SET = "SELECTED_CASE_FILTER_SET";
  private static final String SELECTED_CASE_FILTER = "SELECTED_CASE_FILTER";
  private static final String CASE_KEYWORD_FILTER = "CASE_KEYWORD_FILTER";
  private static final String TASK_SORT_FIELD = "TASK_SORT_FIELD";
  private static final String CASE_SORT_FIELD = "CASE_SORT_FIELD";
  private static final String FILTER_GROUP_ID = "FILTER_GROUP_ID";
  private static final String SELECTED_DEFAULT_TASK_FILTER_SET = "SELECTED_DEFAULT_TASK_FILTER_SET";
  private static final String SELECTED_DEFAULT_CASE_FILTER_SET = "SELECTED_DEFAULT_CASE_FILTER_SET";

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
  
  public static List<IUserAbsence> findAbsenceOfUser(IUser iUser) {
    return IvyExecutor.executeAsSystem(() -> iUser.getAbsences());
  }
  
  public static String findNextAbsenceOfUser(IUser iUser) {
    DateTimeGlobalSettingService service = new DateTimeGlobalSettingService();
    DateFormat formatter = new SimpleDateFormat(service.getDatePattern());
    
    List<IUserAbsence> findAbsenceOfUser = findAbsenceOfUser(iUser);
    String returnString = "";
    Date foundDate = null;
    for (IUserAbsence item : findAbsenceOfUser) {
      if (item.getStartTimestamp().after(new Date())) {
        if (foundDate == null) {
          foundDate = item.getStartTimestamp();
          returnString = String.format("%s - %s", formatter.format(item.getStartTimestamp()), formatter.format(item.getStopTimestamp())); 
        } else if (item.getStartTimestamp().before(foundDate)){
          foundDate = item.getStartTimestamp();
          returnString = String.format("%s - %s", formatter.format(item.getStartTimestamp()), formatter.format(item.getStopTimestamp())); 
        }
      }
    }
     
    return returnString;
  }
  
  public static void setSessionAttribute(String key, Object value) {
    Ivy.session().setAttribute(key, value);
  }

  public static void setSessionSelectedDefaultTaskFilterSetAttribute(Boolean value) {
    setSessionAttribute(SELECTED_DEFAULT_TASK_FILTER_SET, value);
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

  public static void setSessionTaskSortAttribute(String sortField) {
    setSessionAttribute(TASK_SORT_FIELD, sortField);
  }

  public static TaskFilterData getSessionSelectedTaskFilterSetAttribute() {
    Object sessionObject = Ivy.session().getAttribute(SELECTED_TASK_FILTER_SET);
    return sessionObject instanceof TaskFilterData ? (TaskFilterData) sessionObject : null;
  }
  
  public static Long getSessionFilterGroupIdAttribute() {
    return (Long) Ivy.session().getAttribute(FILTER_GROUP_ID);
  }
  
  public static Boolean getSessionSelectedDefaultTaskFilterSetAttribute() {
    return (Boolean) Ivy.session().getAttribute(SELECTED_DEFAULT_TASK_FILTER_SET);
  }

  @SuppressWarnings("unchecked")
  public static List<TaskFilter> getSessionTaskAdvancedFilterAttribute() {
    List<TaskFilter> filters = new ArrayList<>();
    List<?> obj = (List<?>) Ivy.session().getAttribute(SELECTED_TASK_FILTER);
    
    if (CollectionUtils.isNotEmpty(obj) && obj.get(0) instanceof TaskFilter) {
      return (List<TaskFilter>)obj;
    }
    return filters;
  }

  public static String getSessionTaskKeywordFilterAttribute() {
    return StringUtils.defaultIfBlank((String) Ivy.session().getAttribute(TASK_KEYWORD_FILTER), "");
  }

  public static String getSessionTaskSortAttribute() {
    return (String) Ivy.session().getAttribute(TASK_SORT_FIELD);
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
  
  public static void setSessionFilterGroupIdAttribute(Long value) {
    setSessionAttribute(FILTER_GROUP_ID, value);
  }

  public static void setSessionSelectedDefaultCaseFilterSetAttribute(Boolean value) {
    setSessionAttribute(SELECTED_DEFAULT_CASE_FILTER_SET, value);
  }

  public static void setSessionCaseSortAttribute(String sortField) {
    setSessionAttribute(CASE_SORT_FIELD, sortField);
  }

  public static CaseFilterData getSessionSelectedCaseFilterSetAttribute() {
    Object sessionObject = Ivy.session().getAttribute(SELECTED_CASE_FILTER_SET);
    return sessionObject instanceof CaseFilterData ? (CaseFilterData) sessionObject : null;
  }

  @SuppressWarnings("unchecked")
  public static List<CaseFilter> getSessionCaseAdvancedFilterAttribute() {
    List<CaseFilter> filters = new ArrayList<>();
    List<?> obj = (List<?>) Ivy.session().getAttribute(SELECTED_CASE_FILTER);
    
    if (CollectionUtils.isNotEmpty(obj) && obj.get(0) instanceof CaseFilter) {
      return (List<CaseFilter>)obj;
    }
    return filters;
  }

  public static String getSessionCaseKeywordFilterAttribute() {
    return StringUtils.defaultIfBlank((String)Ivy.session().getAttribute(CASE_KEYWORD_FILTER), "");
  }
  
  public static Boolean getSessionSelectedDefaultCaseFilterSetAttribute() {
    return (Boolean) Ivy.session().getAttribute(SELECTED_DEFAULT_CASE_FILTER_SET);
  }

  public static String getSessionCaseSortAttribute() {
    return (String) Ivy.session().getAttribute(CASE_SORT_FIELD);
  }

  /**
   * Finds users who have given roles. If current application is Portal, find all users over all applications, otherwise in current application
   * @param query
   * @param startIndex index of the first record is 0
   * @param count use -1 to return all beginning from the startIndex
   * @param fromRoles role list filter
   * @param excludedUsernames user name list exclude to query 
   * @return user list
   */
  @SuppressWarnings("unchecked")
  public static List<UserDTO> findUsers(String query, int startIndex, int  count, List<String> fromRoles, List<String> excludedUsernames) {
    return IvyExecutor.executeAsSystem(() -> {
      if (Ivy.request().getApplication().getName().equals(PortalConstants.PORTAL_APPLICATION_NAME)) {
        return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
            .withStartName("findUsersOverAllApplications")
            .withParam("username", getSessionUserName())
            .withParam("query", query)
            .withParam("startIndex", startIndex)
            .withParam("count", count)
            .withParam("fromRoles", fromRoles)
            .withParam("excludedUsernames", excludedUsernames)
            .call()
            .get("users", List.class);
      }
      
      return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
          .withStartName("findUsers")
          .withParam("application", Ivy.request().getApplication())
          .withParam("query", query)
          .withParam("startIndex", startIndex)
          .withParam("count", count)
          .withParam("fromRoles", fromRoles)
          .withParam("excludedUsernames", excludedUsernames)
          .call()
          .get("users", List.class);
    });
  }
  
  public static List<UserDTO> filterOut(List<UserDTO> users, UserDTO excludedUser) {
    return users.stream().filter(user -> !StringUtils.equals(user.getName(), excludedUser.getName())).collect(Collectors.toList());
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

}
