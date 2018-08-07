package ch.ivy.addon.portalkit.bo;


import java.util.UUID;

/**
 * This bean represents a configuration of one server.
 * 
 * @author tauser
 */
public class ServerItem {

	/* data */
	private String name;
	private String path;
    private Long id;
    private Boolean active;
    
    
    /**
     * constructor
     */
    public ServerItem() {
	   setId(UUID.randomUUID().getLeastSignificantBits());
	}
	

	/**
	 * Copy the fields from another instance
	 * 
	 * @param serverItem server item
	 */
	public void copyFrom(ServerItem serverItem) {
		 this.name = serverItem.getName();
		 this.path = serverItem.getPath();
		
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
	 * Gets the path
	 *
	 * @return Returns the path
	 */
	public String getPath() {
		return path;
	}


	/**
	 * Sets the path
	 *
	 * @param path The path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}


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
	 * @param id The id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * Gets the active
	 *
	 * @return Returns the active
	 */
	public Boolean getActive() {
		return active;
	}


	/**
	 * Sets the active
	 *
	 * @param active The active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

}
