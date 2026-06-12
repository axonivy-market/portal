package com.axonivy.portal.components.enums;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MenuKind {
  DASHBOARD, PROCESS, CUSTOM, EXTERNAL_LINK, THIRD_PARTY, MAIN_DASHBOARD, STATIC_PAGE, STANDARD;

  @JsonValue
  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }

  /**
   * Case-insensitive: legacy {@code Portal.CustomMenuItems} data persisted the enum
   * name in UPPERCASE, while {@code @JsonValue} now writes lowercase. This creator
   * makes deserialization independent of casing and of the ObjectMapper configuration
   * (not every converter enables ACCEPT_CASE_INSENSITIVE_ENUMS).
   */
  @JsonCreator
  public static MenuKind fromValue(String value) {
    return getKind(value);
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

  public static final Set<MenuKind> CREATABLE_MENU_KINDS = Collections
      .unmodifiableSet(EnumSet.of(EXTERNAL_LINK, CUSTOM, MAIN_DASHBOARD, STATIC_PAGE));
}
