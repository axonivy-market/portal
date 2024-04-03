package com.axonivy.portal.developerexamples.showcase.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum Completeness {
  PERSON_LEGITIMIZED,
  ADDITIONAL_IDENTIFICATION_NEEDED,
  REQUEST_PROOF,
  NO_INFORMATION;

  private String cmsPath = "/Dialogs/GdprRequest/Lists/Completeness/";

  public String getCmsValue() {
    String value = "";
    
    String path = getCmsPath();
    
    if (Ivy.cms().get(path).isPresent()) {
      value = Ivy.cms().coLocale(path, Ivy.session().getContentLocale());
    }
    return value;
  }

  public String getCmsPath() {
    String value = cmsPath + this.name();
    return value;
  }
}
