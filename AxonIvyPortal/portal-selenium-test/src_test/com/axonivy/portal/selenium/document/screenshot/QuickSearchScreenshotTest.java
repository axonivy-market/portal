package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

@IvyWebTest
public class QuickSearchScreenshotTest extends ScreenshotBaseTest {

  // WIDGET
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  private static final String YOUR_CASES_WIDGET = "Your Cases";

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
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "task-quick-search-checkbox");
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.openColumnManagementDialog();
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1000));
    ScreenshotUtils.captureElementScreenshot(taskEditWidget.getColumnManagementDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "task-quick-search-column-management");

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
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "task-quick-search-textbox", new Dimension(800, 150));
  }

  @Test
  public void screenshotForQuickSearchConfigurationOnCaseWidget() throws IOException {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.resizeBrowser(new Dimension(1000, 1000));
    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();

    ScreenshotUtils.captureElementScreenshot(caseEditWidget.getConfigurationFilter(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "case-quick-search-checkbox");
    caseEditWidget.clickOnQuickSearchCheckBox();
    caseEditWidget.openColumnManagementDialog();
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1000));
    ScreenshotUtils.captureElementScreenshot(caseEditWidget.getColumnManagementDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "case-quick-search-column-management");

    caseEditWidget.saveColumn();
    caseEditWidget.save();
    caseEditWidget.waitForPageLoad();
    caseWidget.isQuickSearchInputShow("0");

    caseWidget.setInputForQuickSearch(" ");
    caseWidget.clearQuickSearchInput();
    redirectToNewDashBoard();
    ScreenshotUtils.resizeBrowser(new Dimension(1000, 1000));
    caseWidget.setInputForQuickSearch(" ");
    caseWidget.clearQuickSearchInput();
    caseWidget.clickOnButtonExpandCaseWidget();
    ScreenshotUtils.resizeBrowserAndCaptureHalfRightScreen(
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "case-quick-search-textbox", new Dimension(800, 150));
  }
}
