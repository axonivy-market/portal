package ch.ivy.addon.portalkit.bo;

import ch.ivyteam.api.IvyScriptVisibility;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUserToken;

/**
 * Bean for remote security memeber.
 *
 * @author maonguyen
 */
public class RemoteSecurityMember implements ISecurityMember{

	private String displayName;
	
	private String memberName;
	
	/**
	 * Constructor
	 *
	 */
	public RemoteSecurityMember(){
		super();
	}
	
	/**
	 * Constructor
	 *
	 * @param _memberName member name
	 */
	public RemoteSecurityMember(String _memberName){
		super();
		this.memberName = _memberName;
	}
	
	@Override
	@PublicAPI(IvyScriptVisibility.NOVICE)
	public String getDisplayName() throws PersistencyException {
		return displayName;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public long getId() {
		return 0;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.HIDDEN)
	@Deprecated
	public int getIdentifier() {
		return 0;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.NOVICE)
	public String getMemberName() throws PersistencyException {
		return this.memberName;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public ISecurityContext getSecurityContext() throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public boolean isMember(IUserToken userToken, boolean useSessionRoles)
			throws PersistencyException {
		return false;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.NOVICE)
	public boolean isUser() {
		if(memberName != null && memberName.startsWith("#")){
			return true;
		}
		return false;
	}

	/**
	 * Sets the displayName
	 *
	 * @param displayName The displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Sets the memberName
	 *
	 * @param memberName The memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String getName() throws PersistencyException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
