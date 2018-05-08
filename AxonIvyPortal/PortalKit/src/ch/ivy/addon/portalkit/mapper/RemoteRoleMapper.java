package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.RemoteRole;
import ch.ivy.ws.addon.IvyRole;
import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Map between Ivy role and Remote Role.
 * 
 * @author Bolt
 */
public class RemoteRoleMapper {

  private RemoteRoleMapper() {}

	/**
	 * Convert a ivy role object to a remote role.
	 * 
	 * @param ivyRole ivy role. 
	 * @return RemoteRole
	 */
	public static RemoteRole mapIvyRole(IvyRole ivyRole){
		RemoteRole result = null;
		if(ivyRole != null){
			result = new RemoteRole(ivyRole.getId(), ivyRole.getMemberName(), ivyRole.getDisplayName());
		}
			
		return result;
	}
	
	
	/**
	 * Convert list of {@link IvyRole} to list of remote security member.
	 * 
	 * @param ivyRoles ivy role
	 * @return List of {@link RemoteRole}
	 */
	public static List<RemoteRole> mapIvyRoles(List<IvyRole> ivyRoles){
		List<RemoteRole> result = List.create(RemoteRole.class);
		
		for(IvyRole r : ivyRoles){
			RemoteRole member = mapIvyRole(r);
			
			if(null != member){
				result.add(member);
			}
		}
		
		return result;
	}
	
}
