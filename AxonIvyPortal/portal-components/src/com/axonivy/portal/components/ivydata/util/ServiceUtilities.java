package com.axonivy.portal.components.ivydata.util;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.Objects;

import com.axonivy.portal.components.ivydata.exception.PortalIvyDataErrorType;
import com.axonivy.portal.components.ivydata.exception.PortalIvyDataException;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;

public class ServiceUtilities {

  private ServiceUtilities() {}

  public static IApplication findApp(final String appName) throws PortalIvyDataException {
    Objects.requireNonNull(appName, "The appName must not be null");

    IApplication app = getServer().getApplicationConfigurationManager().findApplication(appName);
    if (app == null || app.getActivityState() != ActivityState.ACTIVE) {
      throw new PortalIvyDataException(appName, PortalIvyDataErrorType.APP_NOT_FOUND.toString());
    }
    return app;
  }
}