package portal.guitest.document.screenshot;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;
import portal.guitest.page.DefaultExpresTaskPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class AdhocScreenshotTest extends ScreenshotTest {
  
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
  }
  
  @Test
  public void takeScreenshotAdhoc() throws IOException {
    String taskNamePrefix = "Sick";
    String defaultTaskName1 = "Adhoc-Ask permission from CEO";
    String defaultTaskName2 = "Adhoc-Inform team member";
    String defaultTaskComment1 = "Get approve from CEO";
    String defaultTaskComment2 = "Inform team";
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(1);
    taskTemplatePage.clickActionMenuButton();
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 580));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-start");
    taskTemplatePage.clickAdhocCreationButton();
    taskTemplatePage.clickAdhocOkButton();
    
    //create tasks in adhoc page
    ExpressProcessPage expressPage = new ExpressProcessPage();
    ExpressResponsible responsible = new ExpressResponsible("demo", false);
    List<ExpressResponsible> responsibles = Arrays.asList(responsible);
    expressPage.createDefaultTask(0, defaultTaskName1, responsibles);
    expressPage.addNewTask(0);
    expressPage.createDefaultTask(1, defaultTaskName2, responsibles);
    Sleeper.sleep(2000);//wait for select user
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 900));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-define");
    expressPage.executeDirectly();
    
    //first task of adhoc
    DefaultExpresTaskPage defaultExpressTaskPage = new DefaultExpresTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment1);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 580));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-first-task");
    defaultExpressTaskPage.finishDefaultTask();
    
    //approval task of adhoc
    new HomePage().isDisplayed();
    taskWidgetPage.filterTasksBy(defaultTaskName2, 1);
    taskWidgetPage.startTask(0);
    defaultExpressTaskPage = new DefaultExpresTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment2);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1000));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-finish");
    defaultExpressTaskPage.finishDefaultTask();
    
    new HomePage().isDisplayed();
    taskWidgetPage.filterTasksBy(taskNamePrefix, 1);
    taskWidgetPage.startTask(0);
    Sleeper.sleep(2000);//wait for history review
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 600));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-process-history");
    
    taskTemplatePage.closeAdhocHistoryDialog();
    taskTemplatePage.clickActionMenuButton();
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 580));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "adhoc-show-history");
    
  }
}
