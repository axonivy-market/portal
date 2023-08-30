package com.axonivy.portal.components.publicapi;

import com.axonivy.portal.components.constant.IvyCacheIdentifier;
import com.axonivy.portal.components.service.IvyCacheService;

import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;

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
    Sudo.get(() -> {
      role.setProperty(key, value);
      IvyCacheService.newInstance().invalidateSessionEntry(ISecurityContext.current().getName(), IvyCacheIdentifier.ROLES_IN_SECURITY_CONTEXT);
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
    Sudo.get(() -> {
      role.removeProperty(key);
      IvyCacheService.newInstance().invalidateSessionEntry(ISecurityContext.current().getName(), IvyCacheIdentifier.ROLES_IN_SECURITY_CONTEXT);
      return Void.class;
    });
  }
}
