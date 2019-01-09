package ch.ivy.addon.portalkit.ivydata.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.service.IApplicationService;
import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.server.ServerFactory;

public class ApplicationService implements IApplicationService {

  private ApplicationService() {
  }
  
  public static ApplicationService newInstance() {
    return new ApplicationService();
  }
  
  @Override
  public List<IvyApplication> findAll() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        List<IApplication> applications =
            ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false);
        return applications.stream().map(this::toIvyApplication).collect(Collectors.toList());
      });
    } catch (Exception exception) {
      throw BpmError.create("ivy:portalconnector:error:applicationservice").withCause(exception).build();
    }
  }

  @Override
  public List<IvyApplication> findBy(List<String> names) {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        return names.stream()
            .map(ServerFactory.getServer().getApplicationConfigurationManager()::findApplication)
            .filter(Objects::nonNull)
            .map(this::toIvyApplication)
            .collect(toList());
      });
    } catch (Exception exception) {
      throw BpmError.create("ivy:portalconnector:error:applicationservice").withCause(exception).build();
    }
  }
  
  @Override
  public List<IvyApplication> findActiveAll() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        List<IApplication> applications =
            ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false);
        return applications.stream().map(this::toIvyApplication)
            .filter(IvyApplication::isActive)
            .collect(Collectors.toList());
      });
    } catch (Exception exception) {
      throw BpmError.create("ivy:portalconnector:error:applicationservice").withCause(exception).build();
    }
  }

  private IvyApplication toIvyApplication(IApplication app) {
    IvyApplication ivyApp = new IvyApplication();
    ivyApp.setName(app.getName());
    ivyApp.setActive(app.getActivityOperationState() == ActivityOperationState.ACTIVE);
    return ivyApp;
  }

}
