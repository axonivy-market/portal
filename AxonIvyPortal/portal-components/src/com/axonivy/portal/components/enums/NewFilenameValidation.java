package com.axonivy.portal.components.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum NewFilenameValidation {
  ALREADY_EXIST, INVALID_FORMAT, VALID;

  public String getLabel() {
    return Ivy.cms().co("/Labels/Enums/NewFilenameValidation/" + name());
  }
}