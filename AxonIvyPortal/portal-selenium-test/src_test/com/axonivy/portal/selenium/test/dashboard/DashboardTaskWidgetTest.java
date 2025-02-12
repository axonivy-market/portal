package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskTemplateIFramePage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class DashboardTaskWidgetTest extends BaseTest {

  // WIDGET
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  // CASES
  private static final String CREATE_12_CASES_WITH_CATEGORY_CASE = "Create 12 Cases with category";

  // TASKS
  private static final String SICK_LEAVE_REQUEST = "Sick Leave Request";
  private static final String DESTROYED = "Destroyed";
  private static final String TASK_NUMBER = "Task number";
  private static final String NEW_YOUR_TASK = "New Your Tasks";
  private static final String EXPIRE_TODAY = "Expire today";
  private static final String TASK_NAME = "Task name";
  private static final String MATERNITY_LEAVE_REQUEST = "Maternity Leave Request";
  private static final String TASK_PRIORITY = "Prio";
  private static final String EXPIRY = "Expiry";
  private static final String IN_PROGRESS = "In progress";
  private static final String OPEN = "Open";
  private static final String AXON_IVY = "AxonIvy";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testHideTasks() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openFilter();
    taskEditWidget.resetFilter();
    taskEditWidget.applyFilter();
    taskEditWidget.openColumnManagementDialog();
    taskEditWidget.clickOnVisibilityCheckBoxByField("State");
    taskEditWidget.saveColumn();
    taskEditWidget.save();
    redirectToNewDashBoard();

    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(SICK_LEAVE_REQUEST, FilterOperator.IS);
    taskWidget.applyFilter();
    taskWidget.clickOnTaskName(SICK_LEAVE_REQUEST);

    TaskTemplateIFramePage templatePage = new TaskTemplateIFramePage();
    templatePage.switchToIFrameOfTask();
    newDashboardPage = templatePage.clickCancelButton();

    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.stateOfFirstTask().shouldHave(text(IN_PROGRESS));
  }

  @Test
  public void testDestroyTaskWithPermission() {
    createJSonFile("dashboard-has-one-task-widget.json", PortalVariable.DASHBOARD.key);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(SICK_LEAVE_REQUEST, FilterOperator.IS);
    taskWidget.applyFilter();
    taskWidget.clickOnTaskActionLink(0);
    taskWidget.destroy();
    taskWidget.stateOfFirstTask().shouldHave(text(DESTROYED));
  }

  @Test
  public void testDestroyTaskWithoutPermission() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(SICK_LEAVE_REQUEST, FilterOperator.IS);
    taskWidget.applyFilter();
    taskWidget.clickOnTaskActionLink(0);
    taskWidget.destroyTaskLink().shouldNotHave(visible);
  }

  @Test
  public void testStickyFilterTaskList() {
    createJSonFile("dashboard-has-one-task-widget.json", PortalVariable.DASHBOARD.key);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    // Filter Task Name
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(TASK_NUMBER, FilterOperator.CONTAINS);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(size(12));
    // Filter State
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(CREATE_12_CASES_WITH_CATEGORY_CASE, FilterOperator.IS);
    taskWidget.addFilter("State", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "OPEN"); 
    taskWidget.applyFilter();
    assertTrue(taskWidget.isEmptyMessageAppear());
  }

  @Test
  public void testEditFilterTaskList() {
    createJSonFile("dashboard-has-one-task-widget.json", PortalVariable.DASHBOARD.key);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.changeWidgetTitle(NEW_YOUR_TASK);
    taskEditWidget.openFilter();
    taskEditWidget.addFilter("State", null);
    taskEditWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "OPEN");
    taskEditWidget.applyFilter();
    taskEditWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(12));
    taskEditWidget.save();
    TaskWidgetNewDashBoardPage taskWidgetEdited = newDashboardPage.selectTaskWidget(NEW_YOUR_TASK);
    taskWidgetEdited.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidgetEdited.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1));
  }

  @Test
  public void testAddNewTaskList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    var configurationPage = LinkNavigator.navigateToPortalDashboardConfiguration();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage newtaskWidget = newDashboardDetailsEditPage.addNewTaskWidget();
    newtaskWidget.changeWidgetTitle(NEW_YOUR_TASK);
    newtaskWidget.save();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(NEW_YOUR_TASK);
    taskWidget.expand().shouldHave(size(1));
  }

  @Test
  public void testTaskWidgetInformation() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();

    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.clickOnButtonWidgetInformation();
    taskWidget.getExpiryTodayLabelInWidgetInfo().shouldHave(text(EXPIRE_TODAY));
    taskWidget.clickToExpandNumberOfTaskByState();
    taskWidget.getFirstStateLabelInWidgetInfo().shouldHave(text(OPEN));
    taskWidget.clickToExpandNumberOfTaskByCategory();
    taskWidget.closeWidgetInformationDialog();
  }

  @Test
  public void testExpandAndCollapseTaskWidget() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();

    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    taskWidget.clickOnButtonExpandTaskWidget();
    taskWidget.getExpandedTaskWidget().shouldHave(size(1));
    taskWidget.clickOnButtonCollapseTaskWidget();
    taskWidget.getExpandedWidget().shouldHave(size(0));
  }

  @Test
  public void testStickySortTaskList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    ScreenshotUtils.maximizeBrowser();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    String sortType = taskWidget.getTaskWidgetHeaderSorted().getAttribute("aria-sort");
    assertEquals(sortType, "descending");
    // Sort by task name
    taskWidget.clickOnHeaderTaskByColumn(TASK_NAME);
    taskWidget.getTaskWidgetHeaderSorted().shouldHave(text(TASK_NAME));
    sortType = taskWidget.getTaskWidgetHeaderSorted().getAttribute("aria-sort");
    assertEquals(sortType, "ascending");
    taskWidget.getTheFirstTaskWidgetByColumn(TASK_NAME).shouldHave(text(MATERNITY_LEAVE_REQUEST));
    // Sort by task id
    taskWidget.clickOnHeaderTaskByColumn(TASK_PRIORITY);
    taskWidget.waitForSortingFinished("ascending");
    taskWidget.clickOnHeaderTaskByColumn(TASK_PRIORITY);
    taskWidget.waitForSortingFinished("descending");
    taskWidget.getTaskWidgetHeaderSorted().shouldHave(text(TASK_PRIORITY));
    sortType = taskWidget.getTaskWidgetHeaderSorted().getAttribute("aria-sort");
    assertEquals(sortType, "descending");
    taskWidget.getTheFirstTaskWidgetByColumn(TASK_NAME).shouldHave(text(MATERNITY_LEAVE_REQUEST));
    // Sort by task expiry
    taskWidget.clickOnHeaderTaskByColumn(EXPIRY);
    taskWidget.getTaskWidgetHeaderSorted().shouldHave(text(EXPIRY));
    sortType = taskWidget.getTaskWidgetHeaderSorted().getAttribute("aria-sort");
    assertEquals(sortType, "ascending");
    taskWidget.getTheFirstTaskWidgetByColumn(TASK_NAME).shouldHave(text(SICK_LEAVE_REQUEST));
  }

  @Test
  public void testExportExcel() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    var configurationPage = LinkNavigator.navigateToPortalDashboardConfiguration();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage newTaskWidget = newDashboardDetailsEditPage.addNewTaskWidget();

    String taskWidgetName = "Export Excel Task";
    newTaskWidget.changeWidgetTitle(taskWidgetName);
    newTaskWidget.save();

    redirectToNewDashBoard();

    NewDashboardPage dashboardPage = new NewDashboardPage();
    TaskWidgetNewDashBoardPage taskWidget = dashboardPage.selectTaskWidget(TASK_NAME);
    taskWidget.clickExportExcel();
    dashboardPage.isDownloadCompleted();
  }
  
  @Test
  public void testHideWidgetInfoIcon() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnWidgetInfoIconCheckbox();
    taskEditWidget.save();
    newDashboardDetailsEditPage.backToConfigurationPage();
    redirectToNewDashBoard();
    assertFalse(taskWidget.isWidgetInfomationIconAppear());
  }

  @Test
  public void testHideExpandMode() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnExpandModeCheckbox();
    taskEditWidget.save();
    newDashboardDetailsEditPage.backToConfigurationPage();
    redirectToNewDashBoard();
    assertFalse(taskWidget.isExpandButtonAppear());
  }
  
  @Test
  public void testShowBusinessCustomFieldOnTaskWidget() {
    redirectToRelativeLink(displayCustomFieldCaseOnTaskWidget);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    taskWidget.openEditTaskWidget();
    taskWidget.clickOnManageColumns();
    taskWidget.selectCustomBusinessCaseFieldType();
    taskWidget.selectCustomerNameField();
    taskWidget.clickAddButton();
    taskWidget.clickSaveButton();
    taskWidget.saveWidgetConfiguration();
    MainMenuPage mainMenu = new MainMenuPage();
    mainMenu.clickOnLogo();
    TaskWidgetNewDashBoardPage taskWidget2 = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    assertEquals(AXON_IVY, taskWidget2.getCustomBusinessCaseFieldValueFromRowIndex(0));
    assertEquals(AXON_IVY, taskWidget2.getCustomBusinessCaseFieldValueFromRowIndex(1));
  }
}
