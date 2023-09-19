package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseMapPage;
import com.axonivy.portal.selenium.page.ExampleOverviewPage;
import com.axonivy.portal.selenium.page.LeaveRequestPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest(headless = false)
public class DemoProcessesScreenshotTest extends ScreenshotBaseTest {

  private static String LEAVE_REQUEST_START_LINK = "portal-user-examples/170321BD7F5539D6/start.ivp";
  private static final String CASE_MAP_URL = "/portal-user-examples/70765b37-a3e8-418a-a8d5-c2b3a539408e.icm";

  private MainMenuPage mainMenuPage;
  // private ExampleOverviewPage exampleOverviewPage;
  private static final int SCREENSHOT_WIDTH = 1400;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    mainMenuPage = new MainMenuPage();
  }

  @Test
  public void screenshotUserExampleLink() throws IOException {
    showNewDashboard();
    mainMenuPage = new MainMenuPage();
    mainMenuPage.expandMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 500));
    ScreenshotUtil.executeDecorateJs("highlightUserExampleNavigation()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.DEMO_FOLDER + "user-example-guide-link");
  }
  
  @Test
  public void screenshotUserExampleOverview() throws IOException{
    mainMenuPage.closeMainMenu();
    
    ScreenshotUtil.resizeBrowser(new Dimension(1600, 1300));
    redirectToRelativeLinkWithEmbedInFrame(startUserExampleProcess);
    ExampleOverviewPage exampleOverviewPage = new ExampleOverviewPage();
    exampleOverviewPage.switchToIFrameOfTask();
    exampleOverviewPage.waitForIFrameContentVisible();
    ScreenshotUtil.executeDecorateJs("highlightUserExampleCard(0)");
    exampleOverviewPage.switchBackToParent();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DEMO_FOLDER + "example-overview-leave-request");
    refreshPage();
    exampleOverviewPage = new ExampleOverviewPage();
    exampleOverviewPage.switchToIFrameOfTask();
    exampleOverviewPage.waitForIFrameContentVisible();
    ScreenshotUtil.executeDecorateJs("highlightUserExampleCard(1)");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DEMO_FOLDER + "example-overview-lending-case");
  }

  @Test
  public void screenshotLeaveRequestProcess() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 950));
    redirectToRelativeLinkWithEmbedInFrame(LEAVE_REQUEST_START_LINK);
    LeaveRequestPage leaveRequestPage = new LeaveRequestPage();
    leaveRequestPage.switchToIFrameOfTask();
    leaveRequestPage.waitForIFrameContentVisible();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DEMO_FOLDER + "leave-request-creation");
  }
  
  @Test
  public void screenshotCaseMapProcess() throws IOException {
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "false");
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 900));
    redirectToRelativeLinkWithEmbedInFrame(CASE_MAP_URL);
    CaseMapPage caseMapPage = new CaseMapPage();
    caseMapPage.switchToIFrameOfTask();
    caseMapPage.waitForIFrameContentVisible();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DEMO_FOLDER + "lending-casemap-collect-personal-data");
    
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1000));
    NewDashboardPage newDashboardPage = caseMapPage.clickSubmitRequestButton();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1500));
    newDashboardPage.startTask(0);
    caseMapPage = new CaseMapPage();
    caseMapPage.switchToIFrameOfTask();
    caseMapPage.clickSubmitButtonAndBackToTaskList();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.clickOnTaskActionLink(0);
    ScreenshotUtil.executeDecorateJs("highlightTaskActionItem(0, 1)");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DEMO_FOLDER + "lending-casemap-external-solvency-service");
    taskWidgetPage.clickOnSideStepAction(0, 1);
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1150));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DEMO_FOLDER + "lending-casemap-approval-task");
  }
}
