package com.axonivy.portal.selenium.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;


@IvyWebTest
public class BackNavigationTest extends BaseTest {
  private static final String CASE_LIST_TITLE = "Cases";
  private static final String CASE_DETAILS_TITLE = "Case Details";
  private static final String TASK_LIST_TITLE = "Tasks";
  private static final String TASK_DETAILS_TITLE = "Task Details";

  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String PAYMENT_CASE_NAME = "Create New Payment";
  private static final String PAYMENT_TASK_NAME = "Do New Payment";
  private static final String SIGNAL_NEW_PAYMENT = "Signal create New Payment";
  private CaseDetailsPage caseDetailsPage;
  private CaseWidgetPage caseWidgetPage;
  private TaskDetailsPage taskDetailsPage;
  private TaskWidgetPage taskWidgetPage;

  @Override
  @BeforeEach
  public void setup() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    login(TestAccount.DEMO_USER);
  }

  @Test
  public void testEnterTaskDetailAndGoBack() {
    createTestingTasks();
    taskWidgetPage = NavigationHelper.navigateToTaskList();

    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    taskDetailsPage.assertPageTitle(TASK_DETAILS_TITLE);
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    taskWidgetPage.assertPageTitle(TASK_LIST_TITLE);

    taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    taskDetailsPage.assertPageTitle(TASK_DETAILS_TITLE);

    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    taskWidgetPage.assertPageTitle(TASK_LIST_TITLE);
  }

  @Test
  public void testEnterCaseDetailAndGoBack() {
    createTestingTasks();
    caseWidgetPage = NavigationHelper.navigateToCaseList();

    caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName(LEAVE_REQUEST_CASE_NAME);
    caseDetailsPage.waitPageLoaded();
    caseDetailsPage.assertPageTitle(CASE_DETAILS_TITLE);
    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    caseWidgetPage.assertPageTitle(CASE_LIST_TITLE);
  }

  @Test
  public void testFinishTaskFromCaseDetailAndGoBack() {
    createTestingTasks();
    caseWidgetPage = NavigationHelper.navigateToCaseList();

    caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName(LEAVE_REQUEST_CASE_NAME);
    caseDetailsPage.waitPageLoaded();

    TaskDetailsPage taskDetailsPage = caseDetailsPage.openTasksOfCasePage("Sick Leave Request");
    taskDetailsPage.waitPageLoaded();

    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    String yesterday =
        LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskTemplatePage.inputValue("tester", yesterday, today, "tester");
    taskTemplatePage.clickOnSubmitButton();

    caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.assertPageTitle(CASE_DETAILS_TITLE);

    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    caseWidgetPage.assertPageTitle(CASE_LIST_TITLE);
  }

  @Test
  public void testNavigateFromTechToBusinessCase() {
    redirectToRelativeLink(createNewPaymentUrl);
    caseWidgetPage = NavigationHelper.navigateToCaseList();

    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName(PAYMENT_CASE_NAME);
    caseDetailsPage.gotoCaseDetailsPageOfRelatedCase(SIGNAL_NEW_PAYMENT);
    caseDetailsPage.waitPageLoaded();
    assertEquals(SIGNAL_NEW_PAYMENT, caseDetailsPage.getCaseName());

    caseDetailsPage.clickBackButton();
    caseDetailsPage.waitPageLoaded();
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    caseWidgetPage.assertPageTitle(CASE_LIST_TITLE);
  }

  @Test
  public void testNavigateAfterFinishedTaskToCaseDetails() {
    redirectToRelativeLink(simplePaymentUrl);
    caseWidgetPage = NavigationHelper.navigateToCaseList();

    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName(PAYMENT_CASE_NAME);

    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    taskDetailsPage = caseDetailsPage.openTasksOfCasePage(PAYMENT_TASK_NAME);
    taskDetailsPage.waitPageLoaded();
    caseDetailsPage = taskDetailsPage.backToCaseDetails();
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    taskDetailsPage = caseDetailsPage.openTasksOfCasePage(PAYMENT_TASK_NAME);

    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.inputField("[id$='payment-request:fullname']", "Demo");
    String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskTemplatePage.inputField("[id$='payment-request:from_input']", today);

    taskTemplatePage.clickOnSubmitButton();
    caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.waitPageLoaded();
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    caseWidgetPage.assertPageTitle(CASE_LIST_TITLE);

  }

  @Test
  public void testNavigateAfterCancelTaskToCaseDetails() {
    redirectToRelativeLink(simplePaymentUrl);
    caseWidgetPage = NavigationHelper.navigateToCaseList();

    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName(PAYMENT_CASE_NAME);

    taskDetailsPage = caseDetailsPage.openTasksOfCasePage(PAYMENT_TASK_NAME);

    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    assertEquals(PAYMENT_TASK_NAME, taskDetailsPage.getTaskName());
    taskDetailsPage.clickBackButton();

    caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.assertPageTitle(CASE_DETAILS_TITLE);


    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    caseWidgetPage.assertPageTitle(CASE_LIST_TITLE);

  }

}
