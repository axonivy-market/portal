package com.axonivy.portal.menu.management.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MenuSource {
  STANDARD, CALLABLE, DASHBOARD, CUSTOM_MENU_CONFIGURATION, THIRD_PARTY_APP_CONFIGURATION;

  @JsonValue
  public String getValue() {
    return this.name().toLowerCase();
  }
}
