package com.axonivy.portal.dto.jsondefinition;

import java.util.List;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;

public class PortalDashboard extends PortalJsonDefinition<Dashboard> {
  private static final String PORTAL_DASHBOARD_SCHEMA_FILE = "portal-dashboard-schema.json";

  public PortalDashboard(List<Dashboard> items) {
    super(PORTAL_DASHBOARD_SCHEMA_FILE, items);
  }
}
