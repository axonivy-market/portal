package com.axonivy.portal.component.ivydata.exception;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IProcessModelVersionRequest;

public class PortalIvyDataException extends Exception {

  private static final long serialVersionUID = -8558165268719107465L;

  private final String appName;
  private final String userText;

  public PortalIvyDataException(String userText) {
    this.userText = userText;
    this.appName = StringUtils.EMPTY;
  }

  public PortalIvyDataException(String appName, String userText) {
    this.appName = appName;
    this.userText = userText;
  }

  public String getAppName() {
    if (StringUtils.isBlank(appName)) {
      return Optional.ofNullable(Ivy.request()).map(IProcessModelVersionRequest::getApplication)
          .map(IApplication::getName).orElse(StringUtils.EMPTY);
    }
    return appName;
  }

  public String getUserText() {
    return userText;
  }

  @Override
  public String toString() {
    return String.format("{Application name: %s, Message: %s}", appName, userText);
  }
}
