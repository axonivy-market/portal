package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.enums.PortalCustomSignature;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bean.PermissionBean;
import ch.ivy.addon.portalkit.bean.PortalExceptionBean;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.dto.UserMenu;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.AnnouncementService;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.RequestUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

@ManagedBean
@ViewScoped
public class UserMenuBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final long TIME_BEFORE_LOST_SESSION = 3 * DateUtils.MILLIS_PER_MINUTE; // 3 minutes
  public static final String TASK_LEAVE_WARNING_COMPONENT = "task-leave-warning-component";
  private static String expressStartLink;
  private String targetPage = StringUtils.EMPTY;
  private String loggedInUser;
  private boolean isShowGlobalSearch;

  public String getLoggedInUser() {
    return loggedInUser;
  }
  
  @PostConstruct
  public void init() {
    if (!Ivy.session().isSessionUserUnknown()) {
      String format = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.LOGGED_IN_USER_FORMAT);
      GlobalVariable.Option option = GlobalVariable.Option.valueOf(format);
      String fullName = Ivy.session().getSessionUser().getFullName();
      String userName = Ivy.session().getSessionUserName();
      String fullDisplayFormat = "%s (%s)";
      loggedInUser = switch (option) {
        case USERNAME -> userName;
        case DISPLAY_NAME -> fullName;
        case DISPLAY_NAME_USERNAME -> String.format(fullDisplayFormat, fullName, userName);
        default -> String.format(fullDisplayFormat, userName, fullName);
      };
    }
    isShowGlobalSearch = GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_GLOBAL_SEARCH);
  }

  public boolean isShowCaseDurationTime() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_CASE_DURATION_TIME);
  }

  public boolean isShowServerInformation() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_ENVIRONMENT_INFO);
  }

  public boolean isHiddenLogout() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_LOGOUT_BUTTON);
  }

  public boolean isHiddenChangePassword() {
    return loggedByExternalSecuritySystem()
        || GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_CHANGE_PASSWORD_BUTTON);
  }
  
  private boolean loggedByExternalSecuritySystem() {
	  return Ivy.session().getSessionUser() != null && Ivy.session().getSessionUser().getExternalId() != null;
  }

  public boolean isHiddenStatisticWidget() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_STATISTIC_WIDGET);
  }
  
  public boolean getIsShowGlobalSearch() {
    return isShowGlobalSearch;
  }

  public long getClientSideTimeout() {
    String clientSideTimeoutInMinute = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.CLIENT_SIDE_TIMEOUT);
    if (StringUtils.isNotBlank(clientSideTimeoutInMinute)) {
      Long timeoutInMinute = Long.valueOf(clientSideTimeoutInMinute);
      if (timeoutInMinute > 0) {
        return timeoutInMinute * DateUtils.MILLIS_PER_MINUTE;
      }
    }
    return getDefaultClientSideTimeout();
  }

  public String getLogoutPage() {
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

  public String getHomePageURL() {
    return PortalNavigator.getPortalStartUrl();
  }

  public void navigateToHomePageOrDisplayWorkingTaskWarning(boolean isWorkingOnATask, ITask task) throws IOException {
    if (isWorkingOnATask && task.getState() != TaskState.DONE) {
      targetPage = getHomePageURL();
      openTaskLosingConfirmationDialog();
    } else {
      navigateToHomePage();
    }
  }

  public void navigateToPortalManagementDisplayWorkingTaskWarning(boolean isWorkingOnATask, ITask task) throws IOException {
    if (isWorkingOnATask && task.getState() != TaskState.DONE) {
      openTaskLosingConfirmationDialog();
      targetPage = getPortalManagementUrl();
    } else {
      executeJSResetPortalMenuState();
      navigateToPortalManagement();
    }
  }

  public void navigateToUserProfileOrDisplayWorkingTaskWarning(boolean isWorkingOnATask, ITask task) throws IOException {
    if (isWorkingOnATask && task.getState() != TaskState.DONE) {
      openTaskLosingConfirmationDialog();
      targetPage = getUserProfileUrl();
    } else {
      executeJSResetPortalMenuState();
      navigateToUserProfile();
    }
  }

  public void navigateToAbsencesOrDisplayWorkingTaskWarning(boolean isWorkingOnATask, ITask task) throws IOException {
    if (isWorkingOnATask && task.getState() != TaskState.DONE) {
      openTaskLosingConfirmationDialog();
      targetPage = getAbsencesUrl();
    } else {
      executeJSResetPortalMenuState();
      navigateToAbsences();
    }
  }

  public void navigateToDashboardConfigurationOrDisplayWorkingTaskWarning(boolean isWorkingOnATask, ITask task) throws IOException {
    var url = PortalNavigator.buildDashboardConfigurationUrl();
    if (isWorkingOnATask && task.getState() != TaskState.DONE) {
      openTaskLosingConfirmationDialog();
      targetPage = url;
    } else {
      executeJSResetPortalMenuState();
      getExternalContext().redirect(url);
    }
  }

  private String getAbsencesUrl() {
    return PortalNavigator.buildAbsencesUrl();
  }

  public void navigateToAbsences() throws IOException {
    getExternalContext().redirect(getAbsencesUrl());
  }

  public void reserveTaskAndNavigateWithGrowl(ITask task) throws IOException {
    IvyComponentLogicCaller<ITask> reserveTask = new IvyComponentLogicCaller<>();
    ITask relatedTask = task != null ? task : Ivy.wfTask();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    reserveTask.invokeComponentLogic(componentId, "#{logic.reserve}", new Object[] {relatedTask.getCase()});
    TaskUtils.parkTask(relatedTask);
    navigateToTargetPage();
  }
  
  public void resetTaskAndNavigateWithGrowl(ITask task) throws IOException {
    IvyComponentLogicCaller<ITask> leaveTask = new IvyComponentLogicCaller<>();
    ITask relatedTask = task != null ? task : Ivy.wfTask();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    leaveTask.invokeComponentLogic(componentId, "#{logic.leave}", new Object[] {relatedTask.getCase()});
    TaskUtils.resetTask(relatedTask);
    navigateToTargetPage();
  }

  private void executeJSResetPortalMenuState() {
    PrimeFaces.current().executeScript("resetPortalLeftMenuState()");
  }
  
  private void openTaskLosingConfirmationDialog() {
    PrimeFaces.current().executeScript("PF('logo-task-losing-confirmation-dialog').show()");
  }

  public boolean hasAdminPermission() {
    return new PermissionBean().hasAdminPermission();
  }

  public boolean isShowAdminSetting() {
    return !RequestUtils.isMobileDevice() && hasAdminPermission();
  }
  
  public boolean isShowDashboardConfigurationMenu() {
    return !RequestUtils.isMobileDevice()
        && (PermissionUtils.hasDashboardWriteOwnPermission() || PermissionUtils.hasDashboardWritePublicPermission());
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
    return AnnouncementService.getInstance().getAnnouncementMessage();
  }

  public boolean isAnnouncementActivated() {
    return AnnouncementService.getInstance().isAnnouncementActivated();
  }
  
  private void navigateToHomePage() throws IOException {
    getExternalContext().redirect(getHomePageURL());
  }
  
  public void navigateToUserProfile() throws IOException {
    getExternalContext().redirect(getUserProfileUrl());
  }
  
  private void navigateToTargetPage() throws IOException {
    getExternalContext().redirect(targetPage);
  }
  
  private String getUserProfileUrl() {
    return PortalNavigator.buildUserProfileUrl();
  }

  private void navigateToPortalManagement() throws IOException {
    getExternalContext().redirect(getPortalManagementUrl());
  }

  private String getPortalManagementUrl() {
    return PortalNavigator.buildPortalManagementUrl();
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
    Map<String, Object> response =  IvyAdapterService.startSubProcessInSecurityContext(PortalCustomSignature.LOGOUT_PAGE.getSignature(), null);
    return (String) Optional.ofNullable(response).map(r -> r.get("logoutPage")).orElse("");
  }
  
  public void navigateToURLOrDisplayWorkingTaskWarning(UserMenu menu, boolean isWorkingOnATask, ITask task) throws IOException {
    if (isWorkingOnATask && task.getState() != TaskState.DONE) {
      openTaskLosingConfirmationDialog();
      targetPage = getURLFromUserMenu(menu);
    } else {
      executeJSResetPortalMenuState();
      getExternalContext().redirect(getURLFromUserMenu(menu));
    }
  }

  private String getURLFromUserMenu(UserMenu menu) {
    String menuUrl = menu.getUrl();
    if (StringUtils.isNotBlank(menuUrl)) {
      if (menuUrl.contains(".ivp")) {
        return PortalNavigator.buildUrlByKeyword(menuUrl, menuUrl, menu.getParams());
      }
      if (menuUrl.contains("http")) {
        return menuUrl;
      }
      if (StringUtils.isNotBlank(getExpressStartLink())) {
        ExpressProcess workflow = ExpressProcessService.getInstance().findExpressProcessByName(menuUrl);
        if (workflow != null && PermissionUtils.checkAbleToStartAndAbleToEditExpressWorkflow(workflow)
            && StringUtils.isNotBlank(workflow.getId())) {
          menu.setUrl(getExpressStartLink() + "?workflowID=" + workflow.getId());
        }
      }
      return menu.getUrl();
    }
    return "";
  }

  private static String getExpressStartLink() {
    if (StringUtils.isEmpty(expressStartLink)) {
      expressStartLink = ProcessStartCollector.getInstance().findExpressWorkflowStartLink();
    }
    return expressStartLink;
  }
}
