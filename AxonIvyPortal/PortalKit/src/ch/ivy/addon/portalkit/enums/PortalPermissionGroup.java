package ch.ivy.addon.portalkit.enums;

public enum PortalPermissionGroup {
  PORTAL_PERMISSION_GROUP("PortalPermissions");

  private String value;

  private PortalPermissionGroup(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
