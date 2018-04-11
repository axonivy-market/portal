package ch.ivy.addon.portalkit.datacollecting.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.RemoteLibrary;
import ch.ivy.addon.portalkit.mapper.RemoteLibraryMapper;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.server.ServerFactory;

public class MultiAppLibraryService extends AbstractLibraryService {

  @Override
  public Map<String, List<RemoteLibrary>> collectLibraries() {
    Map<String, List<RemoteLibrary>> libraries = new HashMap<>();
    ApplicationService applicationService = new ApplicationService();
    for (Application app : applicationService.findOnlineIvyApps()) {
      IApplication ivyApplication = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(app.getName());
      if (ivyApplication != null) {
        libraries.put(
            app.getName(),
            ivyApplication.getLibraries().stream().filter(lib -> !portalLibraryStrings.contains(lib.getId()))
                .map(RemoteLibraryMapper::mapLibrary).collect(Collectors.toList()));
      }
    }
    return libraries;
  }

}
