package com.axonivy.portal.menu.management;

import java.io.Serializable;
import java.util.Arrays;

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

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.util.DashboardUtils;

public final class MenuCreationHandler implements Serializable {

  private static final long serialVersionUID = -4923237321282831103L;

  public static void createMenu(PortalMenuItemDefinition menu) {
    menu.setId(DashboardUtils.generateId());
    switch (menu.getType()) {
      case MAIN_DASHBOARD -> createDashboardMenu(menu);
      case CUSTOM -> createCustomMenu(menu);
      case EXTERNAL_LINK -> createExternalLinkMenu(menu);
      case STATIC_PAGE -> createStaticPageMenu(menu);
      default -> {}
    }
    PortalMenuItemDefinitionService.getInstance().save(menu);
  }

  private static void createDashboardMenu(PortalMenuItemDefinition menu) {
    DashboardMenuItemDefinition dashboardMenu = (DashboardMenuItemDefinition) menu;
    Dashboard dashboard = DashboardService.getInstance().findById(dashboardMenu.getDashboard().getId());
    dashboard.setDashboardDisplayType(DashboardDisplayType.TOP_MENU);
    DashboardService.getInstance().saveAllPublicConfig(Arrays.asList(dashboard));
    DashboardUtils.updateDashboardCache();
  }

  private static void createCustomMenu(PortalMenuItemDefinition menu) {
    if (menu instanceof CustomMenuItemDefinition) {
      CustomMenuItemDefinition customMenu = (CustomMenuItemDefinition) menu;
      customMenu.setIncludedIconFamily(true);
      CustomSubMenuItemService
          .saveConfiguration(CustomMenuItemDefinitionAdapter.getInstance().toSource(customMenu));
    }
  }

  private static void createExternalLinkMenu(PortalMenuItemDefinition menu) {
    if (menu instanceof ExternalLinkMenuItemDefinition) {
      ExternalLinkMenuItemDefinition externalMenu = (ExternalLinkMenuItemDefinition) menu;
      externalMenu.setIncludedIconFamily(true);
      CustomSubMenuItemService
          .saveConfiguration(ExternalLinkMenuItemDefinitionAdapter.getInstance().toSource(externalMenu));
    }
  }

  private static void createStaticPageMenu(PortalMenuItemDefinition menu) {
    if (menu instanceof StaticPageMenuItemDefinition) {
      StaticPageMenuItemDefinition staticMenu = (StaticPageMenuItemDefinition) menu;
      CustomSubMenuItemService
          .saveConfiguration(StaticPageMenuItemDefinitionAdapter.getInstance().toSource(staticMenu));
    }
  }
}