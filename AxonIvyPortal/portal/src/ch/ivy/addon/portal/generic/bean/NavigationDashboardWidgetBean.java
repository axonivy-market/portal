package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;


import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.util.UrlUtils;

@ViewScoped
@ManagedBean
public class NavigationDashboardWidgetBean implements Serializable {
  private static final long serialVersionUID = -4224901891867040688L;
  private MenuModel model;
  
  @PostConstruct
  public void buildBreadcrumb() {
    DashboardBean dashboardBean = ManagedBeans.get("dashboardBean");
    String dashboardName = Optional.ofNullable(dashboardBean).map(DashboardBean::getSelectedDashboardName).orElse(null);
    String dashboardId = Optional.ofNullable(dashboardBean).map(DashboardBean::getSelectedDashboardId).orElse(null);
    model = new DefaultMenuModel();

    var item = DefaultMenuItem.builder()
            .value(dashboardName)
            .url(getDashboardUrlByDashboard(dashboardId))
            .build();

    model.getElements().add(item);
  }
  
  public void buildNextSubitem(NavigationDashboardWidget widget, Dashboard dashboard) {
    var item = buildMenuElement(dashboard);
    model.getElements().add(item);
    item = buildMenuElement(widget.getTargetDashboard());
    model.getElements().add(item);

  }

  private DefaultMenuItem buildMenuElement(Dashboard dashboard) {
    String link = getDashboardUrlByDashboard(dashboard.getId());
    var item = DefaultMenuItem.builder()
        .id(dashboard.getId())
        .value(dashboard.getTitle())
        .url(link)
        .build();
    return item;
  }
  
  public void cleanSubitemList() {
    model.getElements().clear();
  }
  
  public void redirectMethod(NavigationDashboardWidget widget, Dashboard firstDashboard) throws IOException {
    String link = getDashboardUrlByDashboard(widget.getTargetDashboard().getId());
    buildNextSubitem(widget, firstDashboard);
    FacesContext.getCurrentInstance().getExternalContext().redirect(link);
  }
  
  private String getDashboardUrlByDashboard(String id) {
    return UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(id);
  }
  
  public MenuModel getModel() {
    return this.model;
  }
  
  
}


