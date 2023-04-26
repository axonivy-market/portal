package ch.addon.portal.generic.menu;

import com.axonivy.portal.components.util.ApplicationMultiLanguageAPI;

import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;

public class CaseSubMenuItem extends SubMenuItem {
  public CaseSubMenuItem() {
    this.icon = "si si-layout-bullets";
    this.menuKind = MenuKind.CASE;
    this.label = ApplicationMultiLanguageAPI.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases");
    this.name = HomepageType.CASE.name();
    this.link = PortalNavigator.getSubMenuItemUrlOfCurrentApplication(MenuKind.CASE);
  }
}
