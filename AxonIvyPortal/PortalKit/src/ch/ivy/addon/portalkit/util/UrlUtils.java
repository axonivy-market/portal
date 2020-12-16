package ch.ivy.addon.portalkit.util;

import java.net.MalformedURLException;
import java.net.URL;

import ch.ivyteam.ivy.environment.Ivy;

public class UrlUtils {
  public static String getServerUrl() throws MalformedURLException {
    URL url = new URL(Ivy.html().applicationHomeRef());
    StringBuilder builder = new StringBuilder(url.getProtocol()).append("://").append(url.getHost());
    if (url.getPort() != -1) {
      builder.append(":").append(url.getPort());
    }
    return builder.toString();
  }
}
