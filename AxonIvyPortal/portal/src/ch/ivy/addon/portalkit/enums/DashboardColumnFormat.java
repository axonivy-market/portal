package ch.ivy.addon.portalkit.enums;

public enum DashboardColumnFormat {
  STRING, TEXT, NUMBER, TIMESTAMP, CUSTOM, CUSTOM_CASE, CUSTOM_BUSINESS_CASE;

  public boolean isSortableCustomField() {
    return this == STRING || this == NUMBER || this == TIMESTAMP;
  }
}
