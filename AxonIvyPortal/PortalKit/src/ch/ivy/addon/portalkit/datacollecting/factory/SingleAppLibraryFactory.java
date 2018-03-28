package ch.ivy.addon.portalkit.datacollecting.factory;

import ch.ivy.addon.portalkit.datacollecting.service.AbstractLibraryService;
import ch.ivy.addon.portalkit.datacollecting.service.SingleAppLibraryService;

public class SingleAppLibraryFactory extends AbstractLibraryFactory {

  @Override
  public AbstractLibraryService createService() {
    return new SingleAppLibraryService();
  }

}
