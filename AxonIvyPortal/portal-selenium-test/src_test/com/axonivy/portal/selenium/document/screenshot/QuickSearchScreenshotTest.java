package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

@IvyWebTest
public class QuickSearchScreenshotTest extends ScreenshotBaseTest {

  // WIDGET
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void screenshotForQuickSearchConfigurationOnTaskWidget() throws IOException {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.resizeBrowser(new Dimension(1000, 1000));
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();

    ScreenshotUtils.captureElementScreenshot(taskEditWidget.getConfigurationFilter(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "quick-search-checkbox");
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.openColumnManagementDialog();
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1000));
    ScreenshotUtils.captureElementScreenshot(taskEditWidget.getColumnManagementDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "quick-search-column-management");

    taskEditWidget.saveColumnMangement();
    taskEditWidget.save();
    taskEditWidget.waitForPageLoad();
    taskWidget.isQuickSearchInputShow("0");

    taskWidget.setInputForQuickSearch(" ");
    taskWidget.clearQuickSearchInput();
    redirectToNewDashBoard();
    ScreenshotUtils.resizeBrowser(new Dimension(1000, 1000));
    taskWidget.setInputForQuickSearch(" ");
    taskWidget.clearQuickSearchInput();
    taskWidget.clickOnButtonExpandTaskWidget();
    ScreenshotUtils.resizeBrowserAndCaptureHalfRightScreen(
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "quick-search-textbox", new Dimension(800, 150));
  }
}
