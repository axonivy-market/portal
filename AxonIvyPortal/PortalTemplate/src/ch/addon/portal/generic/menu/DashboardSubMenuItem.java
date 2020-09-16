package ch.addon.portal.generic.menu;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;

public class DashboardSubMenuItem extends SubMenuItem {
  public DashboardSubMenuItem() {
    this.icon = "fa fa-bar-chart";
    this.menuKind = MenuKind.DASHBOARD;
    this.label = ApplicationMultiLanguage.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/statistics");
    this.link = new PortalNavigator().getSubMenuItemUrlOfCurrentApplication(MenuKind.DASHBOARD);
  }
}
