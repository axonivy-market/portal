package portal.guitest.document.screenshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;
import portal.guitest.page.TaskAnalysisWidgetPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskAnalysisScreenshotTest extends ScreenshotTest {

  private HomePage homePage;
  private MainMenuPage mainMenuPage;

  @Override
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    homePage = new HomePage();
  }

  @Test
  public void screenshotTaskAnalysisPage() throws IOException {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    WaitHelper.assertTrueWithRefreshPage(taskWidgetPage, () -> !taskWidgetPage.isWelcomeDialogExisted());
    homePage = new HomePage();
    homePage.waitForStatisticRendered();
    mainMenuPage = homePage.openMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 800));
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.closeMainMenu();
    executeDecorateJs("highlightTaskAnalysisNavigationLink()");
    ScreenshotUtil.captureHalfTopRightPageScreenShot(ScreenshotUtil.TASK_ANALYSIS_FOLDER + "navigate-to-task-analysis");
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();

    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_ANALYSIS_FOLDER + "task-analysis");

    taskAnalysisWidgetPage.openAdvancedTaskFilter("Priority", "priority");
    List<String> selectedPriorities = new ArrayList<>();
    selectedPriorities.add("NORMAL");
    taskAnalysisWidgetPage.filterByTaskPriority(selectedPriorities);
    taskAnalysisWidgetPage.clickApplyFilter();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_ANALYSIS_FOLDER + "filtered-tasks");
    taskAnalysisWidgetPage.enterDataToSaveFilterSet("Normal Priority Task", true);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskAnalysisWidgetPage.getSavingFilterDialog(),
        ScreenshotUtil.TASK_ANALYSIS_FOLDER + "saved-filter", new ScreenshotMargin(20));
  }

}
