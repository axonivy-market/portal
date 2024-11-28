package com.axonivy.portal.selenium.test.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class TaskWidgetTest extends BaseTest {

  private TaskDetailsPage taskDetailsPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
  }

  @Test
  public void testOpenRelatedCaseOfTask() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openTaskDetailsPageByAction(0);
    CaseDetailsPage casePage = taskWidget.openRelatedCaseOfTask();
    String caseName = casePage.getCaseName();
    casePage.checkCaseName(caseName);
  }

  @Test
  public void testBreadCrumbInTaskDetail() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();

    taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    assertEquals("Task: Maternity Leave Request", taskDetailsPage.getTextOfCurrentBreadcrumb());

    taskDetailsPage.clickTaskListBreadCrumb();
    taskWidget = new TopMenuTaskWidgetPage();
    assertEquals(true, taskWidget.isDisplayed());

    taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    taskDetailsPage.goToHomeFromBreadcrumb();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    assertEquals(true, newDashboardPage.isDisplayed());
  }

  @Test
  public void testDisplayTaskAndCaseCategory() {
    login(TestAccount.ADMIN_USER);
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openTaskDetailsPageByAction(0);
    taskWidget.getTaskCategory().shouldHave(Condition.text("Other Leave/Maternity"));
    taskWidget.getCaseCategory().shouldHave(Condition.text("Leave Request"));
  }

  @Test
  public void testShowTaskCount() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.waitUntilTaskCountDifferentThanZero();
    assertEquals(4, taskWidget.countAllTasks().size(), "In Task list, Task Count != 4");
  }

}
