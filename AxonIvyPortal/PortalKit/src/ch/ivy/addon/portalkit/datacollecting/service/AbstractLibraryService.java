package ch.ivy.addon.portalkit.datacollecting.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch.ivy.addon.portalkit.bo.RemoteLibrary;
import ch.ivy.addon.portalkit.enums.PortalLibrary;

public abstract class AbstractLibraryService {

  protected final List<String> portalLibraryStrings = Stream.of(PortalLibrary.values()).map(PortalLibrary::getValue)
      .collect(Collectors.toList());
  
  public abstract Map<String, List<RemoteLibrary>> collectLibraries();
}
