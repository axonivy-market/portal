package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class BackNavigationTest extends BaseTest {

  private static final String CASE_LIST_TITLE = "Cases";
  private static final String CASE_DETAILS_TITLE = "Case Details";
  private static final String TASK_LIST_TITLE = "Tasks";
  private static final String TASK_DETAILS_TITLE = "Task Details";

  private static final String BETA_CASE_NAME = "Beta Company";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String PAYMENT_CASE_NAME = "Create New Payment";
  private static final String PAYMENT_TASK_NAME = "Do New Payment";

  private String createNewPaymentUrl = "portal-developer-examples/162511D2577DBA88/createNewPayment.ivp";

  private HomePage homePage;
  private CaseDetailsPage caseDetailsPage;
  private CaseWidgetPage caseWidgetPage;
  private TaskDetailsPage taskDetailsPage;
  private TaskWidgetPage taskWidgetPage;

  @Override
  @Before
  public void setup() {
    super.setup();
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

    TaskDetailsPage taskDetailsPage = caseDetailsPage.openTasksOfCasePage(0);
    assertEquals(TASK_DETAILS_TITLE, taskDetailsPage.getPageTitle());

    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.inputFields("tester", "16.05.2019", "17.05.2019", "tester");
    taskTemplatePage.clickOnSubmitButton();

    caseDetailsPage = new CaseDetailsPage();
    assertEquals(CASE_DETAILS_TITLE, caseDetailsPage.getPageTitle());

    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, caseWidgetPage.getPageTitle());
  }

  @Test
  public void testNavigateFromTechToBusinessCase() {
    redirectToRelativeLink(createBetaCompanyUrl);
    caseWidgetPage = homePage.openCaseList();

    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName(BETA_CASE_NAME);
    assertEquals(CASE_DETAILS_TITLE, caseDetailsPage.getPageTitle());

    caseDetailsPage.openRelatedCaseOfBusinessCase(0);
    caseDetailsPage.waitForCaseDetailsReload();
    assertEquals("Signal create Beta Company", caseDetailsPage.getCaseName());

    caseDetailsPage.clickBackButton();
    caseDetailsPage.waitForCaseDetailsReload();
    assertEquals(BETA_CASE_NAME, caseDetailsPage.getCaseName());

    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, caseWidgetPage.getPageTitle());
  }

  @Test
  public void testNavigateAfterFinishedTaskToCaseDetails() {
    redirectToRelativeLink(createNewPaymentUrl);
    caseWidgetPage = homePage.openCaseList();

    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName(PAYMENT_CASE_NAME);
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    taskDetailsPage = caseDetailsPage.openTasksOfCasePage(0);
    assertEquals(PAYMENT_TASK_NAME, taskDetailsPage.getTaskName());
    caseDetailsPage = taskDetailsPage.backToCaseDetails();
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    caseDetailsPage.openRelatedCaseOfBusinessCase(0);
    caseDetailsPage.waitForCaseDetailsReload();
    assertEquals("Signal create New Payment", caseDetailsPage.getCaseName());

    taskDetailsPage = caseDetailsPage.openTasksOfCasePage(0);
    assertEquals(PAYMENT_TASK_NAME, taskDetailsPage.getTaskName());

    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.type(By.id("payment-request:fullname"), "Demo");
    taskTemplatePage.type(By.id("payment-request:from_input"), "30.06.2020");
    taskTemplatePage.clickOnSubmitButton();
    caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.waitForCaseDetailsReload();
    assertEquals("Signal create New Payment", caseDetailsPage.getCaseName());

    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, caseWidgetPage.getPageTitle());
  }

  @Test
  public void testNavigateAfterCancelTaskToCaseDetails() {
    redirectToRelativeLink(createNewPaymentUrl);
    caseWidgetPage = homePage.openCaseList();

    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName(PAYMENT_CASE_NAME);
    assertEquals(PAYMENT_CASE_NAME, caseDetailsPage.getCaseName());

    taskDetailsPage = caseDetailsPage.openTasksOfCasePage(0);
    assertEquals(PAYMENT_TASK_NAME, taskDetailsPage.getTaskName());

    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelButton();
    taskDetailsPage = new TaskDetailsPage();
    assertEquals(PAYMENT_TASK_NAME, taskDetailsPage.getTaskName());
    taskDetailsPage.clickBackButton();

    caseDetailsPage = new CaseDetailsPage();
    assertEquals(CASE_DETAILS_TITLE, caseDetailsPage.getPageTitle());
    
    caseWidgetPage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, caseWidgetPage.getPageTitle());
  }

}
