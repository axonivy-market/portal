package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.ivydata.service.ILibraryService;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.ReleaseState;
import ch.ivyteam.ivy.server.ServerFactory;

public class MultiAppLibraryService implements ILibraryService {

  @Override
  public Map<String, List<ILibrary>> collectLibraries() {
    Map<String, List<ILibrary>> libraries = new HashMap<>();
    RegisteredApplicationService applicationService = new RegisteredApplicationService();
    for (Application app : applicationService.findAllIvyApplications()) {
      IApplication ivyApplication = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(app.getName());
      if (ivyApplication != null) {
        libraries.put(
            app.getName(),
            ivyApplication.getLibraries().stream()
              .filter(lib -> !portalLibraryStrings.contains(lib.getId())
                  && (lib.getProcessModelVersion().getReleaseState() == ReleaseState.RELEASED || lib.getProcessModelVersion().getReleaseState() == ReleaseState.DEPRECATED))
              .collect(Collectors.toList()));
      }
    }
    return libraries;
  }

}
