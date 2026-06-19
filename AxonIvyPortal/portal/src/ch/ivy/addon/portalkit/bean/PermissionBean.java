package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

/**
 * Handle permission to see functionality belonging to Portal Administrator
 *
 */
@Named
@RequestScoped
public class PermissionBean implements Serializable {
  private static final long serialVersionUID = 1L;

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

      IRole adminRole = ISecurityContext.current().roles().find(AXONIVY_PORTAL_ADMIN);
      return sessionUser.hasRole(adminRole);
    } catch (Exception e) {
      Ivy.log().error(e);
      return false;
    }
  }

}
