package ch.addon.portal.generic.menu;

import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.publicapi.ApplicationMultiLanguageAPI;

public class TaskSubMenuItem extends SubMenuItem {
  public TaskSubMenuItem() {
    this.icon = "si si-task-list-edit";
    this.menuKind = MenuKind.TASK;
    this.label = ApplicationMultiLanguageAPI.getCmsValueByUserLocale("/ch.ivy.addon.portalkit.ui.jsf/common/tasks");
    this.name = HomepageType.TASK.name();
    this.link = PortalNavigator.getSubMenuItemUrlOfCurrentApplication(MenuKind.TASK);
  }
}
