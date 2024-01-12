package com.axonivy.portal.developerexamples.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum DashboardWidgetType {
  TASK, CASE, PROCESS, STATISTIC, NEW, CUSTOM, PROCESS_VIEWER, WELCOME, NEWS;

  public static DashboardWidgetType typeOf(String typeName) {
    for (DashboardWidgetType type : DashboardWidgetType.values()) {
      if (type.name().equalsIgnoreCase(typeName)) {
        return type;
      }
    }
    return null;
  }

  public String getLabel() {
    return Ivy.cms().co("/Labels/DashboardWidgetType/" + name());
  }
}
