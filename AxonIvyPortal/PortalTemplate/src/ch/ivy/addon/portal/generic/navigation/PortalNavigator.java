package ch.ivy.addon.portal.generic.navigation;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.extensions.util.json.GsonConverter;

import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;

public final class PortalNavigator {
  private static final String PORTAL_PROCESS_START_NAME = "Start Processes/PortalStart/PortalStart.ivp";
  private static final String PORTAL_END_PAGE = "Start Processes/PortalStart/DefaultEndPage.ivp";
  private static final String PORTAL_PROCESS = "Start Processes/PortalStart/startPortalProcess.ivp";
  private static final String PORTAL_TASK = "Start Processes/PortalStart/startPortalTask.ivp";
  private static final String PORTAL_CASE = "Start Processes/PortalStart/startPortalCase.ivp";
  private static final String PORTAL_STATISTIC = "Start Processes/PortalStart/startPortalStatistic.ivp";
  private static final String SLASH = "/";

  public String getPortalStartUrl() throws Exception {
    String homePageURL = getHomePageFromSetting();
    if (StringUtils.isNotBlank(homePageURL)) {
      return homePageURL;
    }
    return defaultPortalStartUrl(false);
  }

  private String getHomePageFromSetting() {
    GlobalSettingService globalSettingSerive = new GlobalSettingService();
    return globalSettingSerive.findGlobalSettingValue(GlobalVariable.HOMEPAGE_URL);
  }

  private String defaultPortalStartUrl(boolean isAbsoluteLink) throws Exception {
    String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(PORTAL_PROCESS_START_NAME);
    if (isAbsoluteLink) {
      UrlDetector urlDetector = new UrlDetector();
      String serverUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());
      return serverUrl + requestPath;
    }
    return SLASH + RequestUriFactory.getIvyContextName(ServerFactory.getServer().getApplicationConfigurationManager())
        + requestPath;
  }

  public String getPortalStartUrlOf(PortalPage portalPage, Map<String, String> pageParameters) throws Exception {
    String baseUrl = getPortalStartUrl();
    return generatePortalStartUrl(baseUrl, portalPage, pageParameters);
  }

  private String generatePortalStartUrl(String baseUrl, PortalPage portalPage, Map<String, String> pageParameters) {
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
    if (!homePageURL.isEmpty()) {
      return homePageURL;
    }
    return Ivy.html().startref(PORTAL_PROCESS_START_NAME);
  }

  public void navigateToPortalEndPage() throws Exception {
    String customizePortalEndPage = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword("DefaultEndPage.ivp"); 
    String param = "?endedTaskId=" + Ivy.wfTask().getId();
    if (StringUtils.isNotEmpty(customizePortalEndPage)) {
      navigate(customizePortalEndPage, param);
    } else {
      navigate(PORTAL_END_PAGE, param);
    }
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_NOT_FINISHED.toString(), true);
  }

  public void navigateToPortalProcess() throws Exception {
    navigateByKeyword("startPortalProcess.ivp", PORTAL_PROCESS);
  }

  public void navigateToPortalCase() throws Exception {
    navigateByKeyword("startPortalCase.ivp", PORTAL_CASE);
  }

  public void navigateToPortalTask() throws Exception {
    navigateByKeyword("startPortalTask.ivp", PORTAL_TASK);
  }

  public void navigateToPortalStatistic() throws Exception {
    navigateByKeyword("startPortalStatistic.ivp", PORTAL_STATISTIC);
  }
  
  public void navigateToPortalHome() throws Exception {
    navigateByKeyword("PortalStart.ivp", PORTAL_PROCESS_START_NAME);
  }

  private void navigateByKeyword(String keyword, String defaultFriendlyRequestPath) throws Exception {
    String customizePortalFriendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword(keyword);
    if (StringUtils.isNotEmpty(customizePortalFriendlyRequestPath)) {
      navigate(customizePortalFriendlyRequestPath, StringUtils.EMPTY);
    } else {
      navigate(defaultFriendlyRequestPath, StringUtils.EMPTY);
    }
  }

  private void navigate(String friendlyRequestPath, String param) throws Exception {
    String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
        try {
            String ivyContextName = ServerFactory.getServer().getSecurityManager().executeAsSystem(
                () -> RequestUriFactory.getIvyContextName(ServerFactory.getServer().getApplicationConfigurationManager()));
            redirect(SLASH + ivyContextName + requestPath + param);
          } catch (Exception e) {
            Ivy.log().error(e);
          }
    }
  }
}
