package com.axonivy.portal.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum SidebarBehaviour {
  HOVER, CLICK, STICK;

  public String getLabel() {
    return StringUtils.defaultIfBlank(Ivy.cms().co("/Labels/Enums/SidebarBehaviour/" + name()), name());
  }
}
