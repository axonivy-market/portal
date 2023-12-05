package com.axonivy.portal.response;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;

import ch.ivyteam.ivy.workflow.ICase;

public class CaseData {
  private long id;
  private String name;
  private String link;

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
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

  public CaseData() {
  }

  public CaseData(ICase caze) {
    this.id = caze.getId();
    this.name = caze.getName();
    this.link = PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageByUUID(caze.uuid());
  }

}
