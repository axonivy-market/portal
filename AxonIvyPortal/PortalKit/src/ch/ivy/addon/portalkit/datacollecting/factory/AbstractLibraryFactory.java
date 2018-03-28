package ch.ivy.addon.portalkit.datacollecting.factory;

import ch.ivy.addon.portalkit.datacollecting.service.AbstractLibraryService;


public abstract class AbstractLibraryFactory {

  public abstract AbstractLibraryService createService();
}
