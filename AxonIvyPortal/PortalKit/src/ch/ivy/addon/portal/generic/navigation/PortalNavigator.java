package ch.ivy.addon.portal.generic.navigation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;
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

  public String getPortalStartUrl() {
    return getPortalStartUrl(null);
  }
  
  public String getPortalStartUrl(String application) {
    String homePage = getDefaultApplicationHomePage();
    if (StringUtils.isBlank(application)) {
      return homePage;
    }
    
    return homePage.replaceFirst(Ivy.wf().getApplication().getName(), application);
  }
  
  private String getDefaultApplicationHomePage() {
    return IvyExecutor.executeAsSystem(() ->
      Ivy.wf().getStandardProcessImplementation(StandardProcessType.DefaultApplicationHomePage).getLink().getRelative());
  }

  public static void redirect(String url) {
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    } catch (IOException ex) {
      throw new PortalException(ex);
    }
  }

  public String getSubMenuItemUrlOfCurrentApplication(MenuKind menuKind) {
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
      case DASHBOARD:
        subMenuUrl = PORTAL_STATISTIC;
        break;
      default:
        break;
    }
    return ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(Ivy.wf().getApplication(), subMenuUrl);
  }

  public void navigateToPortalEndPage(Long taskId) {
    String customizePortalEndPage = getDefaultEndPage();
    redirect(customizePortalEndPage + "?endedTaskId=" + taskId);
  }

  /**
   * Navigates to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page:
   * task list or task details or global search NOTES: is only used for the task not started in Portal IFrame
   */
  public void navigateToPortalEndPage() {
    String defaultEndPage = getDefaultEndPage();
    redirect(defaultEndPage + "?endedTaskId=" + Ivy.wfTask().getId());
  }

  private String getDefaultEndPage() {
    return IvyExecutor.executeAsSystem(() ->
        Ivy.wf().getStandardProcessImplementation(StandardProcessType.DefaultEndPage).getLink().getRelative());
  }

  public void navigateToPortalProcess() {
    navigateByKeyword("DefaultProcessStartListPage.ivp", PORTAL_PROCESS, new HashMap<>());
  }

  public void navigateToPortalCase() {
    navigateByKeyword("CaseListPage.ivp", PORTAL_CASE, new HashMap<>());
  }

  public void navigateToPortalTask() {
    navigateByKeyword("DefaultTaskListPage.ivp", PORTAL_TASK, new HashMap<>());
  }

  public void navigateToPortalStatistic() {
    navigateByKeyword("StatisticPage.ivp", PORTAL_STATISTIC, new HashMap<>());
  }

  public void navigateToPortalHome() {
    navigateByKeyword("DefaultApplicationHomePage.ivp", PORTAL_PROCESS_START_NAME, new HashMap<>());
  }

  public void navigateToPortalCaseDetails(Long caseId) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    navigateByKeyword("CaseDetailsPage.ivp", PORTAL_CASE_DETAILS, params);
  }

  public void navigateToPortalRelatedTasksOfCase(Long caseId, boolean isBusinessCase, String caseName) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    params.put("isBusinessCase", String.valueOf(isBusinessCase));
    params.put("caseName", caseName);
    navigateByKeyword("RelatedTasksOfCasePage.ivp", PORTAL_RELATED_TASKS_OF_CASE, params);
  }

  public void navigateToPortalTaskDetails(Long taskId) {
    Map<String, String> params = new HashMap<>();
    params.put("selectedTaskId", String.valueOf(taskId));
    navigateByKeyword("TaskDetailsPage.ivp", PORTAL_TASK_DETAILS, params);
  }

  public void navigateToPortalGlobalSearch(String keyword) {
    Map<String, String> params = new HashMap<>();
    params.put("keyword", keyword);
    navigateByKeyword("GlobalSearchPage.ivp", PORTAL_GLOBAL_SEARCH, params);
  }

  private void navigateByKeyword(String keyword, String defaultFriendlyRequestPath, Map<String, String> param) {
    String customizePortalFriendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword(keyword);
    if (StringUtils.isNotEmpty(customizePortalFriendlyRequestPath)) {
      navigate(customizePortalFriendlyRequestPath, param);
    } else {
      navigate(defaultFriendlyRequestPath, param);
    }
  }

  private void navigate(String friendlyRequestPath, Map<String, String> params) {
    String requestPath = ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(Ivy.wf().getApplication(),
        friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
      try {
        String paramStr = params.entrySet().stream().map(e -> {
          String param = e.getKey() + "=";
          try {
            return param + java.net.URLEncoder.encode(e.getValue(), "ISO-8859-1");
          } catch (UnsupportedEncodingException e1) {
            Ivy.log().error("Failed to encode param {0} with value {1}", e1, e.getKey(), e.getValue());
            return param + e.getValue();
          }
        }).collect(Collectors.joining("&"));
        redirect(requestPath + (StringUtils.isNotBlank(paramStr) ? "?" + paramStr : StringUtils.EMPTY));
      } catch (Exception e) {
        Ivy.log().error(e);
      }
    }
  }
}
