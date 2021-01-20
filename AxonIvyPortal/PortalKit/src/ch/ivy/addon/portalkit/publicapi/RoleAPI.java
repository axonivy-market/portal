package ch.ivy.addon.portalkit.publicapi;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
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
    IvyExecutor.executeAsSystem(() -> {
      role.setProperty(key, value);
      IvyCacheService.newInstance().invalidateSessionEntry(Ivy.request().getApplication().getName(), IvyCacheIdentifier.ROLES_IN_APPLICATION);
      return Void.class;
    });
  }
  
  /**
   * Remove property for passed role
   * 
   * @param role role to remove property
   * @param key key to remove property
   */
  public static void removeProperty(IRole role, String key) {
    IvyExecutor.executeAsSystem(() -> {
      role.removeProperty(key);
      IvyCacheService.newInstance().invalidateSessionEntry(Ivy.request().getApplication().getName(), IvyCacheIdentifier.ROLES_IN_APPLICATION);
      return Void.class;
    });
  }
}
