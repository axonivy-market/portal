package ch.ivy.addon.portalkit.enums;

public enum PortalPermission {
  TASK_WRITE_ACTIVATOR_OWN_TASKS("TaskWriteActivatorOwnTasks", PortalPermissionGroup.TASK_PERMISSIONS_GROUP),
  STATISTIC_ADD_DASHBOARD_CHART("StatisticAddDashboardChart", PortalPermissionGroup.STATISTIC_GROUP),
  EXPRESS_CREATE_WORKFLOW("ExpressCreateWorkflow", PortalPermissionGroup.EXPRESS_GROUP);

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
