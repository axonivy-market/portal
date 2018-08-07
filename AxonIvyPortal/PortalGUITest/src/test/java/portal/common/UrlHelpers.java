package portal.common;

import org.apache.commons.lang.WordUtils;

public class UrlHelpers {

  public static String generateAbsoluteProcessStartLink(String relativeProcessStartLink) {
    if (!SystemProperties.isInServerMode()) {
      relativeProcessStartLink = WordUtils.capitalize(relativeProcessStartLink);
    }
    return "http://" + PropertyLoader.getServerAddress() + ":" + PropertyLoader.getIvyEnginePort() + "/"
        + PropertyLoader.getIvyContextPath() + "/pro/" + PropertyLoader.getApplicationName() + "/"
        + relativeProcessStartLink;
  }
}
