package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.ws.addon.IvyUser;
import ch.ivyteam.ivy.scripting.objects.List;

public class RemoteUserMapper {

  private RemoteUserMapper() {}

  /**
   * Convert a ivy role object to a security member.
   * 
   * @param ivyUser ivy user
   * @param server server that contains user.
   * @return RemoteSecurityMember
   * @see Server
   * @see IvyUser
   * @see RemoteUser
   */
  public static RemoteUser mapIvyUser(IvyUser ivyUser, Server server) {
    RemoteUser result = null;
    if (ivyUser != null) {
      result =
          new RemoteUser(ivyUser.getId(), ivyUser.getDisplayName(), ivyUser.getMemberName(), ivyUser.getApplication(),
              server.getId());
    }
    return result;
  }

  /**
   * Maps list of Ivy Users to list of RemoteSecurityMember.
   * 
   * @param ivyUsers list of web service user.
   * @param server web service server that contains users.
   * @return List<RemoteSecurityMember>
   * @see Server
   * @see IvyUser
   * @see RemoteUser
   */
  public static List<RemoteUser> mapIvyUsers(List<IvyUser> ivyUsers, Server server) {
    List<RemoteUser> result = List.create(RemoteUser.class);

    for (IvyUser r : ivyUsers) {
      RemoteUser member = mapIvyUser(r, server);

      if (null != member) {
        result.add(member);
      }
    }

    return result;
  }

}
