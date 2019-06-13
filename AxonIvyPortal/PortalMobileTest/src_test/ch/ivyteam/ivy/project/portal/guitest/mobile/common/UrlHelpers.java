package ch.ivyteam.ivy.project.portal.guitest.mobile.common;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class UrlHelpers {

  public static String generateAbsoluteProcessStartLink(String relativeProcessStartLink) {
    relativeProcessStartLink = StringUtils.capitalize(relativeProcessStartLink);
    if (relativeProcessStartLink.endsWith(".icm")) {
      return getEngineUrl() + "/casemap/" + getApplicationName() + "/" + relativeProcessStartLink;
    }
    return getEngineUrl() + "/pro/" + getApplicationName() + "/" + relativeProcessStartLink;
  }

  private static String getApplicationName() {
    String applicationName = System.getProperty("engineApplicationName");
    return Optional.ofNullable(applicationName).orElse(PropertyLoader.getApplicationName());
  }

  private static String getEngineUrl() {
    String vmArgUrl = System.getProperty("engineUrl");
    return Optional.ofNullable(vmArgUrl).orElse(
        PropertyLoader.getServerAddress() + ":" + PropertyLoader.getIvyEnginePort() + "/"
            + PropertyLoader.getIvyContextPath());
  }
  
  public static String getAppiumServerUrl() {
    return PropertyLoader.getAppiumAddress() + ":" + PropertyLoader.getAppiumPort();
  }
  
  private static boolean isServerMode() {
    return SystemProperties.isInServerMode() || PropertyLoader.getServerMode();
  }
  
  public static String getLogoutURLOnDesigner() {
    return getEngineUrl() + "/wf/logout.jsp";
  }
}
