package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum TaskSortField {
  PRIORITY("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/PRIORITY"), 
  NAME(""), 
  ACTIVATOR(""), 
  ID(""), 
  CREATION_TIME("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CREATION_TIME"), 
  EXPIRY_TIME("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/EXPIRY_TIME"), 
  STATE(""), 
  CATEGORY(""), 
  DESCRIPTION(""), 
  WORKER(""), 
  FINISHED_TIME("");
  
  private final String cmsURI;
  
  TaskSortField(String cmsURI) {
    this.cmsURI = cmsURI;
  }

  public String getLabel() {
    return Ivy.cms().co(cmsURI);
  }
}
