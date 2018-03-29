package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.types.IvyLibrary;
import ch.ivyteam.ivy.application.ILibrary;

public class IvyLibraryTransformer {

  public static IvyLibrary transform(ILibrary library, String application) {
    IvyLibrary result = new IvyLibrary();
    result.setId(library.getId());
    result.setProjectName(library.getProcessModelVersion().getProcessModel().getName());
    result.setProjectVersion(library.getQualifiedVersion().toString());
    result.setApplication(application);
    return result;
  }


  public static List<IvyLibrary> transform(List<ILibrary> libraries, String application) {
    List<IvyLibrary> result = new ArrayList<IvyLibrary>();
    for (ILibrary library : libraries) {
      result.add(transform(library, application));
    }

    return result;
  }
}
