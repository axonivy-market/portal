package ch.ivy.addon.portalkit.enums;

public enum PortalLibrary {
  PORTAL_KIT("ch.ivyteam.ivy.project.portal:portalKit"), 
  PORTAL_TEMPLATE("ch.ivyteam.ivy.project.portal:portalTemplate"),
  AXON_EXPRESS("ch.ivyteam.ivy.project.portal:axonIvyExpress"),
  PORTAL_CONNECTOR("ch.ivyteam.ivy.project.system:portalConnector");

  private String value;

  private PortalLibrary(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
