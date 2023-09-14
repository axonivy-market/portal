package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
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
    newDashboardPage = new NewDashboardPage();
  }
  
  @Test
  public void screenshotDashboardConfigurationUserGuide() throws IOException {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createSampleDashboardUrl);
    showNewDashboard();
    DashboardConfigurationPage dashboardConfigurationPage = newDashboardPage.openDashboardConfigurationPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1050, 750));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-configuration-item");
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-configuration-page");
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "private-dashboard-configuration");
    dashboardConfigurationPage.createPrivateDashboardFromScratch();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardCreationDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "create-private-dashboard-dialog");
    dashboardConfigurationPage.openMultiLanguageDialog();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getMultipleLanguageDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-multi-language-dialog");
    dashboardConfigurationPage.cancelMultiLanguageDialog();
    dashboardConfigurationPage.cancelCreateDashboard();
    dashboardConfigurationPage.openEditPrivateDashboardsPage();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "edit-private-dashboards");
    dashboardConfigurationPage.reorderPrivateDashboard();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "reorder-your-dashboards");
    dashboardConfigurationPage.selectPublicDashboardType();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "public-dashboard-configuration");
    dashboardConfigurationPage.createPublicDashboardFromScratch();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardCreationDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "create-public-dashboard-dialog");
    dashboardConfigurationPage.cancelCreateDashboard();
    dashboardConfigurationPage.openEditPublicDashboardsPage();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "edit-public-dashboards");
    dashboardConfigurationPage.reorderPublicDashboard();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "reorder-public-dashboards");
    dashboardConfigurationPage.openCreatePrivateDashboardMenu();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardTemplates(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-templates");
    dashboardConfigurationPage.openImportPublicDashboards();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getImportDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "import-public-dashboard-dialog");
    dashboardConfigurationPage.cancelImportDashboard();
    dashboardConfigurationPage.openImportPrivateDashboards();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getImportDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "import-private-dashboard-dialog");
    dashboardConfigurationPage.cancelImportDashboard();
    dashboardConfigurationPage.openEditPublicDashboardsPage();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getShareDashboardDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "share-dashboard-dialog");
  }
}
