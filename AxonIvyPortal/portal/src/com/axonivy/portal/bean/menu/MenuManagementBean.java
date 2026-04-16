package com.axonivy.portal.bean.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.ReorderEvent;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.enums.SidebarMode;
import com.axonivy.portal.menu.management.MenuLoader;
import com.axonivy.portal.menu.management.MenuOrderManager;
import com.axonivy.portal.menu.management.MenuRemovalHandler;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.service.PortalMenuItemDefinitionService;

import ch.ivy.addon.portalkit.configuration.GlobalSetting;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class MenuManagementBean extends AbstractMenuBean implements Serializable {

  private static final long serialVersionUID = -8511047442254110702L;

  private List<PortalMenuItemDefinition> menuDefinitions;
  private PortalMenuItemDefinition selectedMenuDefinition;
  private SidebarMode sidebarMode;

  @PostConstruct
  public void init() {
    menuDefinitions = MenuLoader.loadMenuDefinitions();
    menuDefinitions.forEach(menu -> initDisplayPermissions(menu));
    boolean hasNewItems = MenuOrderManager.initOrder(menuDefinitions);
    if (hasNewItems) {
      PortalMenuItemDefinitionService.getInstance().saveAllPublicConfig(menuDefinitions);
    }
    String storedMode = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.SIDEBAR_MODE);
    sidebarMode = StringUtils.isNotBlank(storedMode) ? SidebarMode.valueOf(storedMode) : SidebarMode.HOVER;
  }

  public void initDisplayPermissions(PortalMenuItemDefinition menu) {
    initMenuPermissionDtos(menu);

    if (CollectionUtils.isEmpty(menu.getPermissionDTOs())) {
      return;
    }

    List<SecurityMemberDTO> permissions = Optional.ofNullable(menu.getPermissionDTOs())
        .orElse(new ArrayList<>());
    String displayedPermission = StringUtils.EMPTY;

    if (CollectionUtils.isNotEmpty(permissions)) {
      Collection<SecurityMemberDTO> distinctPermissionDTOs = permissions.stream()
          .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, responsible -> responsible,
              (responsible1, responsible2) -> responsible1))
          .values();

      displayedPermission = distinctPermissionDTOs.stream().map(SecurityMemberDTO::getDisplayName)
          .collect(Collectors.joining(Ivy.cms().co("/Labels/Comma")));
    }

    menu.setDisplayedPermission(displayedPermission);
  }

  public void onRowReorder(ReorderEvent event) {
    MenuOrderManager.reorderMenu(menuDefinitions, event.getFromIndex(), event.getToIndex());
    PortalMenuItemDefinitionService.getInstance().saveAllPublicConfig(menuDefinitions);
  }

  public boolean hasAction(PortalMenuItemDefinition menuDefinition) {
    return !MenuKind.STANDARD.equals(menuDefinition.getType());
  }
  
  public int getLastIndex() {
    return menuDefinitions.stream().map(PortalMenuItemDefinition::getIndex).sorted(Comparator.reverseOrder())
        .findFirst().orElse(1);
  }

  public void removeMenu() {
    MenuRemovalHandler.removeMenu(getSelectedMenuDefinition());
    init();
  }

  public List<PortalMenuItemDefinition> getMenuDefinitions() {
    return menuDefinitions;
  }

  public void setMenuDefinitions(List<PortalMenuItemDefinition> menuDefinitions) {
    this.menuDefinitions = menuDefinitions;
  }
  
  public PortalMenuItemDefinition getSelectedMenuDefinition() {
    return selectedMenuDefinition;
  }

  public void setSelectedMenuDefinition(PortalMenuItemDefinition selectedMenuDefinition) {
    this.selectedMenuDefinition = selectedMenuDefinition;
  }

  public String getMenuLinksTo(PortalMenuItemDefinition menu) {
    if (menu == null) {
      return "-";
    }
    if (StringUtils.isNotBlank(menu.getUrl())) {
      return menu.getUrl();
    }
    return StringUtils.defaultIfBlank(menu.getDescription(), "-");
  }

  public MenuKind resolveMenuKind(PortalMenuItemDefinition menu) {
    if (menu == null) {
      return MenuKind.STANDARD;
    }
    if (MenuSource.THIRD_PARTY_APP_CONFIGURATION.equals(menu.getSource())) {
      return MenuKind.THIRD_PARTY;
    }
    return menu.getType();
  }

  public String getMenuTypeClass(PortalMenuItemDefinition menu) {
    return getMenuTypeClassByKind(resolveMenuKind(menu));
  }

  private String getMenuTypeClassByKind(MenuKind type) {
    if (type == null) {
      return "menu-type-standard";
    }

    switch (type) {
      case STANDARD:
        return "menu-type-standard";
      case DASHBOARD:
        return "menu-type-dashboard";
      case MAIN_DASHBOARD:
        return "menu-type-main-dashboard";
      case PROCESS:
        return "menu-type-process";
      case CUSTOM:
        return "menu-type-custom";
      case EXTERNAL_LINK:
        return "menu-type-external";
      case THIRD_PARTY:
        return "menu-type-third-party";
      case STATIC_PAGE:
        return "menu-type-static";
      default:
        return "menu-type-standard";
    }
  }

  public boolean isSidebarDisabled() {
    return SidebarMode.HIDDEN.equals(sidebarMode);
  }

  public void setSidebarDisabled(boolean disabled) {
    if (disabled) {
      sidebarMode = SidebarMode.HIDDEN;
    } else {
      // restore to HOVER if coming back from HIDDEN
      if (SidebarMode.HIDDEN.equals(sidebarMode)) {
        sidebarMode = SidebarMode.HOVER;
      }
    }
  }

  public SidebarMode getSidebarMode() {
    return sidebarMode;
  }

  public void setSidebarMode(SidebarMode sidebarMode) {
    this.sidebarMode = sidebarMode;
  }

  public SidebarMode[] getSidebarBehaviourModes() {
    return new SidebarMode[] { SidebarMode.HOVER, SidebarMode.CLICK, SidebarMode.STICK };
  }

  public SidebarMode[] getSidebarModes() {
    return SidebarMode.values();
  }

  public void saveSidebarMode() {
    GlobalSetting setting = GlobalSettingService.getInstance()
        .findGlobalSettingByGlobalVariable(GlobalVariable.SIDEBAR_MODE);
    setting.setValue(sidebarMode != null ? sidebarMode.name() : SidebarMode.HOVER.name());
    GlobalSettingService.getInstance().save(setting);
  }
}