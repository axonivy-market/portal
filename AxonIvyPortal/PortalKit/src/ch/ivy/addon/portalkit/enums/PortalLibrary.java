package ch.ivy.addon.portalkit.enums;

public enum PortalLibrary {
  PORTAL_KIT("ch.ivyteam.ivy.project.portal:portalKit"), 
  PORTAL_TEMPLATE("ch.ivyteam.ivy.project.portal:portalTemplate");

  private String value;

  private PortalLibrary(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
