package ch.ivy.addon.portalkit.ivydata.factory;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.ivydata.service.ILibraryService;
import ch.ivy.addon.portalkit.ivydata.service.impl.MultiAppLibraryService;
import ch.ivy.addon.portalkit.ivydata.service.impl.NoRegisteredAppLibraryService;
import ch.ivy.addon.portalkit.ivydata.service.impl.SingleAppLibraryService;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivyteam.ivy.environment.Ivy;

public class LibraryServiceFactory {

  private LibraryServiceFactory() {}

  public static ILibraryService getLibraryService() {
    ApplicationService applicationService = new ApplicationService();
    if (CollectionUtils.isNotEmpty(applicationService.findAllIvyApplications())) {
      if (Ivy.request().getApplication().getName().equals(PortalConstants.PORTAL_APPLICATION_NAME)) {
        return new MultiAppLibraryService();
      }
      return new SingleAppLibraryService();
    }
    return new NoRegisteredAppLibraryService();
  }
}
