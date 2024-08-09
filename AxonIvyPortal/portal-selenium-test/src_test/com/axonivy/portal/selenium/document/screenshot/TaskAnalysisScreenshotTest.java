package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.StatisticWidgetPage;
import com.axonivy.portal.selenium.page.TaskAnalysisWidgetPage;

@IvyWebTest
public class TaskAnalysisScreenshotTest extends ScreenshotBaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.ENABLE_GROUP_CHAT.getKey(), "true");
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void screenshotTaskAnalysisPage() throws IOException {
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    MainMenuPage mainMenu = new MainMenuPage();
    StatisticWidgetPage statisticWidgetPage = mainMenu.openStatisticPage();

    ScreenshotUtils.executeDecorateJs("highlightTaskAnalysisNavigationLink()");
    ScreenshotUtils.resizeBrowser(new Dimension(1200, 800));
    ScreenshotUtils
        .captureHalfTopRightPageScreenShot(ScreenshotUtils.TASK_ANALYSIS_FOLDER + "navigate-to-task-analysis");
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();

    ScreenshotUtils.resizeBrowser(new Dimension(1400, 900));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_ANALYSIS_FOLDER + "task-analysis");

    taskAnalysisWidgetPage.openAdvancedTaskFilter("Priority", "priority");
    List<String> selectedPriorities = new ArrayList<>();
    selectedPriorities.add("NORMAL");
    taskAnalysisWidgetPage.filterByTaskPriority(selectedPriorities);
    taskAnalysisWidgetPage.clickApplyFilter();
    ScreenshotUtils.resizeBrowser(new Dimension(1400, 800));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_ANALYSIS_FOLDER + "filtered-tasks");
    taskAnalysisWidgetPage.enterDataToSaveFilterSet("Normal Priority Task", true);
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskAnalysisWidgetPage.waitAndGetSavingFilterDialog(),
        ScreenshotUtils.TASK_ANALYSIS_FOLDER + "saved-filter", new ScreenshotMargin(20));
  }
}
