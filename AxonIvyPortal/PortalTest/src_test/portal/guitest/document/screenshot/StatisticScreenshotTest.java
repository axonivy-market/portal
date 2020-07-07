package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;
import portal.guitest.page.TaskWidgetPage;

public class StatisticScreenshotTest extends BaseTest {

  private HomePage homePage;
  private MainMenuPage mainMenuPage;
  
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingCaseContainOneTask);
    homePage = new HomePage();
  }
  
  @Test
  public void screenshotForStatistic() throws IOException {
    homePage.waitForStatisticRendered();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTaskWithoutUI(0);
    homePage.waitForStatisticRendered();
    homePage.waitForGrowlDisappear();
    
    mainMenuPage = homePage.openMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    executeDecorateJs("highlightStatisticNavigation()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "navigate-to-full-statistics-page");
    ScreenshotUtil.maximizeBrowser();
    
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.switchCreateMode();
    statisticWidgetPage.waitForAllChartLoaded();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-creation-page");
    statisticWidgetPage.createTaskByPriorityChart();
    statisticWidgetPage.createTaskByExpiryChart();
    ScreenshotUtil.captureElementScreenshot(statisticWidgetPage.getChartCreationContainer(), ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "available-charts");
    
    WebElement chartCreationDialog = statisticWidgetPage.getCaseByFinishedTaskCreationDialog();
    Sleeper.sleep(1000);//Wait for focus animation finish before capture screenshot
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(chartCreationDialog, ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-creation-dialog", new ScreenshotMargin(100, 100));
    
    refreshPage();
    statisticWidgetPage.waitForChartCreationPageRendered();
    statisticWidgetPage.backToDashboard();
    statisticWidgetPage.waitForAllChartLoaded();
    executeDecorateJs("numberingChartPanel()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(statisticWidgetPage.getChartPanelByIndex(1), ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-detail-with-annotation", new ScreenshotMargin(20, 10));
    ScreenshotUtil.captureElementScreenshot(statisticWidgetPage.getChartInfoDialogOfChart(1), ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-info-dialog");
  }
  
}
