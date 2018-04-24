package ch.ivy.addon.portalkit.datacollecting.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.RemoteLibrary;
import ch.ivy.addon.portalkit.mapper.RemoteLibraryMapper;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.server.ServerFactory;

public class NoRegisteredAppLibraryService extends AbstractLibraryService {

  @Override
  public Map<String, List<RemoteLibrary>> collectLibraries() {
    List<IApplication> apps =
        ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false);
    Map<String, List<RemoteLibrary>> libraries = new HashMap<>();
    for (IApplication app : apps) {
      libraries.put(
          app.getName(),
          app.getLibraries().stream().filter(lib -> !portalLibraryStrings.contains(lib.getId()))
              .map(RemoteLibraryMapper::mapLibrary).collect(Collectors.toList()));
    }
    return libraries;
  }

}
