package com.axonivy.portal.component.ivydata.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.axonivy.portal.component.enums.PortalLibrary;
import com.axonivy.portal.component.ivydata.bo.IvyApplication;
import com.axonivy.portal.component.ivydata.service.IApplicationService;
import com.axonivy.portal.component.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.server.ServerFactory;

public class ApplicationService implements IApplicationService {

  private static ApplicationService instance;

  private ApplicationService() {
  }
  
  public static ApplicationService newInstance() {
    return new ApplicationService();
  }

  public static final ApplicationService getInstance() {
    if (instance == null) {
      instance = new ApplicationService();
    }
    return instance;
  }
  
  @Override
  public List<IvyApplication> findAll() {
    return IvyExecutor.executeAsSystem(() -> {
      List<IApplication> applications = getAllPortalAppsOnServer();
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
      List<IApplication> applications = getAllPortalAppsOnServer();
      return applications.stream()
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

  @Override
  public List<IvyApplication> findActiveAllInvolvedUser(String username) {
    return IvyExecutor.executeAsSystem(() -> {
      List<IApplication> applications = getAllPortalAppsOnServer();
      return applications.stream()
          .filter(app -> app.getSecurityContext().findUser(username) != null)
          .map(this::toIvyApplication)
          .filter(IvyApplication::isActive)
          .collect(Collectors.toList());
    });
  }

  private List<IApplication> getAllPortalAppsOnServer() {
    List<IApplication> applications =
        ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false);
    return applications.stream()
        .filter(app -> app.findReleasedLibrary(PortalLibrary.PORTAL_STYLE.getValue()) != null)
        .collect(Collectors.toList());
  }

}
