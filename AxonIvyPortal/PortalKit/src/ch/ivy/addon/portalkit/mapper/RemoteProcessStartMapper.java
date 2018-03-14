package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.RemoteProcessStart;
import ch.ivy.addon.portalkit.util.UrlValidator;
import ch.ivy.ws.addon.IvyProcessStart;
import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Mapper for remote process start.
 *
 * @author maonguyen
 */
public class RemoteProcessStartMapper {

  protected static RemoteProcessStart mapProcessStart(IvyProcessStart t, Long serverId, String host) {
    RemoteProcessStart result = new RemoteProcessStart();

    result.setDescription(t.getDescription());
    String fullRequestPath = t.getFullRequestPath();
    result.setFullRequestPath(fullRequestPath);
    result.setFullUserFriendlyRequestPath(t.getFullUserFriendlyRequestPath());
    result.setId(t.getId());
    result.setName(t.getName());
    result.setStartLink(UrlValidator.isValidUrl(t.getStartLink()) ? t.getStartLink() : host + t.getStartLink());
    result.setApplicationName(t.getApplicationName());
    result.setProcessElementId(t.getProcessElementId());
    result.setServerId(serverId);

    return result;
  }

  /**
   * Convert from list of {@link IvyProcessStart} to {@link RemoteProcessStart}.
   *
   * @param ivyProcessStarts list of {@link IvyProcessStart}
   * @param serverId 
   * @param host 
   * @return list of {@link RemoteProcessStart}
   */
  public static List<RemoteProcessStart> mapProcessStarts(List<IvyProcessStart> ivyProcessStarts, Long serverId,
      String host) {
    List<RemoteProcessStart> outStarts = List.create(RemoteProcessStart.class);

    for (IvyProcessStart t : ivyProcessStarts) {
      RemoteProcessStart start = mapProcessStart(t, serverId, host);

      if (null != start) {
        outStarts.add(start);
      }
    }
    return outStarts;
  }
}
