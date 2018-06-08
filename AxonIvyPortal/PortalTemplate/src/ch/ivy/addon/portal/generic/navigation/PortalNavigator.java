package ch.ivy.addon.portal.generic.navigation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.extensions.util.json.GsonConverter;

import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;

public final class PortalNavigator {

  private static final String PORTAL_PROCESS_START_NAME = "Start Processes/PortalStart/PortalStart.ivp";
  private static final String PORTAL_END_PAGE = "Start Processes/PortalStart/DefaultEndPage.ivp";

  public String getPortalStartUrl() throws Exception {
    String homePageURL = getHomePageFromSetting();
    if (!homePageURL.isEmpty()) {
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
    return "/" + RequestUriFactory.getIvyContextName(ServerFactory.getServer().getApplicationConfigurationManager())
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

  public void redirect(String url) throws Exception {
    FacesContext.getCurrentInstance().getExternalContext().redirect(url);
  }

  public String getPortalStartUrlOfCurrentApplication() {
    String homePageURL = getHomePageFromSetting();
    if (!homePageURL.isEmpty()) {
      return homePageURL;
    }
    return Ivy.html().startref(PORTAL_PROCESS_START_NAME);
  }

  public void navigateToPortalEndPage() throws Exception{
  	String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(PORTAL_END_PAGE);
  	if (!requestPath.isEmpty()){
  	  UrlDetector urlDetector = new UrlDetector();
      String serverUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());
      redirect(serverUrl + requestPath + "?endedTaskId=" + Ivy.wfTask().getId());
  	}
  }
}
