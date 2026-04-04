package com.axonivy.portal.menu.management;

import static com.axonivy.portal.menu.management.enums.MenuSource.CALLABLE;
import static com.axonivy.portal.menu.management.enums.MenuSource.CUSTOM_MENU_CONFIGURATION;
import static com.axonivy.portal.menu.management.enums.MenuSource.DASHBOARD;
import static com.axonivy.portal.menu.management.enums.MenuSource.STANDARD;
import static com.axonivy.portal.menu.management.enums.MenuSource.THIRD_PARTY_APP_CONFIGURATION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;
import com.axonivy.portal.menu.management.adapter.CustomMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.DashboardMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.ExternalLinkMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.StaticPageMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.ThirdPartyAppMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.service.CustomSubMenuItemService;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.DefaultDashboardUtils;

/**
 * Responsible for loading menu definitions from various sources including
 * default menus, callable processes, configuration variables, dashboards, and
 * third-party applications.
 */
public final class MenuLoader implements Serializable {

  private static final long serialVersionUID = 7858045545456575004L;

  public static List<PortalMenuItemDefinition> loadMenuDefinitions() {
    List<PortalMenuItemDefinition> menuDefinitions = new ArrayList<>();
    menuDefinitions.addAll(buildDefaultMenus());
    menuDefinitions.addAll(buildCallableMenus());
    menuDefinitions.addAll(buildConfigurationMenus());
    menuDefinitions.addAll(buildDashboardMenus());
    menuDefinitions.addAll(buildThirdPartyMenus());
    return menuDefinitions.stream().distinct().collect(Collectors.toList());
  }

  /**
   * Converts a CustomSubMenuItem to a PortalMenuItemDefinition.
   */
  private static PortalMenuItemDefinition convertToMenuDefinition(CustomSubMenuItem menu, MenuSource source) {
    // Silent migrate: Add ID to old menu which doesn't have the ID field
    CustomSubMenuItem migratedMenu = CustomSubMenuItemService.migrate(menu);
    if (menu.getIsExternalLink()) {
      return ExternalLinkMenuItemDefinitionAdapter.getInstance().toMenuDefinition(migratedMenu, source);
    } else if (menu.getMenuKind() == MenuKind.STATIC_PAGE) {
      return StaticPageMenuItemDefinitionAdapter.getInstance().toMenuDefinition(migratedMenu, source);
    } else {
      return CustomMenuItemDefinitionAdapter.getInstance().toMenuDefinition(migratedMenu, source);
    }
  }

  private static List<PortalMenuItemDefinition> buildCallableMenus() {
    List<PortalMenuItemDefinition> result = new ArrayList<>();
    Optional.ofNullable(CustomSubMenuItemService.loadFromSubProcess())
        .ifPresent(menus -> menus.forEach(menu -> result.add(convertToMenuDefinition(menu, CALLABLE))));
    return result;
  }

  private static List<PortalMenuItemDefinition> buildConfigurationMenus() {
    List<PortalMenuItemDefinition> result = new ArrayList<>();
    Optional.ofNullable(CustomSubMenuItemService.loadFromConfiguration()).ifPresent(menus -> menus
        .forEach(menu -> result.add(convertToMenuDefinition(menu, CUSTOM_MENU_CONFIGURATION))));
    return result;
  }

  private static List<PortalMenuItemDefinition> buildDashboardMenus() {
    List<PortalMenuItemDefinition> result = new ArrayList<>();
    Optional.ofNullable(DashboardService.getInstance().loadTopMenuDashboards())
        .ifPresent(dashboards -> dashboards.forEach(dashboard -> result
            .add(DashboardMenuItemDefinitionAdapter.getInstance().toMenuDefinition(dashboard, DASHBOARD))));
    return result;
  }

  private static List<PortalMenuItemDefinition> buildThirdPartyMenus() {
    Function<Application, ExternalLinkMenuItemDefinition> appToMenu = app -> ThirdPartyAppMenuItemDefinitionAdapter
        .getInstance().toMenuDefinition(app, THIRD_PARTY_APP_CONFIGURATION);
    return RegisteredApplicationService.getInstance().findAll().stream().map(appToMenu).collect(Collectors.toList());
  }

  private static List<PortalMenuItemDefinition> buildDefaultMenus() {
    return Arrays.asList(buildDefaultDashboardMenu(), buildDefaultProcessMenu(), buildDefaultTaskMenu(),
        buildDefaultCaseMenu());
  }

  private static PortalMenuItemDefinition buildDefaultDashboardMenu() {
    PortalMenuItemDefinition menu = new StandardMenuItemDefinition(StandardMenuItemDefinitionType.DASHBOARD);
    menu.setSource(STANDARD);
    menu.setIndex(0);
    return menu;
  }

  private static PortalMenuItemDefinition buildDefaultProcessMenu() {
    PortalMenuItemDefinition menu = new StandardMenuItemDefinition(StandardMenuItemDefinitionType.PROCESS);
    menu.setSource(STANDARD);
    menu.setIndex(1);
    return menu;
  }

  private static PortalMenuItemDefinition buildDefaultTaskMenu() {
    PortalMenuItemDefinition menu = DashboardMenuItemDefinitionAdapter.getInstance()
        .toMenuDefinition(DefaultDashboardUtils.getDefaultTaskListDashboard(), DASHBOARD);
    menu.setSource(STANDARD);
    menu.setIndex(2);
    return menu;
  }

  private static PortalMenuItemDefinition buildDefaultCaseMenu() {
    PortalMenuItemDefinition menu = DashboardMenuItemDefinitionAdapter.getInstance()
        .toMenuDefinition(DefaultDashboardUtils.getDefaultCaseListDashboard(), DASHBOARD);
    menu.setSource(STANDARD);
    menu.setIndex(3);
    return menu;
  }
}