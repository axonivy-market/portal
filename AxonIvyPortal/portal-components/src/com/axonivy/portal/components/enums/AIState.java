package com.axonivy.portal.components.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AIState {
  OPEN, IN_PROGRESS, DONE, ERROR;

  @JsonValue
  public String value() {
    return this.name().toLowerCase();
  }
}
