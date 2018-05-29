package ch.ivy.addon.portalkit.enums;

public enum PortalPermissionGroup {
  PORTAL_PERMISSION_GROUP("PortalPermissions"),
  TASK_PERMISSIONS_GROUP("PortalTaskPermissions"),
  CASE_PERMISSIONS_GROUP("PortalCasePermissions"),
  GENERAL_PERMISSIONS_GROUP("PortalGeneralPortalPermissions"),
  ABSENCE_AND_SUBSTITUTE_GROUP("PortalAbsenceAndSubstitutePermissions");

  private String value;

  private PortalPermissionGroup(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
