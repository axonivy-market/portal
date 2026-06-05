package com.axonivy.portal.bean.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import com.axonivy.portal.menu.management.MenuRemovalHandler;
import com.axonivy.portal.menu.management.enums.MenuSource;

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
  private boolean sidebarSettingsSaved;

  @PostConstruct
  public void init() {
    menuDefinitions = MenuLoader.loadMenuDefinitions();
    menuDefinitions.forEach(this::initDisplayPermissions);
    String storedMode = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.SIDEBAR_MODE);
    sidebarMode = parseSidebarMode(storedMode);
  }

  private static SidebarMode parseSidebarMode(String storedMode) {
    if (StringUtils.isBlank(storedMode)) {
      return SidebarMode.HOVER;
    }
    try {
      return SidebarMode.valueOf(storedMode);
    } catch (IllegalArgumentException e) {
      return SidebarMode.HOVER;
    }
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
          .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, p -> p, (a, b) -> a))
          .values();

      displayedPermission = distinctPermissionDTOs.stream().map(SecurityMemberDTO::getDisplayName)
          .collect(Collectors.joining(Ivy.cms().co("/Labels/Comma")));
    }

    menu.setDisplayedPermission(displayedPermission);
  }

  public void onRowReorder(ReorderEvent event) {
    // PrimeFaces dataTable auto-reorders the backing list before the listener fires when
    // the value is a plain List. We just need to refresh indices and persist — doing our
    // own remove/add here would double-shuffle and corrupt the order.
    if (menuDefinitions == null) {
      return;
    }
    for (int i = 0; i < menuDefinitions.size(); i++) {
      menuDefinitions.get(i).setIndex(i);
    }
    MenuLoader.persistManifest(menuDefinitions);
  }

  public boolean hasAction(PortalMenuItemDefinition menuDefinition) {
    return !MenuKind.STANDARD.equals(menuDefinition.getType());
  }

  public int getLastIndex() {
    return menuDefinitions.isEmpty() ? 0 : menuDefinitions.size() - 1;
  }

  public void removeMenu() {
    MenuRemovalHandler.removeMenu(getSelectedMenuDefinition());
    // Loader auto-prunes the stale manifest entry on the next load.
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
    return switch (type) {
      case STANDARD -> "menu-type-standard";
      case DASHBOARD -> "menu-type-dashboard";
      case MAIN_DASHBOARD -> "menu-type-main-dashboard";
      case PROCESS -> "menu-type-process";
      case CUSTOM -> "menu-type-custom";
      case EXTERNAL_LINK -> "menu-type-external";
      case THIRD_PARTY -> "menu-type-third-party";
      case STATIC_PAGE -> "menu-type-static";
    };
  }

  public boolean isSidebarDisabled() {
    return SidebarMode.HIDDEN.equals(sidebarMode);
  }

  public void setSidebarDisabled(boolean disabled) {
    if (disabled) {
      sidebarMode = SidebarMode.HIDDEN;
    } else if (SidebarMode.HIDDEN.equals(sidebarMode)) {
      sidebarMode = SidebarMode.HOVER;
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

  public void saveSidebarMode() {
    GlobalSetting setting = GlobalSettingService.getInstance()
        .findGlobalSettingByGlobalVariable(GlobalVariable.SIDEBAR_MODE);
    setting.setValue(sidebarMode != null ? sidebarMode.name() : SidebarMode.HOVER.name());
    GlobalSettingService.getInstance().save(setting);
    sidebarSettingsSaved = true;
  }

  public boolean isSidebarSettingsSaved() {
    return sidebarSettingsSaved;
  }

  public void setSidebarSettingsSaved(boolean sidebarSettingsSaved) {
    this.sidebarSettingsSaved = sidebarSettingsSaved;
  }
}
