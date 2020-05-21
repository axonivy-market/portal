package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.ivydata.service.ILibraryService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.ReleaseState;
import ch.ivyteam.ivy.server.ServerFactory;

public class NoRegisteredAppLibraryService implements ILibraryService {

  @Override
  public Map<String, List<ILibrary>> collectLibraries() {
    List<IApplication> apps =
        ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false);
    Map<String, List<ILibrary>> libraries = new HashMap<>();
    for (IApplication app : apps) {
      libraries.put(
          app.getName(),
          app.getLibraries().stream().filter(lib -> !portalLibraryStrings.contains(lib.getId())
              && (lib.getProcessModelVersion().getReleaseState() == ReleaseState.RELEASED || lib.getProcessModelVersion().getReleaseState() == ReleaseState.DEPRECATED))
              .collect(Collectors.toList()));
    }
    return libraries;
  }

}
