package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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

@ViewScoped
@ManagedBean
public class MyDashboardReorderBean extends DashboardReorderBean implements Serializable {
  private static final long serialVersionUID = 1L;

  @Override
  @PostConstruct
  public void initConfigration() {
    super.initConfigration();
  }

  @Override
  public void saveArrangement() {
    String dashboardJson = BusinessEntityConverter.entityToJsonValue(getDashboardOrders());
    currentUser().setProperty(PortalVariable.DASHBOARD_ORDER.key, dashboardJson);
  }

  @Override
  protected List<DashboardOrder> createDashboardOrders() {
    List<Dashboard> visibleDashboards = DashboardUtils.getAllVisibleDashboardsOfSessionUser();
    Map<String, Dashboard> idToDashboard = DashboardUtils.createMapIdToDashboard(visibleDashboards);
    List<DashboardOrder> orders = DashboardUtils.getDashboardOrdersOfSessionUser();
    List<DashboardOrder> collectedOrders = new ArrayList<>();
    for (DashboardOrder order : orders) {
      if (idToDashboard.containsKey(order.getDashboardId())) {
        order.setDashboardTitle(idToDashboard.get(order.getDashboardId()).getTitle());
        collectedOrders.add(order);
        idToDashboard.remove(order.getDashboardId());
      }
    }

    Collection<Dashboard> dashboardsWithoutOrders = idToDashboard.values();
    var ordersNotConfigured = convertDashboardsToDashboardOrders(dashboardsWithoutOrders);

    collectedOrders.addAll(ordersNotConfigured);
    return collectedOrders;
  }

  public void handleActivation(DashboardOrder dashboardOrder) {
    dashboardOrder.setVisible(!dashboardOrder.isVisible());
  }
}

