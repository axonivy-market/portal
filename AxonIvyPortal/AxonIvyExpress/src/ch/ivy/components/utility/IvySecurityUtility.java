package ch.ivy.components.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import ch.ivy.components.RoleProperty;
import ch.ivy.components.RoleType;
import ch.ivy.components.service.CacheService;
import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IPermissionAccess;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

/**
 * Utility functions for Ivy security
 * 
 * @author mde
 * 
 */
public class IvySecurityUtility {

	/**
	 * Checks if the user has the given role
	 * @param user
	 * @param rolename
	 * @return true - user has the given role (direct or indirect) <br>
	 * false - user does not have to given role
	 * @throws EnvironmentNotAvailableException
	 * @throws PersistencyException
	 * @throws Exception
	 */
	public static boolean hasRole(final IUser user, final String rolename)
			throws EnvironmentNotAvailableException, PersistencyException,
			Exception {
//		return Ivy.wf().getSecurityContext().executeAsSystemUser(
		return ServerFactory.getServer().getSecurityManager().executeAsSystem( //this is faster because no additional security issue
				new Callable<Boolean>() {
					public Boolean call() throws Exception {
						int t = 5; //number retries
						while (t>=0) {
							try {
								IRole role = Ivy.wf().getSecurityContext().findRole(
										rolename);
								if (role != null) {
//									return role.getAllUsers().contains(user);
									return user.getAllRoles().contains(role); //allowed by EON-Security, configured correctly in LDAP-Setting of server (do not use "groups of a user")
								}
								t = -1;
							}
							catch (Exception ise) {
								Ivy.log().error("Cannot connect for roles:"+ise);
								Ivy.log().error("Retries left:"+t);
								t--;
								int s = 1;
								if (Ivy.session() != null) {
									s = Ivy.session().getIdentifier()+1;
									while (s>10) {
										s = s / 2;
									}
								}
								try {
									Thread.sleep(1000 * s); //wait N secs
								}
								catch (InterruptedException ie) {
									//NOP
								}
							}
						}
						return false;
					}
				});
	}
	
	/**
	 * Checks if the session user has the given role
	 * @param session
	 * @param rolename
	 * @return true - session user has the given role (direct or indirect) <br>
	 * false - session user does not have to given role
	 * @throws EnvironmentNotAvailableException
	 * @throws Exception
	 */
	public static boolean hasRole(final IWorkflowSession session, final String rolename) 
			throws EnvironmentNotAvailableException, Exception{
//		return Ivy.wf().getSecurityContext().executeAsSystemUser(
		return ServerFactory.getServer().getSecurityManager().executeAsSystem( //this is faster because no additional security issue
				new Callable<Boolean>() {
					public Boolean call() throws Exception {
						int t = 5; //number retries
						while (t>=0) {
							try {
								IRole role = Ivy.wf().getSecurityContext().findRole(
										rolename);
								if (role != null) {
									return session.hasRole(role, true);
								}
								t = -1;
							}
							catch (Exception ise) {
								Ivy.log().error("Cannot connect for roles:"+ise);
								Ivy.log().error("Retries left:"+t);
								t--;
								int s = 1;
								if (Ivy.session() != null) {
									s = Ivy.session().getIdentifier()+1;
									while (s>10) {
										s = s / 2;
									}
								}
								try {
									Thread.sleep(1000 * s); //wait N secs
								}
								catch (InterruptedException ie) {
									//NOP
								}
//							} catch (PersistencyException pe) {
//								Ivy.log().error("Cannot connect for roles:"+pe);
//								Ivy.log().error("Retries left:"+t);
//								t--;
//								int s = 1;
//								if (Ivy.session() != null) {
//									s = Ivy.session().getIdentifier()+1;
//									while (s>10) {
//										s = s / 2;
//									}
//								}
//								try {
//									Thread.sleep(1000 * s); //wait N secs
//								}
//								catch (InterruptedException ie) {
//									//NOP
//								}
							}
						}
						return false;
					}
				});
	}
	

	/**
	 * Finds all users that own the given role (direct or indirect
	 * @param rolename
	 * @return List<IUser>
	 */
	public static List<IUser> findUsersForRole(final String rolename) {
		
		/* C23763 - C.Hochwald, 22.06.2015
		 * Changes: Comment "old" code and replaced with method from cache service
		 * Reason: logic is not cached in A.I. 5.0.18, so E.ON implemented a own Cache (Class: CacheService)          
		 */
		
		return CacheService.getInstance().findUsersForRole(rolename);
		/*
		List<IUser> result = new ArrayList<IUser>();

		try {
//			result = Ivy.wf().getSecurityContext().executeAsSystemUser(
			result = ServerFactory.getServer().getSecurityManager().executeAsSystem( //this is faster because no additional security issue
					new Callable<List<IUser>>() {
						public List<IUser> call() throws Exception {
							List<IUser> result = new ArrayList<IUser>();

							IRole role = Ivy.wf().getSecurityContext()
									.findRole(rolename);
							if (role != null) {
								result = role.getAllUsers();
							}
							return result;
						}
					});
		} catch (Exception e) {
		}
		return result;
		*/
	}

