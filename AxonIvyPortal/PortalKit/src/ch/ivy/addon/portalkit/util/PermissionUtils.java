package ch.ivy.addon.portalkit.util;

import java.util.Objects;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;

public class PermissionUtils {
  private static final String ADMIN_ROLE = "AXONIVY_PORTAL_ADMIN";

  private PermissionUtils() {}

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

  /**
   * Check if user can start an Express workflow
   * 
   * @param workflow
   * @return True: has permission to start Express workflow, False: Do not have permission to start Express workflow
   */
  public static boolean canStartExpressWorkflow(ExpressProcess workflow) {
    boolean isWorkflowOwner = Ivy.session().canActAsUser(
        Ivy.request().getApplication().getSecurityContext().findUser(workflow.getProcessOwner().substring(1)));
    boolean hasAdminRole = isSessionUserHasAdminRole();

    if (isWorkflowOwner || hasAdminRole) {
      return true;
    }

    for (String memberName : workflow.getProcessPermissions()) {
      ISecurityMember member = Ivy.session().getSecurityContext().findSecurityMember(memberName);
      boolean isAssignedUser = member.isUser() && Ivy.session().canActAsUser((IUser) member);
      boolean hasAssignedRole = !member.isUser() && Ivy.session().hasRole((IRole) member, false);
      if (isAssignedUser || hasAssignedRole) {
        return true;
      }
    }

    return false;
  }

  public static boolean isSessionUserHasAdminRole() {
    return Ivy.session().hasRole(Ivy.request().getApplication().getSecurityContext().findRole(ADMIN_ROLE), false);
  }

  /**
   * Check if current user has portal permission
   * 
   * @param portalPermission
   * @return true : portal permission is grated, otherwise false
   */
  public static boolean hasPortalPermission(PortalPermission portalPermission) {
    IPermission iPermission = IPermissionRepository.get().findByName(portalPermission.getValue());
    if (Objects.isNull(iPermission)) {
      return false;
    }
    return Ivy.session().hasPermission(Ivy.request().getApplication().getSecurityDescriptor(), iPermission);
  }
}
