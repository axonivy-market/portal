package ch.ivy.addon.portalkit.datacollecting.factory;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.datacollecting.service.AbstractLibraryService;
import ch.ivy.addon.portalkit.datacollecting.service.MultiAppLibraryService;
import ch.ivy.addon.portalkit.datacollecting.service.MultiServerLibraryService;
import ch.ivy.addon.portalkit.datacollecting.service.NoRegisteredAppLibraryService;
import ch.ivy.addon.portalkit.datacollecting.service.SingleAppLibraryService;
import ch.ivy.addon.portalkit.service.ServerService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

public class LibraryServiceFactory {

  public static AbstractLibraryService getLibraryService() {
    ServerService serverService = new ServerService();
    if (CollectionUtils.isNotEmpty(serverService.findActiveServersNotLocalhost())) {
      if (Ivy.request().getApplication().getName().equals(IApplication.PORTAL_APPLICATION_NAME)) {
        if (serverService.isMultiServers()) {
          return new MultiServerLibraryService();
        } else {
          return new MultiAppLibraryService();
        }
      }
      return new SingleAppLibraryService();
    }
    return new NoRegisteredAppLibraryService();
  }
}