	/**
	 * returns all workflow users
	 * @return List<IUser>
	 */
	public static List<IUser> findAllUsers() {
		
		/* C23763 - C.Hochwald, 22.06.2015
		 * Changes: Comment "old" code and replaced with method from cache service
		 * Reason: logic is not cached in A.I. 5.0.18, so E.ON implemented a own Cache (Class: CacheService)          
		 */
		
		return CacheService.getInstance().findAllUsers();
		/*
		List<IUser> result = new ArrayList<IUser>();

		try {
//			result = Ivy.wf().getSecurityContext().executeAsSystemUser(
			result = ServerFactory.getServer().getSecurityManager().executeAsSystem( //this is faster because no additional security issue
					new Callable<List<IUser>>() {
						public List<IUser> call() throws Exception {
							List<IUser> result = new ArrayList<IUser>();

							List<IUser> users = Ivy.wf().getSecurityContext()
									.getUsers();
							for (IUser u : users) {
								if (u.getId() != Ivy.wf()
										.getSecurityContext().getSystemUser()
										.getId()) {
									result.add(u);
								}
							}
							return result;
						}
					});
		} catch (Exception e) {
		}
		return result;
	*/
	}
	

	/**
	 * grants all Permissions to a given workflow user
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static boolean grantAllPermissions(IUser user) throws Exception {
//		return Ivy.session().getSecurityContext().executeAsSystemUser(
		return ServerFactory.getServer().getSecurityManager().executeAsSystem( //this is faster because no additional security issue
				new Callable<Boolean>() {
					public Boolean call() throws Exception {
						Ivy.wf().getApplication().getSecurityDescriptor()
								.grantPermissions(
										Ivy.wf().getApplication()
												.getSecurityDescriptor()
												.getSecurityDescriptorType()
												.getRootPermissionGroup(),
										Ivy.wf().getSecurityContext()
												.getTopLevelRole());
						return true;
					}
				});
	}
	
	public static void mapUsersToRoles(ch.ivyteam.ivy.scripting.objects.List<IRole> roles){
		// prepare List
		HashMap<IUser, HashMap<IRole, Object>> toDelete = new HashMap<IUser, HashMap<IRole,Object>>();
		
		// Loop over all roles
		for (IRole iRole : roles) {
			// Get DN String from properties
			String dn = iRole.getProperty("ExternalSecurityName");
			if (null != dn && !dn.trim().isEmpty()) {
				/*
				 *********************************************************************
				 * Add current configuration
				 ********************************************************************** 
				 */
				List<IUser> user = iRole.getUsers();
				
				for (IUser iUser : user) {
					
					if (!toDelete.containsKey(iUser)) {
						HashMap<IRole, Object> roleMap = new HashMap<IRole, Object>();
						roleMap.put(iRole, null);
						toDelete.put(iUser, roleMap);
					}else{
						HashMap<IRole, Object> roleMap = toDelete.get(iUser);
						if (!roleMap.containsKey(iRole)) {
							roleMap.put(iRole, null);
							toDelete.put(iUser, roleMap);
						}
					}
				}
				
				/*
				 *********************************************************************
				 * Read new Roles
				 ********************************************************************** 
				 */
				List<IUser> member = getIUserFromDN(dn);
				
