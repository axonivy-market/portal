package ch.ivy.addon.portalkit.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DashboardColumnType {
  STANDARD, CUSTOM;

  public static DashboardColumnType findBy(String field) {
    DashboardColumnType result = DashboardColumnType.valueOf(field.toUpperCase());
    return result == null ? CUSTOM : result;
  }

  @JsonValue
  public String getType() {
    return this.name().toLowerCase();
  }
}
