package ch.ivy.addon.portal.generic.navigation;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivy.addon.portalkit.util.RequestUtil;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;
import ch.ivyteam.ivy.workflow.StandardProcessType;

public final class PortalNavigator {
  private static final String PORTAL_PROCESS_START_NAME = "Start Processes/PortalStart/DefaultApplicationHomePage.ivp";
  private static final String PORTAL_PROCESS = "Start Processes/PortalStart/DefaultProcessStartListPage.ivp";
  private static final String PORTAL_TASK = "Start Processes/PortalStart/DefaultTaskListPage.ivp";
  private static final String PORTAL_CASE = "Start Processes/PortalStart/CaseListPage.ivp";
  private static final String PORTAL_STATISTIC = "Start Processes/PortalStart/StatisticPage.ivp";
  private static final String PORTAL_CASE_DETAILS = "Start Processes/PortalStart/CaseDetailsPage.ivp";
  private static final String PORTAL_RELATED_TASKS_OF_CASE = "Start Processes/PortalStart/RelatedTasksOfCasePage.ivp";
  private static final String PORTAL_TASK_DETAILS = "Start Processes/PortalStart/TaskDetailsPage.ivp";
  private static final String PORTAL_GLOBAL_SEARCH = "Start Processes/PortalStart/GlobalSearchPage.ivp";
  private static final String PORTAL_USER_PROFILE =  "Start Processes/PortalStart/UserProfile.ivp";
  
  public static String getPortalStartUrl() {
    return getRelativeLink(StandardProcessType.DefaultApplicationHomePage);
  }
  
  private static String getRelativeLink(StandardProcessType standardProcess) {
    return IvyExecutor.executeAsSystem(() ->
      Ivy.wf().getStandardProcessImplementation(standardProcess).getLink().getRelative());
  }
  
  public static void navigateToPortalLoginPage() {
    IHttpRequest request = (IHttpRequest) Ivy.request();
    String loginPage = getRelativeLink(StandardProcessType.DefaultLoginPage);
    String originalUrl = URLEncoder.encode(request.getHttpServletRequest().getRequestURI(), StandardCharsets.ISO_8859_1);
    redirect(String.format("%s?originalUrl=%s", loginPage, originalUrl));
  }
  
  public static void redirect(String url) {
    try {
      RequestUtil.redirect(url);
    } catch (IOException ex) {
      throw new PortalException(ex);
    }
  }

  public static String getSubMenuItemUrlOfCurrentApplication(MenuKind menuKind) {
    String subMenuUrl = StringUtils.EMPTY;
    switch (menuKind) {
      case PROCESS:
        subMenuUrl = PORTAL_PROCESS;
        break;
      case TASK:
        subMenuUrl = PORTAL_TASK;
        break;
      case CASE:
        subMenuUrl = PORTAL_CASE;
        break;
      case STATISTICS:
        subMenuUrl = PORTAL_STATISTIC;
        break;
      default:
        break;
    }
    return ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(subMenuUrl);
  }

  public static void navigateToPortalEndPage(Long taskId) {
    String customizePortalEndPage = getRelativeLink(StandardProcessType.DefaultEndPage);
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_FINISHED.toString(), false);
    redirect(String.format("%s?endedTaskId=%s", customizePortalEndPage, taskId));
  }

  /**
   * Navigates to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page:
   * task list or task details or global search NOTES: is only used for the task not started in Portal IFrame
   */
  public static void navigateToPortalEndPage() {
    navigateToPortalEndPage(Ivy.wfTask().getId());
  }


  public static void navigateToPortalProcess() {
    navigateByKeyword("DefaultProcessStartListPage.ivp", PORTAL_PROCESS, new HashMap<>());
  }

  public static void navigateToPortalCase() {
    navigateByKeyword("CaseListPage.ivp", PORTAL_CASE, new HashMap<>());
  }

  public static void navigateToPortalTask() {
    navigateByKeyword("DefaultTaskListPage.ivp", PORTAL_TASK, new HashMap<>());
  }

  public static void navigateToPortalStatistic() {
    navigateByKeyword("StatisticPage.ivp", PORTAL_STATISTIC, new HashMap<>());
  }

  public static void navigateToPortalHome() {
    navigateByKeyword("DefaultApplicationHomePage.ivp", PORTAL_PROCESS_START_NAME, new HashMap<>());
  }
  
  public static void navigateToPortalCaseDetails(Long caseId) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    navigateByKeyword("CaseDetailsPage.ivp", PORTAL_CASE_DETAILS, params);
  }

  public static void navigateToPortalRelatedTasksOfCase(Long caseId, boolean isBusinessCase, String caseName) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    params.put("isBusinessCase", String.valueOf(isBusinessCase));
    params.put("caseName", caseName);
    navigateByKeyword("RelatedTasksOfCasePage.ivp", PORTAL_RELATED_TASKS_OF_CASE, params);
  }

  public static void navigateToPortalTaskDetails(Long taskId) {
    Map<String, String> params = new HashMap<>();
    params.put("selectedTaskId", String.valueOf(taskId));
    navigateByKeyword("TaskDetailsPage.ivp", PORTAL_TASK_DETAILS, params);
  }

  public static void navigateToPortalGlobalSearch(String keyword) {
    Map<String, String> params = new HashMap<>();
    params.put("keyword", keyword);
    navigateByKeyword("GlobalSearchPage.ivp", PORTAL_GLOBAL_SEARCH, params);
  }
  
  public static String buildUserProfileUrl() {
    return buildUrlByKeyword("UserProfile.ivp", PORTAL_USER_PROFILE, new HashMap<>());
  }

  public static String buildUrlByKeyword(String keyword, String defaultFriendlyRequestPath, Map<String, String> param) {
    String customizePortalFriendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword(keyword);
    return buildUrl(StringUtils.defaultIfBlank(customizePortalFriendlyRequestPath, defaultFriendlyRequestPath), param);
  }
  
  private static void navigateByKeyword(String keyword, String defaultFriendlyRequestPath, Map<String, String> param) {
    String customizePortalFriendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword(keyword);
    navigate(StringUtils.defaultIfBlank(customizePortalFriendlyRequestPath, defaultFriendlyRequestPath), param);
  }

  private static void navigate(String friendlyRequestPath, Map<String, String> params) {
    redirect(buildUrl(friendlyRequestPath, params));
  }
  
  private static String buildUrl(String friendlyRequestPath, Map<String, String> params) {
    String requestPath = ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isEmpty(requestPath)) {
      return StringUtils.EMPTY;
    }
    String paramStr = params.entrySet().stream().map(e -> {
      return e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.ISO_8859_1);
    }).collect(Collectors.joining("&"));
    return requestPath + (StringUtils.isNotBlank(paramStr) ? "?" + paramStr : StringUtils.EMPTY);
  }
  
  public static void navigateToUserProfile() {
    navigate(PORTAL_USER_PROFILE, new HashMap<>());
  }
}
