package ch.ivy.addon.portalkit.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpResponse;
import ch.ivyteam.ivy.request.RequestUriFactory;

public class RequestUtil {

  public static void redirect(String uri) throws java.io.IOException {
    IHttpResponse httpResponse = (IHttpResponse) Ivy.response();
    httpResponse.sendRedirect(uri);
  }
  
  public static String getContextPath(FacesContext facesContext) {
    if (facesContext == null) {
      return "/" + RequestUriFactory.getIvyContextName();
    }
    return ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getContextPath();
  }
  
  public static String getRelativeUrlByRequestPath(String requestPath) {
    return getContextPath(FacesContext.getCurrentInstance()) + requestPath;
  }
  
  public static String getRelativeUrlByRequestPath(FacesContext facesContext, String requestPath) {
    return getContextPath(facesContext) + requestPath;
  }
}
