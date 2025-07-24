package ch.ivy.addon.portalkit.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.Protocol;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;

public class UrlUtils {
  private static final String EMBED_IN_FRAME = "embedInFrame";

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

  public static String formatLinkWithoutEmbedInFrameParam(String link) {
    try {
      URI uri = new URI(link);
      String query = uri.getQuery();
      if (query == null) {
        return link;
      }
      String filteredQuery = Arrays.stream(query.split("&")).filter(param -> !param.equals(EMBED_IN_FRAME))
          .collect(Collectors.joining("&"));

      URI newUri = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(),
          filteredQuery.isEmpty() ? null : filteredQuery, uri.getFragment());

      return newUri.toString();
    } catch (Exception e) {
      Ivy.log().warn("The link might be in the wrong format: {0}", link);
      return link;
    }
  }

  public static String buildUrlQueryString(Map<String, String> params) {
    if (params == null || params.isEmpty()) {
      return StringUtils.EMPTY;
    }
    String queryString = params.entrySet().stream().map(e -> {
      return e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.ISO_8859_1);
    }).collect(Collectors.joining("&"));
    return queryString;
  }
}
