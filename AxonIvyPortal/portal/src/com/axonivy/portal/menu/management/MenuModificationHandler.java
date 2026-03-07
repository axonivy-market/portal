package com.axonivy.portal.menu.management;

import static com.axonivy.portal.menu.management.enums.MenuSource.CUSTOM_MENU_CONFIGURATION;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.dto.menu.CustomMenuItemDefinition;
import com.axonivy.portal.dto.menu.DashboardMenuItemDefinition;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StaticPageMenuItemDefinition;
import com.axonivy.portal.menu.management.adapter.CustomMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.DashboardMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.ExternalLinkMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.StaticPageMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.adapter.ThirdPartyAppMenuItemDefinitionAdapter;
import com.axonivy.portal.service.CustomSubMenuItemService;
import com.axonivy.portal.service.PortalMenuItemDefinitionService;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.DashboardUtils;

public final class MenuModificationHandler implements Serializable {

  private static final long serialVersionUID = 4323466217181783876L;
  
  public static void updateMenu(PortalMenuItemDefinition menu) {
    switch (menu.getType()) {
      case MAIN_DASHBOARD -> updateDashboardMenu(menu);
      case CUSTOM -> updateCustomMenu(menu);
      case EXTERNAL_LINK -> updateExternalLink(menu);
      case STATIC_PAGE -> updateStaticPageMenu(menu);
      default -> {}
    }

    PortalMenuItemDefinitionService.getInstance().save(menu);
  }

  private static void updateDashboardMenu(PortalMenuItemDefinition menu) {
    if (menu instanceof DashboardMenuItemDefinition) {
      Dashboard dashboard = DashboardMenuItemDefinitionAdapter.getInstance()
          .toSource((DashboardMenuItemDefinition) menu);
      DashboardService.getInstance().saveAllPublicConfig(Arrays.asList(dashboard));
      DashboardUtils.updateDashboardCache();
    }
  }

  private static void updateCustomMenu(PortalMenuItemDefinition menu) {
    if (menu instanceof CustomMenuItemDefinition) {
      CustomMenuItemDefinition customMenu = (CustomMenuItemDefinition) menu;
      if (customMenu.getSource() == CUSTOM_MENU_CONFIGURATION) {
        CustomSubMenuItem menuItem = CustomMenuItemDefinitionAdapter.getInstance().toSource(customMenu);
        CustomSubMenuItemService.saveConfiguration(menuItem);
      }
    }
  }

  private static void updateExternalLink(PortalMenuItemDefinition menu) {
    if (menu instanceof ExternalLinkMenuItemDefinition) {
      ExternalLinkMenuItemDefinition external = (ExternalLinkMenuItemDefinition) menu;
      switch (menu.getSource()) {
        case CUSTOM_MENU_CONFIGURATION -> {
          CustomSubMenuItem menuItem = ExternalLinkMenuItemDefinitionAdapter.getInstance().toSource(external);
          CustomSubMenuItemService.saveConfiguration(menuItem);
        }
        case THIRD_PARTY_APP_CONFIGURATION -> {
          Application modifiedApp = ThirdPartyAppMenuItemDefinitionAdapter.getInstance().toSource(external);
          RegisteredApplicationService.getInstance().save(modifiedApp);
        }
        default -> {}
      }
    }
  }

  private static void updateStaticPageMenu(PortalMenuItemDefinition menu) {
    if (menu instanceof StaticPageMenuItemDefinition) {
      StaticPageMenuItemDefinition staticPageMenu = (StaticPageMenuItemDefinition) menu;
      if (staticPageMenu.getSource() == CUSTOM_MENU_CONFIGURATION) {
        CustomSubMenuItem menuItem = StaticPageMenuItemDefinitionAdapter.getInstance().toSource(staticPageMenu);
        CustomSubMenuItemService.saveConfiguration(menuItem);
      }
    }
  }

  public static void migrate(PortalMenuItemDefinition menu) {
    if (StringUtils.isBlank(menu.getId())) {
      menu.setId(DashboardUtils.generateId());
    }
    switch (menu.getType()) {
      case CUSTOM -> migrateCustomMenu(menu);
      case EXTERNAL_LINK -> migrateExternalLink(menu);
      case STATIC_PAGE -> migrateStaticPageMenu(menu);
      default -> {}
    }
  }

  private static void migrateCustomMenu(PortalMenuItemDefinition menu) {
    if (menu instanceof CustomMenuItemDefinition) {
      CustomMenuItemDefinition customMenu = (CustomMenuItemDefinition) menu;
      if (customMenu.getSource() == CUSTOM_MENU_CONFIGURATION) {
        CustomSubMenuItem menuItem = CustomMenuItemDefinitionAdapter.getInstance().toSource(customMenu);
        CustomSubMenuItemService.saveConfigurationUseLabel(menuItem);
      }
    }
  }

  private static void migrateExternalLink(PortalMenuItemDefinition menu) {
    if (menu instanceof ExternalLinkMenuItemDefinition) {
      ExternalLinkMenuItemDefinition external = (ExternalLinkMenuItemDefinition) menu;
      switch (menu.getSource()) {
        case CUSTOM_MENU_CONFIGURATION -> {
          CustomSubMenuItem menuItem = ExternalLinkMenuItemDefinitionAdapter.getInstance().toSource(external);
          CustomSubMenuItemService.saveConfigurationUseLabel(menuItem);
        }
        default -> {}
      }
    }
  }

  private static void migrateStaticPageMenu(PortalMenuItemDefinition menu) {
    if (menu instanceof StaticPageMenuItemDefinition) {
      StaticPageMenuItemDefinition staticPageMenu = (StaticPageMenuItemDefinition) menu;
      if (staticPageMenu.getSource() == CUSTOM_MENU_CONFIGURATION) {
        CustomSubMenuItem menuItem = StaticPageMenuItemDefinitionAdapter.getInstance().toSource(staticPageMenu);
        CustomSubMenuItemService.saveConfigurationUseLabel(menuItem);
      }
    }
  }
}
