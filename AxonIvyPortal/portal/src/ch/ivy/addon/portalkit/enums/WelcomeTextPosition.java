package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum WelcomeTextPosition {
  TOP_LEFT,
  TOP_RIGHT,
  BOTTOM_LEFT,
  BOTTOM_RIGHT,
  CENTER;
  
  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/WelcomeTextPosition/" + name());
  }
}
