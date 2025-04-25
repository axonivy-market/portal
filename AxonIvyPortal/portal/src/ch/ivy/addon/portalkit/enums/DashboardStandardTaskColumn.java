package ch.ivy.addon.portalkit.enums;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ch.ivyteam.ivy.environment.Ivy;

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
  END("endTimestamp"),
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
  
  public String getLabel() {
    return Ivy.cms().co(String.format("/Labels/Enums/DashboardStandardTaskColumn/%s", this.name()));
  }
  
  public static final Set<DashboardStandardTaskColumn> AI_RESULT_COLUMNS = Collections
      .unmodifiableSet(
          EnumSet.of(ID, NAME, RESPONSIBLE, STATE, PRIORITY, EXPIRY, ACTIONS));
}
