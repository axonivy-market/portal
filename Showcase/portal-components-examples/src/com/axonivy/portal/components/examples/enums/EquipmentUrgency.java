package com.axonivy.portal.components.examples.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum EquipmentUrgency {
  NORMAL,
  URGENT;

  public String getLabel() {
    return Ivy.cms().co("/Enums/" + getClass().getSimpleName() + "/" + name());
  }
}
