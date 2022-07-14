package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class DashboardReorderBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private boolean isPublicDashboard;
  private List<DashboardOrder> dashboardOrders;

  public void initConfigration(boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
    if (isPublicDashboard) {
      List<Dashboard> dashboards = DashboardUtils.getPublicDashboards();
      this.dashboardOrders = convertDashboardsToDashboardOrders(dashboards);
    } else {
      this.dashboardOrders = createMyDashboardOrders();
    }
  }

  public void save() {
    if (isPublicDashboard) {
      saveArrangement();
    } else {
      saveMyArrangement();
    }
  }

  private List<DashboardOrder> convertDashboardsToDashboardOrders(Collection<Dashboard> dashboards) {
    var orders = dashboards.stream().filter(Objects::nonNull)
        .map(DashboardOrder::new).collect(Collectors.toList());
    return orders;
  }

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

  public void saveMyArrangement() {
    String dashboardJson = BusinessEntityConverter.entityToJsonValue(getDashboardOrders());
    Ivy.session().getSessionUser().setProperty(PortalVariable.DASHBOARD_ORDER.key, dashboardJson);
  }

  private List<DashboardOrder> createMyDashboardOrders() {
    List<Dashboard> visibleDashboards = DashboardUtils.getAllVisibleDashboardsOfSessionUser();
    Map<String, Dashboard> dashboardByIdMap = DashboardUtils.createMapIdToDashboard(visibleDashboards);
    List<DashboardOrder> orders = DashboardUtils.getDashboardOrdersOfSessionUser();
    List<DashboardOrder> collectedOrders = new ArrayList<>();
    for (DashboardOrder order : orders) {
      if (dashboardByIdMap.containsKey(order.getDashboardId())) {
        order.setDashboardTitle(dashboardByIdMap.get(order.getDashboardId()).getTitle());
        collectedOrders.add(order);
        dashboardByIdMap.remove(order.getDashboardId());
      }
    }

    Collection<Dashboard> dashboardsWithoutOrders = dashboardByIdMap.values();
    var ordersNotConfigured = convertDashboardsToDashboardOrders(dashboardsWithoutOrders);
    collectedOrders.addAll(ordersNotConfigured);
    return collectedOrders;
  }

  public void handleActivation(DashboardOrder dashboardOrder) {
    dashboardOrder.setVisible(!dashboardOrder.isVisible());
  }

  public List<DashboardOrder> getDashboardOrders() {
    return dashboardOrders;
  }

  public void setDashboardOrders(List<DashboardOrder> dashboardOrders) {
    this.dashboardOrders = dashboardOrders;
  }
}
