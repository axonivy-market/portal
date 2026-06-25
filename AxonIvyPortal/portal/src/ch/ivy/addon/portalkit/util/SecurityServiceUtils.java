package ch.ivy.addon.portalkit.util;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Utility for security service.
 */
public class SecurityServiceUtils {

  private SecurityServiceUtils() {}

  /**
   * Finds portal home page of the default portal application
   * 
   * @return string
   */
  public static String getDefaultPortalStartUrl() {
    return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath("Start Processes/PortalStart/DefaultApplicationHomePage.ivp");
  }

  public static Object getSessionAttribute(String name) {
    return Ivy.session().getAttribute(name);
  }

  public static void setSessionAttribute(String name, Object value) {
    Ivy.session().setAttribute(name, value);
  }

  public static void removeSessionAttribute(String name) {
    Ivy.session().removeAttribute(name);
  }

}
