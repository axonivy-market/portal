package ch.ivy.addon.portalkit.enums;

public enum MenuKind {
  DASHBOARD, PROCESS, TASK, CASE, STATISTICS, CUSTOM, EXTERNAL_LINK, THIRD_PARTY;
  
  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }

  public static MenuKind getKind(String enumName) {
    for (MenuKind menuKind : MenuKind.values()) {
      if (menuKind.name().equalsIgnoreCase(enumName)) {
        return menuKind;
      }
    }
    return null;
  }
  
}
