package com.axonivy.portal.developerexamples.enums;

public enum HomepageType {
  DASHBOARD, PROCESS, TASK, CASE, STATISTICS, CUSTOM;

  public static HomepageType getType(String typeName) {
    for (HomepageType type : HomepageType.values()) {
      if (type.name().equalsIgnoreCase(typeName)) {
        return type;
      }
    }
    return null;
  }
  
  public String getLabel() {
    return name();
  }
}
