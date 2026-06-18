package com.axonivy.portal.components.examples.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum EquipmentItemType {
  LAPTOP,
  MONITOR,
  KEYBOARD,
  MOUSE,
  HEADSET,
  OTHER;

  public String getLabel() {
    return Ivy.cms().co("/Enums/" + getClass().getSimpleName() + "/" + name());
  }
}
