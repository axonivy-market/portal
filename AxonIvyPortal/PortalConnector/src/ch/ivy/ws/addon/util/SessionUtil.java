package ch.ivy.ws.addon.util;

import java.util.concurrent.Callable;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IPermissionAccess;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.security.IUser;

/**
 * Session Utility for SSO
 * 
 */
public class SessionUtil {

  private SessionUtil() {}

  public static void loginAsUser(final String username) throws Exception { //NOSONAR
    final ISecurityContext securityContext = Ivy.wf().getSecurityContext();
    securityContext.executeAsSystemUser(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        Ivy.session().authenticateSessionUser(securityContext.findUser(username), "customAuth",
            Ivy.wfTask().getId());
        return null;
      }
    });
  }

  public static boolean doesUserHavePermission(IApplication application, String username, IPermission permission) {
    IPermissionAccess permissionAccess = null;
    try {
      IUser user = application.getSecurityContext().findUser(username);
      ISecurityDescriptor securityDescriptor = application.getSecurityDescriptor();
      if (user != null){
        permissionAccess = securityDescriptor.getPermissionAccess(permission, user);
        return permissionAccess.isGranted();
      }
    } catch (PersistencyException e) {
      Ivy.log().error(e);
      return false;
    }
    return false;
  }
}
