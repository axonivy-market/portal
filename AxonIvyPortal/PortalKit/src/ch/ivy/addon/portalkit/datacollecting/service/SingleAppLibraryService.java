package ch.ivy.addon.portalkit.datacollecting.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.RemoteLibrary;
import ch.ivy.addon.portalkit.mapper.RemoteLibraryMapper;
import ch.ivyteam.ivy.environment.Ivy;

public class SingleAppLibraryService extends AbstractLibraryService {

  @Override
  public Map<String, List<RemoteLibrary>> collectLibraries() {
    List<RemoteLibrary> libraries = Ivy.request().getApplication().getLibraries().stream()
        .filter(lib -> !portalLibraryStrings.contains(lib.getId())).map(RemoteLibraryMapper::mapLibrary).collect(Collectors.toList());
    Map<String, List<RemoteLibrary>> librariesMap = new HashMap<>();
    librariesMap.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/projectInfo/applicationProjectVersion"), libraries);
    return librariesMap;
  }

}
