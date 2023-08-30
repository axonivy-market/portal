package com.axonivy.portal.components.ivydata.utils;

import java.util.Objects;

import com.axonivy.portal.components.ivydata.exception.PortalIvyDataErrorType;
import com.axonivy.portal.components.ivydata.exception.PortalIvyDataException;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;

public class ServiceUtilities {

  private ServiceUtilities() {}

  public static IApplication findApp(final String appName) throws PortalIvyDataException {
    Objects.requireNonNull(appName, "The appName must not be null");

    IApplication app = IApplicationRepository.instance().findByName(appName).orElse(null);
    if (app == null || app.getActivityState() != ActivityState.ACTIVE) {
      throw new PortalIvyDataException(appName, PortalIvyDataErrorType.APP_NOT_FOUND.toString());
    }
    return app;
  }

  public static IUser findUser(String username) {
    return Sudo.get(() -> {
      return ISecurityContext.current().users().find(username);
    });
  }
}
