package ch.ivy.addon.portalkit.enums;

import java.util.HashMap;
import java.util.Map;

public enum DashboardStandardTaskColumn {
  START("start"), PRIORITY("priority"), ID("id"), NAME("name"), DESCRIPTION("description"), RESPONSIBLE(
      "activator"), STATE("state"), CREATED("startTimestamp"), EXPIRY("expiryTimestamp");

  private final String property;

  private static final Map<String, DashboardStandardTaskColumn> map = new HashMap<>();

  static {
    for (DashboardStandardTaskColumn col : values()) {
      map.put(col.property, col);
    }
  }

  private DashboardStandardTaskColumn(String property) {
    this.property = property;
  }

  public String getProperty() {
    return property;
  }

  public static DashboardStandardTaskColumn findBy(String property) {
    return map.get(property);
  }
}
