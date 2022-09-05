package com.axonivy.portal.enums;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum ThemeMode {
  LIGHT, DARK;

  public String getLabel() {
    return StringUtils.defaultIfBlank(Ivy.cms().co("/Labels/Enums/ThemeMode/" + name()), name());
  }
}
