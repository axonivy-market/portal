package portalmigration.version112.enums;

public enum DashboardCustomParamType {
  USER("user__"),
  DATE("date__"),
  STRING("string__"),
  BOOLEAN("boolean__");
  
  private final String prefix;
  public final static String PARAM_FORMAT = "%s__%s";

  private DashboardCustomParamType(String prefix) {
    this.prefix = prefix;
  }
  
  public String getPrefix() {
    return prefix;
  }
}
