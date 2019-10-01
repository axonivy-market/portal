package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum PortalLibrary {
  PORTAL_STYLE("portalStyle"),
  PORTAL_KIT("portalKit"), 
  PORTAL_TEMPLATE("portalTemplate"),
  AXON_EXPRESS("axonIvyExpress"),
  SELF_SERVICE("selfService");
  private static final String PORTAL_GROUP_ID = "PortalGroupId";
  private String value;

  private PortalLibrary(String value) {
    this.value = value;
  }

  public String getValue() {
    return Ivy.var().get(PORTAL_GROUP_ID) + ":" + value;
  }
}
