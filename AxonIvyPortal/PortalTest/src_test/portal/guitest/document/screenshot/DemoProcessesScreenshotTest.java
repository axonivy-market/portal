package portal.guitest.document.screenshot;

import static ch.ivy.addon.portalkit.util.ScreenshotUtil.DEMO_FOLDER;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.page.ExampleOverviewPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.userexamples.page.CaseMapPage;
import portal.guitest.userexamples.page.LeaveRequestPage;

public class DemoProcessesScreenshotTest extends ScreenshotTest {
  
  private static String LEAVE_REQUEST_START_LINK = "portal-user-examples/170321BD7F5539D6/start.ivp";
  private static final String CASE_MAP_URL = "/portal-user-examples/70765b37-a3e8-418a-a8d5-c2b3a539408e.icm";
  
  private HomePage homePage;
  private ExampleOverviewPage exampleOverviewPage;
  private static final int SCREENSHOT_WIDTH = 1400;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
  }
  @Test
  public void screenshotUserExampleOverview() throws IOException {
    homePage.closeMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 500));
    ScreenshotUtil.captureHalfLeftPageScreenShot(DEMO_FOLDER + "user-example-guide-link");
    
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1200));
    redirectToRelativeLink(startUserExampleProcess);
    executeDecorateJs("highlightUserExampleCard(0)");
    ScreenshotUtil.capturePageScreenshot(DEMO_FOLDER + "example-overview-leave-request");
    
    refreshPage();
    exampleOverviewPage = new ExampleOverviewPage();
    exampleOverviewPage.waitUntilExampleOverviewDisplayed();
    executeDecorateJs("highlightUserExampleCard(1)");
    ScreenshotUtil.capturePageScreenshot(DEMO_FOLDER + "example-overview-lending-case");
  }

  @Test
  public void screenshotLeaveRequestProcess() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    redirectToRelativeLink(LEAVE_REQUEST_START_LINK);
    LeaveRequestPage leaveRequestPage = new LeaveRequestPage();
    leaveRequestPage.waitUntilLeaveRequestPageDisplayed();
    ScreenshotUtil.capturePageScreenshot(DEMO_FOLDER + "leave-request-creation");
  }
  
  @Test
  public void screenshotCaseMapProcess() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    redirectToRelativeLink(CASE_MAP_URL);
    CaseMapPage caseMapPage = new CaseMapPage();
    caseMapPage.waitUntilCaseMapPageDisplayed();
    ScreenshotUtil.capturePageScreenshot(DEMO_FOLDER + "lending-casemap-collect-personal-data");
    
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1000));
    homePage = caseMapPage.clickSubmitRequestButton();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    TaskDetailsPage taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    taskDetailsPage.clickStartTask();
    
    caseMapPage = new CaseMapPage();
    taskWidgetPage = caseMapPage.clickSubmitButtonAndBackToTaskList();
    taskWidgetPage.sideStepMenuOnActionButton(0);
    executeDecorateJs("highlightTaskActionItem(0, 1)");
    ScreenshotUtil.captureHalfTopPageScreenShot(DEMO_FOLDER + "lending-casemap-external-solvency-service");
    
    taskWidgetPage.clickOnSideStepAction(0, 1);
    ScreenshotUtil.capturePageScreenshot(DEMO_FOLDER + "lending-casemap-approval-task");
  }
}
