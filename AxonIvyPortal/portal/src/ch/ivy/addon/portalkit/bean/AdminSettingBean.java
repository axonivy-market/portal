package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.enums.GlobalVariable.Option;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PrimeFacesUtils;
import ch.ivyteam.ivy.environment.Ivy;

@Named
@ViewScoped
public class AdminSettingBean implements Serializable {
  private static final long serialVersionUID = 1506137118077215366L;

  public static final String PORTAL_MESSAGE_INFO = "portal-management-messages-information";
  public static final String ADMIN_SETTING_TAB_ID = "setting-tab";
  public static final String ANNOUNCEMENT_SETTING_TAB_ID = "announcement-tab";
  public static final String ROLE_MANAGEMENT_TAB_ID = "role-management-tab";
  public static final String PASSWORD_VALIDATION_TAB_ID = "password-validation-tab";
  private boolean isShowRoleManagementTab;
  private boolean isTabChangeEventTriggered;
  private boolean isShowPasswordValidationTab;

  public void initAdminTabViewConfig() {
    if (isTabChangeEventTriggered) {
      isTabChangeEventTriggered = false;
      return;
    }
    isShowRoleManagementTab = canSeeRoleManagement();
    isShowPasswordValidationTab = canSeePasswordValidation();
    // Settings is the first tab, so its data must be loaded on the initial render
    // (onTabChange only fires when the user switches to another tab and back).
    invokeAdminSettingsComponentLogic("#{logic.initAdminSettings}", new Object[] {});
  }

  public void onTabChange(TabChangeEvent<Object> tabChangeEvent) {
    if (tabChangeEvent.getComponent() instanceof TabView) {
      var tabId = tabChangeEvent.getTab().getId();
      switch (tabId) {
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

  private void initAnnouncementTab() {
    invokeAdminSettingsComponentLogic("#{logic.initAnnouncementSettings}", new Object[] {});
  }

  private void initAdminSettingsTab() {
    PrimeFacesUtils.executeScript("PF('settingTable').filter()");
    invokeAdminSettingsComponentLogic("#{logic.initAdminSettings}", new Object[] {});
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
}
