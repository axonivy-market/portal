package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.Selenide.open;

import com.axonivy.portal.selenium.page.DashboardConfigurationPage;

public class LinkNavigator {
  private static String portalDashboardConfigurationUrl = "portal/1549F58C18A6C562/PortalDashboardConfiguration.ivp";

  public static void redirectToRelativeLink(String relativeProcessStartUrl) {
    try {
      open(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartUrl));
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  public static void redirectToPortalDashboardConfiguration() {
    redirectToRelativeLink(String.format(portalDashboardConfigurationUrl));
  }

  public static DashboardConfigurationPage navigateToPortalDashboardConfiguration() {
    redirectToRelativeLink(String.format(portalDashboardConfigurationUrl));
    return new DashboardConfigurationPage();
  }

}
