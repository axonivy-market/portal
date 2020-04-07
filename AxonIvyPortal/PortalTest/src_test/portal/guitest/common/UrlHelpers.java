package portal.guitest.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;

public class UrlHelpers {

  public static String generateAbsoluteProcessStartLink(String relativeProcessStartLink) {
    relativeProcessStartLink = WordUtils.capitalize(relativeProcessStartLink);
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
      try {
        URL originalURL = new URL(vmArgUrl);
        URL newURL = new URL(originalURL.getProtocol(), "localhost", originalURL.getPort(), originalURL.getFile());
        return StringUtils.removeEnd(newURL.toString(), "/");
      } catch (MalformedURLException e) {
        throw new PortalGUITestException("Wrong Engine URL");
      }

    } else {
      return "http://" + PropertyLoader.getServerAddress() + ":" + PropertyLoader.getIvyEnginePort() + "/"
          + PropertyLoader.getIvyContextPath();
    }
  }
}
