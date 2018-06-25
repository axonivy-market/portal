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
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
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

  public String getPortalStartUrl() throws MalformedURLException {
    String homePageURL = getHomePageFromSetting();
    if (StringUtils.isNotEmpty(homePageURL)) {
      return homePageURL;
    }
    return defaultPortalStartUrl(false);
  }

  private String getHomePageFromSetting() {
    GlobalSettingService globalSettingSerive = new GlobalSettingService();
    return globalSettingSerive.findGlobalSettingValue(GlobalVariable.HOMEPAGE_URL.toString());
  }

  private String defaultPortalStartUrl(boolean isAbsoluteLink) throws MalformedURLException {
    String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(PORTAL_PROCESS_START_NAME);
    if (isAbsoluteLink) {
      UrlDetector urlDetector = new UrlDetector();
      String serverUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());
      return serverUrl + requestPath;
    }
    return "/"
            + RequestUriFactory
                    .getIvyContextName(ServerFactory.getServer().getApplicationConfigurationManager())
            + requestPath;
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
    return Ivy.html().startref(PORTAL_PROCESS_START_NAME);
  }

  public void navigateToPortalEndPage() throws MalformedURLException {
    String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(PORTAL_END_PAGE);
    if (StringUtils.isNotEmpty(requestPath)) {
      UrlDetector urlDetector = new UrlDetector();
      String serverUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());
      redirect(serverUrl + requestPath + "?endedTaskId=" + Ivy.wfTask().getId());
    }
  }

  public void navigateToPortalProcess() throws MalformedURLException {
    navigateByKeyword("startPortalProcess.ivp", PORTAL_PROCESS);
  }

  public void navigateToPortalCase() throws MalformedURLException {
    navigateByKeyword("startPortalCase.ivp", PORTAL_CASE);
  }

  public void navigateToPortalTask() throws MalformedURLException {
    navigateByKeyword("startPortalTask.ivp", PORTAL_TASK);
  }

  public void navigateToPortalStatistic() throws MalformedURLException {
    navigateByKeyword("startPortalStatistic.ivp", PORTAL_STATISTIC);
  }

  private void navigateByKeyword(String keyword, String defaultFriendlyRequestPath) throws MalformedURLException {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.wf().getApplication());
    final String customizePortalFriendlyRequestPath = collector.findFriendlyRequestPathContainsKeyword(keyword);
    if (StringUtils.isNotEmpty(customizePortalFriendlyRequestPath)) {
      navigate(customizePortalFriendlyRequestPath);
    } else {
      navigate(defaultFriendlyRequestPath );
    }
  }

  private void navigate(String friendlyRequestPath) throws MalformedURLException {
    String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath))
    {
      UrlDetector urlDetector = new UrlDetector();
      String serverUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());
      redirect(serverUrl + requestPath);
    }
  }
}
