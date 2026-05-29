package com.axonivy.portal.dto.menu;

import com.axonivy.portal.components.enums.MenuKind;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;

public class DashboardMenuItemDefinition extends PortalMenuItemDefinition {

  private static final long serialVersionUID = 3638152027138711358L;

  private String dashboardId;
  private Dashboard dashboard;

  @Override
  public MenuKind getType() {
    return MenuKind.MAIN_DASHBOARD;
  }

  public String getDashboardId() {
    return dashboardId;
  }

  public void setDashboardId(String dashboardId) {
    this.dashboardId = dashboardId;
  }

  public Dashboard getDashboard() {
    return dashboard;
  }

  public void setDashboard(Dashboard dashboard) {
    this.dashboard = dashboard;
  }
}
