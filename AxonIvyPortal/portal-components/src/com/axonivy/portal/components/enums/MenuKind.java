package com.axonivy.portal.components.enums;

public enum MenuKind {
  DASHBOARD, PROCESS, CUSTOM, EXTERNAL_LINK, THIRD_PARTY, MAIN_DASHBOARD, STATIC_PAGE;
  
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
