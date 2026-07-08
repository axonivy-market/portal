package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class PortalPackageTest extends BaseTest {

  private dashboardConfigurationPage dashboardConfigurationPage;

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void testVisibilityForPackageManagementTab() {
    grantSpecificPortalPermission(PortalPermission.PORTAL_PACKAGE_MANAGEMENT);
    dashboardConfigurationPage = openDashboardConfiguration();
    assertTrue(dashboardConfigurationPage.isPackageManagementTypeDisplayed(),
        "PackageManagement tab is not displayed");

    denySpecificPortalPermission(PortalPermission.PORTAL_PACKAGE_MANAGEMENT);
    dashboardConfigurationPage = openDashboardConfiguration();
    assertFalse(dashboardConfigurationPage.isPackageManagementTypeDisplayed(),
        "PackageManagement tab is displayed");
  }

  @Test
  public void testExportButtonIsClickable() {
    grantSpecificPortalPermission(PortalPermission.PORTAL_PACKAGE_MANAGEMENT);
    dashboardConfigurationPage = openDashboardConfiguration();
    dashboardConfigurationPage.selectPackageManagementType();
    dashboardConfigurationPage.clickExportButton();
    assertFalse($("span[class$='ui-messages-error-summary']").exists(), "Export triggered an error message");
  }

  private DashboardConfigurationPage openDashboardConfiguration() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    return new NewDashboardPage().openDashboardConfigurationPage();
  }

}
