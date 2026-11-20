package com.axonivy.portal.dto.jsondefinition;

import java.util.List;

import com.axonivy.portal.enums.PortalSchema;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;

public class PortalDashboard extends PortalJsonDefinition<Dashboard> {

  public PortalDashboard() {}

  public PortalDashboard(List<Dashboard> items) {
    super(PortalSchema.DASHBOARD, items);
  }
}
