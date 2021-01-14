package ch.ivy.addon.portalkit.publicapi;

import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.application.IApplication;

/**
 * Portal API for process start
 *
 */
public final class ProcessStartAPI {
  private ProcessStartAPI() {}
  
  /**
   * Find startable link from friendly request path
   * @param application application
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link which session user can start or empty string
   */
  public static String findStartableLinkByUserFriendlyRequestPath(IApplication application, String friendlyRequestPath) {
    return new ProcessStartCollector(application).findStartableLinkByUserFriendlyRequestPath(friendlyRequestPath);
  }
  
  /**
   * Find link from friendly request path
   * @param application application
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link or empty string
   */
  public static String findLinkByFriendlyRequestPath(IApplication application, String friendlyRequestPath) {
    return new ProcessStartCollector(application).findLinkByFriendlyRequestPath(friendlyRequestPath);
  }
}
