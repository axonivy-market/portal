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
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.server.ServerFactory;
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
    // return Ivy.wf().getSecurityContext().executeAsSystemUser(
    return ServerFactory.getServer().getSecurityManager().executeAsSystem( // this is faster because
                                                                           // no additional
                                                                           // security issue
        new Callable<Boolean>() {
          @Override
          public Boolean call() throws Exception {
            int t = 5; // number retries
            while (t >= 0) {
              try {
                IRole role = Ivy.security().roles().find(rolename);
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
          }
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
    // return Ivy.wf().getSecurityContext().executeAsSystemUser(
    return ServerFactory.getServer().getSecurityManager().executeAsSystem( // this is faster because
                                                                           // no additional
                                                                           // security issue
        new Callable<Boolean>() {
          @Override
          public Boolean call() throws Exception {
            int t = 5; // number retries
            while (t >= 0) {
              try {
                IRole role = Ivy.security().roles().find(rolename);
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
                // } catch (PersistencyException pe) {
                // Ivy.log().error("Cannot connect for roles:"+pe);
                // Ivy.log().error("Retries left:"+t);
                // t--;
                // int s = 1;
                // if (Ivy.session() != null) {
                // s = Ivy.session().getIdentifier()+1;
                // while (s>10) {
                // s = s / 2;
                // }
                // }
                // try {
                // Thread.sleep(1000 * s); //wait N secs
                // }
                // catch (InterruptedException ie) {
                // //NOP
                // }
              }
            }
            return false;
          }
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
      // result = Ivy.wf().getSecurityContext().executeAsSystemUser(
      result = ServerFactory.getServer().getSecurityManager().executeAsSystem( // this is faster
                                                                               // because no
                                                                               // additional
                                                                               // security issue
          new Callable<List<IUser>>() {
            @Override
            public List<IUser> call() throws Exception {
              List<IUser> result = new ArrayList<IUser>();

              IRole role = Ivy.security().roles().find(rolename);
              if (role != null) {
                result = role.users().allPaged().stream().collect(Collectors.toList());
              }
              return result;
            }
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
      // result = Ivy.wf().getSecurityContext().executeAsSystemUser(
      result = ServerFactory.getServer().getSecurityManager().executeAsSystem( // this is faster
                                                                               // because no
                                                                               // additional
                                                                               // security issue
          new Callable<List<IUser>>() {
            @Override
            public List<IUser> call() throws Exception {
              List<IUser> result = new ArrayList<IUser>();

              List<IUser> users = Ivy.security().users().paged().stream().collect(Collectors.toList());
              for (IUser u : users) {
                if (u.getId() != Ivy.security().users().system().getId()) {
                  result.add(u);
                }
              }
              return result;
            }
          });
    } catch (Exception e) {
    }
    return result;

  }

  private static void updatePermissionsOfAdminUser() {
    IApplication application = IApplication.current();
    IUser admin = application.getSecurityContext().users().find("admin");

    if (admin != null) {
      for (IPermission permission : ADMIN_PERMISSIONS) {
        application.getSecurityDescriptor().grantPermission(permission, admin);
      }
    }
  }

  public static void updatePermissionsOfTestUsers() {
    updatePermissionsOfAdminUser();
    updatePermissionsOfDemoUser();
  }

  private static void updatePermissionsOfDemoUser() {
    IApplication application = IApplication.current();
    IUser demo = application.getSecurityContext().users().find("demo");
    for (IPermission iPermission : DEMO_DENIED_PERMISSIONS) {
      application.getSecurityDescriptor().denyPermission(iPermission, demo);
    }
    for (IPermission permission : DEMO_GRANTED_PERMISSIONS) {
      application.getSecurityDescriptor().grantPermission(permission, demo);
    }
  }
}
