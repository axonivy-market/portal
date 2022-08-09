package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivyteam.ivy.application.ILibrary;

@FunctionalInterface
public interface ILibraryService { 

  final List<String> portalLibraryStrings = Stream.of(PortalLibrary.values()).map(PortalLibrary::getValue)
      .collect(Collectors.toList());
  
  Map<String, List<ILibrary>> collectLibraries();
}
