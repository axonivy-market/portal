package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.service.ILibraryService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.ReleaseState;
import ch.ivyteam.ivy.environment.Ivy;

public class LibraryService implements ILibraryService {

  @Override
  public Map<String, List<ILibrary>> collectLibraries() {
    IApplication currentApp = Ivy.request().getApplication(); 
    Map<String, List<ILibrary>> libraries = new HashMap<>();
    libraries.put(
        currentApp.getName(),
        currentApp.getLibraries().stream().filter(lib -> !portalLibraryStrings.contains(lib.getId())
            && (lib.getProcessModelVersion().getReleaseState() == ReleaseState.RELEASED || lib.getProcessModelVersion().getReleaseState() == ReleaseState.DEPRECATED))
            .sorted((l1, l2) -> StringUtils.compareIgnoreCase(l1.getProcessModelVersion().getProcessModel().getName(),
                l2.getProcessModelVersion().getProcessModel().getName()))
            .collect(Collectors.toList()));
    return libraries;
  }

}
