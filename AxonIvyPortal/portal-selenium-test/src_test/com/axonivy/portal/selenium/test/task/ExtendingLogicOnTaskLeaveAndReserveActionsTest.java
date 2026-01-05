package com.axonivy.portal.selenium.test.task;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NotificationCompactPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskTemplateIFramePage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.axonivy.portal.selenium.page.WorkingTaskDialogPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class ExtendingLogicOnTaskLeaveAndReserveActionsTest extends BaseTest {
  private NewDashboardPage dashboardPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    dashboardPage = new NewDashboardPage();
  }
  
  @Test
  public void testExecutingCustomizedLogicOnTaskLeaveAction() {
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    redirectToNewDashBoard();
    dashboardPage.waitForCaseWidgetLoaded();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.clickOnTaskName("Sick Leave Request");
    TaskTemplateIFramePage templatePage = new TaskTemplateIFramePage();
    templatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.leaveTask();
    menu.openTaskList();
    taskWidget.setInputForQuickSearch("Sick Leave Request");
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1), DEFAULT_TIMEOUT);
    TaskDetailsPage taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    taskDetailsPage.getNotesWithContent("Demo has left the task recently").shouldHave(CollectionCondition.size(1));
  }
  
  @Test
  public void testExecutingCustomizedLogicOnNotificationPanel() {
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    redirectToNewDashBoard();
    dashboardPage.waitForCaseWidgetLoaded();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.clickOnTaskName("Sick Leave Request");
    TaskTemplateIFramePage templatePage = new TaskTemplateIFramePage();
    NotificationCompactPage notificationPage = templatePage.openNotificationPanel();
    notificationPage.openNotificationMoreActionsMenu();
    notificationPage.clickNotificationSettingMenuItem();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.clickOnLeaveButton();
    menu.openTaskList();
    taskWidget.setInputForQuickSearch("Sick Leave Request");
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1), DEFAULT_TIMEOUT);
    TaskDetailsPage taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    taskDetailsPage.getNotesWithContent("Demo has left the task recently").shouldHave(CollectionCondition.size(1));
  }
  
  @Test
  public void testExecutingCustomizedLogicOnTaskReserveAction() {
    login(TestAccount.ADMIN_USER);
    createTestingTasks();
    redirectToNewDashBoard();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Task name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Sick Leave Request");
    taskWidget.applyFilter();
    taskWidget.reserveTask(0);
    taskWidget.clickCustomFieldsButtonOnActions(0);
    assertTrue(taskWidget.isCustomFieldsDialogDisplayed());
    List<String> taskCustomFieldNames = taskWidget.getCustomFieldNamesOnTaskCustomFieldsDialog();
    assertFalse(taskCustomFieldNames.isEmpty());
    assertTrue(taskCustomFieldNames.contains("reserveTask"));
  }
  
  @Test
  public void testExecutingCustomizedLogicOnTaskReserveInActionPanel() {
    login(TestAccount.ADMIN_USER);
    createTestingTasks();
    redirectToNewDashBoard();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Task name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Sick Leave Request");
    taskWidget.applyFilter();
    taskWidget.reserveTask(0);
    taskWidget.clickCustomFieldsButtonOnActions(0);
    assertTrue(taskWidget.isCustomFieldsDialogDisplayed());
    List<String> taskCustomFieldNames = taskWidget.getCustomFieldNamesOnTaskCustomFieldsDialog();
    assertFalse(taskCustomFieldNames.isEmpty());
    assertTrue(taskCustomFieldNames.contains("reserveTask"));
  }

}
