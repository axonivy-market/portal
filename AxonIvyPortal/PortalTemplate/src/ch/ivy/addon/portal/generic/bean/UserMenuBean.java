package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.system.ISystemProperty;

@ManagedBean
@ViewScoped
public class UserMenuBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private String userName;
  public static final int MINUTE_TO_SECOND = 60;
  public static final int SECONND_TO_MILLISECOND = 1000;
  public static final int TIME_BEFORE_LOST_SESSION = 180000; // 3 minutes

  public String getUserName() {
    userName = Ivy.session().getSessionUserName();
    return userName;
  }

  public boolean isShowServerInformation() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    String isShowServerInformation = globalSettingService.findGlobalSettingValue(GlobalVariable.SHOW_ENVIRONMENT_INFO.toString());
    return Boolean.parseBoolean(isShowServerInformation);
  }

  public boolean isHiddenLogout() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    String isHiddenLogout = globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_LOGOUT_BUTTON.toString());
    return Boolean.parseBoolean(isHiddenLogout);
  }

  public boolean isHiddenChangePassword() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    String isHiddenChangePassword =
        globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_CHANGE_PASSWORD_BUTTON.toString());
    return Boolean.parseBoolean(isHiddenChangePassword);
  }

  public int getClientSideTimeout() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    String clientSideTimeoutInMinute = globalSettingService.findGlobalSettingValue(GlobalVariable.CLIENT_SIDE_TIMEOUT.toString());
    if (clientSideTimeoutInMinute != null && !clientSideTimeoutInMinute.isEmpty()) {
      return Integer.valueOf(clientSideTimeoutInMinute) * MINUTE_TO_SECOND * SECONND_TO_MILLISECOND;
    }
    return getDefaultClientSideTimeout();
  }

  private int getDefaultClientSideTimeout() {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    int serverSideTimeOutInMillisecond = externalContext.getSessionMaxInactiveInterval() * SECONND_TO_MILLISECOND;
    return serverSideTimeOutInMillisecond - TIME_BEFORE_LOST_SESSION;
  }

  public String getLogoutPage() throws MalformedURLException {
    Map<String, Object> response =
        IvyAdapterService.startSubProcess("getLogoutPage()", null,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    String logoutPage = (String) response.get("logoutPage");
    return StringUtils.isNotBlank(logoutPage) ? logoutPage : getHomePageURL();
  }

  public String getHomePageURL() throws MalformedURLException {
    ApplicationService applicationService = new ApplicationService();
    String homePageURL = getHomePageFromSetting();
    if (CollectionUtils.isEmpty(applicationService.findAllIvyApplications())) {
      if (!StringUtils.isEmpty(homePageURL)) {
        return homePageURL;
      } else {
        return new PortalNavigator().getPortalStartUrl();
      }
    }

    Long serverId = SecurityServiceUtils.getServerIdFromSession();
    String selectedApp = SecurityServiceUtils.getApplicationNameFromSession();
    String selectedAppDisplayName = SecurityServiceUtils.getApplicationDisplayNameFromSession();

    if (isDefaultPortalApp() || serverId == null || selectedApp == null || selectedAppDisplayName == null) {
      return SecurityServiceUtils.getDefaultPortalStartUrl();
    }

    Application selectedApplication =
        applicationService.findByDisplayNameAndNameAndServerId(selectedAppDisplayName, selectedApp, serverId);
    return selectedApplication.getLink();
  }

  public void navigateToHomePageOrDisplayWorkingTaskWarning(boolean isWorkingOnATask) throws IOException {
    if (isWorkingOnATask) {
      RequestContext.getCurrentInstance().execute("PF('logo-task-losing-confirmation-dialog').show()");
    } else {
      navigateToHomePage();
    }
  }
  
  public void resetTaskAndNavigateToHomePage() throws IOException {
    TaskUtils.resetTask(Ivy.wfTask());
    navigateToHomePage();
  }

  public void reserveTaskAndNavigateToHomePage() throws IOException {
    TaskUtils.parkTask(Ivy.wfTask());
    navigateToHomePage();
  }

  private void navigateToHomePage() throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(getHomePageURL());
  }

  private String getHomePageFromSetting() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    return globalSettingService.findGlobalSettingValue(GlobalVariable.HOMEPAGE_URL.toString());
  }

  private boolean isDefaultPortalApp() {
    return IApplication.PORTAL_APPLICATION_NAME.equals(Ivy.wf().getApplication().getName());
  }

  public boolean getErrorDetailToEndUser() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(this::findShowErrorDetailSystemProperty);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return true;
  }

  private boolean findShowErrorDetailSystemProperty() {
    ISystemProperty systemProp =
        ServerFactory.getServer().getApplicationConfigurationManager().getSystemProp("Errors.ShowDetailsToEndUser");
    return systemProp.getBooleanValue();
  }
}
