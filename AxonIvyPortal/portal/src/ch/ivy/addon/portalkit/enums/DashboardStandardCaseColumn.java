package ch.ivy.addon.portalkit.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ch.ivyteam.ivy.environment.Ivy;

public enum DashboardStandardCaseColumn {
  PIN("pin", false),
  ID("id", true), 
  NAME("name", true), 
  DESCRIPTION("description", false), 
  STATE("state", true), 
  CREATOR("creator", true), 
  CREATED("startTimestamp", true), 
  FINISHED("endTimestamp", true),
  OWNER("owner", false), 
  CATEGORY("category", false), 
  APPLICATION("application", false),
  ACTIONS("actions", false);

  private final String field;
  private final boolean sortable;

  private static final Map<String, DashboardStandardCaseColumn> map = new HashMap<>();

  static {
    for (DashboardStandardCaseColumn col : values()) {
      map.put(col.field, col);
    }
  }

  private DashboardStandardCaseColumn(String field, boolean sortable) {
    this.field = field;
    this.sortable = sortable;
  }

  public String getField() {
    return field;
  }

  public boolean isSortable() {
    return sortable;
  }

  public static DashboardStandardCaseColumn findBy(String field) {
    return map.get(field);
  }

  public static DashboardStandardCaseColumn findByIgnoreCase(String field) {
    if (field == null) {
      return null;
    }
    for (DashboardStandardCaseColumn col : values()) {
      if (col.field.equalsIgnoreCase(field)) {
        return col;
      }
    }
    return null;
  }

  public static boolean isNonSortableStandardField(String field) {
    DashboardStandardCaseColumn column = findByIgnoreCase(field);
    return column != null && !column.sortable;
  }

  public static List<DashboardStandardCaseColumn> getFilterableFields() {
    return Arrays.asList(PIN, ID, NAME, DESCRIPTION, STATE, CREATOR, CREATED, FINISHED, CATEGORY, APPLICATION);
  }

  public String getLabel() {
    return Ivy.cms().co(String.format("/Labels/Enums/DashboardStandardCaseColumn/%s", this.name()));
  }

  public static final Set<DashboardStandardCaseColumn> AI_RESULT_COLUMNS = Collections
      .unmodifiableSet(EnumSet.of(ID, NAME, OWNER, STATE, ACTIONS));
}
