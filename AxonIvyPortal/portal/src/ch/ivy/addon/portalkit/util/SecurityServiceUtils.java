package ch.ivy.addon.portalkit.util;

import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.restricted.EngineMode;

/**
 * Utility for security service.
 */
@SuppressWarnings("restriction")
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

  @Deprecated(since = "9.3", forRemoval = true)
  public static String findFriendlyRequestPathContainsKeyword(String keyword){
    return IvyExecutor.executeAsSystem(() -> {
      ProcessStartCollector collector = new ProcessStartCollector();
      Object portalStartPmvId = getSessionAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString());
      return collector.findFriendlyRequestPathContainsKeyword(keyword, portalStartPmvId);
    });
  }

  /**
   * Check current engine is designer or not.
   * 
   * @return check result
   */
  public static boolean isDesigner() {
    return EngineMode.isEmbeddedInDesigner();
  }
}
