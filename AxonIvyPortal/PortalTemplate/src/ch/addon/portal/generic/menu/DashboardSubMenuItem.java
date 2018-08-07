package ch.addon.portal.generic.menu;

import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivyteam.ivy.environment.Ivy;

public class DashboardSubMenuItem extends SubMenuItem {
  public DashboardSubMenuItem() {
    this.icon = "fa fa-calendar";
    this.menuKind = MenuKind.DASHBOARD;
    this.label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/statistics");
  }
}
