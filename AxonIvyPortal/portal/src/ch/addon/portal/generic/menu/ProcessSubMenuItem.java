package ch.addon.portal.generic.menu;

import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.publicapi.ApplicationMultiLanguageAPI;

public class ProcessSubMenuItem extends SubMenuItem {
  public ProcessSubMenuItem() {
    this.icon = "si si-hierarchy-6 si-rotate-270";
    this.menuKind = MenuKind.PROCESS;
    this.label = ApplicationMultiLanguageAPI.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/common/processes");
    this.name = HomepageType.PROCESS.name();
    this.link = PortalNavigator.getSubMenuItemUrlOfCurrentApplication(MenuKind.PROCESS);
  }
}
