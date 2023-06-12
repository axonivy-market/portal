package com.axonivy.portal.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum SearchScopeTaskField {
  NAME, DESCRIPTION;

  public String getLabel() {
    return Ivy.cms().co("/Labels/Enums/SearchScopeTaskField/" + name());
  }
}
