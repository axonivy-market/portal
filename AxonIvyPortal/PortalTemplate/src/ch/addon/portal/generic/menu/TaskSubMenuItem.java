package ch.addon.portal.generic.menu;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;

public class TaskSubMenuItem extends SubMenuItem {
  public TaskSubMenuItem() {
    this.icon = "fa fa-check-square-o";
    this.menuKind = MenuKind.TASK;
    this.label = ApplicationMultiLanguage.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/common/tasks");
    this.link = new PortalNavigator().getSubMenuItemUrlOfCurrentApplication(MenuKind.TASK);
  }
}
