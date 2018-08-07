package ch.ivy.addon.portalkit.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;

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
    Boolean hasPermission = false;

    try {
      IUser user = Ivy.session().getSessionUser();
      if (user == null) {
        return false;
      }
      List<IRole> listRole = user.getRoles();
      for (IRole iRole : listRole) {
        if (iRole != null && AXONIVY_PORTAL_ADMIN.equals(iRole.getName())) {
          hasPermission = true;
          break;
        }
      }
    } catch (Exception e) {
      Ivy.log().error(e.getMessage());
    }
    return hasPermission;
  }
}
