package ch.ivy.addon.portalkit.test.util;

import static ch.ivyteam.ivy.security.IPermission.CASE_DESTROY;
import static ch.ivyteam.ivy.security.IPermission.CASE_READ_ALL;
import static ch.ivyteam.ivy.security.IPermission.CASE_WRITE_DESCRIPTION;
import static ch.ivyteam.ivy.security.IPermission.CASE_WRITE_NAME;
import static ch.ivyteam.ivy.security.IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE;
import static ch.ivyteam.ivy.security.IPermission.TASK_PARK_OWN_WORKING_TASK;
import static ch.ivyteam.ivy.security.IPermission.TASK_READ_ALL;
import static ch.ivyteam.ivy.security.IPermission.TASK_RESET_OWN_WORKING_TASK;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_ACTIVATOR;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_DESCRIPTION;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_EXPIRY_TIMESTAMP;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_NAME;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_ORIGINAL_PRIORITY;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_SUBSTITUTE;
import static ch.ivyteam.ivy.security.IPermission.USER_DELETE_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_ABSENCES;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_SUBSTITUTES;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class SecurityUtils {
  private static final IPermission ADMIN_PERMISSIONS[] = {

  USER_READ_ABSENCES,

  USER_READ_SUBSTITUTES,

  USER_CREATE_ABSENCE,

  USER_CREATE_SUBSTITUTE,

  USER_DELETE_ABSENCE,

  CASE_READ_ALL,

  CASE_DESTROY,

  CASE_WRITE_NAME,

  CASE_WRITE_DESCRIPTION,

  TASK_READ_ALL,

  TASK_PARK_OWN_WORKING_TASK,

  TASK_WRITE_NAME,

  TASK_WRITE_DESCRIPTION,

  TASK_WRITE_ORIGINAL_PRIORITY,

  TASK_WRITE_ACTIVATOR,

  TASK_RESET_OWN_WORKING_TASK,

  DOCUMENT_OF_INVOLVED_CASE_WRITE,

  IPermissionRepository.instance().findByName("ShowCaseDetails")
  };
  private static final IPermission DEMO_DENIED_PERMISSIONS[] = {

  TASK_READ_ALL,

  CASE_READ_ALL,

  USER_READ_ABSENCES,

  USER_CREATE_ABSENCE,

  USER_DELETE_ABSENCE

  };
  private static final IPermission DEMO_GRANTED_PERMISSIONS[] = {

  TASK_WRITE_EXPIRY_TIMESTAMP,

  DOCUMENT_OF_INVOLVED_CASE_WRITE

  };

  /**
   * Checks if the user has the given role
   *
   * @param user user
   * @param rolename user
   * @return true - user has the given role (direct or indirect) <br>
   *         false - user does not have to given role
   * @throws PersistencyException user
   * @throws Exception user
   */
  public static boolean hasRole(final IUser user, final String rolename) throws PersistencyException, Exception {
   // this is faster because  no additional security issue
    return Sudo.call(
        () -> {
          int t = 5; // number retries
          while (t >= 0) {
            try {
              IRole role = ISecurityContext.current().roles().find(rolename);
              if (role != null) {
                // return role.getAllUsers().contains(user);
                return user.getAllRoles().contains(role); // allowed by EON-Security, configured
                                                          // correctly in
                                                          // LDAP-Setting of server (do not use
                                                          // "groups of a user")
              }
              t = -1;
            } catch (Exception ise) {
              Ivy.log().error("Cannot connect for roles:" + ise);
              Ivy.log().error("Retries left:" + t);
              t--;
              int s = 1;
              if (Ivy.session() != null) {
                s = Ivy.session().getIdentifier() + 1;
                while (s > 10) {
                  s = s / 2;
                }
              }
              try {
                Thread.sleep(1000 * s); // wait N secs
              } catch (InterruptedException ie) {
                // NOP
              }
            }
          }
          return false;
        });
  }

  /**
   * Checks if the session user has the given role
   *
   * @param session user
   * @param rolename user
   * @return true - session user has the given role (direct or indirect) <br>
   *         false - session user does not have to given role
   * @throws Exception Exception
   */
  public static boolean hasRole(final IWorkflowSession session, final String rolename) throws Exception {
    return Sudo.call(
        () -> {
          int t = 5; // number retries
          while (t >= 0) {
            try {
              IRole role = ISecurityContext.current().roles().find(rolename);
              if (role != null) {
                return session.hasRole(role, true);
              }
              t = -1;
            } catch (Exception ise) {
              Ivy.log().error("Cannot connect for roles:" + ise);
              Ivy.log().error("Retries left:" + t);
              t--;
              int s = 1;
              if (Ivy.session() != null) {
                s = Ivy.session().getIdentifier() + 1;
                while (s > 10) {
                  s = s / 2;
                }
              }
              try {
                Thread.sleep(1000 * s); // wait N secs
              } catch (InterruptedException ie) {
                // NOP
              }
            }
          }
          return false;
        });
  }


  /**
   * Finds all users that own the given role (direct or indirect
   *
   * @param rolename rolename
   * @return List<IUser>
   */
  public static List<IUser> findUsersForRole(final String rolename) {
    List<IUser> result = new ArrayList<IUser>();
    try {
      result = Sudo.call(
              () -> {
                List<IUser> result1 = new ArrayList<IUser>();
                IRole role = ISecurityContext.current().roles().find(rolename);
                if (role != null) {
                  result1 = role.users().allPaged().stream().collect(Collectors.toList());
                }
                return result1;
              });
    } catch (Exception e) {
    }
    return result;
  }

  /**
   * returns all workflow users
   *
   * @return List<IUser>
   */
  public static List<IUser> findAllUsers() {
    List<IUser> result = new ArrayList<IUser>();
    try {
      result = Sudo.call(
          () -> {
            List<IUser> result1 = new ArrayList<IUser>();
            List<IUser> users = ISecurityContext.current().users().paged().stream().collect(Collectors.toList());
            for (IUser u : users) {
              if (!u.getSecurityMemberId().equals(ISecurityContext.current().users().system().getSecurityMemberId())) {
                result1.add(u);
              }
            }
            return result1;
          });
    } catch (Exception e) {
    }
    return result;

  }

  private static void updatePermissionsOfAdminUser() {
    var admin = ISecurityContext.current().users().find("admin");
    if (admin != null) {
      for (IPermission permission : ADMIN_PERMISSIONS) {
        ISecurityContext.current().securityDescriptor().grantPermission(permission, admin);
      }
    }
  }

  public static void updatePermissionsOfTestUsers() {
    updatePermissionsOfAdminUser();
    updatePermissionsOfDemoUser();
  }

  private static void updatePermissionsOfDemoUser() {
    var demo = ISecurityContext.current().users().find("demo");
    if (demo == null) {
      return;
    }
    for (IPermission iPermission : DEMO_DENIED_PERMISSIONS) {
      ISecurityContext.current().securityDescriptor().denyPermission(iPermission, demo);
    }
    for (IPermission permission : DEMO_GRANTED_PERMISSIONS) {
      ISecurityContext.current().securityDescriptor().grantPermission(permission, demo);
    }
  }
}
