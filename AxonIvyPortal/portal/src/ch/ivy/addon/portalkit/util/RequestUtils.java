package ch.ivy.addon.portalkit.util;

import java.net.URI;
import java.net.URISyntaxException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpResponse;

public class RequestUtils {

  public static void redirect(String uri) throws java.io.IOException {
    IHttpResponse httpResponse = (IHttpResponse) Ivy.response();
    httpResponse.sendRedirect(uri);
  }

  public static boolean isMobileDevice() {
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String userAgent = request.getHeader("user-agent");
    return userAgent.matches(".*Android.*|.*webOS.*|.*iPhone.*|.*iPad.*|.*iPod.*|.*BlackBerry.*|.*IEMobile.*|.*Opera Mini.*");
  }

  public static String getFullURL(HttpServletRequest request) {
    StringBuilder requestURL = new StringBuilder(request.getRequestURI());
    String queryString = request.getQueryString();

    if (StringUtils.isBlank(queryString)) {
        return requestURL.toString();
    } else {
        return requestURL.append('?').append(queryString).toString();
    }
  }

  /**
   * Returns true if {@code url} is safe to use as a server-side redirect target.
   * A URL is considered safe when it is either a relative path or an absolute URL
   * with the same origin as the current request (matching scheme, host, and effective port).
   * Protocol-relative URLs ({@code //host}) and opaque schemes ({@code javascript:},
   * {@code data:}, ...) are rejected.
   */
  public static boolean isSafeRedirectUrl(String url, HttpServletRequest request) {
    if (StringUtils.isBlank(url) || request == null) {
      return false;
    }
    if (url.startsWith("//")) {
      return false;
    }
    try {
      URI target = new URI(url);
      if (!target.isAbsolute()) {
        return true;
      }
      if (target.getHost() == null) {
        return false;
      }
      URI current = new URI(request.getRequestURL().toString());
      return StringUtils.equalsIgnoreCase(target.getHost(), current.getHost())
          && StringUtils.equalsIgnoreCase(target.getScheme(), current.getScheme())
          && effectivePort(target) == effectivePort(current);
    } catch (URISyntaxException ex) {
      return false;
    }
  }

  private static int effectivePort(URI uri) {
    int port = uri.getPort();
    if (port != -1) {
      return port;
    }
    return "https".equalsIgnoreCase(uri.getScheme()) ? 443 : 80;
  }
}
