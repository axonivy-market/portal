package portal.guitest.document.screenshot;

import static portal.guitest.common.Variable.ENABLE_GROUP_CHAT;
import static portal.guitest.common.Variable.SHOW_ENVIRONMENT_INFO;
import static portal.guitest.common.Variable.SHOW_LEGACY_UI;
import static portal.guitest.common.Variable.SHOW_USER_GUIDE;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.TaskWidgetPage;

public class DashboardScreenshotTest extends ScreenshotTest {
  
  private HomePage homePage;
  private NewDashboardPage newDashboardPage;
  private static final int SCREENSHOT_WIDTH = 1500;
  private static final int SCREENSHOT_HD_WIDTH = 1920;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    updatePortalSetting(ENABLE_GROUP_CHAT.getKey(), "true");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createUserFavoriteProcess);
    refreshPage();
    homePage = new HomePage();
    homePage.waitForStatisticRendered();
  }
  
  @Test
  public void takeScreenshotOverlayGuide() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    updatePortalSetting(SHOW_USER_GUIDE.getKey(), "true");
    homePage = new HomePage();
    Sleeper.sleep(500); // wait for js calculate resize event
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_FOLDER + "overlay-guide");
  }
  
  @Test
  public void takeScreenshotWithEnvironmentInfo() throws IOException {
    updatePortalSetting(SHOW_ENVIRONMENT_INFO.getKey(), "true");
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 500));
    Sleeper.sleep(500); // wait for js render scrollbar
    executeDecorateJs("highlightServerInfo()");
    ScreenshotUtil.captureHalfRightPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "environment-info");
  }
  
  
  @Test
  public void screenshotDashBoard() throws IOException {
    ScreenshotUtil.maximizeBrowser();
    ScreenshotUtil.captureElementScreenshot(homePage.getProcessWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "process-widget");
    ScreenshotUtil.captureElementScreenshot(homePage.getStatisticWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "statistic-widget");
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_HD_WIDTH, 800));
    Sleeper.sleep(500); // wait for js calculate height of task widget done
    ScreenshotUtil.captureElementScreenshot(homePage.getTaskWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "task-widget");
  }
  
  @Test
  public void screenshotCustomizedDashBoard() throws IOException {
    showNewCustomizedDashboard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.openTaskList();
    ScreenshotUtil.resizeBrowserAndCaptureWholeScreen(ScreenshotUtil.DASHBOARD_FOLDER + "page-header-footer", new Dimension(SCREENSHOT_WIDTH, 900));
  }
  
  @Test
  public void screenshotDashBoardWithAnnotation() throws IOException {
    ScreenshotUtil.maximizeBrowser();
    executeDecorateJs("numberingStatisticWidget();");
    ScreenshotUtil.captureElementScreenshot(homePage.getStatisticWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "statistics-key-information");
    
    ScreenshotUtil.resizeBrowser(new Dimension(1400, 800));
    refreshHomePage();
    executeDecorateJs("numberingTopBar()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(homePage.getTopBar(), ScreenshotUtil.DASHBOARD_FOLDER + "portal-header-with-numbering-annotation", new ScreenshotMargin(20, 20, 20, 120));
    
    executeDecorateJs("numberingTaskItem();");
    ScreenshotUtil.captureElementScreenshot(homePage.getTaskWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "personal-tasks-key-information");
    
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    refreshHomePage();
    executeDecorateJs("highlightAndNumberingDashboardSections();");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_FOLDER + "dashboard-3-sections");
    
    refreshHomePage();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_HD_WIDTH, 800));
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.openCompactSortMenu();
    executeDecorateJs("numberingTaskFilterAndSort();");
    ScreenshotUtil.captureElementScreenshot(homePage.getTaskWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "personal-tasks-sort-and-search-features");
    
  }
  

  @Test
  public void screenshotNewDashBoard() throws IOException{
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();

    ScreenshotUtil.resizeBrowserAndCaptureWholeScreen(ScreenshotUtil.DASHBOARD_FOLDER + "dashboard", new Dimension(1200, 800));

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    executeDecorateJs("highlightLogo();");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "left-menu");

    refreshPage();
    newDashboardPage.openMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "expanded-left-menu");

    ScreenshotUtil.resizeBrowser(new Dimension(1400, 800));
    executeDecorateJs("highlightTopBar()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "portal-header");
  }

  @Test
  public void screenshotConfigureCustomWidget() throws IOException {
    login(TestAccount.ADMIN_USER);
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.switchToEditMode();
    newDashboardPage.addNewCustomWidget();
    newDashboardPage.selectCustomWidgetTypeProcess();
    newDashboardPage.selectIvyProcessForCustomWidget("Investment List (Example for Custom Widget on Dashboard)");

    WebElement dateField = newDashboardPage.findElementById("widget-configuration-form:new-widget-configuration-component:parammeters:1:param-calendar-_input");
    newDashboardPage.type(dateField, "24 Nov, 2021 00:00");

    WebElement stringField = newDashboardPage.findElementById("widget-configuration-form:new-widget-configuration-component:parammeters:2:param-string-");
    newDashboardPage.type(stringField, "a short note");

    WebElement userField = newDashboardPage.findElementById("widget-configuration-form:new-widget-configuration-component:parammeters:0:param-user-:user-selection_input");
    newDashboardPage.type(userField, "demo");
    newDashboardPage.waitForElementDisplayed(By.id("widget-configuration-form:new-widget-configuration-component:parammeters:0:param-user-:user-selection_panel"), true);
    newDashboardPage.click(By.xpath("//*[@id='widget-configuration-form:new-widget-configuration-component:parammeters:0:param-user-:user-selection_panel']/ul/li"));

    ScreenshotUtil.captureElementScreenshot(newDashboardPage.getConfigurationDialog(), ScreenshotUtil.DASHBOARD_FOLDER + "process-custom-widget-configuration");
  }

  private void showNewCustomizedDashboard() {
    updatePortalSetting(SHOW_LEGACY_UI .getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
  }

  private void refreshHomePage() {
    refreshPage();
    homePage.waitForStatisticRendered();
  }
}
