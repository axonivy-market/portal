package ch.ivy.ws.addon.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public class ServerUrlUtils {
  private ServerUrlUtils() {}

  /**
   * Gets server url based on system properties. If the system properties are not configured properly, it will return
   * the default server url
   */
  public static String buildUrlFromSystemProperties() {
    String externalProtocol = SystemProperties.getSystemPropertyValue(SystemProperties.EXTERNAL_PROTOCOL);
    String externalHost = SystemProperties.getSystemPropertyValue(SystemProperties.EXTERNAL_HOST_NAME);
    String externalPort = SystemProperties.getSystemPropertyValue(SystemProperties.EXTERNAL_PORT);

    if (!externalProtocol.isEmpty() && !externalHost.isEmpty() && !externalPort.isEmpty()) {
      try {
        int port = Integer.parseInt(externalPort);
        return new URL(externalProtocol, externalHost, port, "").toString();
      } catch (MalformedURLException | NumberFormatException e) {
        Ivy.log()
            .warn(
                "Unable to get server url based on system properties. System properties WebServer.ExternalProtocol/HostName/Port are not configured properly. Please check and correct it.");
      }
    }
    return StringUtils.EMPTY;
  }

}
