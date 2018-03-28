package ch.ivy.addon.portalkit.datacollecting.factory;

import ch.ivy.addon.portalkit.datacollecting.service.AbstractLibraryService;
import ch.ivy.addon.portalkit.datacollecting.service.NoRegisteredAppLibraryService;

public class NoRegisteredAppLibraryFactory extends AbstractLibraryFactory {

  @Override
  public AbstractLibraryService createService() {
    return new NoRegisteredAppLibraryService();
  }

}
