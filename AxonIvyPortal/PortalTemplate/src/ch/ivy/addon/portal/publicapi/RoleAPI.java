package ch.ivy.addon.portal.publicapi;

import ch.ivy.addon.portalkit.util.RoleUtils;
import ch.ivyteam.ivy.security.IRole;

/**
 * Portal API for {@link IRole}
 *
 */
public final class RoleAPI {
  private RoleAPI() {}
  
  /**
   * Set property for passed role
   * 
   * @param role role for setting property
   * @param key property key
   * @param value property value
   */
  public static void setProperty(IRole role, String key, String value) {
    RoleUtils.setProperty(role, key, value);
  }
  
  /**
   * Remove property for passed role
   * 
   * @param role role to remove property
   * @param key key to remove property
   */
  public static void removeProperty(IRole role, String key) {
    RoleUtils.removeProperty(role, key);
  }
}
