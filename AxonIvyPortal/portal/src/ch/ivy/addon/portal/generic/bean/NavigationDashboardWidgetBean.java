package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;

@SessionScoped
@ManagedBean
public class NavigationDashboardWidgetBean implements Serializable {
  private static final String DEFAULT_DASHBOARD_NAME = "Dashboard";
  private static final long serialVersionUID = -4224901891867040688L;
  private MenuModel model = new DefaultMenuModel();
  private DefaultSubMenu submenu =
      DefaultSubMenu.builder().label("Navigation Dashboard Breadcrumb").expanded(true).build();
  private Map<String, String> dashboardIdAndNameList;
  
  @PostConstruct
  public void init() {
    this.dashboardIdAndNameList = DashboardUtils.getPublicDashboards().stream()
        .collect(Collectors.toMap(
            dashboard -> dashboard.getId(), 
            dashboard -> dashboard.getTitle()
        ));
  }

  public void buildBreadcrumb(NavigationDashboardWidget widget, Dashboard currentDashboard) {
    int currentIndex = findDashboardIndexInPath(currentDashboard.getId());
    int targetIndex = findDashboardIndexInPath(widget.getTargetDashboardId());

    model.getElements().clear();

    if (targetIndex >= 0) {
      if (targetIndex == 0) {
        removeNavigationDashboardBreadcrumb();
        return;
      }

      List<MenuElement> newList = new ArrayList<>();
      for (int i = 0; i <= targetIndex; i++) {
        if (i < submenu.getElements().size()) {
          newList.add(submenu.getElements().get(i));
        }
      }

      submenu.setElements(newList);
      model.getElements().addAll(submenu.getElements());
    } else {

      if (currentIndex == -1) {
        DefaultMenuItem item = DefaultMenuItem.builder().id(currentDashboard.getId()).value(currentDashboard.getTitle())
            .command(String.format("#{navigationDashboardWidgetBean.navigateToDashboardWhenClickingOnElement('%s')}",
                currentDashboard.getId()))
            .build();

        submenu.getElements().add(item);
      }

      DefaultMenuItem item = DefaultMenuItem.builder().id(widget.getTargetDashboardId()).value(widget.getTargetDashboardName())
          .command(String.format("#{navigationDashboardWidgetBean.navigateToDashboardWhenClickingOnElement('%s')}",
              widget.getTargetDashboardId()))
          .build();

      submenu.getElements().add(item);
      model.getElements().addAll(submenu.getElements());
    }
  }

  public void rebuildBreadcrumbWhenClickingOnElement(String elementId) {
    int index = findDashboardIndexInPath(elementId);
    if (index == 0) {
      removeNavigationDashboardBreadcrumb();
      return;
    }
    List<MenuElement> newList = new ArrayList<>();
    for (int i = 0; i <= index; i++) {
      if (i < submenu.getElements().size()) {
        newList.add(submenu.getElements().get(i));
      }
    }
    submenu.setElements(newList);
    model.getElements().clear();
    model.getElements().addAll(newList);
  }

  public void removeNavigationDashboardBreadcrumb() {
    submenu.getElements().clear();
    model.getElements().clear();
  }

  public void redirectToDashboard(NavigationDashboardWidget widget, Dashboard currentDashboard) throws IOException {
    buildBreadcrumb(widget, currentDashboard);
    navigateToDashboard(widget.getTargetDashboardId());
  }

  public void navigateToDashboardWhenClickingOnElement(String elementId) throws IOException {
    rebuildBreadcrumbWhenClickingOnElement(elementId);
    navigateToDashboard(elementId);
  }

  public void navigateToDashboard(String id) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(getDashboardUrlByDashboard(id));
  }

  private String getDashboardUrlByDashboard(String id) {
    return UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(id);
  }

  private int findDashboardIndexInPath(String dashboardId) {
    List<MenuElement> menuItemList = model.getElements();
    for (int i = 0; i < menuItemList.size(); i++) {
      if (menuItemList.get(i).getId().equals(dashboardId)) {
        return i;
      }
    }
    return -1;
  }

  public MenuModel getModel() {
    return this.model;
  }
  
  public void updateTargetDashboardName(NavigationDashboardWidget widget) {
    String dashboardName = this.dashboardIdAndNameList.getOrDefault(widget.getTargetDashboardId(), DEFAULT_DASHBOARD_NAME);
    widget.setTargetDashboardName(dashboardName);
  }
  
  
  
  public Boolean isNotClickable(NavigationDashboardWidget widget, Boolean isReadOnlyMode) {
    if (!isReadOnlyMode) {
        return true;
    }
    return !DashboardUtils.collectDashboardIds().contains(widget.getTargetDashboardId());
}
}
