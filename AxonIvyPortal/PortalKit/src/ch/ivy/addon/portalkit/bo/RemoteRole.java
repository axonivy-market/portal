package ch.ivy.addon.portalkit.bo;

import java.util.List;

import ch.ivyteam.api.IvyScriptVisibility;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserToken;

/**
 * Bean for remote role.
 *
 * @author maonguyen
 */
public class RemoteRole implements IRole{

	private long id;
	private String displayName;
	private String memberName;
	
	
	/**
	 * Constructor
	 *
	 * @param id role id
	 * @param memberName name of role
	 * @param displayName display name of role
	 */
	public RemoteRole(long id, String memberName, String displayName){
		this.id = id;
		this.displayName = displayName;
		this.memberName = memberName;
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
		return false;
	}

	@Override
	public IRole createChildRole(String arg0, String arg1, String arg2,
			boolean arg3) throws PersistencyException {
		return null;
	}

	@Override
	public IRole createChildRole(String arg0, String arg1, String arg2,
			String arg3, boolean arg4) throws PersistencyException {
		return null;
	}

	@Override
	public void delete() throws PersistencyException {
		
	}

	@Override
	public void deleteAllChildRoles() throws PersistencyException {
		
	}

	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public IRole findChildRole(String roleName) throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public List<String> getAllPropertyNames() throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public List<IUser> getAllUsers() throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public List<IRole> getChildRoles() throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.NOVICE)
	public String getDisplayDescription() throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public String getDisplayDescriptionTemplate() throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.NOVICE)
	public String getDisplayName() throws PersistencyException {
		return displayName;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.NOVICE)
	public String getDisplayNameTemplate() throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public String getExternalSecurityName() throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.NOVICE)
	public String getName() throws PersistencyException {
		return memberName;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public IRole getParent() throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public String getProperty(String name) throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public List<IUser> getUsers() throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public boolean isRole(IRole role) throws PersistencyException {
		return false;
	}

	@Override
	public void move(IRole arg0) throws PersistencyException {
		
	}

	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public String removeProperty(String name) throws PersistencyException {
		return null;
	}

	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public void setDisplayDescriptionTemplate(String displayDescriptionTemplate)
			throws PersistencyException {
		
	}

	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public void setDisplayNameTemplate(String displayNameTemplate)
			throws PersistencyException {
		
	}

	@Override
	@PublicAPI(IvyScriptVisibility.EXPERT)
	public void setExternalSecurityName(String externalSecurityName)
			throws PersistencyException {
		
	}

	@Override
	@PublicAPI(IvyScriptVisibility.ADVANCED)
	public void setProperty(String name, String value)
			throws PersistencyException {
		
	}


	@Override
	public void addRoleMember(IRole role) throws PersistencyException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<IRole> getRoleMembers() throws PersistencyException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void removeRoleMember(IRole role) throws PersistencyException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<IRole> getRoles() throws PersistencyException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<IRole> getAllRoles() throws PersistencyException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
