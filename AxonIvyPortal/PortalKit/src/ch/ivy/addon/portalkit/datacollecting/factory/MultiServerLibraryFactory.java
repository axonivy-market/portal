package ch.ivy.addon.portalkit.datacollecting.factory;

import ch.ivy.addon.portalkit.datacollecting.service.AbstractLibraryService;
import ch.ivy.addon.portalkit.datacollecting.service.MultiServerLibraryService;


public class MultiServerLibraryFactory extends AbstractLibraryFactory {

  @Override
  public AbstractLibraryService createService() {
    return new MultiServerLibraryService();
  }

}
