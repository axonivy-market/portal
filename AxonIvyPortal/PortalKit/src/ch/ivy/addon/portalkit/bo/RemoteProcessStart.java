package ch.ivy.addon.portalkit.bo;

import ch.ivyteam.api.IvyScriptVisibility;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.ITaskStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;

/**
 * Bean for remote process start.
 *
 * @author maonguyen
 */
public class RemoteProcessStart implements IProcessStart{

	private String description;
	private String fullRequestPath;
	private String fullUserFriendlyRequestPath;
	private Long id;
	private String name;
	private String fullRequestPathWithDocId;
	private String startLink;
	private String serverAddress;
	private String applicationName;
	private String processElementId;
  private Long serverId;
	
	@Override
	@PublicAPI(IvyScriptVisibility.NOVICE)
	public String getDescription() throws PersistencyException {
		return description;
	}
	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public String getFullRequestPath() throws PersistencyException {
		return fullRequestPath;
	}
	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public String getFullUserFriendlyRequestPath() throws PersistencyException {
		return fullUserFriendlyRequestPath;
	}
	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public long getId() {
		return id;
	}
	@Override
	@PublicAPI(IvyScriptVisibility.HIDDEN)
	@Deprecated
	public int getIdentifier() {
		return 0;
	}
	@Override
	@PublicAPI(IvyScriptVisibility.NOVICE)
	public String getName() throws PersistencyException {
		return name;
	}
	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public String getProcessElementId() throws PersistencyException {
		return processElementId;
	}
	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public IWorkflowProcessModelVersion getProcessModelVersion()
			throws PersistencyException {
		return null;
	}
	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public String getRequestPath() throws PersistencyException {
		return null;
	}
	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public ITaskStart getTaskStart() throws PersistencyException {
		return null;
	}
	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public String getUserFriendlyRequestPath() throws PersistencyException {
		return null;
	}
	/**
	 * Gets the fullRequestPathWithDocId
	 *
	 * @return Returns the fullRequestPathWithDocId
	 */
	public String getFullRequestPathWithDocId() {
		return fullRequestPathWithDocId;
	}
	/**
	 * Sets the fullRequestPathWithDocId
	 *
	 * @param fullRequestPathWithDocId The fullRequestPathWithDocId to set
	 */
	public void setFullRequestPathWithDocId(String fullRequestPathWithDocId) {
		this.fullRequestPathWithDocId = fullRequestPathWithDocId;
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
	 * Gets the serverAddress
	 *
	 * @return Returns the serverAddress
	 */
	public String getServerAddress() {
		return serverAddress;
	}
	/**
	 * Sets the serverAddress
	 *
	 * @param serverAddress The serverAddress to set
	 */
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
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
