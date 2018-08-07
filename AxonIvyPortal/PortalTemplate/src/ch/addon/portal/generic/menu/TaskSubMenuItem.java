package ch.addon.portal.generic.menu;

import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskSubMenuItem extends SubMenuItem {
  public TaskSubMenuItem() {
    this.icon = "fa fa-check-square-o";
    this.menuKind = MenuKind.TASK;
    this.label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/tasks");
  }
}
