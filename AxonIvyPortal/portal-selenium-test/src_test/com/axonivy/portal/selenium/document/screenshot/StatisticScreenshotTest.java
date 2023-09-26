package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.StatisticWidgetPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class StatisticScreenshotTest extends ScreenshotBaseTest{

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.ENABLE_GROUP_CHAT.getKey(), "true");
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "false");
    updatePortalSetting(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(createTestingCaseContainOneTask);
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void screenshotForStatistic() throws IOException {
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    MainMenuPage mainMenu = new MainMenuPage();

    TaskWidgetPage taskWidgetPage = mainMenu.openTaskList();
    taskWidgetPage.openTask("SupportTicket");

    ScreenshotUtil.resizeBrowser(new Dimension(1460, 800));
    WaitHelper.waitForNavigation(() -> redirectToRelativeLink(createTestingTasksUrl));
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    homePage.waitForGrowlMessageDisappear();
    mainMenu.expandMainMenu();

    ScreenshotUtil.executeDecorateJs("highlightStatisticNavigation()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "navigate-to-full-statistics-page");
    ScreenshotUtil.resizeBrowser(new Dimension(1386, 2210));

    StatisticWidgetPage statisticWidgetPage = mainMenu.openStatisticPage();
    statisticWidgetPage.switchCreateMode();
    statisticWidgetPage.waitForAllChartLoaded();
    mainMenu.closeMainMenu();

    statisticWidgetPage.createTaskByPriorityChart();
    statisticWidgetPage.createTaskByExpiryChart();
    ScreenshotUtil.captureElementScreenshot(statisticWidgetPage.waitAndGetChartCreationContainer(), ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "available-charts");
    ScreenshotUtil.maximizeBrowser();

    WebElement chartCreationDialog = statisticWidgetPage.getCaseByFinishedTaskCreationDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(chartCreationDialog, ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-creation-dialog", new ScreenshotMargin(100, 100));
    
    refreshPage();
    statisticWidgetPage.waitForChartCreationPageRendered();
    statisticWidgetPage.backToDashboard();
    statisticWidgetPage.waitForAllChartLoaded();
    ScreenshotUtil.executeDecorateJs("numberingChartPanel()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(statisticWidgetPage.waitAndGetChartPanelByIndex(1), ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-detail-with-annotation", new ScreenshotMargin(20, 10));
    refreshPage();
    statisticWidgetPage.waitForAllChartLoaded();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(statisticWidgetPage.getChartInfoDialogOfChart(1), ScreenshotUtil.STATISTIC_WIDGET_FOLDER + "chart-info-dialog", new ScreenshotMargin(20, 10));
  }

}
