package ch.ivy.addon.portalkit.util;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.application.IApplication;
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
    return IvyExecutor.executeAsSystem(() -> {
      IApplication defaultPortalApplication = getDefaultPortalApplication();
      if (defaultPortalApplication != null) {
        return ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(defaultPortalApplication, "Start Processes/PortalStart/DefaultApplicationHomePage.ivp");
      }
      return StringUtils.EMPTY;
    });
  }

  private static IApplication getDefaultPortalApplication() {
    return getServer().getApplicationConfigurationManager().findApplication(PortalConstants.PORTAL_APPLICATION_NAME);
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

  public static String getApplicationNameFromSession() {
    Object selectedAppAttribute = getSessionAttribute(SessionAttribute.SELECTED_APP.toString());
    return selectedAppAttribute != null ? selectedAppAttribute.toString() : null;
  }

  public static String getApplicationDisplayNameFromSession() {
    Object selectedAppDisplayNameAttribute = getSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString());
    return selectedAppDisplayNameAttribute != null ? selectedAppDisplayNameAttribute.toString() : null;
  }
  
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
