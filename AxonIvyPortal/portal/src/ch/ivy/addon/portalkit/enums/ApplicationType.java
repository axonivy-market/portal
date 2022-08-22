package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum ApplicationType {
  IVY_APPLICATION("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/applicationtype/ivyApplication"),
  THIRD_PARTY_APPLICATION("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/applicationtype/thirdPartyApplication");

  private final String cmsURI;

  ApplicationType(String cmsURI) {
    this.cmsURI = cmsURI;
  }

  /**
   * Get the label value of date in week.
   *
   * @return string value of label
   */
  public String getLabel() {
    return Ivy.cms().co(cmsURI);
  }
}
