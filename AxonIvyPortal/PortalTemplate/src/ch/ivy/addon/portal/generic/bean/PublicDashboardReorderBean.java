package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardOrder;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class PublicDashboardReorderBean extends DashboardReorderBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @PostConstruct
  public void initConfigration() {
    super.initConfigration();
  }

  @Override
  protected List<DashboardOrder> createDashboardOrders() {
    List<Dashboard> dashboards = DashboardUtils.getPublicDashboards();
    return convertDashboardsToDashboardOrders(dashboards);
  }

  @Override
  public void saveArrangement() {
    List<Dashboard> dashboards = DashboardUtils.getPublicDashboards();
    for (Dashboard dashboard : dashboards) {
      if (dashboard.getId() == null) {
        dashboard.setId(DashboardUtils.generateId());
      }
    }

    Map<String, Dashboard> idToDashboard = DashboardUtils.createMapIdToDashboard(dashboards);
    List<Dashboard> newDashboards = new ArrayList<>();
    for (DashboardOrder dashboardOrder : getDashboardOrders()) {
      if (idToDashboard.containsKey(dashboardOrder.getDashboardId())) {
        newDashboards.add(idToDashboard.remove(dashboardOrder.getDashboardId()));
      }
    }
    newDashboards.addAll(idToDashboard.values());
    String dashboardsAsSJSON = BusinessEntityConverter.entityToJsonValue(newDashboards);
   Ivy.var().set(PortalVariable.DASHBOARD.key, dashboardsAsSJSON);
  }

}

