package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.axonivy.portal.bo.jsonversion.DashboardFilterJsonVersion;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
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
    defaultDashboard.setVersion(DashboardFilterJsonVersion.LATEST_VERSION.getValue());
    entities.addFirst(defaultDashboard);
    savePublicConfig(entities);
    return defaultDashboard;
  }

  public List<Dashboard> loadTopMenuDashboards() {
    return Optional.ofNullable(getPublicConfig().stream()
        .filter(dashboard -> dashboard.getDashboardDisplayType() == DashboardDisplayType.TOP_MENU)
        .collect(Collectors.toList())).orElseGet(() -> new ArrayList<>());
  }
}
