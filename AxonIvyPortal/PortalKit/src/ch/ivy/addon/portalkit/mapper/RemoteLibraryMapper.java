package ch.ivy.addon.portalkit.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.RemoteLibrary;
import ch.ivy.ws.addon.IvyLibrary;
import ch.ivyteam.ivy.application.ILibrary;

public class RemoteLibraryMapper {

  public static RemoteLibrary mapLibrary(IvyLibrary ivyLibrary) {
    RemoteLibrary result = new RemoteLibrary();
    result.setId(ivyLibrary.getId());
    result.setProjectName(ivyLibrary.getProjectName());
    result.setProjectVersion(ivyLibrary.getProjectVersion());
    result.setApplication(ivyLibrary.getApplication());
    return result;
  }

  public static List<RemoteLibrary> mapLibraries(List<IvyLibrary> ivyLibraries) {
    return ivyLibraries.stream().map(RemoteLibraryMapper::mapLibrary).collect(Collectors.toList());
  }
  
  public static RemoteLibrary mapLibrary(ILibrary library) {
    RemoteLibrary result = new RemoteLibrary();
    result.setId(library.getId());
    result.setProjectName(library.getProcessModelVersion().getProcessModel().getName());
    result.setProjectVersion(library.getQualifiedVersion().toString());
    return result;
  }
}
