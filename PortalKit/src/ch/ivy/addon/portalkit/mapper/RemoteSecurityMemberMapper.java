package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.RemoteSecurityMember;
import ch.ivy.ws.addon.IvySecurityMember;
import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Map between IvySecurityMember and RemoteSecurityMember.
 * 
 * @author bolt
 *
 */
public class RemoteSecurityMemberMapper {

	/**
	 * Convert from web service security member to remote security member.
	 * 
	 * @param ivySecurityMemeber web service security memeber
	 * @return remote security memeber. 
	 * @see IvySecurityMember
	 * @see RemoteSecurityMember
	 */
	public static RemoteSecurityMember mapSecurityMember(IvySecurityMember ivySecurityMemeber){
		RemoteSecurityMember result = new RemoteSecurityMember();
		
		result.setDisplayName(ivySecurityMemeber.getDisplayName());
		
		return result;
	}
	
	/**
	 * Convert list of {@link IvySecurityMember} to list of {@link RemoteSecurityMember}.
	 * 
	 * @param ivySecurityMembers list of {@link IvySecurityMember}
	 * @return List of {@link RemoteSecurityMember}
	 * @see IvySecurityMember
	 * @see RemoteSecurityMember
	 */
	public static List<RemoteSecurityMember> mapSecurityMembers(List<IvySecurityMember> ivySecurityMembers){
		List<RemoteSecurityMember> result = List.create(RemoteSecurityMember.class);
		
		for(IvySecurityMember m : ivySecurityMembers){
			RemoteSecurityMember member = mapSecurityMember(m);
			
			if(null != member){
				result.add(member);
			}
		}
		
		return result;
	}	
}
