package portal.guitest.common;

import java.util.Optional;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;

public class UrlHelpers {

  public static String generateAbsoluteProcessStartLink(String relativeProcessStartLink) {
    //because we renamed PortalExamples project to portal-developer-examples, so no need to capitalize first character of this project
    if(!relativeProcessStartLink.contains("portal-developer-examples") && !relativeProcessStartLink.contains("portal-user-examples")) {
      relativeProcessStartLink = WordUtils.capitalize(relativeProcessStartLink);
    }
    if (relativeProcessStartLink.endsWith(".icm")) {
      return getEngineUrl() + "/casemap/" + getApplicationName() + "/" + relativeProcessStartLink;
    }
    return getEngineUrl() + "/pro/" + getApplicationName() + "/" + relativeProcessStartLink;
  }

  private static String getApplicationName() {
    String applicationName = System.getProperty("test.engine.app");
    return Optional.ofNullable(applicationName).orElse(PropertyLoader.getApplicationName());
  }

  private static String getEngineUrl() {
    String vmArgUrl = System.getProperty("test.engine.url");
    if (vmArgUrl != null) {
      return StringUtils.removeEnd(vmArgUrl, "/");
    } else {
      return "http://" + PropertyLoader.getServerAddress() + ":" + PropertyLoader.getIvyEnginePort() + "/"
          + PropertyLoader.getIvyContextPath();
    }
  }
}
