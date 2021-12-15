package ch.ivy.addon.portalkit.service;


import static ch.ivy.addon.portalkit.constant.PortalConstants.PORTAL_APPLICATION_NAME;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.server.ServerFactory;

public class ServerService {
  private static ServerService instance;

  private ServerService() {}

  public static final ServerService getInstance() {
    if (instance == null) {
      instance = new ServerService();
    }
    return instance;
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
      if (!configuredApps.contains(PORTAL_APPLICATION_NAME)) {
        configuredApps.add(PORTAL_APPLICATION_NAME);
      }
      apps = IvyExecutor.executeAsSystem(() -> ServiceUtilities.findApps(configuredApps));
    }
    return IvyExecutor.executeAsSystem(() -> apps.stream().filter(app -> app.getActivityState() == ActivityState.ACTIVE).collect(Collectors.toList()));
  }
}
