/*
 * RoleUtils.java
 */
package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;

/**
 * Provide the utilities related to role
 * 
 * @author bolt
 */
public final class RoleUtils {

  private static final String HIDE_IN_DELEGATION = "HIDE_IN_DELEGATION";
  private static final String[] DEFAULT_HIDDEN_ROLES = {"AXONIVY_PORTAL_ADMIN"};
  
  private RoleUtils() {}
  
  /**
   * Get all roles of current Ivy Application
   * 
   * @return List<IRole> : All roles of current Ivy Application
   */
  @PublicAPI
  public static List<IRole> getAllRoles() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<IRole>>() {
        @Override
        public List<IRole> call() throws Exception {
          return Ivy.wf().getSecurityContext().getRoles();
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return Collections.emptyList();
  }
  
  /**
   * Find role by name
   * @param name 
   * 
   * @return <IRole> : role
   */
  @PublicAPI
  public static IRole findRole(String name) {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<IRole>() {
        @Override
        public IRole call() throws Exception {
          return Ivy.wf().getSecurityContext().findRole(name);
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return null;
  }

  /**
   * <p>Get all hidden roles of current Ivy Application.</p>
   * <p>The hidden role is the role which has the HIDE property.</p>
   * 
   * @return List<IRole> : All hidden roles of current Ivy Application
   */
  @PublicAPI
  public static List<IRole> getAllHiddenRoles() {
    return filterHiddenRoles(getAllRoles());
  }

  /**
   * <p>Get all visible roles of current Ivy Application.</p>
   * <p>The visible role is the role which doesn't have the HIDE property.</p>
   * 
   * @return List<IRole> : All visible roles of current Ivy Application
   */
  @PublicAPI
  public static List<IRole> getAllVisibleRoles() {
    return filterVisibleRoles(getAllRoles());
  }

  /**
   * <p>Get hidden roles the user owns direct.</p>
   * <p>The hidden role is the role which has the HIDE property.</p>
   * 
   * @param user user to get roles
   * @return List<IRole> : All hidden roles of the given user
   */
  @PublicAPI
  public static List<IRole> getHiddenRoles(IUser user) {
    return filterHiddenRoles(user.getRoles());
  }
  
  /**
   * <p>Get all hidden roles the user owns (directly and indirectly).</p>
   * <p>The hidden role is the role which has the HIDE property.</p>
   * 
   * @param user user to get roles
   * @return List<IRole> : All hidden roles of the given user
   */
  @PublicAPI
  public static List<IRole> getAllHiddenRoles(IUser user) {
    return filterHiddenRoles(user.getAllRoles());
  }

  /**
   * <p>Gets the directly owned hidden roles. The parent role and all roles where this role is a direct role member.</p> 
   * <p>This list never contains this role itself.</p>
   * <p>The hidden role is the role which has the HIDE property.</p>
   * 
   * @param role role to get sub-roles
   * @return List<IRole> : All hidden sub-roles of the given role
   */
  @PublicAPI
  public static List<IRole> getHiddenRoles(IRole role) {
    return filterHiddenRoles(role.getRoles());
  }
  
  /**
   * <p>Gets all owned hidden roles (directly and indirectly) of this role.</p> 
   * <p>It is possible that the role itself is also contained in the list.</p>
   * <p>The hidden role is the role which has the HIDE property.</p>
   * 
   * @param role role to get sub-roles
   * @return List<IRole> : All hidden sub-roles of the given role
   */
  @PublicAPI
  public static List<IRole> getAllHiddenRoles(IRole role) {
    return filterHiddenRoles(role.getAllRoles());
  }

  /**
   * <p>Get visible roles the user owns direct.</p>
   * <p>The visible role is the role which doesn't have the HIDE property.</p>
   * 
   * @param user user to get roles
   * @return List<IRole> : All visible roles of the given user
   */
  @PublicAPI
  public static List<IRole> getVisibleRoles(IUser user) {
    return filterVisibleRoles(user.getRoles());
  }
  
  /**
   * <p>Get all visible roles the user owns (directly and indirectly).</p>
   * <p>The visible role is the role which doesn't have the HIDE property.</p>
   * 
   * @param user user to get roles
   * @return List<IRole> : All hidden roles of the given user
   */
  @PublicAPI
  public static List<IRole> getAllVisibleRoles(IUser user) {
    return filterVisibleRoles(user.getAllRoles());
  }

  /**
   * <p>Gets the directly owned visible roles. The parent role and all roles where this role is a direct role member.</p> 
   * <p>This list never contains this role itself.</p>
   * <p>The visible role is the role which doesn't have the HIDE property.</p>
   * 
   * @param role role to get sub-roles
   * @return List<IRole> : All visible sub-roles of the given role
   */
  @PublicAPI
  public static List<IRole> getVisibleRoles(IRole role) {
    return filterVisibleRoles(role.getRoles());
  }
  
  /**
   * <p>Gets all owned visible roles (directly and indirectly) of this role.</p> 
   * <p>It is possible that the role itself is also contained in the list.</p>
   * <p>The hidden role is the role which has the HIDE property.</p>
   * 
   * @param role role to get sub-roles
   * @return List<IRole> : All hidden sub-roles of the given role
   */
  @PublicAPI
  public static List<IRole> getAllVisibleRoles(IRole role) {
    return filterVisibleRoles(role.getAllRoles());
  }

  /**
   * Get all roles of current Ivy Application exclude some roles has properties HIDE_IN_DELEGATION and HIDE
   * 
   * @return List<IRole> : All roles of current Ivy Application exclude some roles has properties
   *         HIDE_IN_DELEGATION
   *         HIDE
   */
  public static List<IRole> getRolesForDelegate() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<IRole>>() {
        @Override
        public List<IRole> call() throws Exception {
          List<IRole> roles = new ArrayList<>();
          List<IRole> securityRolesTmp = filterVisibleRoles(Ivy.wf().getSecurityContext().getRoles());
          for (IRole role : securityRolesTmp) {
            // Ignore the role has value in property HIDE_IN_DELEGATION
            if (role.getProperty(HIDE_IN_DELEGATION) != null) {
              continue;
            }
            // Add entry to visible List
            roles.add(role);
          }
          return roles;
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return Collections.emptyList();
  }

  /**
   * Set property for passed role
   * 
   * @param role role for setting property
   * @param key property key
   * @param value property value
   */
  @PublicAPI
  public static void setProperty(final IRole role, final String key, final String value) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
          role.setProperty(key, value);
          invalidCacheForRoles();
          return null;
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  /**
   * Remove property for passed role
   * 
   * @param role role to remove property
   * @param key key to remove property
   */
  @PublicAPI
  public static void removeProperty(final IRole role, final String key) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
          role.removeProperty(key);
          invalidCacheForRoles();
          return null;
        }

      });
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  private static void invalidCacheForRoles() {
    IvyCacheService.newInstance().invalidateSessionEntry(Ivy.request().getApplication().getName(), IvyCacheIdentifier.ROLES_IN_APPLICATION);
  }

  /**
   * Set hide property for specific roles by default
   */
  public static void setHidePropertyForDefaultHiddenRoles() {
    List<String> defaultHiddenRoleNames = Arrays.asList(DEFAULT_HIDDEN_ROLES);
    
    defaultHiddenRoleNames.forEach(roleName -> {
      IRole role = findRole(roleName);
      if (role != null && role.getProperty(AdditionalProperty.HIDE.toString()) == null) {
        setProperty(role, AdditionalProperty.HIDE.toString(), AdditionalProperty.HIDE.toString());
      }
    });
  }

  private static Predicate<IRole> isHiddenRole() {
    return role -> Objects.nonNull(role.getProperty(AdditionalProperty.HIDE.toString()));
  }

  private static Predicate<IRole> isVisibleRole() {
    return role -> Objects.isNull(role.getProperty(AdditionalProperty.HIDE.toString()));
  }

  /**
   * Filter list of ivy roles by name based on provided query
   * 
   * @param roles roles need to be filtered
   * @param query provided query
   * @return Filtered list of ivy roles
   */
  public static List<IRole> filterRoles(List<IRole> roles, String query) {
    if (StringUtils.isEmpty(query)) {
      return roles;
    }

    List<IRole> filterRoles = new ArrayList<>();
    for (IRole role : roles) {
      if (StringUtils.containsIgnoreCase(role.getDisplayName(), query) || StringUtils.containsIgnoreCase(role.getMemberName(), query)) {
        filterRoles.add(role);
      }
    }

    return filterRoles;
  }
  
  public static List<RoleDTO> filterRoleDTO(List<RoleDTO> roles, String query) {
    if (StringUtils.isEmpty(query)) {
      return roles;
    }

    List<RoleDTO> filterRoles = new ArrayList<>();
    for (RoleDTO role : roles) {
      if (StringUtils.containsIgnoreCase(role.getDisplayName(), query) || StringUtils.containsIgnoreCase(role.getMemberName(), query)) {
        filterRoles.add(role);
      }
    }

    return filterRoles;
  }
  
  private static List<IRole> filterHiddenRoles(List<IRole> roles) {
    return CollectionUtils.emptyIfNull(roles).stream().filter(isHiddenRole()).collect(Collectors.toList());
  }

  private static List<IRole> filterVisibleRoles(List<IRole> roles) {
    return CollectionUtils.emptyIfNull(roles).stream().filter(isVisibleRole()).collect(Collectors.toList());
  }

}
