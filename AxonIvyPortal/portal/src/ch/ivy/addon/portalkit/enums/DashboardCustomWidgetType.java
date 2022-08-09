package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum DashboardCustomWidgetType {
  EXTERNAL_URL, PROCESS;
  
  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/DashboardCustomWidgetType/" + name());
  }
}
