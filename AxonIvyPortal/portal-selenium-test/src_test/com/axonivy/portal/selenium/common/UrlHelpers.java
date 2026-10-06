package com.axonivy.portal.selenium.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.apache.commons.lang3.text.WordUtils;

public class UrlHelpers {

  @SuppressWarnings("deprecation")
  public static String generateAbsoluteProcessStartLink(String relativeProcessStartLink) {
    // because we renamed PortalExamples project to portal-developer-examples, so no need to capitalize first character
    // of this project
    // we combined PortalStyle, PortalKit and PortalTemplate to portal, so no need to capitalize first character of this
    // project
    if (!relativeProcessStartLink.contains("portal/") && !relativeProcessStartLink.contains("portal-developer-examples")
        && !relativeProcessStartLink.contains("portal-user-examples")
        && !relativeProcessStartLink.contains("portal-components-examples")) {
      relativeProcessStartLink = WordUtils.capitalize(relativeProcessStartLink);
    }
    if (relativeProcessStartLink.endsWith(".icm") || relativeProcessStartLink.endsWith(".m.json")) {
      return getEngineUrl() + getContext() + getApplicationName() + "/casemap/" + relativeProcessStartLink;
    }
    return getEngineUrl() + getContext() + getApplicationName() + "/pro/" + relativeProcessStartLink;
  }

  public static String getLogoutLink() {
    return getEngineUrl() + getContext() + getApplicationName() + "/logout";
  }

  private static String getApplicationName() {
    String applicationName = System.getProperty("test.engine.app");
    return Optional.ofNullable(applicationName).orElse(PropertyLoader.getApplicationName());
  }

  private static String getContext() {
    return Optional.ofNullable(System.getProperty("test.engine.context"))
        .map(context -> context.replace("/", "") + "/")
        .orElse("default/");
  }

  @SuppressWarnings("deprecation")
  private static String getEngineUrl() {
    String vmArgUrl = System.getProperty("test.engine.url");
    if (vmArgUrl != null) {
      try {
        URL originalURL = new URL(vmArgUrl);
        URL newURL = new URL(originalURL.getProtocol(), originalURL.getHost(), originalURL.getPort(), originalURL.getFile());
        // Make sure the engine URL end with slash (/).
        // Example: https://localhost:8081/
        String newURLStr = newURL.toString();
        return newURLStr.endsWith("/") ? newURLStr : newURLStr + "/";
      } catch (MalformedURLException e) {
        throw new PortalGUITestException("Wrong Engine URL");
      }

    } else {
      return "http://" + PropertyLoader.getServerAddress() + ":" + PropertyLoader.getIvyEnginePort() + "/";
    }
  }
}
