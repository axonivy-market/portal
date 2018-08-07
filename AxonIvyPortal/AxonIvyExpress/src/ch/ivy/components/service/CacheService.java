package ch.ivy.components.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;

/**
 * This class contains a cache of ivy users to enhance loading times <br>
 * The user cache contains {@link IUser} objects identified by the KID as a String <br>
 * The role cache contains a {@link List} of {@link IUser} objects identified by the role name as a String <br>
 * <br>
 * The life time of the cache is one day. <br>
 * <br>
 * If a lot of requests regarding the user cache are expected, it is recommended to initialize the cache 
 * via cron job and the method <code>addAllUsersToUserCache</code>
 * @author A28030
 *
 */
public final class CacheService {

	private final int LIFE_TIME = 86400; // 1 day in seconds
	private final String USER_CACHE_GROUP = "de.eon.components.cache.user";
	private final String ROLE_CACHE_GROUP = "de.eon.components.cache.role";
	
	private static CacheService instance;
	
	private int cacheHit = 0;
	private int cacheMiss = 0;
	
	private CacheService() {
		
	}
	
	/**
	 * Statistics how often a request could be handled via cache since initialization
	 * 
	 * @return Successful responses from cache
	 */
	public int getCacheHit() {
		return cacheHit;
	}
	
	/**
	 * Statistics how often a request could not be handled via cache since initialization
	 * 
	 * @return Necessary reloads from the cache
	 */
	public int getCacheMiss() {
		return cacheMiss;
	}
	
	/**
	 * Return the single instance of this application cache
	 * 
	 * @return instance
	 */
	public static CacheService getInstance(){
		if (null == CacheService.instance) {
			instance = new CacheService();
		}
		return CacheService.instance;
	}
	
	/**
	 * Checks if a given user can be found as an ivy user. If the user can be found the display name is returned, otherwise the given kid.
	 * @param kid
	 * @return the display name of the given user. If the user could not be found the kid is returned instead.
	 */
	public String getDisplayName(String kid) {
		IUser user = findUser(kid);

		if (null == user) {
			return kid;
		} else {
			return user.getDisplayName();
		}
	}
	
	/**
	 * Checks if a user can be found in the local cache.
	 * Search for the user if the kid was not found.
	 * 
	 * @param kid
	 * @return the {@link IUser} to the given username. returns <code>null</code> if no user was found
	 */
	public IUser findUser(final String kid){
		IUser user = null;
		if (null != kid && !kid.isEmpty()) {
			try {
				// check if user is in cache
				IDataCacheEntry oEntry = Ivy.datacache().getAppCache().getEntry(USER_CACHE_GROUP, kid);
				Object oUser = (null == oEntry ? null : oEntry.getValue());
				if (null != oEntry && oEntry.isValid() 
						&& null != oUser) {
					cacheHit++;
					// check if IUser could be found
					if (oUser instanceof IUser) {
						user = (IUser) oUser;
					} else if (oUser instanceof Integer && ((Integer) oUser).intValue() == -1) {
						// an error code of -1 means there is no IUser for this KID
						user = null;
					} else {
						// this state is not defined
						Ivy.log().error("Unknow object found in cache for KID '" + kid + "'");
						Ivy.datacache().getAppCache().getEntry(USER_CACHE_GROUP, kid).invalidate();
					}
					return user;
				} else {
					cacheMiss++;
					user = ServerFactory.getServer().getSecurityManager().executeAsSystem(
							new Callable<IUser>() {
								public IUser call() throws Exception {
									return Ivy.wf().getSecurityContext().findUser(kid);
								}
							});
					// save user in cache
					if (null == user) {
						// an error code of -1 means there is no IUser for this KID
						Ivy.datacache().getAppCache().setEntry(USER_CACHE_GROUP, kid, LIFE_TIME, new Integer(-1));	
					} else {
						Ivy.datacache().getAppCache().setEntry(USER_CACHE_GROUP, kid, LIFE_TIME, user);						
					}
				}
			} catch (Exception e) {
				Ivy.log().error("Could not find user: " + e);
			}
		}
		return user;
	}
	
	/**
	 * Search for all users of the current application. <br>
	 * There is no caching available for this method
	 * 
	 * @return a {@link List} of {@link IUser} from the current application
	 */
	public List<IUser> findAllUsers() {
		List<IUser> result = new ArrayList<IUser>();
		try {
			// check if user is in cache
			final String rolename = "ALL";
			IDataCacheEntry oEntry = Ivy.datacache().getAppCache().getEntry(ROLE_CACHE_GROUP, rolename);
			Object oRoleUsers = (null == oEntry ? null : oEntry.getValue());
			
			if (null != oEntry && oEntry.isValid() 
					&& null != oRoleUsers) {
				cacheHit++;
				// check if List<IUser> could be found
				if (oRoleUsers instanceof List<?>) {
					result = (List<IUser>) oRoleUsers; 
				} else {
					// this state is not defined
					Ivy.log().error("Unknow object found in cache for role '" + rolename + "'");
				}
				return result;
			} else {
				cacheMiss++;
				result = ServerFactory.getServer().getSecurityManager().executeAsSystem(
						//this is faster because no additional security issue
					new Callable<List<IUser>>() {
						public List<IUser> call() throws Exception {
							List<IUser> result = new ArrayList<IUser>();
		
							// get all users
							List<IUser> users = Ivy.wf().getSecurityContext().getUsers();
								
							// save system user ID
							long systemUserID = Ivy.wf().getSecurityContext().getSystemUser().getId();
								
							// exclude the system user
							for (IUser u : users) {
								if (u.getId() != systemUserID) {
									// save user in cache
									// overwrite if necessary
									Ivy.datacache().getAppCache().setEntry(USER_CACHE_GROUP, u.getName(), LIFE_TIME, u);
									result.add(u);
								}
							}
							// persist cache in application
							// save role in cache
							Ivy.datacache().getAppCache().setEntry(ROLE_CACHE_GROUP, rolename, LIFE_TIME, result);
							return result;
						}
					});
			}
		} catch (Exception e) {
			Ivy.log().error("Could not find all users: " + e);
		}
		return result;
	}
	
