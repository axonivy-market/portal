package ch.ivy.addon.portalkit.enums;

import java.util.HashMap;
import java.util.Map;

public enum DashboardStandardTaskColumn {
  START("start"), 
  PRIORITY("priority"), 
  ID("id"), 
  NAME("name"), 
  DESCRIPTION("description"), 
  RESPONSIBLE("activator"), 
  STATE("state"), 
  CREATED("startTimestamp"), 
  EXPIRY("expiryTimestamp"), 
  CATEGORY("category"),
  APPLICATION("application"),
  ACTIONS("actions");

  private final String field;

  private static final Map<String, DashboardStandardTaskColumn> map = new HashMap<>();

  static {
    for (DashboardStandardTaskColumn col : values()) {
      map.put(col.field, col);
    }
  }

  private DashboardStandardTaskColumn(String field) {
    this.field = field;
  }

  public String getField() {
    return field;
  }

  public static DashboardStandardTaskColumn findBy(String field) {
    return map.get(field);
  }
}
