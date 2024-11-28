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
  ID("id"), 
  NAME("name"), 
  DESCRIPTION("description"), 
  STATE("state"), 
  CREATOR("creator"), 
  CREATED("startTimestamp"), 
  FINISHED("endTimestamp"),
  OWNER("owner"), 
  CATEGORY("category"), 
  APPLICATION("application"),
  ACTIONS("actions");

  private final String field;

  private static final Map<String, DashboardStandardCaseColumn> map = new HashMap<>();

  static {
    for (DashboardStandardCaseColumn col : values()) {
      map.put(col.field, col);
    }
  }

  private DashboardStandardCaseColumn(String field) {
    this.field = field;
  }

  public String getField() {
    return field;
  }

  public static DashboardStandardCaseColumn findBy(String field) {
    return map.get(field);
  }

  public static List<DashboardStandardCaseColumn> getFilterableFields() {
    return Arrays.asList(ID, NAME, DESCRIPTION, STATE, CREATOR, CREATED, FINISHED, CATEGORY, APPLICATION);
  }

  public String getLabel() {
    return Ivy.cms().co(String.format("/Labels/Enums/DashboardStandardCaseColumn/%s", this.name()));
  }

  public static final Set<DashboardStandardCaseColumn> AI_RESULT_COLUMNS = Collections
      .unmodifiableSet(EnumSet.of(ID, NAME, OWNER, STATE, ACTIONS));
}
