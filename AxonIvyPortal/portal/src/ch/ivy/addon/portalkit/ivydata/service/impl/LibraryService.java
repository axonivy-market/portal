package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.ReleaseState;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;

public class LibraryService{
  final List<String> portalLibraryStrings = Stream.of(PortalConstants.PORTAL_LIBRARY_ID).collect(Collectors.toList());

  public Map<String, List<ILibrary>> collectLibraries() {
    Map<String, List<ILibrary>> libraries = new HashMap<>();
    List<IApplication> applicationsInSecurityContext = IApplicationRepository.of(ISecurityContext.current()).all();
    List<ReleaseState> displayStates = Arrays.asList(ReleaseState.RELEASED, ReleaseState.DEPRECATED);
    
    for (IApplication currentApp : applicationsInSecurityContext) {
      List<ILibrary> collect = currentApp
                              .getLibraries()
                              .stream()
                              .filter(lib -> !portalLibraryStrings.contains(lib.getId()) && displayStates.contains(lib.getProcessModelVersion().getReleaseState()))
                              .sorted((l1, l2) -> StringUtils.compareIgnoreCase(l1.getProcessModelVersion().getProcessModel().getName(), l2.getProcessModelVersion().getProcessModel().getName()))
                              .collect(Collectors.toList());
      
      libraries.put(currentApp.getName(), collect);
    }
    return libraries;
  }

}
