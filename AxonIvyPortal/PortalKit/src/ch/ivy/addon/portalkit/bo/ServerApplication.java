package ch.ivy.addon.portalkit.bo;

/**
 * This bean represents a application of one server for absence
 * 
 * @author ptanh
 */
public class ServerApplication {

	private String appName;
	private Integer serverId;
    
    
    /**
     * constructor
     */
    public ServerApplication() {
	   
	}
    
	/**
	 * Constructor
	 *
	 * @param appName application name
	 * @param serverId server id
	 */
	public ServerApplication(String appName, Integer serverId){
		this.setAppName(appName);
		this.setServerId(serverId);
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

	/**
	 * Gets the serverId
	 *
	 * @return Returns the serverId
	 */
	public Integer getServerId() {
		return serverId;
	}

	/**
	 * Sets the serverId
	 *
	 * @param serverId The serverId to set
	 */
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	
	

}
