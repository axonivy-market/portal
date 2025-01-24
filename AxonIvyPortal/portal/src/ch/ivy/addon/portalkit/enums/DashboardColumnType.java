package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum DashboardColumnType {
  STANDARD, CUSTOM, CUSTOM_CASE, CUSTOM_BUSINESS_CASE;

  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/columnType/" + name());
  }

  public boolean isStandardColumn() {
    return this == STANDARD;
  }
}
