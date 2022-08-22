package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum DashboardWidgetType {
  TASK, CASE, PROCESS, STATISTIC, NEW, CUSTOM, PROCESS_VIEWER, WELCOME;

  public static DashboardWidgetType typeOf(String typeName) {
    for (DashboardWidgetType type : DashboardWidgetType.values()) {
      if (type.name().equalsIgnoreCase(typeName)) {
        return type;
      }
    }
    return null;
  }

  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/DashboardWidgetType/" + name());
  }
}
