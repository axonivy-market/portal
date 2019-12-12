package ch.ivy.addon.portal.generic.navigation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.extensions.util.json.GsonConverter;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.StandardProcessType;

public final class PortalNavigator {
  private static final String PORTAL_PROCESS_START_NAME = "Start Processes/PortalStart/DefaultApplicationHomePage.ivp";
  private static final String PORTAL_PROCESS = "Start Processes/PortalPages/DefaultProcessStartListPage.ivp";
  private static final String PORTAL_TASK = "Start Processes/PortalStart/DefaultTaskListPage.ivp";
  private static final String PORTAL_CASE = "Start Processes/PortalStart/CaseListPage.ivp";
  private static final String PORTAL_STATISTIC = "Start Processes/PortalPages/StatisticPage.ivp";
  private static final String SLASH = "/";

  public String getPortalStartUrl() throws MalformedURLException {
    String homePageURL = getHomePageFromSetting();
    if (StringUtils.isNotEmpty(homePageURL)) {
      return homePageURL;
    }
    return defaultPortalStartUrl(false);
  }

  private String getHomePageFromSetting() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    return globalSettingService.findGlobalSettingValue(GlobalVariable.HOMEPAGE_URL.toString());
  }

  private String defaultPortalStartUrl(boolean isAbsoluteLink) throws MalformedURLException {
    String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(PORTAL_PROCESS_START_NAME);
    if (isAbsoluteLink) {
      UrlDetector urlDetector = new UrlDetector();
      String serverUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());
      return serverUrl + requestPath;
    }
    return SLASH + RequestUriFactory.getIvyContextName() + requestPath;
  }

  public String getPortalStartUrlOf(PortalPage portalPage, Map<String, String> pageParameters)
          throws MalformedURLException {
    String baseUrl = getPortalStartUrl();
    return generatePortalStartUrl(baseUrl, portalPage, pageParameters);
  }

  private String generatePortalStartUrl(String baseUrl, PortalPage portalPage,
          Map<String, String> pageParameters) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("portalNavigator", Arrays.asList(portalPage.name()));
    parameters.put("parameters", Arrays.asList(GsonConverter.getGson().toJson(pageParameters)));
    return FacesContext.getCurrentInstance().getExternalContext().encodeRedirectURL(baseUrl, parameters);
  }

  public void redirect(String url) {
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    } catch (IOException ex) {
      throw new PortalException(ex);
    }
  }

  public String getPortalStartUrlOfCurrentApplication() {
    String homePageURL = getHomePageFromSetting();
    if (StringUtils.isNotEmpty(homePageURL)) {
      return homePageURL;
    }
    return Ivy.html().startRef(PORTAL_PROCESS_START_NAME);
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
    String customizePortalFriendlyRequestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(subMenuUrl);
    if (StringUtils.isNotEmpty(customizePortalFriendlyRequestPath)) {
      UrlDetector urlDetector = new UrlDetector();
      String serverUrl = StringUtils.EMPTY;
      try {
        serverUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());
      } catch (MalformedURLException e) {
        Ivy.log().info("Cannot detect server Url" + e.getMessage());
      }
      return serverUrl + customizePortalFriendlyRequestPath;
    }
    return Ivy.html().startRef(subMenuUrl);
  }

  public void navigateToPortalEndPage(Long taskId) {
    String customizePortalEndPage = getDefaultEndPage(); 
    redirect(customizePortalEndPage + "?endedTaskId=" + taskId);
  }
  
  /**
   * Navigates to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page: task list or task details or global search
   * NOTES: is only used for the task not started in Portal IFrame
   */
  public void navigateToPortalEndPage() {
    String defaultEndPage = getDefaultEndPage(); 
    redirect(defaultEndPage + "?endedTaskId=" + Ivy.wfTask().getId());
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_NOT_FINISHED.toString(), true);
  }
  
  private String getDefaultEndPage() {
    return IvyExecutor.executeAsSystem(() -> Ivy.html().startRef(Ivy.wf().getStandardProcessImplementation(StandardProcessType.DefaultEndPage).getUserFriendlyRequestPath()));
  }

  public void navigateToPortalProcess() {
    navigateByKeyword("DefaultProcessStartListPage.ivp", PORTAL_PROCESS);
  }

  public void navigateToPortalCase() {
    navigateByKeyword("CaseListPage.ivp", PORTAL_CASE);
  }

  public void navigateToPortalTask() {
    navigateByKeyword("DefaultTaskListPage.ivp", PORTAL_TASK);
  }

  public void navigateToPortalStatistic() {
    navigateByKeyword("StatisticPage.ivp", PORTAL_STATISTIC);
  }
  
  public void navigateToPortalHome() {
    navigateByKeyword("DefaultApplicationHomePage.ivp", PORTAL_PROCESS_START_NAME);
  }

  private void navigateByKeyword(String keyword, String defaultFriendlyRequestPath) {
    String customizePortalFriendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword(keyword);
    if (StringUtils.isNotEmpty(customizePortalFriendlyRequestPath)) {
      navigate(customizePortalFriendlyRequestPath, StringUtils.EMPTY);
    } else {
      navigate(defaultFriendlyRequestPath, StringUtils.EMPTY);
    }
  }

  private void navigate(String friendlyRequestPath, String param) {
    String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
      try {
        String ivyContextName = ServerFactory.getServer().getSecurityManager().executeAsSystem(
            () -> RequestUriFactory.getIvyContextName());
        redirect(SLASH + ivyContextName + requestPath + param);
      } catch (Exception e) {
        Ivy.log().error(e);
      }
    }
  }
}
