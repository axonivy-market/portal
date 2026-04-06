package com.axonivy.portal.selenium.test.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

@IvyWebTest(headless = false)
public class CustomTaskDelegateTest extends BaseTest {

  private static final String SICK_LEAVE_REQUEST = "Sick Leave Request";
  private static final String MATERNITY_LEAVE_REQUEST = "Maternity Leave Request";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestingTasks();
  }

  @Test
  public void testCustomTaskDelegateOnlyToGroup() {
    login(TestAccount.HR_ROLE_USER);
    openDashboard();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, SICK_LEAVE_REQUEST);
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.applyFilter();
    taskWidget.clickOnTaskActionLink(0);
    taskWidget.clickTaskAction(0, "Delegate");
    taskWidget.waitForTaskDelegateDialogContent();

    // User type is disabled
    assertTrue(taskWidget.isDelegateTypeDisabled(0));
    // Role type is enabled
    assertFalse(taskWidget.isDelegateTypeDisabled(1));
    assertTrue(taskWidget.isDelegateListSelectionAvailable());
  }

  @Test
  public void testCustomTaskDelegateOnlyToUser() {
    login(TestAccount.HR_ROLE_USER);
    openDashboard();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, MATERNITY_LEAVE_REQUEST);
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.applyFilter();
    taskWidget.clickOnTaskActionLink(0);
    taskWidget.clickTaskAction(0, "Delegate");
    taskWidget.waitForTaskDelegateDialogContent();

    // Role type is disabled
    assertTrue(taskWidget.isDelegateTypeDisabled(1));
    // User type is enabled
    assertFalse(taskWidget.isDelegateTypeDisabled(0));
    assertTrue(taskWidget.isDelegateListSelectionAvailable());
  }

  @Test
  public void testCustomTaskDelegateNoDelegateOption() {
    login(TestAccount.GUEST_USER);
    openDashboard();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, MATERNITY_LEAVE_REQUEST);
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.applyFilter();
    taskWidget.clickOnTaskActionLink(0);
    taskWidget.clickTaskAction(0, "Delegate");
    taskWidget.waitForTaskDelegateDialogContent();
    assertFalse(taskWidget.isDelegateTypeAvailable());
    assertEquals("This task cannot be delegated to any other user or group.", taskWidget.getCannotDelegateText());
  }

  @Test
  public void testBulkDelegateTasksOnDashboard() {
    login(TestAccount.HR_ROLE_USER);
    redirectToNewDashBoard();
    NewDashboardPage dashboardPage = new NewDashboardPage();
    dashboardPage.waitForCaseWidgetLoaded();

    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();

    // Toggle selection mode
    taskWidget.clickBulkDelegateToggleButton(0);

    // Select both tasks
    taskWidget.selectTaskByName(MATERNITY_LEAVE_REQUEST);
    taskWidget.selectTaskByName(SICK_LEAVE_REQUEST);

    // Open bulk delegate dialog
    taskWidget.clickDelegateTasksButton(0);

    // Open user autocomplete dropdown and assert 2 items: emma and ethan
    ElementsCollection items = taskWidget.openBulkDelegateUserDropdownAndGetItems()
        .shouldHave(CollectionCondition.size(2), DEFAULT_TIMEOUT);
    items.filter(Condition.text("Emma")).shouldHave(CollectionCondition.size(1));
    items.filter(Condition.text("Ethan")).shouldHave(CollectionCondition.size(1));
  }

  private void openDashboard() {
    redirectToNewDashBoard();
    NewDashboardPage dashboardPage = new NewDashboardPage();
    dashboardPage.waitForCaseWidgetLoaded();
  }

  
}
