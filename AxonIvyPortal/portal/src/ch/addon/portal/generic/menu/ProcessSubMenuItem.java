package ch.addon.portal.generic.menu;

import com.axonivy.portal.components.publicapi.ApplicationMultiLanguageAPI;

import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import com.axonivy.portal.components.enums.MenuKind;

public class ProcessSubMenuItem extends SubMenuItem {
  public ProcessSubMenuItem() {
    this.icon = "ti ti-sitemap";
    this.menuKind = MenuKind.PROCESS;
    this.label = ApplicationMultiLanguageAPI.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/common/processes");
    this.name = HomepageType.PROCESS.name();
    this.link = PortalNavigator.getSubMenuItemUrlOfCurrentApplication(MenuKind.PROCESS);
  }
}
