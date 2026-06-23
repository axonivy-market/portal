package com.axonivy.portal.menu.management;

import static com.axonivy.portal.menu.management.enums.MenuSource.CUSTOM_MENU_CONFIGURATION;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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
  }

  private static void removeDashboardMenu(PortalMenuItemDefinition menu) {
    DashboardMenuItemDefinition dashboardMenu = (DashboardMenuItemDefinition) menu;
    Dashboard dashboardToSave = DashboardService.getInstance().findById(dashboardMenu.getDashboard().getId());
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
    RegisteredApplicationService service = RegisteredApplicationService.getInstance();
    if (StringUtils.isNotBlank(menu.getId())) {
      service.delete(menu.getId());
      return;
    }
    String locale = Locales.getCurrentLocale().toLanguageTag();
    String menuTitle = menu.getDisplayTitle();
    if (StringUtils.isBlank(menuTitle)) {
      return;
    }
    service.findAll().stream()
        .filter(app -> menuTitle.equals(displayNameInLocale(app, locale)))
        .findFirst()
        .ifPresent(app -> service.delete(app.getId()));
  }

  private static String displayNameInLocale(Application app, String locale) {
    Map<String, String> displayNames = DisplayNameConvertor.parseJson(app.getDisplayName()).getDisplayNameAsMap();
    return displayNames.get(locale);
  }

  private static void removeStaticPageMenu(PortalMenuItemDefinition menu) {
    StaticPageMenuItemDefinition staticPageMenu = (StaticPageMenuItemDefinition) menu;
    if (staticPageMenu.getSource() == CUSTOM_MENU_CONFIGURATION) {
      CustomSubMenuItem menuItem = StaticPageMenuItemDefinitionAdapter.getInstance().toSource(staticPageMenu);
      CustomSubMenuItemService.removeConfiguration(menuItem);
    }
  }}