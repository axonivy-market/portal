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
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.environment.Ivy;
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
  public static final String HIDE = "HIDE";
  
  private RoleUtils() {
  }
  
  /**
   * Get all roles of current Ivy Application
   * 
   * @return List<IRole> : All roles of current Ivy Application
   */
  @PublicAPI
  public static List<IRole> getAllRoles() {
    return IvyExecutor.executeAsSystem(() -> {
      ISecurityContext security = Ivy.wf().getSecurityContext();
      return security.getRoles();
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
      ISecurityContext security = Ivy.wf().getSecurityContext();
      return security.findRole(name);
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
    List<IRole> allRoles = getAllRoles();
    if (allRoles != null) {
      return allRoles.stream().filter(isHiddenRole()).collect(Collectors.toList());
    } else {
      return new ArrayList<>();
    }
  }

  /**
   * <p>Get all visible roles of current Ivy Application.</p>
   * <p>The visible role is the role which doesn't have the HIDE property.</p>
   * 
   * @return List<IRole> : All visible roles of current Ivy Application
   */
  @PublicAPI
  public static List<IRole> getAllVisibleRoles() {
    List<IRole> allRoles = getAllRoles();
    if (allRoles != null) {
      return allRoles.stream().filter(isVisibleRole()).collect(Collectors.toList());
    } else {
      return new ArrayList<>();
    }
   
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
    return user.getRoles().stream()
        .filter(isHiddenRole())
        .collect(Collectors.toList());
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
    return user.getAllRoles().stream()
        .filter(isHiddenRole())
        .collect(Collectors.toList());
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
    return role.getRoles().stream()
        .filter(isHiddenRole())
        .collect(Collectors.toList());
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
    return role.getAllRoles().stream()
        .filter(isHiddenRole())
        .collect(Collectors.toList());
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
    return user.getRoles().stream()
        .filter(isVisibleRole())
        .collect(Collectors.toList());
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
    return user.getAllRoles().stream()
        .filter(isVisibleRole())
        .collect(Collectors.toList());
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
    return role.getRoles().stream()
        .filter(isVisibleRole())
        .collect(Collectors.toList());
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
    return role.getAllRoles().stream()
        .filter(isVisibleRole())
        .collect(Collectors.toList());
  }

  /**
   * Get all roles of current Ivy Application exclude some roles has properties HIDE_IN_DELEGATION and HIDE
   * 
   * @return List<IRole> : All roles of current Ivy Application exclude some roles has properties
   *         HIDE_IN_DELEGATION
   *         HIDE
   */
  public static List<IRole> getRolesForDelegate() {
    return IvyExecutor.executeAsSystem(() -> {
      List<IRole> roles = new ArrayList<>();
      List<IRole> securityRolesTmp = Ivy.wf().getSecurityContext().getRoles();
      for (IRole role : securityRolesTmp) {
        // Ignore the role has value in property HIDE_IN_DELEGATION
        // or the role has value in property HIDE
        if (role.getProperty(HIDE_IN_DELEGATION) != null || role.getProperty(HIDE) != null) {
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
   * 
   * @param role role for setting property
   * @param key property key
   * @param value property value
   */
  @PublicAPI
  public static void setProperty(final IRole role, final String key, final String value) {
    IvyExecutor.executeAsSystem(() ->{
      role.setProperty(key, value);
      invalidCacheForRoles();
      return Void.class;
    });
  }

  private static void invalidCacheForRoles() {
    IvyCacheService.newInstance().invalidateEntryOfGroup(Ivy.request().getApplication().getName(), IvyCacheIdentifier.ROLES_IN_APPLICATION);
  }
  
  /**
   * Remove property for passed role
   * 
   * @param role role to remove property
   * @param key key to remove property
   */
  @PublicAPI
  public static void removeProperty(final IRole role, final String key) {
    IvyExecutor.executeAsSystem(() ->{
      role.removeProperty(key);
      invalidCacheForRoles();
      return Void.class;
    });
  }

  /**
   * Set hide property for specific roles by default
   */
  public static void setHidePropertyForDefaultHiddenRoles() {
    List<String> defaultHiddenRoleNames = Arrays.asList(DEFAULT_HIDDEN_ROLES);
    
    defaultHiddenRoleNames.forEach(roleName -> {
      IRole role = findRole(roleName);
      if (role != null && role.getProperty(HIDE) == null) {
        setProperty(role, HIDE, HIDE);
      }
    });
  }

  private static Predicate<IRole> isHiddenRole() {
    return role -> Objects.nonNull(role.getProperty(HIDE));
  }

  private static Predicate<IRole> isVisibleRole() {
    return role -> Objects.isNull(role.getProperty(HIDE));
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

  /**
   * Finds the roles in current application.
   * 
   * @param fromRoles parent role name list
   * @param excludedRoleNames role name list exclude
   * @return role list
   */
  public static List<RoleDTO> findRoles(List<String> fromRoleNames, List<String> excludedRoleNames, String query) {
    List<RoleDTO> roles = findAllRolesFromRoles(fromRoleNames);

    if (CollectionUtils.isNotEmpty(roles) && CollectionUtils.isNotEmpty(excludedRoleNames)) {
      roles = excludeRoleNames(roles, excludedRoleNames);
    }

    if (CollectionUtils.isNotEmpty(roles) && StringUtils.isNotEmpty(query)) {
      roles = filterRoleDTO(roles, query);
    }

    roles.sort((first, second) -> StringUtils.compareIgnoreCase(first.getDisplayName(), second.getDisplayName()));
    
    return roles;
  }

  private static List<RoleDTO> findAllRolesFromRoles(List<String> roleNames) {
    if (CollectionUtils.isNotEmpty(roleNames)) {
      return findAllChildrenOfRoles(roleNames);
    }

    return findVisibleRoles();
  }

  private static List<RoleDTO> findAllChildrenOfRoles(List<String> fromRoles) {
    List<RoleDTO> roles = new ArrayList<>();
    for (String roleName : fromRoles) {
      IRole role = findRole(roleName);
      if (Objects.nonNull(role)) {
        roles = mergeTwoRoleList(roles, getAllChildrenOfRole(role));
      }
    }

    return roles;
  }

  private static List<RoleDTO> mergeTwoRoleList(List<RoleDTO> firstList, List<RoleDTO> secondList) {
    return Stream.of(firstList, secondList).flatMap(x -> x.stream()).collect(Collectors.toList());
  }

  private static List<RoleDTO> getAllChildrenOfRole(IRole role) {
    return role.getChildRoles().stream().filter(isVisibleRole()).map(childRole -> new RoleDTO(childRole))
        .collect(Collectors.toList());
  }

  private static List<RoleDTO> findVisibleRoles() {
    List<IRole> allRoles = getAllRoles();
    if (CollectionUtils.isNotEmpty(allRoles)) {
      return allRoles.stream().filter(isVisibleRole()).map(role -> new RoleDTO(role)).collect(Collectors.toList());
    } else {
      return new ArrayList<>();
    }
  }

  private static List<RoleDTO> excludeRoleNames(List<RoleDTO> roleDTOs, List<String> excludedRoleNames) {
    List<RoleDTO> roles = roleDTOs;
    for (String excludedRoleName : excludedRoleNames) {
      roles = excludeRoleDTOByName(roles, excludedRoleName);
    }
    return roles;
  }

  private static List<RoleDTO> excludeRoleDTOByName(List<RoleDTO> roleDTOs, String roleName) {
    List<RoleDTO> roles = new ArrayList<>();
    for (RoleDTO roleDTO : roleDTOs) {
      if (!isNameOfRole(roleDTO, roleName)) {
        roles.add(roleDTO);
      }
    }
    return roles;
  }

  private static boolean isNameOfRole(RoleDTO roleDTO, String roleName) {
    return roleDTO.getDisplayName().equalsIgnoreCase(roleName.trim())
        || roleDTO.getMemberName().equalsIgnoreCase(roleName.trim())
        || roleDTO.getName().equalsIgnoreCase(roleName.trim());
  }
}
