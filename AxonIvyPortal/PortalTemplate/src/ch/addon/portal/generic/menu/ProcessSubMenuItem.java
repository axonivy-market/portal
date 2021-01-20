package ch.addon.portal.generic.menu;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.publicapi.ApplicationMultiLanguageAPI;

public class ProcessSubMenuItem extends SubMenuItem {
  public ProcessSubMenuItem() {
    this.icon = "fa fa-cogs";
    this.menuKind = MenuKind.PROCESS;
    this.label = ApplicationMultiLanguageAPI.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/common/processes");
    this.link = new PortalNavigator().getSubMenuItemUrlOfCurrentApplication(MenuKind.PROCESS);
  }
}
