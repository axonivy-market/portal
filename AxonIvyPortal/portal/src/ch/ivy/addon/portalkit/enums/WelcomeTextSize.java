package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum WelcomeTextSize {
  NORMAL_TEXT,
  HEADING_1,
  HEADING_2,
  HEADING_3;

  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/WelcomeTextSize/" + name());
  }
}
