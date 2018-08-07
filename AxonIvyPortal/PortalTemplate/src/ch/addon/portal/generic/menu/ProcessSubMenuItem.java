package ch.addon.portal.generic.menu;

import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivyteam.ivy.environment.Ivy;

public class ProcessSubMenuItem extends SubMenuItem {
  public ProcessSubMenuItem() {
    this.icon = "fa fa-magic";
    this.menuKind = MenuKind.PROCESS;
    this.label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/processes");
  }
}
