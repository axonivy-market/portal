package ch.addon.portal.generic.menu;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;

public class CaseSubMenuItem extends SubMenuItem {
  public CaseSubMenuItem() {
    this.icon = "fa fa-list-ul";
    this.menuKind = MenuKind.CASE;
    this.label = ApplicationMultiLanguage.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases");
    this.link = new PortalNavigator().getSubMenuItemUrlOfCurrentApplication(MenuKind.CASE);
  }
}
