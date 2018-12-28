package ch.ivy.addon.portalkit.persistence.variable;

public class PortalCacheConstants {

	// cache for users
	public static final String PORTAL_USERS_CACHE_GROUP_NAME = "portalCache";
	public static final String USERS_LIST_CACHE_ENTRY_NAME = "usersList";
	public static final String USERS_REPO_CACHE_ENTRY_NAME = "usersRepo";
	
	// cache for application web service
	public static final String GET_APPLICATIONS_WS_CACHE_GROUP_NAME = "getApplications";
	public static final Integer GET_APPLICATIONS_WS_CACHE_LIFETIME = 3600;  
	
	// cache for setting, logout page
	public static final String GLOBAL_SETTING_CACHE_GROUP_NAME = "Global Settings Group";
	
	public static final String LOGOUT_PAGE_CACHE_GROUP_NAME = "Logout Page Group";
	public static final String LOGOUT_PAGE_CACHE_ENTRY_NAME = "Logout Page Id";
	
	public static final String IS_MULTI_SERVERS_CACHE_GROUP_NAME = "Is Multi Servers Group";
  public static final String IS_MULTI_SERVERS_CACHE_ENTRY_NAME = "Is Multi Servers Id";
}
