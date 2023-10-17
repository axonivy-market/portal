package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class DashboardConfigurationScreenshotTest extends ScreenshotBaseTest {
  private NewDashboardPage newDashboardPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.ENABLE_GROUP_CHAT.getKey(), "true");
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "false");
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createSampleDashboardUrl);
  }
  
  @Test
  public void screenshotDashboardConfigurationUserGuide() throws IOException {
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    DashboardConfigurationPage dashboardConfigurationPage = newDashboardPage.openDashboardConfigurationPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1050, 750));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-configuration-item");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-configuration-page", new ScreenshotMargin(10));
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "private-dashboard-configuration", new ScreenshotMargin(10));
    dashboardConfigurationPage.createPrivateDashboardFromScratch();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardCreationDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "create-private-dashboard-dialog", new ScreenshotMargin(10));
    dashboardConfigurationPage.openMultiLanguageDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getMultipleLanguageDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-multi-language-dialog", new ScreenshotMargin(10));
    dashboardConfigurationPage.cancelMultiLanguageDialog();
    dashboardConfigurationPage.cancelCreateDashboard();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "edit-private-dashboards", new ScreenshotMargin(10));
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "reorder-your-dashboards", new ScreenshotMargin(10));
    dashboardConfigurationPage.selectPublicDashboardType();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "public-dashboard-configuration", new ScreenshotMargin(10));
    dashboardConfigurationPage.createPublicDashboardFromScratch();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardCreationDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "create-public-dashboard-dialog", new ScreenshotMargin(10));
    dashboardConfigurationPage.cancelCreateDashboard();
    dashboardConfigurationPage.openEditPublicDashboardsPage();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "edit-public-dashboards", new ScreenshotMargin(10));
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "reorder-public-dashboards", new ScreenshotMargin(10));
    dashboardConfigurationPage.openCreatePrivateDashboardMenu();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardTemplates(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-templates", new ScreenshotMargin(10));
    dashboardConfigurationPage.openImportPublicDashboards();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getImportDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "import-public-dashboard-dialog", new ScreenshotMargin(10));
    dashboardConfigurationPage.cancelImportDashboard();
    dashboardConfigurationPage.openImportPrivateDashboards();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getImportDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "import-private-dashboard-dialog", new ScreenshotMargin(10));
    dashboardConfigurationPage.cancelImportDashboard();
    dashboardConfigurationPage.openEditPublicDashboardsPage();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getShareDashboardDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "share-dashboard-dialog", new ScreenshotMargin(10));
  }
}
