package com.axonivy.portal.response;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

public class CaseData {
  private String uuid;
  private String name;
  private String link;

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CaseData() {
  }

  public CaseData(ICase caze, String link) {
    this.uuid = caze.uuid();
    this.name = StringUtils.defaultIfBlank(caze.getName(),
        Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/caseNameNotAvailable"));
    this.link = link;
  }

}
