package ch.addon.portal.generic.menu;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.publicapi.ApplicationMultiLanguageAPI;

public class CaseSubMenuItem extends SubMenuItem {
  public CaseSubMenuItem() {
    this.icon = "fa fa-list-ul";
    this.menuKind = MenuKind.CASE;
    this.label = ApplicationMultiLanguageAPI.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases");
    this.link = new PortalNavigator().getSubMenuItemUrlOfCurrentApplication(MenuKind.CASE);
  }
}
