package ch.ivy.addon.portalkit.enums;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ch.ivyteam.ivy.environment.Ivy;

public enum DashboardStandardTaskColumn {
  START("start", false),
  PIN("pin", false),
  PRIORITY("priority", true), 
  ID("id", true), 
  NAME("name", true), 
  DESCRIPTION("description", false), 
  RESPONSIBLE("activator", false), 
  STATE("state", true),
  CREATED("startTimestamp", true),
  COMPLETED("endTimestamp", true),
  EXPIRY("expiryTimestamp", true), 
  CATEGORY("category", false),
  APPLICATION("application", false),
  WORKER("worker", true),
  BUSINESS_CASE_ID("businessCaseId", true),
  TECHNICAL_CASE_ID("technicalCaseId", true),
  ACTIONS("actions", false);

  private final String field;
  private final boolean sortable;

  private static final Map<String, DashboardStandardTaskColumn> map = new HashMap<>();

  static {
    for (DashboardStandardTaskColumn col : values()) {
      map.put(col.field, col);
    }
  }

  private DashboardStandardTaskColumn(String field, boolean sortable) {
    this.field = field;
    this.sortable = sortable;
  }

  public String getField() {
    return field;
  }

  public boolean isSortable() {
    return sortable;
  }

  public static DashboardStandardTaskColumn findBy(String field) {
    return map.get(field);
  }

  public static DashboardStandardTaskColumn findByIgnoreCase(String field) {
    if (field == null) {
      return null;
    }
    for (DashboardStandardTaskColumn col : values()) {
      if (col.field.equalsIgnoreCase(field)) {
        return col;
      }
    }
    return null;
  }

  public static boolean isNonSortableStandardField(String field) {
    DashboardStandardTaskColumn column = findByIgnoreCase(field);
    return column != null && !column.sortable;
  }
  
  public String getLabel() {
    return Ivy.cms().co(String.format("/Labels/Enums/DashboardStandardTaskColumn/%s", this.name()));
  }
  
  public static final Set<DashboardStandardTaskColumn> AI_RESULT_COLUMNS = Collections
      .unmodifiableSet(
          EnumSet.of(ID, NAME, RESPONSIBLE, STATE, PRIORITY, EXPIRY, ACTIONS));
}
