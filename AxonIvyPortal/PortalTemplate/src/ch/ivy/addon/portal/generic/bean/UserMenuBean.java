package ch.ivy.addon.portal.generic.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.events.GlobalSearchEvent;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.event.SystemEvent;
import ch.ivyteam.ivy.workflow.IProcessStart;

@ManagedBean
@ViewScoped
public class UserMenuBean {

  private List<IProcessStart> foundProcesses;
  private List<RemoteTask> foundTasks;
  private List<RemoteCase> foundCases;
  private String searchKeyWord;
  private String userName;

  private boolean hasNoRecordsFound;

  public void logout() {
    Ivy.session().logoutSessionUser();
  }

  public String getUserName() {
    userName = Ivy.session().getSessionUserName();
    return userName;
  }

  public boolean isShowServerInformation() {
    GlobalSettingService globalSettingSerive = new GlobalSettingService();
    String isShowServerInformation = globalSettingSerive.findGlobalSettingValue(GlobalVariable.SHOW_ENVIRONMENT_INFO);
    return Boolean.parseBoolean(isShowServerInformation);
  }

  public boolean isHiddenLogout() {
    GlobalSettingService globalSettingSerive = new GlobalSettingService();
    String isHiddenLogout = globalSettingSerive.findGlobalSettingValue(GlobalVariable.HIDE_LOGOUT_BUTTON);
    return Boolean.parseBoolean(isHiddenLogout);
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

  private String getHomePageFromSetting() {
    GlobalSettingService globalSettingSerive = new GlobalSettingService();
    return globalSettingSerive.findGlobalSettingValue(GlobalVariable.HOMEPAGE_URL);
  }

  private boolean isDefaultPortalApp() {
    return IApplication.PORTAL_APPLICATION_NAME.equals(Ivy.wf().getApplication().getName());
  }

  public void search() {
    String keyWord = searchKeyWord.trim();
    if (StringUtils.isBlank(keyWord)) {
      foundProcesses = new ArrayList<>();
      foundTasks = new ArrayList<>();
      foundCases = new ArrayList<>();
      return;
    }

    Long serverId = SecurityServiceUtils.getServerIdFromSession();
    String selectedApp = SecurityServiceUtils.getApplicationNameFromSession();

    GlobalSearchEvent globalSearchEvent = new GlobalSearchEvent(keyWord, userName, serverId, selectedApp);
    SystemEvent<GlobalSearchEvent> systemEvent = globalSearchEvent.toSystemEvent();
    sendSystemEvent(systemEvent);
  }

  public void resetSearchData() {
    searchKeyWord = StringUtils.EMPTY;
    foundProcesses.clear();
    foundTasks.clear();
    foundCases.clear();
    hasNoRecordsFound = false;
  }

  public void setDataForSearchResult(List<IProcessStart> processes, List<RemoteTask> tasks, List<RemoteCase> cases) {
    foundProcesses = processes;
    foundTasks = tasks;
    foundCases = cases;
    hasNoRecordsFound = processes.isEmpty() && tasks.isEmpty() && cases.isEmpty();
  }

  private static void sendSystemEvent(SystemEvent<?> event) {
    Ivy.wf().getApplication().sendSystemEvent(event);
  }

  public String getSearchKeyWord() {
    return searchKeyWord;
  }

  public void setSearchKeyWord(String searchKeyWord) {
    this.searchKeyWord = searchKeyWord;
  }

  public List<IProcessStart> getFoundProcesses() {
    return foundProcesses;
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
}
