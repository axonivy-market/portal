package ch.ivy.addon.portalkit.datacollecting.factory;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.service.ServerService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

public class FactoryCreator {

  public static AbstractLibraryFactory getLibraryFactory() {
    ServerService serverService = new ServerService();
    if (CollectionUtils.isNotEmpty(serverService.findActiveServersNotLocalhost())) {
      if (Ivy.request().getApplication().getName().equals(IApplication.PORTAL_APPLICATION_NAME)) {
        if (serverService.isMultiServers()) {
          return new MultiServerLibraryFactory();
        } else {
          return new MultiAppLibraryFactory();
        }
      }
      return new SingleAppLibraryFactory();
    }
    return new NoRegisteredAppLibraryFactory();
  }
}
