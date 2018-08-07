package ch.ivy.addon.portalkit.service;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class PermissionCheckerService {

  public boolean hasPermission(IPermission permission) {
    IWorkflowSession currentSession = Ivy.session();
    ISecurityDescriptor applicationSecurityDescriptor = Ivy.wf().getApplication().getSecurityDescriptor();
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
}
