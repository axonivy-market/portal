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
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;

@IvyWebTest
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
    taskWidget.openTaskDelegateDialog(0);

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
    taskWidget.openTaskDelegateDialog(0);

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
    taskWidget.openTaskDelegateDialog(0);
    assertFalse(taskWidget.isDelegateTypeAvailable());
    assertEquals("This task cannot be delegated to any other user or group.", taskWidget.getCannotDelegateText());
  }

  private void openDashboard() {
    redirectToNewDashBoard();
    NewDashboardPage dashboardPage = new NewDashboardPage();
    dashboardPage.waitForCaseWidgetLoaded();
  }
}
