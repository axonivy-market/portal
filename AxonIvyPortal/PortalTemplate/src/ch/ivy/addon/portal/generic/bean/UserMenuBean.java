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
import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bean.PortalExceptionBean;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.AnnouncementService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

@ManagedBean
@ViewScoped
public class UserMenuBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final long TIME_BEFORE_LOST_SESSION = 3 * DateUtils.MILLIS_PER_MINUTE; // 3 minutes
  public static final String TASK_LEAVE_WARNING_COMPONENT = "task-leave-warning-component";

  private String loggedInUser;
  private boolean isShowGlobalSearch;
  GlobalSettingService globalSettingService;

  public String getLoggedInUser() {
    return loggedInUser;
  }
  
  @PostConstruct
  public void init() {
    globalSettingService = new GlobalSettingService();
    if (!Ivy.session().isSessionUserUnknown()) {
      String format = globalSettingService.findGlobalSettingValue(GlobalVariable.LOGGED_IN_USER_FORMAT.toString());
      GlobalVariable.Option option = GlobalVariable.Option.valueOf(format);
      String fullName = Ivy.session().getSessionUser().getFullName();
      String userName = Ivy.session().getSessionUserName();
      String fullDisplayFormat = "%s (%s)";
      switch (option) {
        case USERNAME:
          loggedInUser = userName;
          break;
        case DISPLAY_NAME:
          loggedInUser = fullName;
          break;
        case DISPLAY_NAME_USERNAME:
          loggedInUser = String.format(fullDisplayFormat, fullName, userName);
          break;
        default:
          loggedInUser = String.format(fullDisplayFormat, userName, fullName);
          break;
      }
    }
    isShowGlobalSearch = Boolean.parseBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.SHOW_GLOBAL_SEARCH.toString()));
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
  
  public boolean getIsShowGlobalSearch() {
    return isShowGlobalSearch;
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

  public void resetTaskAndNavigateToHomePageWithGrowl(ITask task) throws IOException {
    IvyComponentLogicCaller<ITask> leaveTask = new IvyComponentLogicCaller<>();
    ITask relatedTask = task != null ? task : Ivy.wfTask();
    leaveTask.invokeComponentLogic(TASK_LEAVE_WARNING_COMPONENT, "#{logic.leave}", new Object[] {relatedTask.getCase()});
    TaskUtils.resetTask(relatedTask);
    navigateToHomePage();
  }

  public void reserveTaskAndNavigateToHomePage(ITask task) throws IOException {
    TaskUtils.parkTask(task != null ? task : Ivy.wfTask());
    navigateToHomePage();
  }
  
  public void reserveTaskAndNavigateToHomePageWithGrowl(ITask task) throws IOException {
    IvyComponentLogicCaller<ITask> reserveTask = new IvyComponentLogicCaller<>();
    ITask relatedTask = task != null ? task : Ivy.wfTask();
    reserveTask.invokeComponentLogic(TASK_LEAVE_WARNING_COMPONENT, "#{logic.reserve}", new Object[] {relatedTask.getCase()});
    TaskUtils.parkTask(relatedTask);
    navigateToHomePage();
  }
  
  /**
   * We moved this method to PortalExceptionBean#getErrorDetailToEndUser
   * @return system configuration of ErrorDetailToEndUser
   */
  @Deprecated
  public boolean getErrorDetailToEndUser() {
    try {
      PortalExceptionBean portalExceptionBean = (PortalExceptionBean) ManagedBeans.find("portalExceptionBean").get();
      return portalExceptionBean.getErrorDetailToEndUser();
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
    return PortalConstants.PORTAL_APPLICATION_NAME.equals(IApplication.current().getName());
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
