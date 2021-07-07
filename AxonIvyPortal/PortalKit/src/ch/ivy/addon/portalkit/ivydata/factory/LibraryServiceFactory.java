package ch.ivy.addon.portalkit.ivydata.factory;

import ch.ivy.addon.portalkit.ivydata.service.ILibraryService;
import ch.ivy.addon.portalkit.ivydata.service.impl.LibraryService;

public class LibraryServiceFactory {

  private LibraryServiceFactory() {}

  public static ILibraryService getLibraryService() {
    return new LibraryService();
  }
}
