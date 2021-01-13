package ch.ivy.addon.portalkit.publicapi;

import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;

/**
 * Portal API for process start
 *
 */
public final class ProcessStartAPI {
  private ProcessStartAPI() {}
  
  /**
   * Find startable link from friendly request path
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link which session user can start or empty string
   */
  public static String findStartableLinkByUserFriendlyRequestPath(String friendlyRequestPath) {
    return new ProcessStartCollector().findStartableLinkByUserFriendlyRequestPath(friendlyRequestPath);
  }
  
  /**
   * Find start link from friendly request path
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link or empty string
   */
  public static String findRelativeUrlByProcessStartFriendlyRequestPath(String friendlyRequestPath) {
    return ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
  }
}
