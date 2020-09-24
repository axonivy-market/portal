package ch.addon.portal.generic.menu;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;

public class ProcessSubMenuItem extends SubMenuItem {
  public ProcessSubMenuItem() {
    this.icon = "icon ivyicon-cog-double-2";
    this.menuKind = MenuKind.PROCESS;
    this.label = ApplicationMultiLanguage.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/common/processes");
    this.link = new PortalNavigator().getSubMenuItemUrlOfCurrentApplication(MenuKind.PROCESS);
  }
}
