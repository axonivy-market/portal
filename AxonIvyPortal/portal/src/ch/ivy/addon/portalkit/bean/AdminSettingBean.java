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
import org.primefaces.event.TabChangeEvent;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.enums.GlobalVariable.Option;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
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
  public static final String EXPRESS_MANAGEMENT_TAB_ID = "express-management-tab";
  public static final String ROLE_MANAGEMENT_TAB_ID = "role-management-tab";
  private boolean isShowExpressManagementTab;
  private boolean isShowRoleManagementTab;
  private boolean isTabChangeEventTriggered;

  public void initAdminTabViewConfig() {
    if (isTabChangeEventTriggered) {
      isTabChangeEventTriggered = false;
      return;
    }
    isShowExpressManagementTab = new ProcessStartCollector().findExpressCreationProcess() != null;
    isShowRoleManagementTab = canSeeRoleManagement();
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
        case EXPRESS_MANAGEMENT_TAB_ID:
          if (isShowExpressManagementTab) {
            initExpressManagementTab();
          }
          break;
        case ROLE_MANAGEMENT_TAB_ID:
          break;
        default:
          break;
      }
    }
    isTabChangeEventTriggered = true;
  }

  private void initApplicationTab() {
    invokeAdminSettingsComponentLogic("#{logic.initApplicationSettings}", new Object[] {});
  }

  private void initExpressManagementTab() {
    PrimeFacesUtils.executeScript("refreshExpressManagement()");
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
    List<Application> applicationList = Attrs.currentContext().getAttribute("#{data.applicationList}", List.class);

    Application selectedApp = applicationList.remove(fromIndex);
    applicationList.add(toIndex, selectedApp);

    for (int i = 0; i < applicationList.size(); i++) {
      applicationList.get(i).setMenuOrdinal(i);
    }

    invokeAdminSettingsComponentLogic("#{logic.onApplicationReorder}", new Object[] {applicationList, selectedApp});
  }

  private void invokeAdminSettingsComponentLogic(String methodName, Object[] param) {
    var adminSettingTabChange = new IvyComponentLogicCaller<String>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    adminSettingTabChange.invokeComponentLogic(componentId, methodName, param);
  }

  public static void updatePortalManagementMessages() {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/dialogclosinginformation/informMessage"),
        null);
    FacesContext.getCurrentInstance().addMessage(PORTAL_MESSAGE_INFO, message);
    PrimeFaces.current().ajax().update("portal-management-messages");
  }

  private boolean canSeeRoleManagement() {
    return PermissionUtils.hasPortalPermission(PortalPermission.ROLE_MANAGEMENT);
  }

  public String getDropdownItemlabel(Object item) {
    if (item instanceof Option) {
      return ((Option) item).translate();
    }
    return String.valueOf(item);
  }

  public boolean isShowExpressManagementTab() {
    return isShowExpressManagementTab;
  }

  public void setShowExpressManagementTab(boolean isShowExpressManagementTab) {
    this.isShowExpressManagementTab = isShowExpressManagementTab;
  }


  public boolean isShowRoleManagementTab() {
    return isShowRoleManagementTab;
  }

  public void setShowRoleManagementTab(boolean isShowRoleManagementTab) {
    this.isShowRoleManagementTab = isShowRoleManagementTab;
  }
}
