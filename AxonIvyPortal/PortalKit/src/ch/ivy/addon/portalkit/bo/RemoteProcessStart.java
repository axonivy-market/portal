package ch.ivy.addon.portalkit.bo;


/**
 * Bean for remote process start.
 */
public class RemoteProcessStart {

	private String description;
	private String fullRequestPath;
	private String fullUserFriendlyRequestPath;
	private Long id;
	private String name;
	private String startLink;
	private String applicationName;
	private String processElementId;
  private Long serverId;
	
	public String getDescription() {
		return description;
	}
	public String getFullRequestPath() {
		return fullRequestPath;
	}
	public String getFullUserFriendlyRequestPath() {
		return fullUserFriendlyRequestPath;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getProcessElementId() {
		return processElementId;
	}
	/**
	 * Gets the startLink
	 *
	 * @return Returns the startLink
	 */
	public String getStartLink() {
		return startLink;
	}
	/**
	 * Sets the startLink
	 *
	 * @param startLink The startLink to set
	 */
	public void setStartLink(String startLink) {
		this.startLink = startLink;
	}
	/**
	 * Gets the applicationName
	 *
	 * @return Returns the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}
	/**
	 * Sets the applicationName
	 *
	 * @param applicationName The applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	/**
	 * Sets the description
	 *
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Sets the fullRequestPath
	 *
	 * @param fullRequestPath The fullRequestPath to set
	 */
	public void setFullRequestPath(String fullRequestPath) {
		this.fullRequestPath = fullRequestPath;
	}
	/**
	 * Sets the fullUserFriendlyRequestPath
	 *
	 * @param fullUserFriendlyRequestPath The fullUserFriendlyRequestPath to set
	 */
	public void setFullUserFriendlyRequestPath(String fullUserFriendlyRequestPath) {
		this.fullUserFriendlyRequestPath = fullUserFriendlyRequestPath;
	}
	/**
	 * Sets the id
	 *
	 * @param id The id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Sets the processElementId
	 *
	 * @param processElementId The processElementId to set
	 */
	public void setProcessElementId(String processElementId) {
		this.processElementId = processElementId;
	}
  public Long getServerId() {
    return serverId;
  }
  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }
	
	
}
