package ch.ivy.addon.portalkit.ivydata.factory;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.ivydata.service.ILibraryService;
import ch.ivy.addon.portalkit.ivydata.service.impl.MultiAppLibraryService;
import ch.ivy.addon.portalkit.ivydata.service.impl.NoRegisteredAppLibraryService;
import ch.ivy.addon.portalkit.ivydata.service.impl.SingleAppLibraryService;
import ch.ivy.addon.portalkit.service.ServerService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

public class LibraryServiceFactory {

  private LibraryServiceFactory() {}

  public static ILibraryService getLibraryService() {
    ServerService serverService = new ServerService();
    if (CollectionUtils.isNotEmpty(serverService.findActiveServersNotLocalhost())) {
      if (Ivy.request().getApplication().getName().equals(IApplication.PORTAL_APPLICATION_NAME)) {
        return new MultiAppLibraryService();
      }
      return new SingleAppLibraryService();
    }
    return new NoRegisteredAppLibraryService();
  }
}
