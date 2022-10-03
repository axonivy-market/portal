package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum CaseSortField {
  NAME, CREATOR, OWNER, ID, CREATION_TIME, FINISHED_TIME, STATE, ELAPSED_TIME, CATEGORY, APPLICATION;

  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/" + name());
  }
}
