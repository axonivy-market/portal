package ch.ivy.addon.portalkit.test.util;

import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_CREATE_SUBSTITUTE;
import static ch.ivyteam.ivy.security.IPermission.USER_DELETE_ABSENCE;
import static ch.ivyteam.ivy.security.IPermission.USER_DELETE_SUBSTITUTE;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_ABSENCES;
import static ch.ivyteam.ivy.security.IPermission.USER_READ_SUBSTITUTES;
import static ch.ivyteam.ivy.security.IPermission.TASK_READ_ALL;
import static ch.ivyteam.ivy.security.IPermission.CASE_READ_ALL;
import static ch.ivyteam.ivy.security.IPermission.CASE_DESTROY;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class SecurityUtils {
  private static final IPermission ADMIN_PERMISSIONS[] = {USER_READ_ABSENCES, USER_CREATE_ABSENCE, USER_DELETE_ABSENCE,
      USER_READ_SUBSTITUTES, USER_CREATE_SUBSTITUTE, USER_DELETE_SUBSTITUTE, TASK_READ_ALL, CASE_READ_ALL, CASE_DESTROY};

  /**
   * Checks if the user has the given role
   * 
   * @param user user
   * @param rolename user
   * @return true - user has the given role (direct or indirect) <br>
   *         false - user does not have to given role
   * @throws EnvironmentNotAvailableException user
   * @throws PersistencyException user
   * @throws Exception user
   */
  public static boolean hasRole(final IUser user, final String rolename) throws EnvironmentNotAvailableException,
      PersistencyException, Exception {
    // return Ivy.wf().getSecurityContext().executeAsSystemUser(
    return ServerFactory.getServer().getSecurityManager().executeAsSystem( // this is faster because no additional
                                                                           // security issue
        new Callable<Boolean>() {
          public Boolean call() throws Exception {
            int t = 5; // number retries
            while (t >= 0) {
              try {
                IRole role = Ivy.wf().getSecurityContext().findRole(rolename);
                if (role != null) {
                  // return role.getAllUsers().contains(user);
                  return user.getAllRoles().contains(role); // allowed by EON-Security, configured correctly in
                                                            // LDAP-Setting of server (do not use "groups of a user")
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
   * @throws EnvironmentNotAvailableException user
   * @throws Exception Exception
   */
  public static boolean hasRole(final IWorkflowSession session, final String rolename)
      throws EnvironmentNotAvailableException, Exception {
    // return Ivy.wf().getSecurityContext().executeAsSystemUser(
    return ServerFactory.getServer().getSecurityManager().executeAsSystem( // this is faster because no additional
                                                                           // security issue
        new Callable<Boolean>() {
          public Boolean call() throws Exception {
            int t = 5; // number retries
            while (t >= 0) {
              try {
                IRole role = Ivy.wf().getSecurityContext().findRole(rolename);
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
      result = ServerFactory.getServer().getSecurityManager().executeAsSystem( // this is faster because no additional
                                                                               // security issue
          new Callable<List<IUser>>() {
            public List<IUser> call() throws Exception {
              List<IUser> result = new ArrayList<IUser>();

              IRole role = Ivy.wf().getSecurityContext().findRole(rolename);
              if (role != null) {
                result = role.getAllUsers();
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
      result = ServerFactory.getServer().getSecurityManager().executeAsSystem( // this is faster because no additional
                                                                               // security issue
          new Callable<List<IUser>>() {
            public List<IUser> call() throws Exception {
              List<IUser> result = new ArrayList<IUser>();

              List<IUser> users = Ivy.wf().getSecurityContext().getUsers();
              for (IUser u : users) {
                if (u.getId() != Ivy.wf().getSecurityContext().getSystemUser().getId()) {
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

  public static void grantAllPermissionsTo(IUser user) {
    Ivy.wf()
        .getApplication()
        .getSecurityDescriptor()
        .grantPermissions(
            Ivy.wf().getApplication().getSecurityDescriptor().getSecurityDescriptorType().getRootPermissionGroup(),
            Ivy.wf().getSecurityContext().getTopLevelRole());
  }

  public static void grantPermissionToAdminUser() {
    IApplication application = Ivy.wf().getApplication();
    IUser admin = application.getSecurityContext().findUser("portaladmin");
    for (IPermission permission : ADMIN_PERMISSIONS) {
      application.getSecurityDescriptor().grantPermission(permission, admin);
    }
  }
}
