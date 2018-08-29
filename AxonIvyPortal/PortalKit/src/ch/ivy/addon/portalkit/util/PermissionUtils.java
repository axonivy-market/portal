package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.environment.Ivy;

public class PermissionUtils {
  private static final String ADMIN_ROLE = "AXONIVY_PORTAL_ADMIN";

  /**
   * Check if current user has read all tasks permission
   * 
   * @return True : has read all tasks permission, False : do not have this permission
   */
  public static boolean checkReadAllTasksPermission() {
    return Ivy.session().hasPermission(Ivy.request().getApplication().getSecurityDescriptor(),
        ch.ivyteam.ivy.security.IPermission.TASK_READ_ALL);
  }

  /**
   * Check if current user has read all cases permission
   * 
   * @return True : has read all cases permission, False : do not have this permission
   */
  public static boolean checkReadAllCasesPermission() {
    return Ivy.session().hasPermission(Ivy.request().getApplication().getSecurityDescriptor(),
        ch.ivyteam.ivy.security.IPermission.CASE_READ_ALL);
  }

  /**
   * Check if current user has task read own case tasks permission
   * 
   * @return True : has task read own case tasks permission, False : do not have this permission
   */
  public static boolean checkTaskReadOwnCaseTasksPermission() {
    return Ivy.session().hasPermission(Ivy.request().getApplication().getSecurityDescriptor(),
        ch.ivyteam.ivy.security.IPermission.TASK_READ_OWN_CASE_TASKS);
  }

  public static boolean isSessionUserHasAdminRole() {
    return Ivy.session().hasRole(Ivy.request().getApplication().getSecurityContext().findRole(ADMIN_ROLE), false);
  }
}
