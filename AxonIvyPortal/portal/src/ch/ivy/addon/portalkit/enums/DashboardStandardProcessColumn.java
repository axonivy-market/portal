package ch.ivy.addon.portalkit.enums;

import java.util.HashMap;
import java.util.Map;

public enum DashboardStandardProcessColumn {
  ID("id"), NAME("name"), TYPE("type"), CATEGORY("category"), APPLICATION("application");

  private final String field;

  private static final Map<String, DashboardStandardProcessColumn> map = new HashMap<>();

  static {
    for (DashboardStandardProcessColumn col : values()) {
      map.put(col.field, col);
    }
  }

  private DashboardStandardProcessColumn(String field) {
    this.field = field;
  }

  public String getField() {
    return field;
  }

  public static DashboardStandardProcessColumn findBy(String field) {
    return map.get(field);
  }
}
