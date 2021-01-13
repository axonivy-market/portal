package ch.ivy.addon.portalkit.publicapi;

import ch.ivy.addon.portal.generic.navigation.PortalNavigatorInFrame;

/**
 * Portal API for navigation in iFrame
 *
 */
public final class PortalNavigatorInFrameAPI {
  private PortalNavigatorInFrameAPI() {}
  
  /**
   * Navigates to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page: task list or task details or global search
   */
  public static void navigateToPortalEndPage() {
    PortalNavigatorInFrame.navigateToPortalEndPage();
  }

  /**
   * Navigate to target url
   * @param url target url
   */
  public static void navigateToUrl(String url) {
    PortalNavigatorInFrame.navigateToUrl(url);
  }

}
