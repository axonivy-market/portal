package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.RemoteWebStartable;
import ch.ivy.ws.addon.IvyWebStartable;
import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Mapper for remote web startable.
 */
public class RemoteWebStartableMapper {

  private RemoteWebStartableMapper() {}
  protected static RemoteWebStartable mapWebStartable(IvyWebStartable webStartable) {
    RemoteWebStartable result = new RemoteWebStartable();

    result.setName(webStartable.getName());
    result.setDisplayName(webStartable.getDisplayName());
    result.setDescription(webStartable.getDescription());
    result.setStartLink(webStartable.getLink());
    result.setActivatorDisplayName(webStartable.getActivatorDisplayName());
    result.setActivatorMemberName(webStartable.getActivatorMemberName());
    return result;
  }

  /**
   * Convert from list of {@link IvyWebStartable} to {@link RemoteWebStartable}.
   *
   * @param webStartables list of {@link IvyWebStartable}
   * @return list of {@link RemoteWebStartable}
   */
  public static List<RemoteWebStartable> mapWebStartables(List<IvyWebStartable> webStartables) {
    List<RemoteWebStartable> outStarts = List.create(RemoteWebStartable.class);

    for (IvyWebStartable t : webStartables) {
      RemoteWebStartable start = mapWebStartable(t);

      if (start != null) {
        outStarts.add(start);
      }
    }
    return outStarts;
  }
}
