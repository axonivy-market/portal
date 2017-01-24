package ch.ivy.addon.portalkit.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

/**
 * Handle permission to see functionality belonging to Portal Administrator
 * 
 */
@ManagedBean
@RequestScoped
public class PermissionBean {

  private static final String AXONIVY_PORTAL_ADMIN = "AXONIVY_PORTAL_ADMIN";

  /**
   * If user don't have role ADMIN, then redirect to no permission page
   * 
   * @return true if user has AXONIVY_PORTAL_ADMIN role, otherwise return false.
   */
  public boolean hasAdminPermission() {
    try {
      IWorkflowSession sessionUser = Ivy.session();
      if (sessionUser == null) {
        return false;
      }

      IRole adminRole = Ivy.wf().getSecurityContext().findRole(AXONIVY_PORTAL_ADMIN);
      return sessionUser.hasRole(adminRole, true);
    } catch (Exception e) {
      Ivy.log().error(e.getMessage());
      return false;
    }
  }
}
