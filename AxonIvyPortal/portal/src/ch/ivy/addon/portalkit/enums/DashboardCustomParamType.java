package ch.ivy.addon.portalkit.enums;

public enum DashboardCustomParamType {
  USER("user__"),
  DATE("date__"),
  STRING("string__"),
  BOOLEAN("boolean__");
  
  private final String prefix;

  private DashboardCustomParamType(String prefix) {
    this.prefix = prefix;
  }
  
  public String getPrefix() {
    return prefix;
  }
  
}
