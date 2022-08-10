package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.StatisticWidgetPage;
import portal.guitest.page.TaskWidgetPage;

public class StatisticScreenshotTest extends ScreenshotTest {
  private NewDashboardPage newDashboardPage;
  private MainMenuPage mainMenuPage;
  
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingCaseContainOneTask);
  }
  
  @Test
  public void screenshotForStatistic() throws IOException {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    WaitHelper.assertTrueWithRefreshPage(taskWidgetPage, () -> !taskWidgetPage.isWelcomeDialogExisted());
    taskWidgetPage.startTaskWithoutUI(0);
    redirectToRelativeLink(createTestingTasksUrl);
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    
    mainMenuPage = newDashboardPage.openMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(1460, 800));
    executeDecorateJs("highlightStatisticNavigation()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "navigate-to-full-statistics-page");
    ScreenshotUtil.maximizeBrowser();
    
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.switchCreateMode();
    statisticWidgetPage.waitForAllChartLoaded();
    statisticWidgetPage.closeMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(1386, 2210));
    statisticWidgetPage.waitForAllChartLoaded();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-creation-page");
    statisticWidgetPage.createTaskByPriorityChart();
    statisticWidgetPage.createTaskByExpiryChart();
    ScreenshotUtil.captureElementScreenshot(statisticWidgetPage.getChartCreationContainer(), ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "available-charts");
    ScreenshotUtil.maximizeBrowser();

    WebElement chartCreationDialog = statisticWidgetPage.getCaseByFinishedTaskCreationDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(chartCreationDialog, ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-creation-dialog", new ScreenshotMargin(100, 100));
    
    refreshPage();
    statisticWidgetPage.waitForChartCreationPageRendered();
    statisticWidgetPage.backToDashboard();
    statisticWidgetPage.waitForAllChartLoaded();
    executeDecorateJs("numberingChartPanel()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(statisticWidgetPage.getChartPanelByIndex(1), ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-detail-with-annotation", new ScreenshotMargin(20, 10));
    refreshPage();
    statisticWidgetPage.waitForAllChartLoaded();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(statisticWidgetPage.getChartInfoDialogOfChart(1), ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-info-dialog", new ScreenshotMargin(20, 10));
  }
  
}
