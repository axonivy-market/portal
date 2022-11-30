package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum WelcomeImageFit {
  NONE,
  FILL,
  COVER,
  CONTAIN;
  
  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/WelcomeImageFit/" + name());
  }
}