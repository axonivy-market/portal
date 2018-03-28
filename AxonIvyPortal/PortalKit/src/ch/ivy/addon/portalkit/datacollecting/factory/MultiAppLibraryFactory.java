package ch.ivy.addon.portalkit.datacollecting.factory;

import ch.ivy.addon.portalkit.datacollecting.service.AbstractLibraryService;
import ch.ivy.addon.portalkit.datacollecting.service.MultiAppLibraryService;

public class MultiAppLibraryFactory extends AbstractLibraryFactory {

  @Override
  public AbstractLibraryService createService() {
    return new MultiAppLibraryService();
  }

}
