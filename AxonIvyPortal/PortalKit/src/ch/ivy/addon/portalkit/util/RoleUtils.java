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

import org.apache.commons.lang3.StringUtils;

import ch.ivy.add.portalkit.admin.RolePropertyData;
import ch.ivy.ws.addon.IvyRole;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;

/**
 * Provide the utilities related to role
 * 
 * @author bolt
 */
public final class RoleUtils {

  private RoleUtils() {
  }

  private static final String HIDE_IN_DELEGATION = "HIDE_IN_DELEGATION";
  private static final String[] DEFAULT_HIDDEN_ROLES = {"AXONIVY_PORTAL_ADMIN"};
  public static final String HIDE = "HIDE";

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
          ISecurityContext security = Ivy.wf().getSecurityContext();
          return security.getRoles();
        }
      });
    } catch (PersistencyException e) {
      Ivy.log().error(e);
    } catch (EnvironmentNotAvailableException e) {
      Ivy.log().error(e);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return null;
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
          ISecurityContext security = Ivy.wf().getSecurityContext();
          return security.findRole(name);
        }
      });
    } catch (PersistencyException e) {
      Ivy.log().error(e);
    } catch (EnvironmentNotAvailableException e) {
      Ivy.log().error(e);
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
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<IRole>>() {
        @Override
        public List<IRole> call() throws Exception {
          List<IRole> roles = new ArrayList<IRole>();
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
        }
      });
    } catch (PersistencyException e) {
      Ivy.log().error(e);
    } catch (EnvironmentNotAvailableException e) {
      Ivy.log().error(e);
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
          @SuppressWarnings("unused")
          ISecurityContext security = Ivy.wf().getSecurityContext();
          role.setProperty(key, value);
          return null;
        }
      });
    } catch (PersistencyException e) {
      Ivy.log().error(e);
    } catch (EnvironmentNotAvailableException e) {
      Ivy.log().error(e);
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
          @SuppressWarnings("unused")
          ISecurityContext security = Ivy.wf().getSecurityContext();
          role.removeProperty(key);
          return null;
        }
      });
    } catch (PersistencyException e) {
      Ivy.log().error(e);
    } catch (EnvironmentNotAvailableException e) {
      Ivy.log().error(e);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  /**
   * Get the properties of the given role.
   * 
   * @param role role to get properties.
   * @return List of RolePropertyData
   */
  @PublicAPI
  public static List<RolePropertyData> getProperties(IRole role) {
    List<String> propertyNames = role.getAllPropertyNames();
    List<RolePropertyData> properties = new ArrayList<>();
    for (String propertyName : propertyNames) {
      RolePropertyData property = new RolePropertyData();
      property.setPropertyKey(propertyName);
      property.setValue(role.getProperty(propertyName));
      properties.add(property);
    }
    return properties;
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
   * @return Filtered and sorted list of ivy roles
   */
  public static List<IvyRole> filterIvyRoles(List<IvyRole> roles, String query) {
    if (StringUtils.isEmpty(query)) {
      return roles;
    }

    List<IvyRole> filterRoles = new ArrayList<>();
    for (IvyRole role : roles) {
      if (role.getDisplayName().toLowerCase().contains(query.toLowerCase())
          || role.getMemberName().toLowerCase().contains(query.toLowerCase())) {
        filterRoles.add(role);
      }
    }

    filterRoles.sort((first, second) -> first.getDisplayName().toLowerCase().compareTo(
        second.getDisplayName().toLowerCase()));
    return filterRoles;
  }
}
