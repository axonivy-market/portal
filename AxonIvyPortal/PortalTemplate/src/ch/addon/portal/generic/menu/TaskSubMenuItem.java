package ch.addon.portal.generic.menu;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskSubMenuItem extends SubMenuItem {
  public TaskSubMenuItem() {
    this.icon = "icon ivyicon-task-list-edit";
    this.menuKind = MenuKind.TASK;
    this.label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/tasks");
    this.link = new PortalNavigator().getSubMenuItemUrlOfCurrentApplication(MenuKind.TASK);
  }
}
