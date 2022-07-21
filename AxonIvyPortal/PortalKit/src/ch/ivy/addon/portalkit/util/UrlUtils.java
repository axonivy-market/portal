package ch.ivy.addon.portalkit.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.Protocol;
import ch.ivyteam.ivy.environment.Ivy;

public class UrlUtils {
  private static final String EMBED_IN_FRAME = "embedInFrame";

  public static String getServerUrl() throws MalformedURLException {
    URL url = new URL(Ivy.html().applicationHomeRef());
    StringBuilder builder = new StringBuilder(url.getProtocol()).append("://").append(url.getHost());
    if (url.getPort() != -1) {
      builder.append(":").append(url.getPort());
    }
    return builder.toString();
  }

  public static boolean isIvyUrl(String url) {
    return url == null
        || (StringUtils.contains(url, ".ivp?")
            || StringUtils.endsWith(url, ".ivp")
            || StringUtils.endsWith(url, ".icm"));
  }

  public static String buildUrl(String url) {
    return correctProcessUrl(url);
  }

  private static String correctProcessUrl(String url) {
    String processUrl = url;
    if (processUrl != null && !hasProtocolOrIsARelativeUrl(processUrl)) {
      processUrl = Protocol.HTTP.getValue() + processUrl;
    }
    return processUrl;
  }

  private static boolean hasProtocolOrIsARelativeUrl(String url) {
    String urlInLowerCase = url.toLowerCase();
    return urlInLowerCase.startsWith(Protocol.HTTP.getValue())
        || urlInLowerCase.startsWith(Protocol.HTTPS.getValue())
        || urlInLowerCase.startsWith("/");
  }

  public static String formatLinkWithEmbedInFrameParam(String link) {
    if (StringUtils.isBlank(link) || link.contains(EMBED_IN_FRAME)) {
      return link;
    }
    return link + (link.contains("?") ? "&" : "?") + EMBED_IN_FRAME;
  }

}
