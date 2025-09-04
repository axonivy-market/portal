package com.axonivy.portal.components.enums;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum NewFilenameValidation {
  ALREADY_EXIST, INVALID_FORMAT, VALID;

  public String getLabel() {
    return Optional.ofNullable(Ivy.cms().co("/Labels/Enums/NewFilenameValidation/" + name())).orElse(StringUtils.EMPTY);
  }
}
