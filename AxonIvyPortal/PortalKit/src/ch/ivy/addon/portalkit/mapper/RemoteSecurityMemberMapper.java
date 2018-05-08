package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.RemoteRole;
import ch.ivy.addon.portalkit.bo.RemoteSecurityMember;
import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivy.ws.addon.IvySecurityMember;
import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Map between IvySecurityMember and RemoteSecurityMember.
 * 
 * @author bolt
 *
 */
public class RemoteSecurityMemberMapper {

  private RemoteSecurityMemberMapper() {}
  /**
   * Convert from web service security member to remote security member.
   * 
   * @param ivySecurityMemeber web service security memeber
   * @return remote security memeber.
   * @see IvySecurityMember
   * @see RemoteSecurityMember
   */
  public static RemoteSecurityMember mapSecurityMember(IvySecurityMember ivySecurityMemeber) {
    RemoteSecurityMember result = new RemoteSecurityMember();

    result.setDisplayName(ivySecurityMemeber.getDisplayName());
    result.setMemberName(ivySecurityMemeber.getMemberName());

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
  public static List<RemoteSecurityMember> mapSecurityMembers(List<IvySecurityMember> ivySecurityMembers) {
    List<RemoteSecurityMember> result = List.create(RemoteSecurityMember.class);

    for (IvySecurityMember m : ivySecurityMembers) {
      RemoteSecurityMember member = mapSecurityMember(m);

      if (null != member) {
        result.add(member);
      }
    }

    return result;
  }

  public static RemoteSecurityMember mapFromRemoteRole(RemoteRole role) {
    RemoteSecurityMember result = new RemoteSecurityMember();

    result.setDisplayName(role.getDisplayName());
    result.setMemberName(role.getMemberName());
    result.setUser(false);

    return result;
  }

  public static List<RemoteSecurityMember> mapFromRemoteRoles(java.util.List<RemoteRole> roles) {
    List<RemoteSecurityMember> result = List.create(RemoteSecurityMember.class);

    for (RemoteRole m : roles) {
      RemoteSecurityMember member = mapFromRemoteRole(m);

      if (null != member) {
        result.add(member);
      }
    }

    return result;
  }

  public static RemoteSecurityMember mapFromRemoteUser(RemoteUser user) {
    RemoteSecurityMember result = new RemoteSecurityMember();

    result.setDisplayName(user.getName());
    result.setMemberName(user.getUsername());
    result.setUser(true);

    return result;
  }

  public static List<RemoteSecurityMember> mapFromRemoteUsers(java.util.List<RemoteUser> users) {
    List<RemoteSecurityMember> result = List.create(RemoteSecurityMember.class);

    for (RemoteUser m : users) {
      RemoteSecurityMember member = mapFromRemoteUser(m);

      if (null != member) {
        result.add(member);
      }
    }

    return result;
  }
}
