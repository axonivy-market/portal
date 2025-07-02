package com.axonivy.portal.components.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum SideStepType {
  PARALLEL, SWITCH;

  public String getLabel() {
    String label = Ivy.cms().co("/Dialogs/com/axonivy/portal/components/SideStepType/" + name());
    return StringUtils.isBlank(label) ? name() : label;
  }
}
