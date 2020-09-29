package ch.addon.portal.generic.menu;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivyteam.ivy.environment.Ivy;

public class StatisticSubMenuItem extends SubMenuItem {
  public StatisticSubMenuItem() {
    this.icon = "icon ivyicon-pie-line-graph";
    this.menuKind = MenuKind.STATISTICS;
    this.label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/statistics");
    this.link = PortalNavigator.getSubMenuItemUrlOfCurrentApplication(MenuKind.STATISTICS);
  }
}
