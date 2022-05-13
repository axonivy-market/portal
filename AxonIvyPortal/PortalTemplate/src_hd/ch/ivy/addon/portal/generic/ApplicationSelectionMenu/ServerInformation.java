package ch.ivy.addon.portal.generic.ApplicationSelectionMenu;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.net.InetAddress;
import java.net.UnknownHostException;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;

/**
 * An instance of this class contains information of current server such as: hostname, active environment.
 */
public class ServerInformation {

  private static final String ERROR = "[ERROR]";

  private final String host;
  private final String environment;

  public ServerInformation() {
    host = getHostName();
    environment = getActiveEnvironment();
  }

  private String getActiveEnvironment() {
    try {
      String activeEnvironment = IApplication.current().getActiveEnvironment();
      if (isNotEmpty(activeEnvironment)) {
        return activeEnvironment;
      }
      return "Default";
    } catch (PersistencyException e) {
      Ivy.log().error("Cannot get the active environment.", e);
      return ERROR;
    }
  }

  private String getHostName() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      Ivy.log().error("Cannot get the hostname", e);
      return ERROR;
    }
  }

  public String getHost() {
    return host;
  }

  public String getEnvironment() {
    return environment;
  }
}
