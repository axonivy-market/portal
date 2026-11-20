package com.axonivy.portal.dto.jsondefinition;

import java.util.List;

import com.axonivy.portal.enums.PortalSchema;

import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;

public class PortalDashboardTemplate extends PortalJsonDefinition<DashboardTemplate> {

  public PortalDashboardTemplate() {}

  public PortalDashboardTemplate(List<DashboardTemplate> items) {
    super(PortalSchema.DASHBOARD_TEMPLATES, items);
  }
}
