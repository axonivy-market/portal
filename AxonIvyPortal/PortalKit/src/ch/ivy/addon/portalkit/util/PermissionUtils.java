package ch.ivy.addon.portalkit.util;

import java.util.Collection;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

public class PermissionUtils {
  private static final String ADMIN_ROLE = "AXONIVY_PORTAL_ADMIN";
  private static final String EXTERNAL_ID_PREFIX = " externalId:";

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

  /**
   * Check if user can start an Express workflow
   * and set permission if user able to edit/delete express WF
   *
   * @param workflow
   * @return True: has permission to start Express workflow, False: Do not have permission to start Express workflow
   */
  public static boolean checkAbleToStartAndAbleToEditExpressWorkflow(ExpressProcess workflow) {
    String validProcessOwnerName = ExpressManagementUtils.getValidMemberName(workflow.getProcessOwner());
    boolean isWorkflowOwner = StringUtils.isNotBlank(validProcessOwnerName) ? Ivy.session().canActAsUser(
        ISecurityContext.current().users().find(validProcessOwnerName.substring(1))) : false;
    boolean hasAdminRole = isSessionUserHasAdminRole();

    if (isWorkflowOwner || hasAdminRole) {
      workflow.setAbleToEdit(true);
      return true;
    }
    Collection<String> ableToStartResponsibles = CollectionUtils.emptyIfNull(workflow.getProcessPermissions());
    Collection<String> processOwners = CollectionUtils.emptyIfNull(workflow.getProcessCoOwners());

    for (String memberName : processOwners) {
      if(isSessionUserBelongsToPermissionGroup(memberName)) {
        workflow.setAbleToEdit(true);
        return true;
      }
    }

    for (String memberName : ableToStartResponsibles) {
      if(isSessionUserBelongsToPermissionGroup(memberName)) {
        return true;
      }
    }

    return false;
  }

  private static boolean isSessionUserBelongsToPermissionGroup(String memberName) {
    if(memberName == null) {
      return false;
    }

    String memberNameWithoutExternalId = getMemberNameWithoutExternalId(memberName);
    ISecurityMember member = ISecurityContext.current().members().find(memberNameWithoutExternalId);
    if(member != null) {
      boolean isAssignedUser = member.isUser() && Ivy.session().canActAsUser((IUser) member);
      boolean hasAssignedRole = !member.isUser() && Ivy.session().hasRole((IRole) member, false);
      return isAssignedUser || hasAssignedRole;
    }
    return false;
  }

  private static String getMemberNameWithoutExternalId(String memberName) {
    int indexOfExternalId = memberName.indexOf(EXTERNAL_ID_PREFIX);
    return indexOfExternalId > -1 ? memberName.substring(0, indexOfExternalId) : memberName;
  }

  public static boolean isSessionUserHasAdminRole() {
    return Ivy.session().hasRole(ISecurityContext.current().roles().find(ADMIN_ROLE), false);
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
   * Check if current user has permission to see full statistic list
   *
   * @return true if current user has permission to see full statistic list
   */
  public static boolean checkAccessFullStatisticsListPermission() {
    return hasPortalPermission(PortalPermission.ACCESS_FULL_STATISTICS_LIST);
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

  public static String getCaseName(ICase iCase) {
    return IvyExecutor.executeAsSystem(iCase::getName);
  }

  public static String getTaskName(ITask task) {
    return IvyExecutor.executeAsSystem(task::getName);
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
}