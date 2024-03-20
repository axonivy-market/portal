package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.bean.ExpressResponsible;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DefaultExpressTaskPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;


@IvyWebTest
public class AdhocScreenshotTest extends ScreenshotBaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
  }

  @Test
  public void takeScreenshotAdhoc() throws IOException {
    String mainTask = "Sick Leave Request";
    String defaultTaskName1 = "Adhoc-Ask permission from CEO";
    String defaultTaskName2 = "Adhoc-Inform team member";
    String defaultTaskComment1 = "Get approve from CEO";
    String defaultTaskComment2 = "Inform team";

    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    showNewDashboard();
    MainMenuPage menuPage = new MainMenuPage();
    TaskWidgetPage taskWidget = menuPage.openTaskList();
    taskWidget.openTask(mainTask);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.switchBackToParent();
    taskTemplatePage.clickActionButton();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "adhoc-start");

    taskTemplatePage.clickOnStartAdhocLink();
    taskTemplatePage.clickAdhocOkButton();

    // create tasks in adhoc page
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1200));
    ExpressProcessPage expressPage = new ExpressProcessPage();

    ExpressResponsible responsible = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    List<ExpressResponsible> responsibles = Arrays.asList(responsible);
    expressPage.createDefaultTask(0, defaultTaskName1, responsibles);
    expressPage.addNewTask(0);
    expressPage.createDefaultTask(1, defaultTaskName2, responsibles);
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "adhoc-define");
    expressPage.clickSave();

    // first task of adhoc
    DefaultExpressTaskPage defaultExpressTaskPage = new DefaultExpressTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment1);
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "adhoc-first-task");
    defaultExpressTaskPage.clickOK();

    // approval task of adhoc
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    menuPage = new MainMenuPage();
    taskWidget = menuPage.openTaskList();
    taskWidget.openTask(defaultTaskName2);

    defaultExpressTaskPage = new DefaultExpressTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment2);
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "adhoc-finish");
    defaultExpressTaskPage.clickOK();

    showNewDashboard();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    menuPage = new MainMenuPage();
    taskWidget = menuPage.openTaskList();
    taskWidget.openTask(mainTask);

    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    taskTemplatePage.closeAdhocHistoryDialog();
    taskTemplatePage.clickActionButton();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "adhoc-show-history");
    ScreenshotUtils.maximizeBrowser();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskTemplatePage.openAdhocHistoryDialog(),
        ScreenshotUtils.EXPRESS_FOLDER + "adhoc-process-history", new ScreenshotMargin(20));
  }
}
