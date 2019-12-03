package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.PrimeFaces;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.AnnouncementService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.system.ISystemProperty;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

@ManagedBean
@ViewScoped
public class UserMenuBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final long TIME_BEFORE_LOST_SESSION = 3 * DateUtils.MILLIS_PER_MINUTE; // 3 minutes
  
  private String userName;
  GlobalSettingService globalSettingService;

  public String getUserName() {
    return userName;
  }
  
  @PostConstruct
  public void init() {
    userName = Ivy.session().getSessionUserName();
    globalSettingService = new GlobalSettingService();
  }

  public boolean isShowServerInformation() {
    return Boolean.parseBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.SHOW_ENVIRONMENT_INFO.toString()));
  }

  public boolean isHiddenLogout() {
    return Boolean.parseBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_LOGOUT_BUTTON.toString()));
  }

  public boolean isHiddenChangePassword() {
    return Boolean.parseBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_CHANGE_PASSWORD_BUTTON.toString()));
  }

  public boolean isHiddenStatisticWidget() {
    return BooleanUtils.toBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_STATISTIC_WIDGET.toString()));
  }

  public long getClientSideTimeout() {
    String clientSideTimeoutInMinute = globalSettingService.findGlobalSettingValue(GlobalVariable.CLIENT_SIDE_TIMEOUT.toString());
    if (StringUtils.isNotBlank(clientSideTimeoutInMinute)) {
      return Integer.valueOf(clientSideTimeoutInMinute) * DateUtils.MILLIS_PER_MINUTE;
    }
    return getDefaultClientSideTimeout();
  }

  public String getLogoutPage() throws MalformedURLException {
    IvyCacheService cacheService = IvyCacheService.newInstance();
    String logoutPageUrl = cacheService.getLogoutPageFromCache();
    if (StringUtils.isNotBlank(logoutPageUrl)){
      return logoutPageUrl;
    }
    String customizedlogoutPage = getCustomizedLogoutPage();
    String logoutPage = StringUtils.isNotBlank(customizedlogoutPage) ? customizedlogoutPage : getHomePageURL();
    cacheService.cacheLogoutPage(logoutPage);
    return logoutPage;
  }

  public String getHomePageURL() throws MalformedURLException {
    RegisteredApplicationService applicationService = new RegisteredApplicationService();
    // Special handle since this function is call in javascript: window.location = "${userMenuBean.getHomePageURL()}";
    if (globalSettingService == null) {
      globalSettingService = new GlobalSettingService();
    }
    String homePageURL = globalSettingService.findGlobalSettingValue(GlobalVariable.HOMEPAGE_URL.toString());
    if (CollectionUtils.isEmpty(applicationService.findAllIvyApplications())) {
      if (StringUtils.isNotBlank(homePageURL)) {
        return homePageURL;
      } else {
        return new PortalNavigator().getPortalStartUrl();
      }
    }

    String selectedApp = SecurityServiceUtils.getApplicationNameFromSession();
    String selectedAppDisplayName = SecurityServiceUtils.getApplicationDisplayNameFromSession();

    if (isDefaultPortalApp() || selectedApp == null || selectedAppDisplayName == null) {
      return SecurityServiceUtils.getDefaultPortalStartUrl();
    }

    Application selectedApplication = applicationService.findByDisplayNameAndName(selectedAppDisplayName, selectedApp);
    return Optional.ofNullable(selectedApplication)
        .map(Application::getLink)
        .orElse(StringUtils.EMPTY);
  }

  public void navigateToHomePageOrDisplayWorkingTaskWarning(boolean isWorkingOnATask, ITask task) throws IOException {
    if (isWorkingOnATask && task.getState() != TaskState.DONE) {
      PrimeFaces.current().executeScript("PF('logo-task-losing-confirmation-dialog').show()");
    } else {
      navigateToHomePage();
    }
  }

  public void resetTaskAndNavigateToHomePage(ITask task) throws IOException {
    TaskUtils.resetTask(task != null ? task : Ivy.wfTask());
    navigateToHomePage();
  }

  public void reserveTaskAndNavigateToHomePage(ITask task) throws IOException {
    TaskUtils.parkTask(task != null ? task : Ivy.wfTask());
    navigateToHomePage();
  }
  
  public boolean getErrorDetailToEndUser() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(this::findShowErrorDetailSystemProperty);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return true;
  }

  public String getAnnouncement() {
    return AnnouncementService.getInstance().getAnnouncement();
  }

  public boolean isAnnouncementActivated() {
    return AnnouncementService.getInstance().isAnnouncementActivated();
  }
  
  private void navigateToHomePage() throws IOException {
    getExternalContext().redirect(getHomePageURL());
  }

  private boolean isDefaultPortalApp() {
    return PortalConstants.PORTAL_APPLICATION_NAME.equals(Ivy.wf().getApplication().getName());
  }

  private boolean findShowErrorDetailSystemProperty() {
    ISystemProperty systemProp =
        ServerFactory.getServer().getApplicationConfigurationManager().getSystemProp("Errors.ShowDetailsToEndUser");
    return systemProp.getBooleanValue();
  }
  
  private long getDefaultClientSideTimeout() {
    ExternalContext externalContext = getExternalContext();
    long serverSideTimeOutInMillisecond = externalContext.getSessionMaxInactiveInterval() * DateUtils.MILLIS_PER_SECOND;
    return serverSideTimeOutInMillisecond - TIME_BEFORE_LOST_SESSION;
  }

  private ExternalContext getExternalContext() {
    return FacesContext.getCurrentInstance().getExternalContext();
  }
  
  private String getCustomizedLogoutPage() {
    Map<String, Object> response = IvyAdapterService.startSubProcess("getLogoutPage()", null,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (String) response.get("logoutPage");
  }
}