	/**
	 * Checks if a role can be found in the local cache.
	 * Search for the role if it was not found.
	 * 
	 * @param rolename
	 * @return all users owning the specific role. if the role could not be found an empty list is returned.
	 */
	public List<IUser> findUsersForRole(final String rolename) {
		List<IUser> users = new ArrayList<IUser>();
		if (null != rolename && !rolename.isEmpty()) {
			try {
				// check if user is in cache
				IDataCacheEntry oEntry = Ivy.datacache().getAppCache().getEntry(ROLE_CACHE_GROUP, rolename);
				Object oRoleUsers = (null == oEntry ? null : oEntry.getValue());
				if (null != oEntry && oEntry.isValid() 
						&& null != oRoleUsers) {
					cacheHit++;
					// check if List<IUser> could be found
					if (oRoleUsers instanceof List<?>) {
						users = (List<IUser>) oRoleUsers; 
					} else {
						// this state is not defined
						Ivy.log().error("Unknow object found in cache for role '" + rolename + "'");
					}
					return users;
				} else {
					cacheMiss++;
					users = ServerFactory.getServer().getSecurityManager().executeAsSystem(
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
					// save role in cache
					Ivy.datacache().getAppCache().setEntry(ROLE_CACHE_GROUP, rolename, LIFE_TIME, users);
				}
			} catch (Exception e) {
				Ivy.log().error("Could not find users for role: " + e);
			}
		}
		return users;
	}
	
	/**
	 * Reloads all ivy users of an application, ignoring the remaining life time.<br>
	 * The cache will not be cleared before.
	 * 
	 * @see CacheService#clearUserCache()
	 * 
	 */
	public void addAllUsersToUserCache() {
		/*
		 * persist cache within this method
		 */
		findAllUsers();
	}
	
	/**
	 * invalidates the user cache and the ALL role from the role cache
	 */
	public void clearUserCache() {
		// try to delete old user cache
		IDataCacheGroup userCache = Ivy.datacache().getAppCache().getGroup(USER_CACHE_GROUP);
		if (null != userCache) {
			userCache.invalidateAllEntries();
		}
		
		// try to delete old entry for ALL in the role cache
		IDataCacheEntry currRoleCache = Ivy.datacache().getAppCache().getEntry(ROLE_CACHE_GROUP, "ALL");
		if (null != currRoleCache) {
			currRoleCache.invalidate();
		}
	}
	
	/**
	 * invalidates the role cache, except the ALL role 
	 */
	public void clearRoleCache() {
		IDataCacheGroup roleCache = Ivy.datacache().getAppCache().getGroup(ROLE_CACHE_GROUP);
		if (null != roleCache) {
			for (IDataCacheEntry oRole : roleCache.getEntries()) {
				if (null != oRole) {
					String rolename = oRole.getIdentifier();
					if (null != rolename && rolename.equals("ALL")) {
						// do not delete the ALL users in the role cache
						continue;
					}
					// invalidate all other roles
					oRole.invalidate();
				}
			}
		}
	}
	
	/**
	 * invalidates the specific role cache
	 */
	public void clearRoleCache(String rolename) {
		if (null != rolename && !rolename.isEmpty()) {
			try {
				// check if role is in cache
				IDataCacheEntry oRole = Ivy.datacache().getAppCache().getEntry(ROLE_CACHE_GROUP, rolename);
				if (null != oRole) {
					oRole.invalidate();
				}
			} catch (Exception e) {
				Ivy.log().error("Could not find users for role: " + e);
			}
		}
	}
	
	/**
	 * Loads all roles to the role cache from a given {@link Enum}.<br>
	 * The {@link Enum} must store the roles as attributes.<br>
	 * They will be identified by their name. <br>
	 * The cache will not be cleared before. 
	 * @see CacheService#clearRoleCache()
	 * @param roleClass given {@link Enum} definition of the specific roles
	 */
	public <E extends Enum<?>> void addAllRolesToCache (Class<E> roleClass){
		for (E roleName : roleClass.getEnumConstants()) {
			// save enum name
			String rName = roleName.name();
			
			/*
			 * persist cache within this method
			 */
			findUsersForRole(rName);
		}
	}
}
