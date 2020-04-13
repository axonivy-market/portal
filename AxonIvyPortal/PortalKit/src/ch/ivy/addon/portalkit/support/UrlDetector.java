package ch.ivy.addon.portalkit.support;

import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ch.ivyteam.ivy.request.RequestUriFactory;

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
      return RequestUriFactory.createLocalHostServerUri().toString() + "/" + RequestUriFactory.getIvyContextName();
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
  
  public String getBaseURLWithoutContextPath(final HttpServletRequest request) throws MalformedURLException {
    URL url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath());
    return url.toString().substring(0, url.toString().lastIndexOf(url.getPath()));
  }

  public String getBaseURLWithoutPort(final HttpServletRequest request) throws MalformedURLException {
    return new URL(request.getScheme(), request.getServerName(), request.getContextPath()).toString();
  }

}
