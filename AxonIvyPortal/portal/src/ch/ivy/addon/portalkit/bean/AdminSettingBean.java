package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.GlobalVariable.Option;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PrimeFacesUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class AdminSettingBean implements Serializable {
  private static final long serialVersionUID = 1506137118077215366L;

  public static final String PORTAL_MESSAGE_INFO = "portal-management-messages-information";
  public static final String APPLICATION_TAB_ID = "application-tab";
  public static final String ADMIN_SETTING_TAB_ID = "setting-tab";
  public static final String ANNOUNCEMENT_SETTING_TAB_ID = "announcement-tab";
  public static final String ROLE_MANAGEMENT_TAB_ID = "role-management-tab";
  public static final String PASSWORD_VALIDATION_TAB_ID = "password-validation-tab";
  private boolean isShowRoleManagementTab;
  private boolean isTabChangeEventTriggered;
  private boolean isShowPasswordValidationTab;
  private ThirdPartyApplicationBean thirdPartyApplicationBean;

  public void initAdminTabViewConfig() {
    if (isTabChangeEventTriggered) {
      isTabChangeEventTriggered = false;
      return;
    }
    isShowRoleManagementTab = canSeeRoleManagement();
    isShowPasswordValidationTab = canSeePasswordValidation();
    initApplicationTab();
  }

  public void onTabChange(TabChangeEvent<Object> tabChangeEvent) {
    if (tabChangeEvent.getComponent() instanceof TabView) {
      var tabId = tabChangeEvent.getTab().getId();
      switch (tabId) {
        case APPLICATION_TAB_ID:
          initApplicationTab();
          break;
        case ADMIN_SETTING_TAB_ID:
          initAdminSettingsTab();
          break;
        case ANNOUNCEMENT_SETTING_TAB_ID:
          initAnnouncementTab();
          break;
        case ROLE_MANAGEMENT_TAB_ID:
          break;
        case PASSWORD_VALIDATION_TAB_ID:
          break;
        default:
          break;
      }
    }
    isTabChangeEventTriggered = true;
  }

  private ThirdPartyApplicationBean getThirdPartyApplicationBean() {
    if (thirdPartyApplicationBean == null) {
      thirdPartyApplicationBean = new ThirdPartyApplicationBean();
    }
    return thirdPartyApplicationBean;
  }

  public void setThirdPartyApplicationBean(ThirdPartyApplicationBean thirdPartyApplicationBean) {
    this.thirdPartyApplicationBean = thirdPartyApplicationBean;
  }

  // Delegation methods for third-party application management
  public void addNewApplication() {
    getThirdPartyApplicationBean().addNewApplication();
  }

  public void editApplication(Application application) {
    getThirdPartyApplicationBean().editApplication(application);
  }

  public void saveApplication() {
    getThirdPartyApplicationBean().saveApplication();
  }

  public void deleteApplication(Application application) {
    getThirdPartyApplicationBean().deleteApplication(application);
  }

  public List<Application> getApplicationList() {
    return getThirdPartyApplicationBean().getApplicationList();
  }

  public void setApplicationList(List<Application> applicationList) {
    getThirdPartyApplicationBean().setApplicationList(applicationList);
  }

  public Application getSelectedApp() {
    return getThirdPartyApplicationBean().getSelectedApp();
  }

  public void setSelectedApp(Application selectedApp) {
    getThirdPartyApplicationBean().setSelectedApp(selectedApp);
  }

  public String getDialogTitle() {
    return getThirdPartyApplicationBean().getDialogTitle();
  }

  public void setDialogTitle(String dialogTitle) {
    getThirdPartyApplicationBean().setDialogTitle(dialogTitle);
  }

  public boolean isAddMode() {
    return getThirdPartyApplicationBean().isAddMode();
  }

  public void setAddMode(boolean isAddMode) {
    getThirdPartyApplicationBean().setAddMode(isAddMode);
  }

  public String getAppNameInCurrentLocale(Application application) {
    return getThirdPartyApplicationBean().getAppNameInCurrentLocale(application);
  }

  public List<String> getSelectedApplicationPermissions() {
    return getThirdPartyApplicationBean().getSelectedApplicationPermissions();
  }

  public void setSelectedApplicationPermissions(List<String> selectedApplicationPermissions) {
    getThirdPartyApplicationBean().setSelectedApplicationPermissions(selectedApplicationPermissions);
  }

  public String getDisplayNameInCurrentLanguage() {
    return getThirdPartyApplicationBean().getDisplayNameInCurrentLanguage();
  }

  public void setDisplayNameInCurrentLanguage(String displayNameInCurrentLanguage) {
    getThirdPartyApplicationBean().setDisplayNameInCurrentLanguage(displayNameInCurrentLanguage);
  }

  public List<DisplayName> getSupportedLanguages() {
    return getThirdPartyApplicationBean().getSupportedLanguages();
  }

  public List<String> getLanguages() {
    return getThirdPartyApplicationBean().getLanguages();
  }

  public void updateDisplayNameByLocale() {
    getThirdPartyApplicationBean().updateDisplayNameByLocale();
  }

  public List<DisplayName> getTitles() {
    return getThirdPartyApplicationBean().getTitles();
  }

  public void updateCurrentLanguage() {
    getThirdPartyApplicationBean().updateCurrentLanguage();
  }

  public String getTranslatedText() {
    return getThirdPartyApplicationBean().getTranslatedText();
  }

  public void setTranslatedText(String translatedText) {
    getThirdPartyApplicationBean().setTranslatedText(translatedText);
  }

  public String getWarningText() {
    return getThirdPartyApplicationBean().getWarningText();
  }

  public void setWarningText(String warningText) {
    getThirdPartyApplicationBean().setWarningText(warningText);
  }

  public void translate(DisplayName title) {
    getThirdPartyApplicationBean().translate(title);
  }

  public void applyTranslatedText(DisplayName displayName) {
    getThirdPartyApplicationBean().applyTranslatedText(displayName);
  }

  public boolean isRequiredField(DisplayName displayName) {
    return getThirdPartyApplicationBean().isRequiredField(displayName);
  }

  public boolean isShowTranslation(DisplayName title) {
    return getThirdPartyApplicationBean().isShowTranslation(title);
  }

  public boolean isFocus(DisplayName title) {
    return getThirdPartyApplicationBean().isFocus(title);
  }

  public void onApplicationReorderDelegate(List<Application> applications, Application selectedApp) {
    getThirdPartyApplicationBean().onApplicationReorder(applications, selectedApp);
  }

  private void initApplicationTab() {

    getThirdPartyApplicationBean().loadApplications();
  }

  private void initAnnouncementTab() {
    invokeAdminSettingsComponentLogic("#{logic.initAnnouncementSettings}", new Object[] {});
  }

  private void initAdminSettingsTab() {
    PrimeFacesUtils.executeScript("PF('settingTable').filter()");
    invokeAdminSettingsComponentLogic("#{logic.initAdminSettings}", new Object[] {});
  }
  public void onApplicationReorder(ReorderEvent reorderEvent) {
    int fromIndex = reorderEvent.getFromIndex();
    int toIndex = reorderEvent.getToIndex();
    
    List<Application> applicationList = getThirdPartyApplicationBean().getApplicationList();
    
    if (applicationList != null && !applicationList.isEmpty()) {
      Application selectedApp = applicationList.remove(fromIndex);
      applicationList.add(toIndex, selectedApp);

      for (int i = 0; i < applicationList.size(); i++) {
        applicationList.get(i).setMenuOrdinal(i);
      }

      getThirdPartyApplicationBean().onApplicationReorder(applicationList, selectedApp);
    }
  }

  private void invokeAdminSettingsComponentLogic(String methodName, Object[] param) {
    var adminSettingTabChange = new IvyComponentLogicCaller<String>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    adminSettingTabChange.invokeComponentLogic(componentId, methodName, param);
  }

  public static void updatePortalManagementMessages() {
    FacesMessage message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_WARN,
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/dialogclosinginformation/informMessage"), null);
    FacesContext.getCurrentInstance().addMessage(PORTAL_MESSAGE_INFO, message);
    PrimeFaces.current().ajax().update("portal-management-messages");
  }

  private boolean canSeeRoleManagement() {
    return PermissionUtils.hasPortalPermission(PortalPermission.ROLE_MANAGEMENT);
  }

  private boolean canSeePasswordValidation() {
    return PermissionUtils.hasPortalPermission(PortalPermission.PASSWORD_VALIDATION);
  }

  public String getDropdownItemlabel(Object item) {
    if (item instanceof Option) {
      return ((Option) item).translate();
    }
    return String.valueOf(item);
  }

  public boolean isShowRoleManagementTab() {
    return isShowRoleManagementTab;
  }

  public void setShowRoleManagementTab(boolean isShowRoleManagementTab) {
    this.isShowRoleManagementTab = isShowRoleManagementTab;
  }

  public boolean isShowPasswordValidationTab() {
    return isShowPasswordValidationTab;
  }

  public void setShowPasswordValidationTab(boolean isShowPasswordValidationTab) {
    this.isShowPasswordValidationTab = isShowPasswordValidationTab;
  }

  public List<SecurityMemberDTO> completeApplicationPermissions(String query) {
    return getThirdPartyApplicationBean().completeApplicationPermissions(query);
  }

  public void onSelectPermissionForApplication(SelectEvent<Object> event) {
    getThirdPartyApplicationBean().onSelectPermissionForApplication(event);
  }

  public void onUnSelectPermissionForApplication(UnselectEvent<Object> event) {
    getThirdPartyApplicationBean().onUnSelectPermissionForApplication(event);
  }
}
