package com.axonivy.portal.components.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MenuKind {
  DASHBOARD, PROCESS, CUSTOM, EXTERNAL_LINK, THIRD_PARTY, MAIN_DASHBOARD, STATIC_PAGE, STANDARD;
  
  @JsonValue
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

  public String getCmsUri() {
    return "/Labels/Enums/MenuKind/" + name();
  }
}
