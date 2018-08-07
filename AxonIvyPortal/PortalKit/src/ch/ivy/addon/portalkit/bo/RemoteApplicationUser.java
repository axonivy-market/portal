package ch.ivy.addon.portalkit.bo;

/**
 * 
 * Bean for application user.
 * 
 * @author bolt
 * 
 */
public class RemoteApplicationUser {
	private Long id;
	private String appName;
	private String appDisplayName;
	private String memberName;
	private String displayName;

	/**
	 * Gets the id
	 * 
	 * @return Returns the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id
	 * 
	 * @param id
	 *            The id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @param appName
	 *            The appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * Gets the memberName
	 * 
	 * @return Returns the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Sets the memberName
	 * 
	 * @param memberName
	 *            The memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * Gets the displayName
	 * 
	 * @return Returns the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the displayName
	 * 
	 * @param displayName
	 *            The displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the appDisplayName
	 *
	 * @return Returns the appDisplayName
	 */
	public String getAppDisplayName() {
		return appDisplayName;
	}

	/**
	 * Sets the appDisplayName
	 *
	 * @param appDisplayName The appDisplayName to set
	 */
	public void setAppDisplayName(String appDisplayName) {
		this.appDisplayName = appDisplayName;
	}

}
