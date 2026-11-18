package com.axonivy.portal.dto.jsondefinition;

import java.util.List;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;

public class PortalDashboard extends PortalJsonDefinition<Dashboard> {
  public PortalDashboard(List<Dashboard> items) {
    super("portal-dashboard-schema.json", items);
  }
}
