package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.FacesMessageUtils;
import com.axonivy.portal.components.util.RoleUtils;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.enums.GlobalVariable.Option;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PrimeFacesUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
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
  private List<String> selectedApplicationPermissions;

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

  private void initApplicationTab() {
    invokeAdminSettingsComponentLogic("#{logic.initApplicationSettings}", new Object[] {});
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
    FacesMessage message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_WARN,
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/dialogclosinginformation/informMessage"),
        null);
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

  public void initApplicationPermissions(Application application) {
    if (application == null) {
      this.selectedApplicationPermissions = new ArrayList<>();
      return;
    }
    
    application.setPermissionDTOs(Optional.ofNullable(application)
        .map(Application::getPermissions).orElse(new ArrayList<>()).stream()
        .filter(Objects::nonNull).distinct()
        .map(permission -> findSecurityMemberDtoByName(permission))
        .collect(Collectors.toList()));

    this.selectedApplicationPermissions = Optional.ofNullable(application)
        .map(Application::getPermissionDTOs).orElse(new ArrayList<>()).stream()
        .map(SecurityMemberDTO::getName).collect(Collectors.toList());
  }

  private SecurityMemberDTO findSecurityMemberDtoByName(String permission) {
    return permission.startsWith("#")
        ? new SecurityMemberDTO(UserUtils.findUserByUsername(permission))
        : new SecurityMemberDTO(RoleUtils.findRole(permission));
  }
  public List<SecurityMemberDTO> completeApplicationPermissions(String query) {
    if (this.selectedApplicationPermissions == null) {
      this.selectedApplicationPermissions = new ArrayList<>();
    }
    return RoleUtils.findRoles(null, selectedApplicationPermissions, query).stream()
        .map(SecurityMemberDTOMapper::mapFromRoleDTO).collect(Collectors.toList());
  }
  public void onSelectPermissionForApplication(SelectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    if (this.selectedApplicationPermissions == null) {
      this.selectedApplicationPermissions = new ArrayList<>();
    }
    this.selectedApplicationPermissions.add(selectedItem.getName());
  }

  public void onUnSelectPermissionForApplication(UnselectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    if (this.selectedApplicationPermissions == null) {
      this.selectedApplicationPermissions = new ArrayList<>();
    }
    this.selectedApplicationPermissions.remove(selectedItem.getName());
  }
  public void updateApplicationPermissions(Application application) {
    if (application == null) {
      return;
    }
    
    List<SecurityMemberDTO> responsibles = application.getPermissionDTOs();
    List<String> permissions = new ArrayList<>();
    String displayedPermission = "";
    
    if (CollectionUtils.isNotEmpty(responsibles)) {
      Collection<SecurityMemberDTO> distinctPermissionDTOs = responsibles.stream()
          .collect(Collectors
              .toMap(SecurityMemberDTO::getMemberName, responsible -> responsible, (responsible1, responsible2) -> responsible1)).values();
      responsibles.clear();
      responsibles.addAll(distinctPermissionDTOs);
      displayedPermission = responsibles.stream().map(SecurityMemberDTO::getDisplayName).collect(Collectors.joining(", "));
      permissions = responsibles.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    }
    application.setDisplayedPermission(displayedPermission);
    application.setPermissions(permissions);
    
    // Sync the selected permissions list with the application's permissions
    this.selectedApplicationPermissions = new ArrayList<>(permissions);
  }

  public List<String> getSelectedApplicationPermissions() {
    return selectedApplicationPermissions;
  }

  public void setSelectedApplicationPermissions(List<String> selectedApplicationPermissions) {
    this.selectedApplicationPermissions = selectedApplicationPermissions;
  }
}