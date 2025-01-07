package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
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
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createSampleDashboardUrl);
  }

  @Test
  public void screenshotDashboardConfigurationUserGuide() throws IOException {
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    DashboardConfigurationPage dashboardConfigurationPage = newDashboardPage.openDashboardConfigurationPage();
    ScreenshotUtils.resizeBrowser(new Dimension(1050, 750));
    ScreenshotUtils
        .capturePageScreenshot(ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-configuration-item");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-configuration-page", new ScreenshotMargin(10));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "private-dashboard-configuration", new ScreenshotMargin(10));
    dashboardConfigurationPage.createPrivateDashboardFromScratch();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardCreationDialog(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "create-private-dashboard-dialog", new ScreenshotMargin(10));
    dashboardConfigurationPage.openMultiLanguageDialog();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getMultipleLanguageDialog(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-multi-language-dialog", new ScreenshotMargin(10));
    dashboardConfigurationPage.cancelMultiLanguageDialog();
    dashboardConfigurationPage.cancelCreateDashboard();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPageWithActionsMenu(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "edit-private-dashboards", new ScreenshotMargin(50));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "reorder-your-dashboards", new ScreenshotMargin(10));
    dashboardConfigurationPage.selectPublicDashboardType();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "public-dashboard-configuration", new ScreenshotMargin(10));
    dashboardConfigurationPage.createPublicDashboardFromScratch();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardCreationDialog(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "create-public-dashboard-dialog", new ScreenshotMargin(10));
    dashboardConfigurationPage.cancelCreateDashboard();
    dashboardConfigurationPage.openEditPublicDashboardsPage();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPageWithActionsMenu(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "edit-public-dashboards", new ScreenshotMargin(10));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "reorder-public-dashboards", new ScreenshotMargin(10));
    dashboardConfigurationPage.openCreatePublicDashboardMenu();
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    ScreenshotUtils.captureElementScreenshot(dashboardConfigurationPage.getDashboardTemplates(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-templates");
    // screenshot highlight for accessibility template
    ScreenshotUtils.executeDecorateJs("createBlackMediumOutline($($('.dashboard-action-container')[4]));");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getDashboardTemplates(),
            ScreenshotUtils.ACCESSIBILITY_DASHBOARD_FOLDER + "accessibility-dashboard-creation", new ScreenshotMargin(10));
    dashboardConfigurationPage.closeAddDashboardDialog();
    dashboardConfigurationPage.openEditPublicDashboardsPage();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dashboardConfigurationPage.getShareDashboardDialog(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "share-dashboard-dialog", new ScreenshotMargin(10));
  }
  
  @Test
  public void screenshotImportPublicDashboard() throws IOException {
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    DashboardConfigurationPage dashboardConfigurationPage = newDashboardPage.openDashboardConfigurationPage();
    dashboardConfigurationPage.openCreatePublicDashboardMenu();
    dashboardConfigurationPage.openImportPublicDashboards();
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    ScreenshotUtils.captureElementScreenshot(dashboardConfigurationPage.getImportDialog(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "import-public-dashboard-dialog");
  }
  
  @Test
  public void screenshotImportPrivateDashboard() throws IOException {
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    DashboardConfigurationPage dashboardConfigurationPage = newDashboardPage.openDashboardConfigurationPage();
    dashboardConfigurationPage.openImportPrivateDashboards();
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    ScreenshotUtils.captureElementScreenshot(dashboardConfigurationPage.getImportDialog(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "import-private-dashboard-dialog");
  }
}
