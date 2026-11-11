package com.axonivy.portal.jsonschema.definition;

import java.util.List;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;

public class PortalDashboard extends PortalDefinition<Dashboard> {
  public PortalDashboard(List<Dashboard> items) {
    super("portal-dashboard-schema.json", items);
  }
}
