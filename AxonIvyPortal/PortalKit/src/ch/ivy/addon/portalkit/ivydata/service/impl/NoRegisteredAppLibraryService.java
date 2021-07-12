package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.ivydata.service.ILibraryService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.ReleaseState;
import ch.ivyteam.ivy.environment.Ivy;

public class NoRegisteredAppLibraryService implements ILibraryService {

  @Override
  public Map<String, List<ILibrary>> collectLibraries() {
    IApplication currentApp = Ivy.request().getApplication(); 
    Map<String, List<ILibrary>> libraries = new HashMap<>();
    libraries.put(
        currentApp.getName(),
        currentApp.getLibraries().stream().filter(lib -> !portalLibraryStrings.contains(lib.getId())
            && (lib.getProcessModelVersion().getReleaseState() == ReleaseState.RELEASED || lib.getProcessModelVersion().getReleaseState() == ReleaseState.DEPRECATED))
            .collect(Collectors.toList()));
    return libraries;
  }

}
