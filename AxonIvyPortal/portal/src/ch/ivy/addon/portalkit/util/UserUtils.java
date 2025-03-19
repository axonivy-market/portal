package ch.ivy.addon.portalkit.util;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.language.LanguageManager;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserAbsence;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class UserUtils {

  private static final String SELECTED_TASK_FILTER_SET = "SELECTED_TASK_FILTER_SET";
  private static final String SELECTED_TASK_FILTER = "SELECTED_TASK_FILTER";
  private static final String TASK_KEYWORD_FILTER = "TASK_KEYWORD_FILTER";
  private static final String TASK_SORT_FIELD = "TASK_SORT_FIELD";
  private static final String SELECTED_CASE_FILTER_SET = "SELECTED_CASE_FILTER_SET";
  private static final String SELECTED_CASE_FILTER = "SELECTED_CASE_FILTER";
  private static final String CASE_KEYWORD_FILTER = "CASE_KEYWORD_FILTER";
  private static final String CASE_SORT_FIELD = "CASE_SORT_FIELD";
  private static final String FILTER_GROUP_ID = "FILTER_GROUP_ID";
  private static final String SELECTED_DEFAULT_TASK_FILTER_SET = "SELECTED_DEFAULT_TASK_FILTER_SET";
  private static final String SELECTED_DEFAULT_CASE_FILTER_SET = "SELECTED_DEFAULT_CASE_FILTER_SET";

  private UserUtils() {
  }

  /**
   * Set locale for session from user setting or application default
   */
  public static void setLanguage() {
    Sudo.get(()->{
      IUser sessionUser = getIvySession().getSessionUser();
      Locale defaultContentLocale = LanguageService.getInstance().getDefaultLanguage();
      Locale defaultFormattingLocale = LanguageService.getInstance().getDefaultFormattingLanguage();
      
      Locale contentLocale = sessionUser.getLanguage() != null && StringUtils.isNotBlank(sessionUser.getLanguage().toString()) ? sessionUser.getLanguage() : defaultContentLocale;
      Locale formattingLocale = sessionUser.getFormattingLanguage() != null && StringUtils.isNotBlank(sessionUser.getFormattingLanguage().toString()) ? sessionUser.getFormattingLanguage() : defaultFormattingLocale;
      
      getIvySession().setContentLocale(contentLocale);
      getIvySession().setFormattingLocale(formattingLocale);
      return null;
    });
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
    return String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/StringFormat/TextWithRoundBracket"), fullname, username);
  }

  public static List<IUserAbsence> findAbsenceOfUser(IUser iUser) {
    return Sudo.get(() -> iUser.getAbsences());
  }

  public static String findNextAbsenceOfUser(IUser iUser) {

    DateFormat formatter = DateTimeGlobalSettingService.getInstance().getDefaultDateFormatter();

    List<IUserAbsence> findAbsenceOfUser = findAbsenceOfUser(iUser);
    String returnString = "";
    Date foundDate = null;
    for (IUserAbsence item : findAbsenceOfUser) {
      Date startTimestamp = item.getStartTimestamp();
      if (startTimestamp.after(new Date()) && (foundDate == null || startTimestamp.before(foundDate))) {
        foundDate = startTimestamp;
        returnString = String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/StringFormat/DateFromTo"),
            formatter.format(startTimestamp), formatter.format(item.getStopTimestamp()));
      }
    }
    return returnString;
  }

  public static String findActiveAbsenceOfUser(IUser iUser) {
    DateFormat formatter = DateTimeGlobalSettingService.getInstance().getDefaultDateFormatter();

    List<IUserAbsence> findAbsenceOfUser = findAbsenceOfUser(iUser);
    String returnString = "";
    Date now = new Date();

    for (IUserAbsence item : findAbsenceOfUser) {
      Date startTimestamp = item.getStartTimestamp();
      Date stopTimestamp = item.getStopTimestamp();

      if (!now.before(startTimestamp) && !now.after(stopTimestamp)) {
        returnString = String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/StringFormat/DateFromTo"),
            formatter.format(startTimestamp), formatter.format(stopTimestamp));
        break;
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
   * Finds the users who have the given roles in current application.
   * @param query
   * @param startIndex index of the first record is 0
   * @param count use -1 to return all beginning from the startIndex
   * @param fromRoles role list filter
   * @param excludedUsernames user name list exclude
   * @return user list
   */
  @SuppressWarnings("unchecked")
  public static List<UserDTO> findUsers(String query, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    return Sudo.get(() -> {
      return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
          .withStartName("findUsers")
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
    return Sudo.get(() -> {
      return user.getName();
    });
  }

  public static String getFullName(IUser user) {
    return Sudo.get(() -> {
      return user.getFullName();
    });
  }

  public static IUser findUserByUsername(String username) {
    return Sudo.get(() -> {
      return ISecurityContext.current().users().find(username);
    });
  }

  /**
   * use {@link #findUserBySecurityMemberId(String)}
   * @param userId
   * @return {@link IUser}
   */
  @Deprecated(forRemoval = true, since = "11.2.0")
  public static IUser findUserByUserId(Long userId) {
    return Sudo.get(() -> {
      return ISecurityContext.current().users().find(userId);
    });
  }
  
  public static IUser findUserBySecurityMemberId(String securityMemberId) {
    return Sudo.get(() -> {
      return ISecurityContext.current().users().find(securityMemberId);
    });
  }

  public static boolean isValidPasswordResetToken(String token, IUser user) {
    if (user != null && StringUtils.isNotBlank(token)) {
      String tokenInDb = user.getProperty(UserProperty.RESET_PASSWORD_TOKEN);
      String tokenExpiryInDb = user.getProperty(UserProperty.RESET_PASSWORD_TOKEN_EXPIRY);
      long expiryTime = StringUtils.isNotBlank(tokenExpiryInDb) ? Long.valueOf(tokenExpiryInDb) : 0l;
      long currentTime = Calendar.getInstance().getTimeInMillis();
      return token.equals(tokenInDb) && currentTime < expiryTime;
    }
    return false;
  }

  public static String getUserLanguage() {
    String userLanguage = LanguageService.getInstance().getIvyLanguageOfUser().getUserLanguage();
    String systemLanguage = LanguageManager.instance().configurator(ISecurityContext.current()).content().toString();
    return StringUtils.defaultIfBlank(userLanguage, systemLanguage);
  }

  public static String getSessionIdentifierAttribteWithInitIfEmpty() {
    String sessionIdAttribute = SessionAttribute.SESSION_IDENTIFIER.toString();
    if (Ivy.session().getAttribute(sessionIdAttribute) == null) {
      Ivy.session().setAttribute(sessionIdAttribute, UUID.randomUUID().toString());
    }
    return (String) Ivy.session().getAttribute(sessionIdAttribute);
  }
}
