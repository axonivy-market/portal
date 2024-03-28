package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.StatisticWidgetPage;

@IvyWebTest
public class StatisticScreenshotTest extends ScreenshotBaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.ENABLE_GROUP_CHAT.getKey(), "true");
    redirectToRelativeLink(createTestingCaseContainOneTask);
    login(TestAccount.DEMO_USER);
  }

  @Test
  public void screenshotForStatistic() throws IOException {
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);
    WaitHelper.waitForNavigation(() -> redirectToRelativeLink(createTestingTasksUrl));
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    MainMenuPage mainMenu = new MainMenuPage();
    WaitHelper.waitForActionComplete("#theme-switcher", () -> mainMenu.clickOnSupportTicketOnTaskWidget());

    ScreenshotUtils.resizeBrowser(new Dimension(1460, 800));
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    mainMenu.expandMainMenu();

    ScreenshotUtils.executeDecorateJs("highlightStatisticNavigation()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "navigate-to-full-statistics-page");
    ScreenshotUtils.resizeBrowser(new Dimension(1386, 2210));

    StatisticWidgetPage statisticWidgetPage = mainMenu.openStatisticPage();
    statisticWidgetPage.switchCreateMode();
    statisticWidgetPage.waitForAllChartLoaded();
    mainMenu.closeMainMenu();
    ScreenshotUtils.resizeBrowser(new Dimension(1386, 2210));
    statisticWidgetPage.waitForAllChartLoaded();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "chart-creation-page");

    statisticWidgetPage.createTaskByPriorityChart();
    statisticWidgetPage.createTaskByExpiryChart();
    ScreenshotUtils.captureElementScreenshot(statisticWidgetPage.waitAndGetChartCreationContainer(),
        ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "available-charts");

    WebElement chartCreationDialog = statisticWidgetPage.getCaseByFinishedTaskCreationDialog();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(chartCreationDialog,
        ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "chart-creation-dialog", new ScreenshotMargin(50, 50));

    refreshPage();
    statisticWidgetPage.waitForChartCreationPageRendered();
    statisticWidgetPage.backToDashboard();
    statisticWidgetPage.clickChartInfoAndCloseToWaitAnimation();
    ScreenshotUtils.executeDecorateJs("numberingChartPanel()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(statisticWidgetPage.waitAndGetChartPanelByIndex(1),
    ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "chart-detail-with-annotation", new ScreenshotMargin(20, 10));
    refreshPage();
    mainMenu.waitForPageLoad();
    statisticWidgetPage.waitForPageLoad();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(statisticWidgetPage.getChartInfoDialogOfChart(1),
        ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "chart-info-dialog", new ScreenshotMargin(20, 10));
  }

}
