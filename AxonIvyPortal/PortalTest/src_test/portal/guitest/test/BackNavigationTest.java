package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import portal.guitest.common.BaseTest;
import portal.guitest.common.DateTimePattern;
import portal.guitest.common.TestAccount;
import portal.guitest.common.Variable;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class BackNavigationTest extends BaseTest {

  private static final String CASE_LIST_TITLE = "Cases - Portal - Axon Ivy";
  private static final String CASE_DETAILS_TITLE = "Case Details - Portal - Axon Ivy";
  private static final String TASK_LIST_TITLE = "Tasks - Portal - Axon Ivy";
  private static final String TASK_DETAILS_TITLE = "Task Details - Portal - Axon Ivy";

  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String PAYMENT_CASE_NAME = "Create New Payment";
  private static final String PAYMENT_TASK_NAME = "Do New Payment";

  private HomePage homePage;
  private CaseDetailsPage caseDetailsPage;
  private CaseWidgetPage caseWidgetPage;
  private TaskDetailsPage taskDetailsPage;
  private TaskWidgetPage taskWidgetPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    login(TestAccount.DEMO_USER);
    homePage = new HomePage();
  }

  @Test
  public void testEnterTaskDetailAndGoBack() {
    createTestingTasks();
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    assertEquals(TASK_DETAILS_TITLE, taskDetailsPage.getPageTitle());
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertEquals(TASK_LIST_TITLE, taskWidgetPage.getPageTitle());

    taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    assertEquals(TASK_DETAILS_TITLE, taskDetailsPage.getPageTitle());
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertEquals(TASK_LIST_TITLE, taskWidgetPage.getPageTitle());
  }

  @Test
  public void testEnterCaseDetailAndGoBack() {
    createTestingTasks();
    caseWidgetPage = homePage.openCaseList();
    caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName(LEAVE_REQUEST_CASE_NAME);
    assertEquals(CASE_DETAILS_TITLE, caseDetailsPage.getPageTitle());
    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, caseWidgetPage.getPageTitle());
  }

  @Test
  public void testFinishTaskFromCaseDetailAndGoBack() {
    createTestingTasks();
    caseWidgetPage = homePage.openCaseList();

    caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName(LEAVE_REQUEST_CASE_NAME);
    assertEquals(CASE_DETAILS_TITLE, caseDetailsPage.getPageTitle());

    TaskDetailsPage taskDetailsPage = caseDetailsPage.openTasksOfCasePage(1);
    assertEquals(TASK_DETAILS_TITLE, taskDetailsPage.getPageTitle());

    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    String today =  LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    String yesterday =  LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskTemplatePage.inputFields("tester", yesterday, today, "tester");
    taskTemplatePage.clickOnSubmitButton();

    caseDetailsPage = new CaseDetailsPage();
    assertEquals(CASE_DETAILS_TITLE, caseDetailsPage.getPageTitle());

    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, caseWidgetPage.getPageTitle());
  }

  @Test
  public void testNavigateFromTechToBusinessCase() {
    redirectToRelativeLink(createNewPaymentUrl);
    caseWidgetPage = homePage.openCaseList();

    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName(PAYMENT_CASE_NAME);
    assertEquals(CASE_DETAILS_TITLE, caseDetailsPage.getPageTitle());

    caseDetailsPage.openRelatedCaseOfBusinessCase(0);
    caseDetailsPage.waitForCaseDetailsReload();
    assertEquals("Signal create New Payment", caseDetailsPage.getCaseName());

    caseDetailsPage.clickBackButton();
    caseDetailsPage.waitForCaseDetailsReload();
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, caseWidgetPage.getPageTitle());
  }

  @Test
  public void testNavigateAfterFinishedTaskToCaseDetails() {
    redirectToRelativeLink(simplePaymentUrl);
    caseWidgetPage = homePage.openCaseList();

    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName(PAYMENT_CASE_NAME);
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    taskDetailsPage = caseDetailsPage.openTasksOfCasePage(1);
    assertEquals(PAYMENT_TASK_NAME, taskDetailsPage.getTaskName());
    caseDetailsPage = taskDetailsPage.backToCaseDetails();
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    taskDetailsPage = caseDetailsPage.openTasksOfCasePage(1);
    assertEquals(PAYMENT_TASK_NAME, taskDetailsPage.getTaskName());

    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.type(By.id("payment-request:fullname"), "Demo");
    String today =  LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskTemplatePage.type(By.id("payment-request:from_input"), today);
    taskTemplatePage.clickOnSubmitButton();
    caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.waitForCaseDetailsReload();
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, caseWidgetPage.getPageTitle());
  }

  @Test
  public void testNavigateAfterCancelTaskToCaseDetails() {
    redirectToRelativeLink(simplePaymentUrl);
    caseWidgetPage = homePage.openCaseList();

    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName(PAYMENT_CASE_NAME);
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    taskDetailsPage = caseDetailsPage.openTasksOfCasePage(1);
    assertEquals(PAYMENT_TASK_NAME, taskDetailsPage.getTaskName());

    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    assertEquals(PAYMENT_TASK_NAME, taskDetailsPage.getTaskName());
    taskDetailsPage.clickBackButton();

    caseDetailsPage = new CaseDetailsPage();
    assertEquals(CASE_DETAILS_TITLE, caseDetailsPage.getPageTitle());
    
    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, caseWidgetPage.getPageTitle());
  }

}
