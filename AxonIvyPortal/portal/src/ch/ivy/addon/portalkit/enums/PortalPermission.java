package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;

public enum PortalPermission {
  DASHBOARD_WRITE_OWN("DashboardWriteOwn", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP),
  DASHBOARD_WRITE_PUBLIC("DashboardWritePublic", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP),
  TASK_WRITE_ACTIVATOR_OWN_TASKS("TaskWriteActivatorOwnTasks", PortalPermissionGroup.TASK_PERMISSIONS_GROUP),
  TASK_DISPLAY_ADDITIONAL_OPTIONS("TaskDisplayAdditionalOptions", PortalPermissionGroup.TASK_PERMISSIONS_GROUP),
  STATISTIC_ADD_DASHBOARD_CHART("StatisticAddDashboardChart", PortalPermissionGroup.STATISTIC_GROUP),
  STATISTIC_ANALYZE_TASK("StatisticAnalyzeTask", PortalPermissionGroup.STATISTIC_GROUP),
  EXPRESS_CREATE_WORKFLOW("ExpressCreateWorkflow", PortalPermissionGroup.EXPRESS_GROUP),
  ACCESS_FULL_PROCESS_LIST("AccessFullProcessList", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP),
  ACCESS_FULL_TASK_LIST("AccessFullTaskList", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP),
  ACCESS_FULL_CASE_LIST("AccessFullCaseList", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP),
  ACCESS_FULL_STATISTICS_LIST("AccessFullStatisticsList", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP),
  SHOW_ALL_TASKS_OF_CASE("ShowAllTasksOfCase", PortalPermissionGroup.CASE_PERMISSIONS_GROUP),
  SHOW_CASE_DETAILS("ShowCaseDetails", PortalPermissionGroup.CASE_PERMISSIONS_GROUP),
  TASK_CASE_ADD_NOTE("TaskCaseAddNote", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP),
  TASK_CASE_SHOW_MORE_NOTE("TaskCaseShowMoreNote", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP),
  TASK_DISPLAY_RESET_ACTION("TaskDisplayResetAction", PortalPermissionGroup.TASK_PERMISSIONS_GROUP),
  TASK_DISPLAY_RESERVE_ACTION("TaskDisplayReserveAction", PortalPermissionGroup.TASK_PERMISSIONS_GROUP),
  TASK_DISPLAY_DELEGATE_ACTION("TaskDisplayDelegateAction", PortalPermissionGroup.TASK_PERMISSIONS_GROUP),
  TASK_DISPLAY_WORKFLOW_EVENT_ACTION("TaskDisplayWorkflowEventAction", PortalPermissionGroup.TASK_PERMISSIONS_GROUP),
  TASK_DISPLAY_DESTROY_ACTION("TaskDisplayDestroyAction", PortalPermissionGroup.TASK_PERMISSIONS_GROUP),
  TASK_RESET_READY_FOR_JOIN("TaskResetReadyForJoin", PortalPermissionGroup.TASK_PERMISSIONS_GROUP),
  CREATE_PUBLIC_EXTERNAL_LINK("CreatePublicExternalLink", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP),
  ROLE_MANAGEMENT("RoleManagement", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP),
  PASSWORD_VALIDATION("PasswordValidation", PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP);

  private String value;
  private PortalPermissionGroup group;
  private IPermission permission;

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

  public IPermission getPermission() {
    if (permission == null) {
      permission = IPermissionRepository.instance().findByName(value);
      
    }
    return permission;
  }
}
