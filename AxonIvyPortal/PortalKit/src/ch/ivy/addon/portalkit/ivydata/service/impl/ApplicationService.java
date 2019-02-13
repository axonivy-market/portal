package ch.ivy.addon.portalkit.ivydata.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.service.IApplicationService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.server.ServerFactory;

public class ApplicationService implements IApplicationService {

  private ApplicationService() {
  }
  
  public static ApplicationService newInstance() {
    return new ApplicationService();
  }
  
  @Override
  public List<IvyApplication> findAll() {
    return IvyExecutor.executeAsSystem(() -> {
      List<IApplication> applications =
          ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false);
      return applications.stream().map(this::toIvyApplication).collect(Collectors.toList());
    });
  }

  @Override
  public List<IvyApplication> findBy(List<String> names) {
    return IvyExecutor.executeAsSystem(() -> 
      names.stream()
          .map(ServerFactory.getServer().getApplicationConfigurationManager()::findApplication)
          .filter(Objects::nonNull)
          .map(this::toIvyApplication)
          .collect(toList())
    );
  }
  
  @Override
  public List<IvyApplication> findActiveAll() {
    return IvyExecutor.executeAsSystem(() -> {
      List<IApplication> applications =
          ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false);
      return applications.stream()
          .filter(app -> app.findReleasedLibrary(PortalLibrary.PORTAL_STYLE.getValue()) != null)
          .map(this::toIvyApplication)
          .filter(IvyApplication::isActive)
          .collect(Collectors.toList());
    });
  }
  
  private IvyApplication toIvyApplication(IApplication app) {
    IvyApplication ivyApp = new IvyApplication();
    ivyApp.setName(app.getName());
    ivyApp.setActive(app.getActivityOperationState() == ActivityOperationState.ACTIVE);
    return ivyApp;
  }

}
