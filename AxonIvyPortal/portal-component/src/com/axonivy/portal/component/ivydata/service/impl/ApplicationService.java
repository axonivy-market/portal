package com.axonivy.portal.component.ivydata.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.axonivy.portal.component.enums.PortalLibrary;
import com.axonivy.portal.component.ivydata.bo.IvyApplication;
import com.axonivy.portal.component.ivydata.service.IApplicationService;
import com.axonivy.portal.component.util.IvyExecutor;

import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;

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
      List<IApplication> applications = getAllPortalAppsOnSecurityContext();
      return applications.stream().map(this::toIvyApplication).collect(Collectors.toList());
    });
  }

  @Override
  public List<IvyApplication> findBy(List<String> names) {
    Function<String, IApplication> mapper = (name) -> IApplicationRepository.instance().findByName(name).orElse(null);

    return IvyExecutor.executeAsSystem(() -> 
          names.stream()
          .map(mapper)
          .filter(Objects::nonNull)
          .map(this::toIvyApplication)
          .collect(toList())
    );
  }
  
  @Override
  public List<IvyApplication> findActiveAll() {
    return IvyExecutor.executeAsSystem(() -> {
      List<IApplication> applications = getAllPortalAppsOnSecurityContext();
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

  @Deprecated(forRemoval = true, since = "9.4")
  @Override
  public List<IvyApplication> findActiveAllInvolvedUser(String username) {
    return new ArrayList<>();
  }

  private List<IApplication> getAllPortalAppsOnSecurityContext() {
    List<IApplication> applications = IApplicationRepository.instance().allOf(ISecurityContext.current());
    return applications.stream()
        .filter(app -> app.findReleasedLibrary(PortalLibrary.PORTAL_KIT.getValue()) != null)
        .collect(Collectors.toList());
  }

}
