/*
 * RoleUtils.java
 */
package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ch.ivy.add.portalkit.admin.RolePropertyData;
import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Provide the utilities related to role
 * 
 * @author bolt
 */
public final class RoleUtils {

  private RoleUtils() {

  }

  private static final String HIDE_IN_DELEGATION = "HIDE_IN_DELEGATION";
  private static final String HIDE = "HIDE";
  private static final String[] HIDE_BY_DEFAULT_ROLES = {"AXONIVY_PORTAL_ADMIN"};

  /**
   * Get all roles in current Ivy Server
   * 
   * @return List<IRole> : All roles in current Ivy Server
   */
  public static List<IRole> getAllRoles() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<IRole>>() {
        public List<IRole> call() throws Exception {
          ISecurityContext security = Ivy.session().getSecurityContext();
          List<IRole> roles = new ArrayList<IRole>();
          roles = security.getRoles();
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
    return null;
  }

  /**
   * Get all hidden roles in current Ivy Server
   * 
   * @return List<IRole> : All hidden roles in current Ivy Server
   */
  public static List<IRole> getAllHiddenRoles() {
    return getAllRoles().stream()
        .filter(isHiddenRole())
        .collect(Collectors.toList());
  }

  /**
   * Get all visible role in current Ivy Server
   * 
   * @return List<IRole> : All visible roles in current Ivy Server
   */
  public static List<IRole> getAllVisibleRoles() {
    return getAllRoles().stream()
        .filter(isVisibleRole())
        .collect(Collectors.toList());
  }

  /**
   * Get all hidden roles of an user
   * 
   * @param user user to get roles
   * @return List<IRole> : All hidden roles of an user
   */
  public static List<IRole> getHiddenRoles(IUser user) {
    return user.getRoles().stream()
        .filter(isHiddenRole())
        .collect(Collectors.toList());
  }

  /**
   * Get all hidden sub-roles of a role
   * 
   * @param role role to get sub-roles
   * @return List<IRole> : All hidden sub-roles of an user
   */
  public static List<IRole> getHiddenRoles(IRole role) {
    return role.getAllRoles().stream()
        .filter(isHiddenRole())
        .collect(Collectors.toList());
  }

  /**
   * Get all visible roles of an user
   * 
   * @param user user to get roles
   * @return List<IRole> : All visible roles of an user
   */
  public static List<IRole> getVisibleRoles(IUser user) {
    return user.getRoles().stream()
        .filter(isVisibleRole())
        .collect(Collectors.toList());
  }

  /**
   * Get all visible sub-roles of a role
   * 
   * @param role role to get sub-roles
   * @return List<IRole> : All visible sub-roles of an user
   */
  public static List<IRole> getVisibleRoles(IRole role) {
    return role.getAllRoles().stream()
        .filter(isVisibleRole())
        .collect(Collectors.toList());
  }

  /**
   * Get all roles in current Ivy Server exclude some roles has properties HIDE_IN_DELEGATION and HIDE
   * 
   * @return List<IRole> : All roles in current Ivy Server exclude some roles has properties
   *         HIDE_IN_DELEGATION
   *         HIDE
   */
  public static List<IRole> getRolesForDelegate() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<IRole>>() {
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
  public static void setRoleProperty(final IRole role, final String key, final String value) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Void>() {
        public Void call() throws Exception {
          @SuppressWarnings("unused")
          ISecurityContext security = Ivy.session().getSecurityContext();
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
  public static void removeRoleProperty(final IRole role, final String key) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Void>() {
        public Void call() throws Exception {
          @SuppressWarnings("unused")
          ISecurityContext security = Ivy.session().getSecurityContext();
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
   * get role properties.
   * 
   * @param role role to get properties.
   * @return List of RolePropertyData
   */
  public static List<RolePropertyData> getRoleProperties(IRole role) {
    List<String> propertyNames = role.getAllPropertyNames();
    List<RolePropertyData> properties = new ArrayList<RolePropertyData>();
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
   * 
   */
  public static void setDefaultHiddenRoles() {
    List<IRole> roles = RoleUtils.getAllRoles();

    @SuppressWarnings("unchecked")
    List<String> hideByDefaultRoles = Arrays.asList(HIDE_BY_DEFAULT_ROLES);

    for (IRole role : roles) {
      boolean isHideRole = hideByDefaultRoles.contains(role.getName());
      if (isHideRole && Objects.isNull(role.getProperty(HIDE))) {
        setRoleProperty(role, HIDE, HIDE);
      }
    }
  }

  private static Predicate<IRole> isHiddenRole() {
    return role -> Objects.nonNull(role.getProperty(HIDE));
  }

  private static Predicate<IRole> isVisibleRole() {
    return role -> Objects.isNull(role.getProperty(HIDE));
  }

}
