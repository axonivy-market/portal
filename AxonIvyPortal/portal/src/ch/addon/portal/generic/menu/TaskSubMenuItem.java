package ch.addon.portal.generic.menu;

import com.axonivy.portal.components.publicapi.ApplicationMultiLanguageAPI;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.util.DashboardUtils;

public class TaskSubMenuItem extends SubMenuItem {
  public TaskSubMenuItem() {
    Dashboard taskDashboard = DashboardUtils.getTaskTemplateDashboard();
    this.icon = taskDashboard.getIcon();
    this.menuKind = MenuKind.TASK;
    this.label = ApplicationMultiLanguageAPI.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/common/tasks");
    this.name = taskDashboard.getTitle();
    this.link = PortalNavigator.getSubMenuItemUrlOfCurrentApplication(MenuKind.TASK);
  }
}
