package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  private static final long serialVersionUID = -4224901891867040688L;
  private MenuModel model = new DefaultMenuModel();
  private DefaultSubMenu submenu =
      DefaultSubMenu.builder().label("Navigation Dashboard Breadcrumb").expanded(true).build();

  public void buildBreadcrumb(NavigationDashboardWidget widget, Dashboard currentDashboard) {
    int currentIndex = findDashboardIndexInPath(currentDashboard.getId());
    int targetIndex = findDashboardIndexInPath(widget.getTargetDashboardId());

    model.getElements().clear();

    if (targetIndex >= 0) {

      List<MenuElement> newList = new ArrayList<>();
      for (int i = 0; i <= targetIndex; i++) {
        if (i < submenu.getElements().size()) {
          newList.add(submenu.getElements().get(i));
        }
      }

      // Update submenu elements
      submenu.setElements(newList);

      // Add all elements to model
      model.getElements().addAll(submenu.getElements());
    } else {

      if (currentIndex == -1) {
        // Current dashboard not in list
        DefaultMenuItem item =
            DefaultMenuItem.builder().id(currentDashboard.getId()).value(currentDashboard.getTitle()).build();

        String onCommandScript = String
            .format("#{navigationDashboardWidgetBean.navigateToDashboardWhenClickingOnElement('%s')}", item.getId());
        item.setCommand(onCommandScript);

        submenu.getElements().add(item);
      }

      // Add target dashboard
      widget.setTargetDashboardName(widget.getDashboardNameById(widget.getTargetDashboardId()));
      var item =
          DefaultMenuItem.builder().id(widget.getTargetDashboardId()).value(widget.getTargetDashboardName()).build();

      String onCommandScript = String
          .format("#{navigationDashboardWidgetBean.navigateToDashboardWhenClickingOnElement('%s')}", item.getId());
      item.setCommand(onCommandScript);

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
  
  public Boolean isNotClickable(NavigationDashboardWidget widget, Boolean isReadOnlyMode) {
    List<String> dashboardIdList = DashboardUtils.collectDashboards().stream().map(dashboard -> dashboard.getId()).collect(Collectors.toList());
    if (isReadOnlyMode) {
      return !dashboardIdList.contains(widget.getTargetDashboardId());
    }
    return true;
  }
}
