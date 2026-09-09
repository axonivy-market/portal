package ch.ivy.addon.portalkit.service;

import java.util.List;


import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.PortalVariable;

public class DashboardService extends JsonConfigurationService<Dashboard> {

  private static DashboardService instance;

  public static DashboardService getInstance() {
    if (instance == null) {
      instance = new DashboardService();
    }
    return instance;
  }

  @Override
  public Class<Dashboard> getType() {
    return Dashboard.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.DASHBOARD.key;
  }

  public Dashboard saveDefaultDashboardAsFirstDashboard(Dashboard defaultDashboard) {
    List<Dashboard> entities = getPublicConfig();
    // Versioning now lives on the export wrapper container, not on individual entities.
    entities.addFirst(defaultDashboard);
    savePublicConfig(entities);
    return defaultDashboard;
  }

}
