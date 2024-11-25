package ch.ivy.addon.portalkit.util;

import java.util.Objects;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

public class PermissionUtils {
  private static final String ADMIN_ROLE = "AXONIVY_PORTAL_ADMIN";

  private PermissionUtils() {}

  /**
   * Check if current user has read all tasks permission
   *
   * @return True : has read all tasks permission
   */
  public static boolean checkReadAllTasksPermission() {
    return hasPermission(IPermission.TASK_READ_ALL);
  }

  private static ISecurityDescriptor getSecurityDescriptor() {
    return ISecurityContext.current().securityDescriptor();
  }
  
  /**
   * Check if current user has read all cases permission
   *
   * @return True : has read all cases permission
   */
  public static boolean checkReadAllCasesPermission() {
    return hasPermission(IPermission.CASE_READ_ALL);
  }

  /**
   * Check if current user has task read own case tasks permission
   *
   * @return True : has task read own case tasks permission
   */
  public static boolean checkTaskReadOwnCaseTasksPermission() {
    return hasPermission(IPermission.TASK_READ_OWN_CASE_TASKS);
  }

  /**
   * Check if current user has document write permission
   *
   * @return True : has document write permission
   */
  public static boolean checkDocumentWritePermission() {
    return hasPermission(IPermission.DOCUMENT_WRITE);
  }

  /**
   * Check if current user has document of involved case write permission
   *
   * @return True : has task document of involved case write permission
   */
  public static boolean checkDocumentOfInvolvedCaseWritePermission() {
    return hasPermission(IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE);
  }

  /**
   * Check if current user has task destroy permission,
   * @return True : has task destroy permission
   */
  public static boolean checkDestroyTaskPermission() {
    return hasPermission(IPermission.TASK_DESTROY);
  }

  public static boolean isSessionUserHasAdminRole() {
    return doesSessionUserHaveRole(ADMIN_ROLE);
  }

  public static boolean doesSessionUserHaveRole(String roleName) {
    return Ivy.session().has().roles(roleName);
  }

  /**
   * Check if current user has portal permission
   *
   * @param portalPermission
   * @return true : portal permission is granted
   */
  public static boolean hasPortalPermission(PortalPermission portalPermission) {
    IPermission iPermission = IPermissionRepository.instance().findByName(portalPermission.getValue());
    if (Objects.isNull(iPermission)) {
      return false;
    }
    return hasPermission(iPermission);
  }

  /**
   * Check if current user has permission to see full process list
   *
   * @return true if user has permission to see full process list
   */
  public static boolean checkAccessFullProcessListPermission() {
    return hasPortalPermission(PortalPermission.ACCESS_FULL_PROCESS_LIST);
  }

  /**
   * Check if current user has permission to see full task list
   *
   * @return true if current user has permission to see full task list
   */
  public static boolean checkAccessFullTaskListPermission() {
    return hasPortalPermission(PortalPermission.ACCESS_FULL_TASK_LIST);
  }

  /**
   * Check if current user has permission to see full case list
   *
   * @return true if current user has permission to see full case list
   */
  public static boolean checkAccessFullCaseListPermission() {
    return hasPortalPermission(PortalPermission.ACCESS_FULL_CASE_LIST);
  }

  /**
   * Check if current user has permission to create public external link
   *
   * @return true if current user has permission to create public external link
   */
  public static boolean checkPublicLinkCreationPermission() {
    return hasPortalPermission(PortalPermission.CREATE_PUBLIC_EXTERNAL_LINK);
  }

  public static boolean hasDashboardWriteOwnPermission() {
    return hasPortalPermission(PortalPermission.DASHBOARD_WRITE_OWN);
  }

  public static boolean hasDashboardWritePublicPermission() {
    return hasPortalPermission(PortalPermission.DASHBOARD_WRITE_PUBLIC);
  }

  public static boolean hasDashboardExportOwnPermission() {
    return hasPortalPermission(PortalPermission.DASHBOARD_EXPORT_OWN);
  }

  public static boolean hasDashboardExportPublicPermission() {
    return hasPortalPermission(PortalPermission.DASHBOARD_EXPORT_PUBLIC);
  }
  
  public static boolean hasDashboardImportOwnPermission() {
    return hasPortalPermission(PortalPermission.DASHBOARD_IMPORT_OWN);
  }

  public static boolean hasDashboardImportPublicPermission() {
    return hasPortalPermission(PortalPermission.DASHBOARD_IMPORT_PUBLIC);
  }
  
  public static boolean hasShareDashboardPermission() {
    return hasPortalPermission(PortalPermission.DASHBOARD_SHARE_LINK);
  }
  
  public static boolean hasShareTaskDetailsPermission() {
    return hasPortalPermission(PortalPermission.TASK_DETAILS_SHARE_LINK);
  }
  
  public static boolean hasShareCaseDetailsPermission() {
    return hasPortalPermission(PortalPermission.CASE_DETAILS_SHARE_LINK);
  }

  public static boolean hasSystemTaskReadAllPermission() {
    return hasPortalPermission(PortalPermission.SYSTEM_TASK_READ_ALL);
  }

  public static String getCaseName(ICase iCase) {
    return Sudo.get(() -> {
      return iCase.names().current();
    });
  }

  public static String getTaskName(ITask task) {
    return Sudo.get(() -> {
      return task.names().current();
    });
  }

  /**
   * Check if current user has permission to see/reset task is in state ReadyForJoin
   * @return true if current user has permission.
   */
  public static boolean canResetTaskReadyForJoin() {
    return hasPortalPermission(PortalPermission.TASK_RESET_READY_FOR_JOIN);
  }

  public static boolean hasPermission(IPermission permission) {
    return Ivy.session().hasPermission(getSecurityDescriptor(), permission);
  }

  public static boolean hasAllPermissions(IPermission permission, IPermission... permissions) {
    boolean hasAllPermissions = hasPermission(permission);
    for (IPermission perm : permissions) {
      hasAllPermissions &= hasPermission(perm);
    }
    return hasAllPermissions;
  }

  public static boolean hasAtLeastOnePermission(IPermission permission, IPermission... permissions) {
    boolean hasAtLeastOnePermission = hasPermission(permission);
    for (IPermission perm : permissions) {
      hasAtLeastOnePermission |= hasPermission(perm);
    }
    return hasAtLeastOnePermission;
  }

  /**
   * Check if current user has permission to see the WorkflowEvent of task
   * @return true if current user has permission.
   */
  public static boolean checkReadAllWorkflowEventPermission() {
    return hasPermission(IPermission.WORKFLOW_EVENT_READ_ALL);
  }

  /**
   * Check if current user has permission to set/change their account password
   * @return true if current user has permission.
   */
  public static boolean checkUserSetOwnPasswordPermission() {
    return hasPermission(IPermission.USER_SET_OWN_PASSWORD);
  }
}