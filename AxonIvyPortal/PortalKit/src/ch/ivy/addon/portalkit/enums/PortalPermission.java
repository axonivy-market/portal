package ch.ivy.addon.portalkit.enums;

public enum PortalPermission {
  TASK_WRITE_ACTIVATOR_OWN_TASKS("TaskWriteActivatorOwnTasks", PortalPermissionGroup.TASK_PERMISSIONS_GROUP),
  ADD_DASHBOARDS_CHARTS("AddDashboardsCharts", PortalPermissionGroup.STATISTICS_GROUP),
  CREATE_EXPRESS_WORKFLOW("CreateExpressWorkflow", PortalPermissionGroup.EXPRESS_GROUP);

  private String value;
  private PortalPermissionGroup group;

  private PortalPermission(String value, PortalPermissionGroup group) {
    this.value = value;
    this.group = group;
  }

  public String getValue() {
    return value;
  }

  public PortalPermissionGroup getGroup() {
    return group;
  }

}
