package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpResponse;

public class RequestUtil {

  public static void redirect(String uri) throws java.io.IOException {
    Ivy.log().error("go to redirect IHttpResponse");
    IHttpResponse httpResponse = (IHttpResponse) Ivy.response();
    httpResponse.sendRedirect(uri);
  }

}
