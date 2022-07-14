package portalmigration.enums;

public enum PortalLibrary {
  PORTAL_KIT("portalKit"), 
  PORTAL_TEMPLATE("portalTemplate"),
  AXON_EXPRESS("axonIvyExpress"),
  SELF_SERVICE("selfService");
  private String value;

  private PortalLibrary(String value) {
    this.value = value;
  }

  public String getProjectId() {
    return value;
  }
}
