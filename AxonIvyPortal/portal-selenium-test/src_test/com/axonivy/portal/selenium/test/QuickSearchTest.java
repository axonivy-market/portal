package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

@IvyWebTest(headless = false)
public class QuickSearchTest extends BaseTest {

  // WIDGET
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  // CASES
  private static final String CREATE_12_CASES_WITH_CATEGORY_CASE = "Create 12 Cases with category";

  // TASKS
  private static final String SICK_LEAVE_REQUEST = "Sick Leave Request";
  private static final String DESTROYED = "Destroyed";
  private static final String TASK_NUMBER = "Task number";
  private static final String DONE = "Done";
  private static final String NEW_YOUR_TASK = "New Your Tasks";
  private static final String SUSPENDED = "Suspended";
  private static final String EXPIRE_TODAY = "Expire today";
  private static final String TASK_NAME = "Task name";
  private static final String MATERNITY_LEAVE_REQUEST = "Maternity Leave Request";
  private static final String ANNUAL_LEAVE_REQUEST = "Annual Leave Request";
  private static final String TASK_PRIORITY = "Prio";
  private static final String EXPIRY = "Expiry";
  private static final String IN_PROGRESS = "In progress";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testQuickSearchOnConfiguration() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();

    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.save();
    taskWidget.isQuickSearchInputShow("0");

    taskWidget.setInputForQuickSearch("Task number 10");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.clearQuickSearchInput();

    taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.save();
    assertFalse(taskWidget.isQuickSearchInputShow("0"));
  }

  @Test
  public void testQuicSearchIsSelectedOnIdAndName() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openColumnManagementDialog();

    assertTrue(taskEditWidget.isQuickSearchClicked("id"));
    assertTrue(taskEditWidget.isQuickSearchClicked("name"));

  }

  @Test
  public void testQuickSearchOnColumnManagement() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();

    taskEditWidget.openColumnManagementDialog();
    taskEditWidget.addFirstStandardField();
    taskEditWidget.clickOnQuickSearchByField("description");
    taskEditWidget.clickOnQuickSearchByField("activator");
    taskEditWidget.clickOnQuickSearchByField("category");
    taskEditWidget.clickOnQuickSearchByField("application");

    taskEditWidget.isQuickSearchClicked("description");
    taskEditWidget.isQuickSearchClicked("activator");
    taskEditWidget.isQuickSearchClicked("category");
    taskEditWidget.isQuickSearchClicked("application");
  }

  @Test
  public void testQuickSearchForStandardFieldsOnDashboardConfigurationPage() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.openColumnManagementDialog();
    taskEditWidget.addFirstStandardField();
    taskEditWidget.clickOnQuickSearchByField("description");
    taskEditWidget.clickOnQuickSearchByField("activator");
    taskEditWidget.clickOnQuickSearchByField("category");
    taskEditWidget.clickOnQuickSearchByField("application");

    taskEditWidget.saveColumnMangement();
    taskEditWidget.save();

    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("engine");
    assertTrue(taskWidget.isEmptyMessageAppear());

    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("TestCase1");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1));

    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("everybody");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(12));
  }

}
