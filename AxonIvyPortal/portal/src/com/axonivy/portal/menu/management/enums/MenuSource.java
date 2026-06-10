package com.axonivy.portal.menu.management.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MenuSource {
  STANDARD, CALLABLE, DASHBOARD, CUSTOM_MENU_CONFIGURATION, THIRD_PARTY_APP_CONFIGURATION;

  @JsonValue
  public String getValue() {
    return this.name().toLowerCase();
  }

  /**
   * Case-insensitive: persisted values must deserialize regardless of casing and of
   * which ObjectMapper configuration reads them.
   */
  @JsonCreator
  public static MenuSource fromValue(String value) {
    for (MenuSource source : values()) {
      if (source.name().equalsIgnoreCase(value)) {
        return source;
      }
    }
    return null;
  }
}
