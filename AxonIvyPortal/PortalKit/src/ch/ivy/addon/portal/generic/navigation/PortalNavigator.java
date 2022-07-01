package ch.ivy.addon.portal.generic.navigation;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.publicapi.PortalNavigatorAPI;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;
import ch.ivyteam.ivy.workflow.StandardProcessType;

public final class PortalNavigator extends BaseNavigator{
  private static final String PORTAL_PROCESS_START_NAME = "Start Processes/PortalStart/DefaultApplicationHomePage.ivp";
  private static final String PORTAL_DASHBOARD = "Start Processes/PortalStart/DefaultDashboardPage.ivp";
  private static final String PORTAL_PROCESS = "Start Processes/PortalStart/DefaultProcessStartListPage.ivp";
  private static final String PORTAL_TASK = "Start Processes/PortalStart/DefaultTaskListPage.ivp";
  private static final String PORTAL_CASE = "Start Processes/PortalStart/CaseListPage.ivp";
  private static final String PORTAL_STATISTIC = "Start Processes/PortalStart/StatisticPage.ivp";
  private static final String PORTAL_CASE_DETAILS = "Start Processes/PortalStart/CaseDetailsPage.ivp";
  private static final String PORTAL_RELATED_TASKS_OF_CASE = "Start Processes/PortalStart/RelatedTasksOfCasePage.ivp";
  private static final String PORTAL_RELATED_TASKS_OF_CASE_IN_FRAME = "Start Processes/PortalStart/RelatedTasksOfCasePageInFrame.ivp";
  private static final String PORTAL_TASK_DETAILS = "Start Processes/PortalStart/TaskDetailsPage.ivp";
  private static final String PORTAL_TASK_DETAILS_IN_FRAME = "Start Processes/PortalStart/TaskDetailsPageInFrame.ivp";
  private static final String PORTAL_GLOBAL_SEARCH = "Start Processes/PortalStart/GlobalSearchPage.ivp";
  private static final String PORTAL_USER_PROFILE =  "Start Processes/PortalStart/UserProfile.ivp";
  private static final String PORTAL_MANAGEMENT =  "Start Processes/PortalStart/PortalManagement.ivp";
  private static final String PORTAL_ABSENCE_MANAGEMENT =  "Start Processes/PortalStart/AbsenceManagement.ivp";
  private static final String PORTAL_FORGOT_PASSWORD = "Start Processes/PortalStart/ForgotPasswordPage.ivp";
  private static final String PORTAL_PASSWORD_RESET = "Start Processes/PortalStart/PasswordResetPage.ivp";
  private static final String PORTAL_CASE_DETAILS_IN_FRAME = "Start Processes/PortalStart/CaseDetailsInIFrame.ivp";
  private static final String PORTAL_NEW_DASHBOARD_CONFIGURATION = "Start Processes/PortalStart/PortalDashboardConfiguration.ivp";
  private static final String PORTAL_DASHBOARD_REORDER = "Start Processes/PortalStart/DashboardReorder.ivp";
  private static final String PORTAL_PROCESS_INFO = "Start Processes/PortalStart/ProcessInformation.ivp";
  private static final String PORTAL_DASHBOARD_DETAILS = "Start Processes/PortalStart/DashboardDetails.ivp";

  public static final String PORTAL_DASHBOARD_START = "/DefaultDashboardPage.ivp";
  public static final String PORTAL_PROCESS_START = "/DefaultProcessStartListPage.ivp";
  public static final String PORTAL_TASK_START = "/DefaultTaskListPage.ivp";
  public static final String PORTAL_CASE_START = "/CaseListPage.ivp";
  public static final String PORTAL_STATISTIC_START = "/StatisticPage.ivp";
  public static final String PORTAL_USER_PROFILE_START =  "/UserProfile.ivp";
  public static final String PORTAL_CASE_DETAILS_IN_IFRAME_START = "/CaseDetailsInIFrame.ivp";
  private static final String PORTAL_DASHBOARD_REORDER_START = "/DashboardReorder.ivp";

  private final static String DASHBOARD_PARAM = "isShowDashboard";
  
  public static String getPortalStartUrl() {
    return getRelativeLink(StandardProcessType.DefaultApplicationHomePage);
  }

  public static String getPortalDashboardPageUrl(Map<String, String> params) {
    String customizePortalFriendlyRequestPath = ProcessStartUtils.findFriendlyRequestPathContainsKeyword(PORTAL_DASHBOARD_START);
    return buildUrl(StringUtils.defaultIfBlank(customizePortalFriendlyRequestPath, PORTAL_DASHBOARD), params);
  }

