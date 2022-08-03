package portal.guitest.document.screenshot;

import static portal.guitest.common.Variable.ENABLE_GROUP_CHAT;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;

public class DashboardConfigurationScreenshotTest extends ScreenshotTest {
  private HomePage homePage;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    updatePortalSetting(ENABLE_GROUP_CHAT.getKey(), "true");
    homePage = new HomePage();
  }
  
  @Test
  public void screenshotDashboardConfigurationUserGuide() throws IOException {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createSampleDashboardUrl);
    showNewDashboard();
    var dashboardConfigurationPage = homePage.openDashboardConfigurationPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1050, 750));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-configuration-item");
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-configuration-page");
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "private-dashboard-configuration");
    dashboardConfigurationPage.createPrivateDashboardFromScratch();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardCreationDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "create-private-dashboard-dialog");
    dashboardConfigurationPage.cancelCreateDashboard();
    dashboardConfigurationPage.openEditPrivateDashboards();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "edit-private-dashboards");
    dashboardConfigurationPage.reorderPrivateDashboards();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "reorder-your-dashboards");
    dashboardConfigurationPage.selectPublicDashboardType();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "public-dashboard-configuration");
    dashboardConfigurationPage.createPublicDashboardFromScratch();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardCreationDialog(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "create-public-dashboard-dialog");
    dashboardConfigurationPage.cancelCreateDashboard();
    dashboardConfigurationPage.openEditPublicDashboards();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "edit-public-dashboards");
    dashboardConfigurationPage.reorderPublicDashboards();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardConfigurationPage(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "reorder-public-dashboards");
    dashboardConfigurationPage.createPrivateDashboard();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardTemplates(), ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-templates");
  }
}
