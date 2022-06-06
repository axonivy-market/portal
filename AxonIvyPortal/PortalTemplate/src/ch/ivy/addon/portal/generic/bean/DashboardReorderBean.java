package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardOrder;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public abstract class DashboardReorderBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private List<DashboardOrder> dashboardOrders;

  public void initConfigration() {
    dashboardOrders = createDashboardOrders();
  }

  public List<DashboardOrder> getDashboardOrders() {
    return dashboardOrders;
  }

  public void setDashboardOrders(List<DashboardOrder> dashboardOrders) {
    this.dashboardOrders = dashboardOrders;
  }

  public void save() {
    saveArrangement();
    backToHome();
  }

  public abstract void saveArrangement();

  protected abstract List<DashboardOrder> createDashboardOrders();

  protected List<DashboardOrder> convertDashboardsToDashboardOrders(Collection<Dashboard> dashboards) {
    var orders = dashboards.stream().filter(Objects::nonNull).map(dashboard -> {
      DashboardOrder dashboardOrder = new DashboardOrder();
      dashboardOrder.setDashboardId(dashboard.getId());
      dashboardOrder.setIsPublicDashboard(dashboard.getIsPublic());
      dashboardOrder.setDashboardTitle(dashboard.getTitle());
      dashboardOrder.setVisible(true);
      return dashboardOrder;
    }).collect(Collectors.toList());
    return orders;
  }

  protected IUser currentUser() {
    return Ivy.session().getSessionUser();
  }

  public void backToHome() {
    PortalNavigator.navigateToDashboard();
  }

}

