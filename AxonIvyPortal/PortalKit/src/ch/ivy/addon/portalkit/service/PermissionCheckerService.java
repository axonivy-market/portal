package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class PermissionCheckerService {

  public boolean hasPermission(IPermission permission) {
    IWorkflowSession currentSession = Ivy.session();
    ISecurityDescriptor applicationSecurityDescriptor = IApplication.current().getSecurityDescriptor();
    return currentSession.hasPermission(applicationSecurityDescriptor, permission);
  }

  public boolean hasAllPermissions(IPermission permission, IPermission... permissions) {
    boolean hasAllPermissions = hasPermission(permission);
    for (IPermission perm : permissions) {
      hasAllPermissions &= hasPermission(perm);
    }
    return hasAllPermissions;
  }

  public boolean hasAtLeaseOnePermission(IPermission permission, IPermission... permissions) {
    boolean hasAtLeastOnePermission = hasPermission(permission);
    for (IPermission perm : permissions) {
      hasAtLeastOnePermission |= hasPermission(perm);
    }
    return hasAtLeastOnePermission;
  }
  
  private boolean hasPermissionOnApp(String appName, IPermission permission) {
    IApplication app = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(appName);
    if (app != null) {
      return PermissionUtils.hasPermission(app, Ivy.session().getSessionUserName(), permission);
    }
    return false;
  }
  
  public boolean hasAtLeaseOnePermissionOnApp(String appName, IPermission... permissions) {
    boolean hasAtLeastOnePermission = false;
    
    for (IPermission perm : permissions) {
      hasAtLeastOnePermission |= hasPermissionOnApp(appName, perm);
    }
    return hasAtLeastOnePermission;
  }
}
