package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum DashboardWidgetType {
  TASK, CASE, PROCESS, NEW, CUSTOM, PROCESS_VIEWER, WELCOME, NEWS, NOTIFICATION, STATISTIC;

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

  public boolean canEnableQuickSearch() {
    return this == TASK || this == CASE || this == PROCESS;
  }
  
  public boolean canShowWidgetInfoOption() {
    return this == TASK || this == CASE || this == PROCESS;
  }

  public boolean canShowFullscreenModeOption() {
    return this == TASK || this == CASE || this == PROCESS || this == NEWS || this == CUSTOM || this == PROCESS_VIEWER
        || this == STATISTIC || this == NOTIFICATION;
  }
}
