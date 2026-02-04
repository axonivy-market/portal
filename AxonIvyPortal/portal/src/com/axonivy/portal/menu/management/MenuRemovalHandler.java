package com.axonivy.portal.menu.management;

import static com.axonivy.portal.menu.management.enums.MenuSource.CUSTOM_MENU_CONFIGURATION;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.components.util.Locales;
import com.axonivy.portal.dto.menu.CustomMenuItemDefinition;
import com.axonivy.portal.dto.menu.DashboardMenuItemDefinition;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StaticPageMenuItemDefinition;
import com.axonivy.portal.menu.management.adapter.CustomMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.ExternalLinkMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.StaticPageMenuItemDefinitionAdapter;
import com.axonivy.portal.service.CustomSubMenuItemService;
import com.axonivy.portal.service.PortalMenuItemDefinitionService;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;

public final class MenuRemovalHandler implements Serializable {

  private static final long serialVersionUID = 2297872352497985206L;

  public static void removeMenu(PortalMenuItemDefinition menu) {
    switch (menu.getType()) {
      case MAIN_DASHBOARD -> removeDashboardMenu(menu);
      case CUSTOM -> removeCustomMenu(menu);
      case EXTERNAL_LINK -> removeExternalLink(menu);
      case STATIC_PAGE -> removeStaticPageMenu(menu);
      default -> {}
    }
    PortalMenuItemDefinitionService.getInstance().delete(menu.getId());
  }

  private static void removeDashboardMenu(PortalMenuItemDefinition menu) {
    DashboardMenuItemDefinition dashboardMenu = (DashboardMenuItemDefinition) menu;
    Dashboard dashboardToSave = DashboardService.getInstance().findById(dashboardMenu.getDashboard().getId());
    // Don't remove dashboard from the variable, just set the display type to sub
    // menu
    dashboardToSave.setDashboardDisplayType(DashboardDisplayType.SUB_MENU);
    DashboardService.getInstance().saveAllPublicConfig(Arrays.asList(dashboardToSave));
    DashboardUtils.updateDashboardCache();
  }

  private static void removeCustomMenu(PortalMenuItemDefinition menu) {
    CustomMenuItemDefinition customMenu = (CustomMenuItemDefinition) menu;
    if (customMenu.getSource() == CUSTOM_MENU_CONFIGURATION) {
      CustomSubMenuItem menuItem = CustomMenuItemDefinitionAdapter.getInstance().toSource(customMenu);
      CustomSubMenuItemService.removeConfiguration(menuItem);
    }
  }

  private static void removeExternalLink(PortalMenuItemDefinition menu) {
    ExternalLinkMenuItemDefinition externalLinkMenu = (ExternalLinkMenuItemDefinition) menu;
    switch (externalLinkMenu.getSource()) {
      case CUSTOM_MENU_CONFIGURATION -> {
        CustomSubMenuItem menuItem = ExternalLinkMenuItemDefinitionAdapter.getInstance().toSource(externalLinkMenu);
        CustomSubMenuItemService.removeConfiguration(menuItem);
      }
      case THIRD_PARTY_APP_CONFIGURATION -> {
        removeThirdPartyApp(menu);
      }
      default -> {}
    }
  }

  private static void removeThirdPartyApp(PortalMenuItemDefinition menu) {
    List<Application> apps = RegisteredApplicationService.getInstance().findAll();
    String locale = Locales.getCurrentLocale().toLanguageTag();
    Predicate<Application> matchesMenuTitle = app -> {
      Map<String, String> displayNames = DisplayNameConvertor.parseJson(app.getDisplayName()).getDisplayNameAsMap();
      return menu.getDisplayTitle().equals(displayNames.get(locale));
    };
    apps.stream().filter(matchesMenuTitle).findFirst()
        .ifPresent(app -> RegisteredApplicationService.getInstance().delete(app.getId()));
  }

  private static void removeStaticPageMenu(PortalMenuItemDefinition menu) {
    StaticPageMenuItemDefinition staticPageMenu = (StaticPageMenuItemDefinition) menu;
    if (staticPageMenu.getSource() == CUSTOM_MENU_CONFIGURATION) {
      CustomSubMenuItem menuItem = StaticPageMenuItemDefinitionAdapter.getInstance().toSource(staticPageMenu);
      CustomSubMenuItemService.removeConfiguration(menuItem);
    }
  }}