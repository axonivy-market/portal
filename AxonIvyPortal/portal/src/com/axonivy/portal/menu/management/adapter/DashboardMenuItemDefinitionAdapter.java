package com.axonivy.portal.menu.management.adapter;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.menu.DashboardMenuItemDefinition;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.util.MenuUtils;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.util.DashboardUtils;

public class DashboardMenuItemDefinitionAdapter
    implements IMenuItemDefinitionAdapter<DashboardMenuItemDefinition, Dashboard> {

  private static final DashboardMenuItemDefinitionAdapter instance = new DashboardMenuItemDefinitionAdapter();

  public static DashboardMenuItemDefinitionAdapter getInstance() {
    return instance;
  }

  @Override
  public DashboardMenuItemDefinition toMenuDefinition(Dashboard source, MenuSource type) {
    DashboardMenuItemDefinition menu = new DashboardMenuItemDefinition();
    menu.setSource(MenuSource.DASHBOARD);
    menu.setId(source.getId());
    menu.setVersion(source.getVersion());
    menu.setDashboard(source);
    menu.setDashboardId(source.getId());
    menu.setDescription(source.getDescription());
    menu.setPermissions(source.getPermissions());
    menu.setTitles(source.getTitles());
    menu.setIncludedIconFamily(hasIconFamily(source.getIcon()));
    menu.setIcon(removeIconFamily(source.getIcon()));
    menu.setDisplayTitle(MenuUtils.getDisplayTitle(menu));
    return menu;
  }

  @Override
  public Dashboard toSource(DashboardMenuItemDefinition menu) {
    if (menu == null || StringUtils.isBlank(menu.getId())) {
      return null;
    }
    Dashboard source = DashboardUtils.getPublicDashboards().stream()
        .filter(dashboard -> dashboard.getId().equals(menu.getId())).findFirst().orElse(null);

    if (source != null) {
      source.setPermissions(menu.getPermissions());
      source.setTitles(menu.getTitles());
      source.setIcon(menu.isIncludedIconFamily() ? addIconFamily(menu.getIcon()) : menu.getIcon());
      return source;
    }

    return null;
  }


}
