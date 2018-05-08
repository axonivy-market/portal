package ch.ivy.addon.portalkit.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * This bean is used to
 */
public class PortalConfiguration {

	private List<ServerItem> servers;

	/**
	 * constructor
	 */
	public PortalConfiguration() {
		servers = new ArrayList<>();
	}

	/**
	 * Gets the servers
	 *
	 * @return Returns the servers
	 */
	public List<ServerItem> getServers() {
		return servers;
	}

	/**
	 * Sets the servers
	 *
	 * @param servers The servers to set
	 */
	public void setServers(List<ServerItem> servers) {
		this.servers = servers;
	}
	
}
