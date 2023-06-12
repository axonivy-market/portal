package com.axonivy.portal.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum SearchScopeCaseField {
  NAME, DESCRIPTION, CUSTOM;

  public String getLabel() {
    return Ivy.cms().co("/Labels/Enums/SearchScopeCaseField/" + name());
  }
}
