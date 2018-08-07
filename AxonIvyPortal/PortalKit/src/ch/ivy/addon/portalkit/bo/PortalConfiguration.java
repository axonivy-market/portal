package ch.ivy.addon.portalkit.bo;

import java.util.ArrayList;

/**
 * This bean is used to
 * 
 * @author tauser
 * 
 */
public class PortalConfiguration {

	private ArrayList<ServerItem> servers;

	/**
	 * constructor
	 */
	public PortalConfiguration() {
		servers = new ArrayList<ServerItem>();
	}

	/**
	 * Gets the servers
	 *
	 * @return Returns the servers
	 */
	public ArrayList<ServerItem> getServers() {
		return servers;
	}

	/**
	 * Sets the servers
	 *
	 * @param servers The servers to set
	 */
	public void setServers(ArrayList<ServerItem> servers) {
		this.servers = servers;
	}
	
}
