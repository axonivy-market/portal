package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.RemoteSubstitute;
import ch.ivy.ws.addon.IvySubstitute;
import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Map between IvySubstitute to RemoteSubstitute.
 * 
 */
public class RemoteSubstituteMapper {

  /**
   * Convert from IvySubstitute to RemoteSubstitute.
   * 
   * @param ivySubstitute
   *          web service substitute
   * @return remote substitute
   * @see IvySubstitute
   * @see RemoteSubstitute
   */
  protected static RemoteSubstitute mapToRemoteSubstitute(IvySubstitute ivySubstitute, Integer serverId) {
    RemoteSubstitute result = new RemoteSubstitute();
    result.setAppName(ivySubstitute.getAppName());
    result.setDescription(ivySubstitute.getDescription());
    result.setForThisRole(ivySubstitute.getForThisRole());
    result.setRoleDisplayName(ivySubstitute.getRoleDisplayName());
    result.setMySubstitute(ivySubstitute.getMySubstitute());
    result.setServerId(serverId);
    return result;
  }

  /**
   * Maps list of {@link IvySubstitute} to list of {@link RemoteSubstitute}.
   * 
   * @param ivySubstitutes
   *          list of {@link IvySubstitute}
   * @param serverId
   *          server id
   * @return List<{@link RemoteSubstitute}>
   * @see IvySubstitute
   * @see RemoteSubstitute
   */
  public static List<RemoteSubstitute> mapToRemoteSubstitutes(List<IvySubstitute> ivySubstitutes, Integer serverId) {
    List<RemoteSubstitute> outSubstitutes = List.create(RemoteSubstitute.class);

    for (IvySubstitute substitute : ivySubstitutes) {
      RemoteSubstitute remoteSubstitute = mapToRemoteSubstitute(substitute, serverId);

      if (null != remoteSubstitute) {
        outSubstitutes.add(remoteSubstitute);
      }
    }

    return outSubstitutes;
  }

  /**
   * Convert from IvySubstitute to RemoteSubstitute
   * 
   * @return RemoteSubstitute
   * @see IvySubstitute
   * @see RemoteSubstitute
   */
  protected static IvySubstitute mapToIvySubstitute(RemoteSubstitute remoteSubstitute) {
    IvySubstitute result = new IvySubstitute();
    result.setAppName(remoteSubstitute.getAppName());
    result.setDescription(remoteSubstitute.getDescription());
    result.setForThisRole(remoteSubstitute.getForThisRole());
    result.setMySubstitute(remoteSubstitute.getMySubstitute());
    return result;
  }

  /**
   * Maps list of {@link IvySubstitute} to list of {@link RemoteSubstitute}.
   * 
   * @param remoteSubstitutes
   *          list of remote substitute
   * @param serverId
   *          server id
   * @return List<{@link RemoteSubstitute}>
   * @see IvySubstitute
   * @see RemoteSubstitute
   */
  public static List<IvySubstitute> mapToIvySubstitutesForServer(List<RemoteSubstitute> remoteSubstitutes,
      Integer serverId) {
    List<IvySubstitute> outSubstitutes = List.create(IvySubstitute.class);

    for (RemoteSubstitute substitute : remoteSubstitutes) {
      if (substitute.getServerId().equals(serverId)) {
        IvySubstitute ivySubstitute = mapToIvySubstitute(substitute);

        if (null != ivySubstitute) {
          outSubstitutes.add(ivySubstitute);
        }
      }
    }

    return outSubstitutes;
  }
}
