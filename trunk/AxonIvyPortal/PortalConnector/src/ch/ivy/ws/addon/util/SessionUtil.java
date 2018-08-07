package ch.ivy.ws.addon.util;

import java.util.concurrent.Callable;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IPermissionAccess;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.security.IUser;

/**
 * Session Utility for SSO
 * 
 * @author mde
 *
 */
public class SessionUtil {
  public static void loginAsUser(final String username) throws Exception {
    final ISecurityContext securityContext = Ivy.wf().getSecurityContext();
    securityContext.executeAsSystemUser(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        Ivy.session().authenticateSessionUser(securityContext.findUser(username), "customAuth",
            Ivy.wfTask().getIdentifier());
        return null;
      }
    });
  }

  public static boolean doesUserHavePermission(IApplication application, String username, IPermission permission) {
    IUser user = application.getSecurityContext().findUser(username);
    ISecurityDescriptor securityDescriptor = application.getSecurityDescriptor();
    IPermissionAccess permissionAccess = securityDescriptor.getPermissionAccess(permission, user);
    return permissionAccess.isGranted();
  }
}
