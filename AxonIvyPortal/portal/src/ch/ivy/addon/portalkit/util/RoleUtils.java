/*
 * RoleUtils.java
 */
package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.component.dto.RoleDTO;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.publicapi.RoleAPI;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;

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
    return IvyExecutor.executeAsSystem(() -> {
      return ISecurityContext.current().roles().all();
    });
  }

  /**
   * Find role by name
   * @param name
   *
   * @return <IRole> : role
   */
  @PublicAPI
  public static IRole findRole(String name) {
    return IvyExecutor.executeAsSystem(() -> {
      return ISecurityContext.current().roles().find(name);
    });
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
   * Get all roles of security context exclude some roles has properties HIDE_IN_DELEGATION and HIDE
   *
   * @return List<IRole> : All roles of security context exclude some roles has properties
   *         HIDE_IN_DELEGATION
   *         HIDE
   */
  public static List<IRole> getRolesForDelegate() {
    return IvyExecutor.executeAsSystem(() -> {
      List<IRole> roles = new ArrayList<>();
      List<IRole> securityRolesTmp = filterVisibleRoles(ISecurityContext.current().roles().all());
      for (IRole role : securityRolesTmp) {
        // Ignore the role has value in property HIDE_IN_DELEGATION
        if (role.getProperty(HIDE_IN_DELEGATION) != null) {
          continue;
        }
        // Add entry to visible List
        roles.add(role);
      }
      return roles;
    });
  }

  /**
   * Set property for passed role
   * @deprecated Use {@link RoleAPI#setProperty(IRole, String, String)} instead
   * @param role role for setting property
   * @param key property key
   * @param value property value
   */
  @Deprecated
  public static void setProperty(final IRole role, final String key, final String value) {
    IvyExecutor.executeAsSystem(() -> {
      role.setProperty(key, value);
      invalidateCacheForRoles();
      return Void.class;
    });
  }

  private static void invalidateCacheForRoles() {
    IvyCacheService.newInstance().invalidateSessionEntry(ISecurityContext.current().getName(), IvyCacheIdentifier.ROLES_IN_SECURITY_CONTEXT);
  }

  /**
   * Remove property for passed role
   * @deprecated Use {@link RoleAPI#removeProperty(IRole, String)} instead
   * @param role role to remove property
   * @param key key to remove property
   */
  @Deprecated
  public static void removeProperty(final IRole role, final String key) {
    IvyExecutor.executeAsSystem(() -> {
      role.removeProperty(key);
      invalidateCacheForRoles();
      return Void.class;
    });
  }

  /**
   * Set hide property for specific roles by default
   */
  public static void setHidePropertyForDefaultHiddenRoles() {
    List<String> defaultHiddenRoleNames = Arrays.asList(DEFAULT_HIDDEN_ROLES);
    String hideProperty = AdditionalProperty.HIDE.toString();

    defaultHiddenRoleNames.forEach(roleName -> {
      IRole role = findRole(roleName);
      if (role != null && role.getProperty(hideProperty) == null) {
        setProperty(role, hideProperty, hideProperty);
      }
    });
  }

  private static Predicate<IRole> predicateIsHiddenRole() {
    return role -> Objects.nonNull(role.getProperty(AdditionalProperty.HIDE.toString()));
  }

  private static Predicate<IRole> predicateIsVisibleRole() {
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
    return filterRole(roles, predicateIsHiddenRole());
  }

  private static List<IRole> filterVisibleRoles(List<IRole> roles) {
    return filterRole(roles, predicateIsVisibleRole());
  }

  private static List<IRole> filterRole(List<IRole> roles, Predicate<IRole> predicate) {
    return CollectionUtils.emptyIfNull(roles).stream().filter(predicate).collect(Collectors.toList());
  }
}
