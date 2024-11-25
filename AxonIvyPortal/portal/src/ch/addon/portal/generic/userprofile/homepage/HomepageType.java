package ch.addon.portal.generic.userprofile.homepage;

public enum HomepageType {
  DASHBOARD, PROCESS, CUSTOM, MAIN_DASHBOARD;

  public static HomepageType getType(String typeName) {
    for (HomepageType type : HomepageType.values()) {
      if (type.name().equalsIgnoreCase(typeName)) {
        return type;
      }
    }
    return null;
  }
  
  public String getLabel() {
    return name();
  }
}
