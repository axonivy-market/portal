package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DefaultExpressTaskPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

import ch.ivyteam.ivy.project.portal.test.ExpressResponsible;

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
    
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 580));
    showNewDashboard();
    MainMenuPage menuPage = new MainMenuPage();
    TaskWidgetPage taskWidget = menuPage.openTaskList();
    taskWidget.openTask(mainTask);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.switchBackToParent();
    taskTemplatePage.clickActionButton();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-start");

    taskTemplatePage.clickOnStartAdhocLink();
    taskTemplatePage.clickAdhocOkButton();

    //create tasks in adhoc page
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1200));
    ExpressProcessPage expressPage = new ExpressProcessPage();

    ExpressResponsible responsible = new ExpressResponsible();
    responsible.setResponsibleName("demo");
    responsible.setIsGroup(false);

    List<ExpressResponsible> responsibles = Arrays.asList(responsible);
    expressPage.createDefaultTask(0, defaultTaskName1, responsibles);
    expressPage.addNewTask(0);
    expressPage.createDefaultTask(1, defaultTaskName2, responsibles);
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-define");
    expressPage.clickSave();

    //first task of adhoc
    DefaultExpressTaskPage defaultExpressTaskPage = new DefaultExpressTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment1);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 600));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-first-task");
    defaultExpressTaskPage.clickOK();
    
    //approval task of adhoc
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    menuPage = new MainMenuPage();
    taskWidget = menuPage.openTaskList();
    taskWidget.openTask(defaultTaskName2);

    defaultExpressTaskPage = new DefaultExpressTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment2);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1000));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-finish");
    defaultExpressTaskPage.clickOK();

    showNewDashboard();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    menuPage = new MainMenuPage();
    taskWidget = menuPage.openTaskList();
    taskWidget.openTask(mainTask);

    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    taskTemplatePage.closeAdhocHistoryDialog();
    taskTemplatePage.clickActionButton();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-show-history");
    ScreenshotUtil.maximizeBrowser();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskTemplatePage.openAdhocHistoryDialog(), ScreenshotUtil.EXPRESS_FOLDER + "adhoc-process-history", new ScreenshotMargin(20));
  }
}