				for (IUser iUser : member) {
					// Check if role is already assigned
					if (toDelete.containsKey(iUser) && toDelete.get(iUser).containsKey(iRole)) {
						// No Action required
						toDelete.get(iUser).remove(iRole);
					} else {
						// Add role
						if (!iUser.getAllRoles().contains(iRole)) {
							Ivy.log().info("Add role " + iRole.getName() + " to User " + iUser.getName());
							iUser.addRole(iRole);						
						}
					}
				}
			}
			// Next if Property is empty or not set
		}
		
		/*
		 *********************************************************************
		 * Remove obsolete Roles
		 ********************************************************************** 
		 */
		for (IUser iUser : toDelete.keySet()) {
			// Check if there are Roles to remove
			HashMap<IRole, Object> roleMap = toDelete.get(iUser);
			if (roleMap.size() == 0) {
				continue;
			}
			
			// Remove all Roles from user
			for (IRole iRole : roleMap.keySet()) {
				Ivy.log().info("Remove role " + iRole.getName() + " from User " + iUser.getName());
				iUser.removeRole(iRole);
			}
		}
	}
	
	private static ch.ivyteam.ivy.scripting.objects.List<IUser> getIUserFromDN(String dn){
		List<String> kids = LdapUtility.getPersons(dn);
		ch.ivyteam.ivy.scripting.objects.List<IUser> result = ch.ivyteam.ivy.scripting.objects.List.create(IUser.class);
		for (String kid : kids) {
			IUser user = Ivy.wf().getSecurityContext().findUser(kid);
			if (user != null && !result.contains(user)) {
				result.add(user);
			} else {
				// If user was not found -> ignore
				Ivy.log().info("Could not find user with KID: " + kid);
			}
		}
		return result;
	}

	/*
	 * Set Role Type
	 * @param String - roleName
	 * @param RoleType - type
	 * @return true if role exists
	 */
	public static boolean setRoleType(final String roleName, final RoleType roleType) throws Exception {
		final String ROLE_TYPE_PROPERTY_NAME = RoleProperty.ROLETYPE.name(); 
//		return Ivy.session().getSecurityContext().executeAsSystemUser(
		return ServerFactory.getServer().getSecurityManager().executeAsSystem( //this is faster because no additional security issue
				new Callable<Boolean>() {
					public Boolean call() throws Exception {
						IRole r = Ivy.wf().getSecurityContext().findRole(roleName);
						Boolean res = false;
						if (r != null) {
					        r.setProperty(ROLE_TYPE_PROPERTY_NAME, roleType.name());		
							res = true;
						}
						return res;
					}
				});
	}
	
	/*
	 * has Roles for Type
	 * @param roleName
	 * @param RoleType
	 * @return Boolean
	 */
	public static Boolean hasRoleType(final IRole r, final RoleType roleType) throws Exception{
		final String ROLE_TYPE_PROPERTY_NAME = RoleProperty.ROLETYPE.name(); 
//		return Ivy.session().getSecurityContext().executeAsSystemUser(
		return ServerFactory.getServer().getSecurityManager().executeAsSystem( //this is faster because no additional security issue
				new Callable<Boolean>() {			
					public Boolean call() throws Exception {
					    Boolean res = false;	
					    try {
	                          String attr = r.getProperty(ROLE_TYPE_PROPERTY_NAME);
		                      if (attr != null && RoleType.getFromName(attr).equals(roleType)) {
		 	                     res = true;
		                      }
					    }
						catch (Exception e) {
					           Ivy.log().error(e);			
								
						}
						return res;
					}
				});

	}


	/*
	 * get RoleType
	 * @param roleName
	 * @return RoleType
	 */
	public static RoleType getRoleType(final String roleName) {
		RoleType result = RoleType.NONE;
	    try {
//	      result = Ivy.session().getSecurityContext().executeAsSystemUser(
		  result = ServerFactory.getServer().getSecurityManager().executeAsSystem( //this is faster because no additional security issue
				new Callable<RoleType>() {			
					public RoleType call() throws Exception {
						  RoleType res = RoleType.NONE;
						  IRole r = Ivy.wf().getSecurityContext().findRole(roleName);
		                  if (r != null) {
	                          String attr = r.getProperty(RoleProperty.ROLETYPE.name());
		                      if (attr != null) {
		 	                     res = RoleType.getFromName(attr);
		                      }
		                  }
						return res;
					}
				});
	    }
	    catch (Exception e) {
	    	Ivy.log().error(e);			
			
	    }
       return result;  
	}

	/**
	 * has User a Permission ?
	 * @param IUser
	 * @param IPermission
	 * @return Boolean
	 * @throws Exception
	 */
	public static boolean hasPermission(final IUser user, final IPermission p) throws Exception {
//		return Ivy.session().getSecurityContext().executeAsSystemUser(
		return ServerFactory.getServer().getSecurityManager().executeAsSystem( //this is faster because no additional security issue
				new Callable<Boolean>() {
					public Boolean call() throws Exception {
						IPermissionAccess  a = Ivy.wf().getApplication().getSecurityDescriptor().getPermissionAccess(p, user);
						return a.isGranted();

					}
				});
	}	
	
	/**
	 * Transforms list of ivy users to list of ivy user names
	 * @param List<IUser> users
	 * @return List<String> usernames
	 * @see IUser
	 */
	public static List<String> usersToUsernames(List<IUser> users) {
		List<String> usernames = new ArrayList<String>();
		
		for(IUser user: users) {
			usernames.add(user.getName());
		}
		
		return usernames;
	}
}
