package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;


import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.util.UrlUtils;

@SessionScoped
@ManagedBean
public class NavigationDashboardWidgetBean implements Serializable {
  private static final long serialVersionUID = -4224901891867040688L;
  private MenuModel model;
  private DefaultSubMenu submenu;
  
  @PostConstruct
  public void buildBreadcrumb() {
    
    DashboardBean dashboardBean = ManagedBeans.get("dashboardBean");
    String dashboardName = Optional.ofNullable(dashboardBean).map(DashboardBean::getSelectedDashboardName).orElse(null);
    String dashboardId = Optional.ofNullable(dashboardBean).map(DashboardBean::getSelectedDashboardId).orElse(null);
    String link = getDashboardUrlByDashboard(dashboardId);
    model = new DefaultMenuModel();

    this.submenu = DefaultSubMenu.builder().label("Dashboard Breadcrumb").expanded(true).build();

    if (dashboardName != null && dashboardId != null) {
      var item = DefaultMenuItem.builder().value(dashboardName).url(link).build();
      submenu.getElements().add(item);
      model.getElements().addAll(submenu.getElements());
      dashboardBean.getSelectedDashboard().setIsContainNavigationDashboardWidget(true);
    }
  }

  
  private void buildNextMenuElement(NavigationDashboardWidget widget) {
    
    String dashboardName = widget.getDashboardNameById(widget.getTargetDashboard());
    String link = getDashboardUrlByDashboard(widget.getTargetDashboard());
    
    DefaultMenuItem item = DefaultMenuItem.builder()
        .value(dashboardName)
        .url(link)
        .build();
    
    submenu.getElements().add(item);
    model.getElements().add(submenu.getElements().getLast());
  }
  
  public void cleanSubitemList() {
    model.getElements().clear();
  }
  
  public void redirectToDashboard(NavigationDashboardWidget widget) throws IOException {
    String link = getDashboardUrlByDashboard(widget.getTargetDashboard());
    widget.setTargetDashboardName(widget.getDashboardNameById(widget.getTargetDashboard()));
    buildNextMenuElement(widget);
    FacesContext.getCurrentInstance().getExternalContext().redirect(link);
  }
  
  private String getDashboardUrlByDashboard(String id) {
    return UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(id);
  }
  
  public MenuModel getModel() {
    return this.model;
  }
  
}


