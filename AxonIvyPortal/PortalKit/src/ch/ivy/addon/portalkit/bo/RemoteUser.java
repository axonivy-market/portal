package ch.ivy.addon.portalkit.bo;

/**
 * Bean for remote user.
 * 
 * @author maonguyen
 *
 */
public class RemoteUser {

	private long id;
	private String name;
	private String username;
	private Long serverId;
	private String appName;

	public RemoteUser(){}
	
	/**
	 * Constructor
	 *
	 * @param id user id
	 * @param name name of user
	 * @param username username of user
	 * @param appName application that user on.
	 * @param serverId server id that contains user.
	 */
	public RemoteUser(long id, String name, String username, String appName,
			Long serverId) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.appName = appName;
		this.serverId = serverId;
	}

	/**
	 * Gets the id
	 *
	 * @return Returns the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id
	 *
	 * @param id The id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name
	 *
	 * @return Returns the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 *
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the username
	 *
	 * @return Returns the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username
	 *
	 * @param username The username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the serverId
	 *
	 * @return Returns the serverId
	 */
	public Long getServerId() {
		return serverId;
	}

	/**
	 * Sets the serverId
	 *
	 * @param serverId The serverId to set
	 */
	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	/**
	 * Gets the appName
	 *
	 * @return Returns the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * Sets the appName
	 *
	 * @param appName The appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}


}