  public static void navigateToPortalLoginPage() {
    IHttpRequest request = (IHttpRequest) Ivy.request();
    String loginPage = getRelativeLink(StandardProcessType.DefaultLoginPage);
    String originalUrl = URLEncoder.encode(request.getHttpServletRequest().getRequestURI(), StandardCharsets.ISO_8859_1);
    redirect(String.format("%s?originalUrl=%s", loginPage, originalUrl));
  }

  public static String getForgotPasswordUrl() {
    return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(PORTAL_FORGOT_PASSWORD);
  }


  public static String getPasswordResetUrl(String token, String username) {
    Map<String, String> params = new HashMap<>();
    params.put("token", token);
    params.put("username", username);
    return buildUrl(PORTAL_PASSWORD_RESET, params);
  }

  public static void redirect(String url) {
    redirectURL(url);
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
    return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(subMenuUrl);
  }

  public static void navigateToPortalEndPage(Long taskId) {
    String customizePortalEndPage = getRelativeLink(StandardProcessType.DefaultEndPage);
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_FINISHED.toString(), false);
    redirect(String.format("%s?endedTaskId=%s", customizePortalEndPage, taskId));
  }

  /**
   * Navigates to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page:
   * task list or task details or global search NOTES: is only used for the task not started in Portal IFrame
   * @deprecated Use {@link PortalNavigatorAPI#navigateToPortalEndPage()} instead
   */
  @Deprecated
  public static void navigateToPortalEndPage() {
    navigateToPortalEndPage(Ivy.wfTask().getId());
  }

  public static void navigateToPortalProcess() {
    navigateByKeyword(PORTAL_PROCESS_START, PORTAL_PROCESS, new HashMap<>());
  }

  public static void navigateToPortalCase() {
    navigateByKeyword(PORTAL_CASE_START, PORTAL_CASE, new HashMap<>());
  }

  public static void navigateToPortalTask() {
    navigateByKeyword(PORTAL_TASK_START, PORTAL_TASK, new HashMap<>());
  }

  public static void navigateToPortalStatistic() {
    navigateByKeyword(PORTAL_STATISTIC_START, PORTAL_STATISTIC, new HashMap<>());
  }

  /**
   * Navigate to Portal home
   * @deprecated Use {@link PortalNavigatorAPI#navigateToPortalHome()} instead
   */
  @Deprecated
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

  public static void navigateToPortalRelatedTasksOfCaseInFrame(Long caseId, boolean isBusinessCase, String caseName) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    params.put("isBusinessCase", String.valueOf(isBusinessCase));
    params.put("caseName", caseName);
    navigateByKeyword("RelatedTasksOfCasePageInFrame.ivp", PORTAL_RELATED_TASKS_OF_CASE_IN_FRAME, params);
  }

  public static void navigateToPortalTaskDetails(Long taskId) {
    Map<String, String> params = new HashMap<>();
    params.put("selectedTaskId", String.valueOf(taskId));
    navigateByKeyword("TaskDetailsPage.ivp", PORTAL_TASK_DETAILS, params);
  }

  public static void navigateToPortalTaskDetailsInFrame(Long taskId) {
    Map<String, String> params = new HashMap<>();
    params.put("selectedTaskId", String.valueOf(taskId));
    navigateByKeyword("TaskDetailsPageInFrame.ivp", PORTAL_TASK_DETAILS_IN_FRAME, params);
  }
  
  public static void navigateToPortalCaseDetailsInFrame(Long caseId, boolean isBusinessCase) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    params.put("isBusinessCase", String.valueOf(isBusinessCase));
    navigateByKeyword("CaseDetailsPageInFrame.ivp", PORTAL_CASE_DETAILS_IN_FRAME, params);
  }

  public static void navigateToPortalGlobalSearch(String keyword) {
    Map<String, String> params = new HashMap<>();
    params.put("keyword", keyword);
    navigateByKeyword("GlobalSearchPage.ivp", PORTAL_GLOBAL_SEARCH, params);
  }
  
  public static void navigateToUserProfile() {
    navigateByKeyword(PORTAL_USER_PROFILE_START, PORTAL_USER_PROFILE, new HashMap<>());
  }

  public static void navigateToDashboardDetailsPage(String dashboardId, boolean isPublicDashboard) {
    Map<String, String> params = new HashMap<>();
    params.put("dashboardId", dashboardId);
    params.put("isPublicDashboard", Boolean.valueOf(isPublicDashboard).toString());
    navigateByKeyword("DashboardDetails.ivp", PORTAL_DASHBOARD_DETAILS, params);
  }

  public static String getDashboardLink() {
    Map<String, String> params = new HashMap<>();
    params.put(DASHBOARD_PARAM, Boolean.TRUE.toString());
    return PortalNavigator.getPortalDashboardPageUrl(params);
  }

  public static void navigateToDashboard() {
    redirect(getDashboardLink());
  }

  public static void navigateToDashboardReorder(boolean isPublicDashboard) {
    Map<String, String> params = new HashMap<>();
    params.put("isPublicDashboard", String.valueOf(isPublicDashboard));
    navigateByKeyword(PORTAL_DASHBOARD_REORDER_START, PORTAL_DASHBOARD_REORDER, params);
  }

  public static String buildDashboardReorderUrl(boolean isPublicDashboard) {
    Map<String, String> params = new HashMap<>();
    params.put("isPublicDashboard", String.valueOf(isPublicDashboard));
    return buildUrlByKeyword("DashboardReorder.ivp", PORTAL_DASHBOARD_REORDER, params);
  }

  public static String buildDashboardConfigurationUrl(boolean isPublicDashboard) {
    Map<String, String> params = new HashMap<>();
    params.put("isPublicDashboard", String.valueOf(isPublicDashboard));
    return buildUrlByKeyword("PortalDashboardConfiguration.ivp", PORTAL_NEW_DASHBOARD_CONFIGURATION, params);
  }

  public static String buildUserProfileUrl() {
    return buildUrlByKeyword("UserProfile.ivp", PORTAL_USER_PROFILE, new HashMap<>());
  }

  public static String buildAbsencesUrl() {
    return buildUrlByKeyword("AbsenceManagement.ivp", PORTAL_ABSENCE_MANAGEMENT, new HashMap<>());
  }

  public static String buildPortalCaseDetailsUrl(Long caseId) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    return buildUrlByKeyword("CaseDetailsPage.ivp", PORTAL_CASE_DETAILS, params);
  }

  public static String buildPortalCaseDetailInFrameUrl(Long caseId, IProcessModelVersion processModelVersion) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    return buildUrlByKeywordInPMV(PORTAL_CASE_DETAILS_IN_IFRAME_START, processModelVersion, PORTAL_CASE_DETAILS_IN_FRAME, params);
  }

  public static String buildPortalCaseDetailInFrameUrl(Long caseId) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    return buildUrlByKeyword(PORTAL_CASE_DETAILS_IN_IFRAME_START, PORTAL_CASE_DETAILS_IN_FRAME, params);
  }
  
  /**
   * Generate URL for process information page of selected process
   * 
   * @param processId
   * @return URL of process information page
   */
  public static String buildProcessInfoUrl(String processId) {
    Map<String, String> params = new HashMap<>();
    params.put("processKey", processId);
    return buildUrlByKeyword("ProcessInformation.ivp", PORTAL_PROCESS_INFO, params);
  }
  
  public static String buildUrlByKeyword(String keyword, String defaultFriendlyRequestPath, Map<String, String> param) {
    String customizePortalFriendlyRequestPath = ProcessStartUtils.findFriendlyRequestPathContainsKeyword(keyword);
    return buildUrl(StringUtils.defaultIfBlank(customizePortalFriendlyRequestPath, defaultFriendlyRequestPath), param);
  }
  
  public static String buildUrlByKeywordInPMV(String keyword, IProcessModelVersion processModelVersion ,String defaultFriendlyRequestPath, Map<String, String> param) {
    String customizePortalFriendlyRequestPath = ProcessStartUtils.findFriendlyRequestPathContainsKeywordInPMV(keyword, processModelVersion);
    return buildUrl(StringUtils.defaultIfBlank(customizePortalFriendlyRequestPath, defaultFriendlyRequestPath), param);
  }

  private static String buildUrl(String friendlyRequestPath, Map<String, String> params) {
    String requestPath = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isEmpty(requestPath)) {
      return StringUtils.EMPTY;
    }
    String paramStr = params.entrySet().stream().map(e -> {
      return e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.ISO_8859_1);
    }).collect(Collectors.joining("&"));
    return requestPath + (StringUtils.isNotBlank(paramStr) ? "?" + paramStr : StringUtils.EMPTY);
  }

  public static String buildPortalManagementUrl() {
    return buildUrlByKeyword("PortalManagement.ivp", PORTAL_MANAGEMENT, new HashMap<>());
  }
}
