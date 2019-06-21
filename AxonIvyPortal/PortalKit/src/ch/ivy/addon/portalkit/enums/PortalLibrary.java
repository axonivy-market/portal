package ch.ivy.addon.portalkit.enums;

public enum PortalLibrary {
  PORTAL_STYLE("at.aws.ivy:portalStyle"),
  PORTAL_KIT("at.aws.ivy:portalKit"), 
  PORTAL_TEMPLATE("at.aws.ivy:portalTemplate"),
  AXON_EXPRESS("at.aws.ivy:axonIvyExpress"),
  SELF_SERVICE("at.aws.ivy:selfService");

  private String value;

  private PortalLibrary(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
