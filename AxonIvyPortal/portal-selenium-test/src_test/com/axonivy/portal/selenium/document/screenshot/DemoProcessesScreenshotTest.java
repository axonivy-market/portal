package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseMapPage;
import com.axonivy.portal.selenium.page.ExampleOverviewPage;
import com.axonivy.portal.selenium.page.LeaveRequestPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;

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
    redirectToRelativeLink(createTestingTasksUrl);
    showNewDashboard();
    NewDashboardPage dashboardPage = new NewDashboardPage();
    dashboardPage.waitForCaseWidgetLoaded();
    mainMenuPage = new MainMenuPage();
    mainMenuPage.expandMainMenu();
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 500));
    ScreenshotUtils.executeDecorateJs("highlightUserExampleNavigation()");
    ScreenshotUtils.captureHalfLeftPageScreenShot(ScreenshotUtils.DEMO_FOLDER + "user-example-guide-link");
  }

  @Test
  public void screenshotUserExampleOverview() throws IOException {
    mainMenuPage.closeMainMenu();
    ScreenshotUtils.resizeBrowser(new Dimension(1600, 1300));
    redirectToRelativeLinkWithEmbedInFrame(startUserExampleProcess);
    ExampleOverviewPage exampleOverviewPage = new ExampleOverviewPage();
    exampleOverviewPage.switchToIFrameOfTask();
    exampleOverviewPage.waitForIFrameContentVisible();
    ScreenshotUtils.executeDecorateJs("highlightUserExampleCard(0)");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.DEMO_FOLDER + "example-overview-leave-request");
    refreshPage();
    exampleOverviewPage = new ExampleOverviewPage();
    exampleOverviewPage.switchToIFrameOfTask();
    exampleOverviewPage.waitForIFrameContentVisible();
    ScreenshotUtils.executeDecorateJs("highlightUserExampleCard(1)");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.DEMO_FOLDER + "example-overview-lending-case");
  }

  @Test
  public void screenshotLeaveRequestProcess() throws IOException {
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 950));
    redirectToRelativeLinkWithEmbedInFrame(LEAVE_REQUEST_START_LINK);
    LeaveRequestPage leaveRequestPage = new LeaveRequestPage();
    leaveRequestPage.switchToIFrameOfTask();
    leaveRequestPage.waitForIFrameContentVisible();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.DEMO_FOLDER + "leave-request-creation");
  }

  @Test
  public void screenshotCaseMapProcess() throws IOException {
    login(TestAccount.DEMO_USER);
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 900));
    redirectToRelativeLinkWithEmbedInFrame(CASE_MAP_URL);
    CaseMapPage caseMapPage = new CaseMapPage();
    caseMapPage.switchToIFrameOfTask();
    caseMapPage.waitForIFrameContentVisible();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.DEMO_FOLDER + "lending-casemap-collect-personal-data");

    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1000));
    caseMapPage.clickSubmitRequestButton();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1500));
    newDashboardPage.startTask(0);
    caseMapPage = new CaseMapPage();
    caseMapPage.switchToIFrameOfTask();
    caseMapPage.clickSubmitButtonAndBackToTaskList();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.clickOnTaskActionLink(0);
    ScreenshotUtils.executeDecorateJs("highlightTaskAdditionalActionItem(0)");
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.DEMO_FOLDER + "lending-casemap-external-solvency-service");
    taskWidget.clickOnSideStepAction(0, 0);
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1150));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.DEMO_FOLDER + "lending-casemap-approval-task");
  }
}
