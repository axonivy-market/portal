package ch.ivy.ws.addon.service;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.ws.addon.types.IvyApplication;
import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.server.ServerFactory;

public class ApplicationServiceImpl implements IApplicationService {

  @Override
  public List<IvyApplication> getAllApplications() throws BpmError {
    try {
      return ServerFactory.getServer().getSecurityManager()
          .executeAsSystem(this::getAllApplicationOfServerExceptSystem);
    } catch (Exception exception) {
      throw BpmError.create("ivy:portalconnector:error:applicationservice").withCause(exception).build();
    }
  }

  private List<IvyApplication> getAllApplicationOfServerExceptSystem() {
    List<IApplication> serverApplications =
        ServerFactory.getServer().getApplicationConfigurationManager().getApplications();
    return serverApplications.stream().filter(app -> !app.isSystem()).map(this::convertToIvyApplicationBy)
        .collect(Collectors.toList());
  }

  private IvyApplication convertToIvyApplicationBy(IApplication app) {
    IvyApplication ivyApp = new IvyApplication();
    ivyApp.setName(app.getName());
    ivyApp.setId(app.getId());
    ivyApp.setIsActive(Objects.equals(ActivityOperationState.ACTIVE, app.getActivityOperationState()));
    return ivyApp;
  }

  @Override
  public List<IvyApplication> getApplicationsBy(List<String> applicationNames) {
    try {
      return ServerFactory.getServer().getSecurityManager()
          .executeAsSystem(getApplicationsStateCallable(applicationNames));
    } catch (Exception exception) {
      throw BpmError.create("ivy:portalconnector:error:applicationservice").withCause(exception).build();
    }
  }

  private Callable<List<IvyApplication>> getApplicationsStateCallable(List<String> applicationNames) {
    return () -> applicationNames.stream()
        .map(ServerFactory.getServer().getApplicationConfigurationManager()::findApplication).filter(Objects::nonNull)
        .map(this::newIvyApplication).collect(toList());
  }

  private IvyApplication newIvyApplication(IApplication foundApplication) {
    IvyApplication application = new IvyApplication();
    application
        .setIsActive(Objects.equals(foundApplication.getActivityOperationState(), ActivityOperationState.ACTIVE));
    application.setName(foundApplication.getName());
    application.setId(foundApplication.getId());
    return application;
  }

  @Override
  public List<Long> convertApplicationIdsToList(String applicationIds) {
    if (StringUtils.isBlank(applicationIds)) {
      return Collections.emptyList();
    }
    return Arrays.stream(applicationIds.split("\\s*,\\s*")).filter(id -> id.trim().matches("\\d+"))
        .map(id -> Long.valueOf(id.trim())).collect(Collectors.toList());
  }

}
