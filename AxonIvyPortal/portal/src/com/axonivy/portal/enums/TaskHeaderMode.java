package com.axonivy.portal.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum TaskHeaderMode {
  DEFAULT, LEGACY, HIDDEN;

  public String getLabel() {
    return StringUtils.defaultIfBlank(Ivy.cms().co("/Labels/Enums/TaskHeaderMode/" + name()), name());
  }

  public static TaskHeaderMode parse(String storedMode) {
    if (StringUtils.isBlank(storedMode)) {
      return DEFAULT;
    }
    try {
      return valueOf(storedMode);
    } catch (IllegalArgumentException e) {
      return DEFAULT;
    }
  }
}
