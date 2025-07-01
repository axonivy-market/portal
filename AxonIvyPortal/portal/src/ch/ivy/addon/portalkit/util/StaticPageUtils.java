package ch.ivy.addon.portalkit.util;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;

import ch.ivyteam.ivy.application.IApplication;

public class StaticPageUtils {

  private static final String DEFAULT_FRAME_PAGE = "Start Processes/PortalStart/DefaultFramePage.ivp";
  
  public static String buildUrl(String staticPageUrl, Boolean embedInFrame) {
    if (embedInFrame) {
      return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(DEFAULT_FRAME_PAGE) + "?relativeUrl=/"
          + IApplication.current().getName() + "/faces/view/" + staticPageUrl;
    }
    return "/" + IApplication.current().getName() + "/faces/view/" + staticPageUrl;
  }
}
