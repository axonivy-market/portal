package com.axonivy.portal.developerexamples.util;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpResponse;

public class RequestUtils {

  public static void redirect(String uri) throws java.io.IOException {
    IHttpResponse httpResponse = (IHttpResponse) Ivy.response();
    httpResponse.sendRedirect(uri);
  }

}
