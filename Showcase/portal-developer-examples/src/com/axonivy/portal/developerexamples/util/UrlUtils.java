package com.axonivy.portal.developerexamples.util;

import java.net.MalformedURLException;
import java.net.URL;

import com.axonivy.portal.components.service.exception.PortalException;

import ch.ivyteam.ivy.environment.Ivy;

public class UrlUtils {

  public static String getServerUrl() {
    URL url;
    try {
      url = Ivy.html().applicationHomeLink().toAbsoluteUri().toURL();
    } catch (MalformedURLException e) {
      throw new PortalException(e);
    }
    StringBuilder builder = new StringBuilder(url.getProtocol()).append("://").append(url.getHost());
    if (url.getPort() != -1) {
      builder.append(":").append(url.getPort());
    }
    return builder.toString();
  }

}
