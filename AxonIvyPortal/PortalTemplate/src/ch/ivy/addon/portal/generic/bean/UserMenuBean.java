package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.bo.RemoteWebStartable;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.support.DataCache;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.process.call.SubProcessCallResult;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.system.ISystemProperty;

@ManagedBean
@ViewScoped
public class UserMenuBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private List<RemoteWebStartable> foundWebStartables;
  private List<RemoteTask> foundTasks;
  private List<RemoteCase> foundCases;
  private String searchKeyword;
  private String userName;
  public static final int MINUTE_TO_SECOND = 60;
  public static final int SECONND_TO_MILLISECOND = 1000;
  public static final int TIME_BEFORE_LOST_SESSION = 180000; // 3 minutes

  private boolean hasNoRecordsFound;

  public String getUserName() {
    userName = Ivy.session().getSessionUserName();
    return userName;
  }
  
  public boolean isShowServerInformation() {
    return DataCache.getGlobalSettingValueAsBoolean(GlobalVariable.SHOW_ENVIRONMENT_INFO);
  }

  public boolean isHiddenLogout() {
    return DataCache.getGlobalSettingValueAsBoolean(GlobalVariable.HIDE_LOGOUT_BUTTON);
  }

  public boolean isHiddenChangePassword() {
    return DataCache.getGlobalSettingValueAsBoolean(GlobalVariable.HIDE_CHANGE_PASSWORD_BUTTON);
  }

  public int getClientSideTimeout() {
    Object attribute = DataCache.getGlobalSettingFromCache(GlobalVariable.CLIENT_SIDE_TIMEOUT);
    if (attribute == null){
      GlobalSettingService globalSettingSerive = new GlobalSettingService();
      String clientSideTimeoutInMinute = globalSettingSerive.findGlobalSettingValue(GlobalVariable.CLIENT_SIDE_TIMEOUT);
      int timeout;
      if (StringUtils.isNotBlank(clientSideTimeoutInMinute)) {
        timeout = Integer.valueOf(clientSideTimeoutInMinute) * MINUTE_TO_SECOND * SECONND_TO_MILLISECOND;
      } else {
        timeout = getDefaultClientSideTimeout();
      }
      DataCache.cacheGlobalSetting(GlobalVariable.CLIENT_SIDE_TIMEOUT, String.valueOf(timeout));
      return timeout;
    } 
    return Integer.valueOf((String)attribute);
  }

  private int getDefaultClientSideTimeout() {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    int serverSideTimeOutInMillisecond = externalContext.getSessionMaxInactiveInterval() * SECONND_TO_MILLISECOND;
    int defaultClientSideTimeout = serverSideTimeOutInMillisecond - TIME_BEFORE_LOST_SESSION;
    return defaultClientSideTimeout;
  }

  public String getLogoutPage() throws Exception {
    String logoutPageUrl = DataCache.getLogoutPageFromCache();
    if (StringUtils.isBlank(logoutPageUrl)){
      Map<String, Object> response =
          IvyAdapterService.startSubProcess("getLogoutPage()", null,
              Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
      String logoutPage = (String) response.get("logoutPage");
      logoutPageUrl = StringUtils.isNotBlank(logoutPage) ? logoutPage : getHomePageURL();
      DataCache.cacheLogoutPage(logoutPageUrl);
    }
    return logoutPageUrl;
  }

  public String getHomePageURL() throws Exception {
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
  
  public void navigateToHomePageOrDisplayWorkingTaskWarning(boolean isWorkingOnATask) throws Exception {
    if (isWorkingOnATask) {
      RequestContext.getCurrentInstance().execute("PF('logo-task-losing-confirmation-dialog').show()");
    } else {
      navigateToHomePage();
    }
  }
  
  public void resetTaskAndNavigateToHomePage() throws Exception {
    TaskUtils.resetTask(Ivy.wfTask());
    navigateToHomePage();
  }

  public void reserveTaskAndNavigateToHomePage() throws Exception {
    TaskUtils.parkTask(Ivy.wfTask());
    navigateToHomePage();
  }
  
  private void navigateToHomePage() throws Exception {
    FacesContext.getCurrentInstance().getExternalContext().redirect(getHomePageURL());
  }

  private String getHomePageFromSetting() {
    return DataCache.getGlobalSettingValueAsString(GlobalVariable.HOMEPAGE_URL);
  }

  private boolean isDefaultPortalApp() {
    return IApplication.PORTAL_APPLICATION_NAME.equals(Ivy.wf().getApplication().getName());
  }

  @SuppressWarnings("unchecked")
  public void search() {
    String keyword = searchKeyword.trim();
    if (StringUtils.isBlank(keyword)) {
      foundWebStartables = new ArrayList<>();
      foundTasks = new ArrayList<>();
      foundCases = new ArrayList<>();
      return;
    }

    Long serverId = SecurityServiceUtils.getServerIdFromSession();
    String selectedApp = SecurityServiceUtils.getApplicationNameFromSession();

    SubProcessCallResult result =
        SubProcessCall.withPath("Functional Processes/GlobalSearch").withParam("keyword", keyword)
            .withParam("serverId", serverId).withParam("applicationName", selectedApp).call();
    foundWebStartables = (List<RemoteWebStartable>) result.get("webStartables");
    foundTasks = (List<RemoteTask>) result.get("tasks");
    foundCases = (List<RemoteCase>) result.get("cases");
    hasNoRecordsFound =
        CollectionUtils.isEmpty(foundWebStartables) && CollectionUtils.isEmpty(foundTasks)
            && CollectionUtils.isEmpty(foundCases);
  }

  public void resetSearchData() {
    searchKeyword = StringUtils.EMPTY;
    foundWebStartables = new ArrayList<>();
    foundTasks = new ArrayList<>();
    foundCases = new ArrayList<>();
    hasNoRecordsFound = false;

  }

  public String getSearchKeyword() {
    return searchKeyword;
  }

  public void setSearchKeyword(String searchKeyword) {
    this.searchKeyword = searchKeyword;
  }

  public List<RemoteWebStartable> getFoundWebStartables() {
    return foundWebStartables;
  }

  public List<RemoteTask> getFoundTasks() {
    return foundTasks;
  }

  public List<RemoteCase> getFoundCases() {
    return foundCases;
  }

  public boolean isHasNoRecordsFound() {
    return hasNoRecordsFound;
  }

  public boolean getErrorDetailToEndUser() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> findShowErrorDetailSystemProperty());
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
