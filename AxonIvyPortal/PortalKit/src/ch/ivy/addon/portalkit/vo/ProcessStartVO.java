package ch.ivy.addon.portalkit.vo;

import java.io.Serializable;
/**
 * Object store IProcessStarts data, implement the {@link Serializable} interface to for sorting, filter ...
 *
 * @author ptnha
 */
public class ProcessStartVO implements Serializable{

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/** id of case*/
	private long id;
	/** title case name*/
	private String name;
	/** request path friendly*/
	private String userFriendlyRequestPath;
	/** description of process start*/
	private String description;
	/** full request path*/
	private String fullRequestPath;
	/** request path*/
	private String requestPath;
	
	/**
	 * Gets the requestPath
	 * @return the requestPath
	 */
	public String getRequestPath() {
		return requestPath;
	}
	/**
	 * Sets the requestPath
	 * @param requestPath The RequestPath to set
	 */
	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}
	/**
	 * Gets the id
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * Sets the id
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Gets the name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the userFriendlyRequestPath
	 * @return the userFriendlyRequestPath
	 */
	public String getUserFriendlyRequestPath() {
		return userFriendlyRequestPath;
	}
	/**
	 * Sets the userFriendlyRequestPath
	 * @param userFriendlyRequestPath the userFriendlyRequestPath to set
	 */
	public void setUserFriendlyRequestPath(String userFriendlyRequestPath) {
		this.userFriendlyRequestPath = userFriendlyRequestPath;
	}
	/**
	 * Gets the description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Gets the fullRequestPath
	 * @return the fullRequestPath
	 */
	public String getFullRequestPath() {
		return fullRequestPath;
	}
	/**
	 * Sets the fullRequestPath
	 * @param fullRequestPath the fullRequestPath to set 
	 */
	public void setFullRequestPath(String fullRequestPath) {
		this.fullRequestPath = fullRequestPath;
	}
}
