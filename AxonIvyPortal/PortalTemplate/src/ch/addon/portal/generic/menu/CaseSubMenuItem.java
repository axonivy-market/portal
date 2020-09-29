package ch.addon.portal.generic.menu;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivyteam.ivy.environment.Ivy;

public class CaseSubMenuItem extends SubMenuItem {
  public CaseSubMenuItem() {
    this.icon = "icon ivyicon-layout-bullets";
    this.menuKind = MenuKind.CASE;
    this.label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases");
    this.link = PortalNavigator.getSubMenuItemUrlOfCurrentApplication(MenuKind.CASE);
  }
}
