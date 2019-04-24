package ch.ivy.addon.portalkit.service;


import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.server.ServerFactory;

public class ServerService {
  private static ServerService INSTANCE;

  private ServerService() {}

  public static final ServerService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ServerService();
    }
    return INSTANCE;
  }

  public List<IApplication> getApplicationsRelatedToPortal() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    List<String> configuredApps =
        service.findAllIvyApplications().stream().map(Application::getName).collect(Collectors.toList());
    List<IApplication> apps;
    if (CollectionUtils.isEmpty(configuredApps)) {
      apps = IvyExecutor.executeAsSystem(
          () -> ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false));
    } else {
      apps = IvyExecutor.executeAsSystem(() -> ServiceUtilities.findApps(configuredApps));
      IApplication defaultApplication = ServerFactory.getServer().getApplicationConfigurationManager()
          .findApplication(PortalConstants.PORTAL_APPLICATION_NAME);
      if (defaultApplication != null && defaultApplication.getActivityState() == ActivityState.ACTIVE) {
        apps.add(defaultApplication);
      }
    }
    return apps;
  }
}
