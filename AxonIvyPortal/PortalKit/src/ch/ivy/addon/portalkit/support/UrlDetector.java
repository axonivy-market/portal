package ch.ivy.addon.portalkit.support;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;

public class UrlDetector {

  /**
   * <p>
   * Determines the Base URL, e.g.,
   * {@literal http://localhost:8080/myApplication} from the
   * {@link FacesContext}.
   * </p>
   *
   * @param facesContext
   *          The {@link FacesContext} to examine.
   * @return the base URL.
   * @throws MalformedURLException
   *           if an exception occurs during parsing of the URL.
   * @since 1.3
   */
  public String getBaseURL(final FacesContext facesContext) throws MalformedURLException {
    if (facesContext == null) {
      IApplicationConfigurationManager configManager = ServerFactory.getServer().getApplicationConfigurationManager();
      try {
        return RequestUriFactory.createLocalHostServerUri(configManager).toString() + "/"
            + RequestUriFactory.getIvyContextName(configManager);
      } catch (UnknownHostException | URISyntaxException e) {
        throw new PortalException(e);
      }
    }
    return getBaseURL(facesContext.getExternalContext());
  }

  /**
   * <p>
   * Determines the Base URL, e.g.,
   * {@literal http://localhost:8080/myApplication} from the
   * {@link ExternalContext}.
   * </p>
   *
   * @param externalContext
   *          The {@link ExternalContext} to examine.
   * @return the base URL.
   * @throws MalformedURLException
   *           if an exception occurs during parsing of the URL.
   */
  public String getBaseURL(final ExternalContext externalContext) throws MalformedURLException {
    return getBaseURL((HttpServletRequest) externalContext.getRequest());
  }

  /**
   * <p>
   * Determines the Base URL, e.g.,
   * {@literal http://localhost:8080/myApplication} from the
   * {@link HttpServletRequest}.
   * </p>
   *
   * @param request
   *          The {@link HttpServletRequest} to examine.
   * @return the base URL.
   * @throws MalformedURLException
   *           if an exception occurs during parsing of the URL.
   * @see URL
   */
  public String getBaseURL(final HttpServletRequest request) throws MalformedURLException {
    return new URL(request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath())
        .toString();
  }

  public String getBaseURLWithoutPort(final HttpServletRequest request) throws MalformedURLException {
    return new URL(request.getScheme(), request.getServerName(), request.getContextPath()).toString();
  }

  public String getHost(String url) throws MalformedURLException {
    URL urlObject = new URL(url);
    int portNumber = getPortForConstructingUrl(urlObject.getProtocol(), urlObject.getPort());
    return new URL(urlObject.getProtocol(), urlObject.getHost(), portNumber, "").toString();
  }

  /**
   * If using default port of the protocol then return -1 to ignore port when
   * constructing URL. E.g: http://localhost/ivy instead of
   * http://localhost:80/ivy
   */
  public int getPortForConstructingUrl(String protocol, int port) {
    if ((RequestUriFactory.HTTPS_PROTOCOL.equalsIgnoreCase(protocol) && port == RequestUriFactory.HTTPS_PORT)
        || (RequestUriFactory.HTTP_PROTOCOL.equalsIgnoreCase(protocol) && port == RequestUriFactory.HTTP_PORT)) {
      return -1;
    }
    return port;
  }
}
