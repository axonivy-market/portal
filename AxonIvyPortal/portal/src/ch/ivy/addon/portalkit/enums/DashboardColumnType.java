package ch.ivy.addon.portalkit.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import ch.ivyteam.ivy.environment.Ivy;

public enum DashboardColumnType {
  STANDARD, CUSTOM, CUSTOM_CASE, CUSTOM_BUSINESS_CASE;

  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/columnType/" + name());
  }

  public static DashboardColumnType findBy(String field) {
    DashboardColumnType result = DashboardColumnType.valueOf(field.toUpperCase());
    return result == null ? CUSTOM : result;
  }

  @JsonValue
  public String getType() {
    return this.name().toLowerCase();
  }
}
