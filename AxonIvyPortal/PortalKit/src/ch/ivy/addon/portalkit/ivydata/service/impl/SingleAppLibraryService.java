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
import ch.ivyteam.ivy.server.IServer;
import ch.ivyteam.ivy.server.ServerFactory;

public class SingleAppLibraryService implements ILibraryService {

  @Override
  public Map<String, List<ILibrary>> collectLibraries() {
    List<ILibrary> libraries =
        Ivy.request().getApplication().getLibraries().stream()
            .filter(lib -> !portalLibraryStrings.contains(lib.getId()) 
                && (lib.getProcessModelVersion().getReleaseState() == ReleaseState.RELEASED || lib.getProcessModelVersion().getReleaseState() == ReleaseState.DEPRECATED))
            .collect(Collectors.toList());
    Map<String, List<ILibrary>> librariesMap = new HashMap<>();
    librariesMap.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/projectInfo/applicationProjectVersion"), libraries);
    return librariesMap;
  }

  public ILibrary findLibraryFromApp(String appName, String libraryId) {
    IServer server = ServerFactory.getServer();
    IApplication app = server.getApplicationConfigurationManager().findApplication(appName);
    if (app != null) {
      return app.findReleasedLibrary(libraryId);
    }
    return null;
  }
}
