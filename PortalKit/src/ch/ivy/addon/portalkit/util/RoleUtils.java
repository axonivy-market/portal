/*
 * RoleUtils.java
 *
 */
package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import javax.faces.bean.ManagedBean;

import ch.ivy.add.portalkit.admin.RolePropertyData;
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
  
  private RoleUtils(){
    
  }

	private static final String HIDE_IN_DELEGATION = "HIDE_IN_DELEGATION";

	/**
	 * Get all roles in current Ivy Server
	 * 
	 * @return List<IRole> : All roles in current Ivy Server
	 */
	public static List<IRole> getAllRoles() {
		try {
			return ServerFactory.getServer().getSecurityManager()
					.executeAsSystem(new Callable<List<IRole>>() {
						public List<IRole> call() throws Exception {
							ISecurityContext security = Ivy.session()
									.getSecurityContext();
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
	 * Get all roles in current Ivy Server exclude some roles has property
	 * HIDE_IN_DELEGATION
	 * 
	 * @return List<IRole> : All roles in current Ivy Server exclude some roles
	 *         has property HIDE_IN_DELEGATION
	 */
	public static List<IRole> getRolesForDelegate() {
		try {
			return ServerFactory.getServer().getSecurityManager()
					.executeAsSystem(new Callable<List<IRole>>() {
						public List<IRole> call() throws Exception {
							List<IRole> roles = new ArrayList<IRole>();
							List<IRole> securityRolesTmp = Ivy.wf()
									.getSecurityContext().getRoles();
							for (IRole role : securityRolesTmp) {
								// Ignore the role has value in property
								// HIDE_IN_DELEGATION
								if (role.getProperty(HIDE_IN_DELEGATION) != null) {
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
	 * @param role
	 *            role for setting property
	 * @param key
	 *            property key
	 * @param value
	 *            property value
	 */
	public static void setRoleProperty(final IRole role, final String key,
			final String value) {
		try {
			ServerFactory.getServer().getSecurityManager()
					.executeAsSystem(new Callable<Void>() {
						public Void call() throws Exception {
							@SuppressWarnings("unused")
							ISecurityContext security = Ivy.session()
									.getSecurityContext();
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
	 * @param role
	 *            role to remove property
	 * @param key
	 *            key to remove property
	 */
	public static void removeRoleProperty(final IRole role, final String key) {
		try {
			ServerFactory.getServer().getSecurityManager()
					.executeAsSystem(new Callable<Void>() {
						public Void call() throws Exception {
							@SuppressWarnings("unused")
							ISecurityContext security = Ivy.session()
									.getSecurityContext();
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
	 * @param role
	 *            role to get properties.
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
	 * Check is user has the given role.
	 * 
	 * @param roleName
	 * @return true if existing
	 */
	public static boolean checkRoleExist(String roleName) {
		Boolean hasRole = false;
		if (roleName != null) {
			try {
				IUser user = Ivy.session().getSessionUser();
				if (user == null) {
					return false;
				}
				List<IRole> listRole = user.getRoles();
				for (IRole iRole : listRole) {
					if (iRole != null
							&& roleName.equals(iRole.getName())) {
						hasRole = true;
						break;
					}
				}
			} catch (Exception e) {
				Ivy.log().error(e.getMessage());
			}
		}
		return hasRole;
	}
}
