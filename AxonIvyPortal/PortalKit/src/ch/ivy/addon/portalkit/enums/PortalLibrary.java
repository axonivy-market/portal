package ch.ivy.addon.portalkit.enums;

public enum PortalLibrary {
  PORTAL_STYLE("ch.ivyteam.ivy.project.portal:portalStyle"),
  PORTAL_KIT("ch.ivyteam.ivy.project.portal:portalKit"), 
  PORTAL_TEMPLATE("ch.ivyteam.ivy.project.portal:portalTemplate"),
  AXON_EXPRESS("ch.ivyteam.ivy.project.portal:axonIvyExpress"),
  SELF_SERVICE("ch.ivyteam.ivy.project.portal:selfService");

  private String value;

  private PortalLibrary(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
