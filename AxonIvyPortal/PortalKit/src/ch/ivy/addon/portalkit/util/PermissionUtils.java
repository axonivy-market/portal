package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.environment.Ivy;

public class PermissionUtils {
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
  
  /**
   * Check if current user has document write permission
   * 
   * @return True : has document write permission, False : do not have this permission
   */
  public static boolean checkDocumentWritePermission() {
    return Ivy.session().hasPermission(Ivy.request().getApplication().getSecurityDescriptor(),
        ch.ivyteam.ivy.security.IPermission.DOCUMENT_WRITE);
  }
  
  /**
   * Check if current user has document of involved case write permission
   * 
   * @return True : has task document of involved case write permission, False : do not have this permission
   */
  public static boolean checkDocumentOfInvolvedCaseWritePermission() {
    return Ivy.session().hasPermission(Ivy.request().getApplication().getSecurityDescriptor(),
        ch.ivyteam.ivy.security.IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE);
  }
  
}
