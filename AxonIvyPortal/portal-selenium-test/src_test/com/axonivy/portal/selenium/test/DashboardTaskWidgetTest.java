package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

@IvyWebTest(headless = false)
public class DashboardTaskWidgetTest extends BaseTest {

  //WIDGET
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
  private static final String CATEGORIED_LEAVE_REQUEST = "Categoried Leave Request";
  private static final String TASK_ID = "Task Id";
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
  public void testHideTasks() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(SICK_LEAVE_REQUEST);
    taskWidget.applyFilter();
    taskWidget.startFirstTaskAndWaitShowHomePageButton();
    taskWidget.clickCancelTask();
    taskWidget.stateOfFirstTask().shouldHave(text(IN_PROGRESS));
  }
  
  @Test
  public void testDestroyTaskWithPermission() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(SICK_LEAVE_REQUEST);
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
    taskWidget.filterTaskName(SICK_LEAVE_REQUEST);
    taskWidget.applyFilter();
    taskWidget.clickOnTaskActionLink(0);
    taskWidget.destroyTaskLink().shouldNotHave(visible);
  }
  
  @Test
  public void testStickyFilterTaskList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    //Filter Task Name
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(TASK_NUMBER);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(5));
    //Filter State
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(CREATE_12_CASES_WITH_CATEGORY_CASE);
    taskWidget.filterTaskState();
    taskWidget.selectState(DONE);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHaveSize(1);
    taskWidget.stateOfFirstTask().shouldHave(text(DONE));
  }
  
  @Test
  public void testEditFilterTaskList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.changeWidgetTitle(NEW_YOUR_TASK);
    taskEditWidget.filterTaskName(TASK_NUMBER);
    taskEditWidget.clickOnStateToShowDropdown();
    taskEditWidget.selectState(SUSPENDED);
    taskEditWidget.preview();
    taskEditWidget.waitPageSelected(1);
    taskEditWidget.countAllTasks().shouldHaveSize(5);
    taskEditWidget.nextPageTable();
    taskEditWidget.waitPageSelected(2);
    taskEditWidget.countAllTasks().shouldHaveSize(5);
    taskEditWidget.nextPageTable();
    taskEditWidget.waitPageSelected(3);
    taskEditWidget.countAllTasks().shouldHaveSize(2);
    taskEditWidget.save();
    TaskWidgetNewDashBoardPage taskWidgetEdited = newDashboardPage.selectTaskWidget(NEW_YOUR_TASK);
    taskWidgetEdited.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidgetEdited.countAllTasks().shouldHaveSize(5);
  }
  
  @Test
  public void testAddNewTaskList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage newtaskWidget = newDashboardDetailsEditPage.addNewTaskWidget();
    newtaskWidget.changeWidgetTitle(NEW_YOUR_TASK);
    newtaskWidget.save();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(NEW_YOUR_TASK);
    taskWidget.expand().shouldHaveSize(1);
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
    taskWidget.getFirstStateLabelInWidgetInfo().shouldHave(text(SUSPENDED));
    taskWidget.clickToExpandNumberOfTaskByCategory();    
    taskWidget.clickToExpandPredefinedFilters();
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
    taskWidget.getExpandedTaskWidget().shouldHaveSize(1);
    taskWidget.clickOnButtonCollapseTaskWidget();
    taskWidget.getExpandedWidget().shouldHaveSize(0);
  }
  
  @Test
  public void testStickySortTaskList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    
    // Verify default task should be sorted by  Created descending
    taskWidget.getTaskWidgetHeaderSorted().shouldHave(text("Created"));
    String sortType = taskWidget.getTaskWidgetHeaderSorted().getAttribute("aria-sort");
    assertEquals(sortType, "descending");
    // Sort by task name
    taskWidget.clickOnHeaderTaskByColumn(TASK_NAME);
    taskWidget.getTaskWidgetHeaderSorted().shouldHave(text(TASK_NAME));
    sortType = taskWidget.getTaskWidgetHeaderSorted().getAttribute("aria-sort");
    assertEquals(sortType, "ascending");
    taskWidget.getTheFirstTaskWidgetByColumn(TASK_NAME).shouldHave(text(ANNUAL_LEAVE_REQUEST));
    // Sort by task id
    taskWidget.clickOnHeaderTaskByColumn(TASK_ID);
    taskWidget.waitForSortingFinished("ascending");
    taskWidget.clickOnHeaderTaskByColumn(TASK_ID);
    taskWidget.waitForSortingFinished("descending");
    taskWidget.getTaskWidgetHeaderSorted().shouldHave(text(TASK_ID));
    sortType = taskWidget.getTaskWidgetHeaderSorted().getAttribute("aria-sort");
    assertEquals(sortType, "descending");
    taskWidget.getTheFirstTaskWidgetByColumn(TASK_NAME).shouldHave(text(MATERNITY_LEAVE_REQUEST));
    // Sort by task expiry
    taskWidget.clickOnHeaderTaskByColumn(EXPIRY);
    taskWidget.getTaskWidgetHeaderSorted().shouldHave(text(EXPIRY));
    sortType = taskWidget.getTaskWidgetHeaderSorted().getAttribute("aria-sort");
    assertEquals(sortType, "ascending");
    taskWidget.getTheFirstTaskWidgetByColumn(TASK_NAME).shouldHave(text(CATEGORIED_LEAVE_REQUEST));
  }
  
}
