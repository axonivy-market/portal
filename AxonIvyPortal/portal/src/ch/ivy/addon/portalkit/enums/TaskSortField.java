package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum TaskSortField {
  PRIORITY, 
  NAME, 
  ACTIVATOR, 
  ID, 
  CREATION_TIME, 
  EXPIRY_TIME, 
  COMPLETED_ON,
  STATE,
  CATEGORY,
  APPLICATION;

  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/" + name());
  }
}
