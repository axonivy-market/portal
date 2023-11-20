package com.axonivy.portal.response;

import ch.ivyteam.ivy.workflow.ICase;

public class CaseData {
  private long id;
  private String name;
  private String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CaseData() {
  }

  public CaseData(ICase caze) {
    this.id = caze.getId();
    this.name = caze.getName();
    this.description = caze.getDescription();
  }

}
