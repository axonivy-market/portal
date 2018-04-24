package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.IProcessModel;

public class LibraryUtils {

	public static ILibrary findReleasedLibrary(IApplication application, String libraryId) {
		ILibrary library = null;
        for (IProcessModel processModel : application.getProcessModels()) {
          ILibrary iLibrary = processModel.getReleasedProcessModelVersion().getLibrary();
          if (libraryId.equals(iLibrary.getId())) {
        	  if (library == null || library.getQualifiedVersion().isLessThan(iLibrary.getQualifiedVersion())) {
        		  library = iLibrary;
        	  }
        	  
          }
        }
        return library;
	}
}
