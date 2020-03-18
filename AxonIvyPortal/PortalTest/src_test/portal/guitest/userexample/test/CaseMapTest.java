package portal.guitest.userexample.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.userexamples.page.CaseMapPage;

public class CaseMapTest extends BaseTest {

  private static final String CREATE_CONTRACT = "Create Contract";
  private static final String CASE_MAP_URL = "/portal-user-examples/70765b37-a3e8-418a-a8d5-c2b3a539408e.icm";
  private static final String VERIFY_PERSONAL_DATA = "Verify Personal Data";
  private static final String INTERNAL_SOLVENCY_CHECK = "Internal Solvency Check";
  private static final String APPROVAL_LEVEL_1 = "Approve Level 1";
  private static final String APPROVAL_LEVEL_2 = "Approve Level 2";
  private CaseMapPage caseMapPage;
  private TaskWidgetPage taskWidgetPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testCaseMapApprovalWorkflow() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(CASE_MAP_URL);
    caseMapPage = new CaseMapPage();
    caseMapPage.inputFields("John", "Jack", "1.1.2019", "VN", "20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitRequestButton();
    login(TestAccount.DEMO_USER);
    startTaskByTaskName(VERIFY_PERSONAL_DATA);
    assertInputData();
    caseMapPage.inputVerifierComment("Ok");
    caseMapPage.clickSubmitButton();
    login(TestAccount.DEMO_USER);
    startTaskByTaskName(INTERNAL_SOLVENCY_CHECK);
    assertInputData();
    Assert.assertEquals("Ok", caseMapPage.getVerifierComment());
    caseMapPage.inputInternalCreditComment("Pass");
    caseMapPage.clickSubmitButton();
    login(TestAccount.DEMO_USER);
    startTaskByTaskName(APPROVAL_LEVEL_1);
    assertInputData();
    Assert.assertEquals("Ok", caseMapPage.getVerifierComment());
    Assert.assertEquals("Pass", caseMapPage.getInternalCreditComment());
    caseMapPage.clickApproveButton();
    login(TestAccount.DEMO_USER);
    startTaskByTaskName(APPROVAL_LEVEL_2);
    assertInputData();
    Assert.assertEquals("Ok", caseMapPage.getVerifierComment());
    Assert.assertEquals("Pass", caseMapPage.getInternalCreditComment());
    caseMapPage.clickApproveButton();
    login(TestAccount.DEMO_USER);
    startTaskByTaskName(CREATE_CONTRACT);
    assertInputData();
    
  }

  @Test
  public void testCaseMapRejectedWorkflow() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(CASE_MAP_URL);
    caseMapPage = new CaseMapPage();
    caseMapPage.inputFields("John", "Jack", "1.1.2019", "VN", "20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitRequestButton();
    login(TestAccount.DEMO_USER);
    startTaskByTaskName(VERIFY_PERSONAL_DATA);
    assertInputData();
    caseMapPage.inputVerifierComment("Ok");
    caseMapPage.clickSubmitButton();
    login(TestAccount.DEMO_USER);
    startTaskByTaskName(INTERNAL_SOLVENCY_CHECK);
    assertInputData();
    Assert.assertEquals("Ok", caseMapPage.getVerifierComment());
    caseMapPage.inputInternalCreditComment("Fail");
    caseMapPage.clickSubmitButton();
    login(TestAccount.DEMO_USER);
    startTaskByTaskName(APPROVAL_LEVEL_1);
    assertInputData();
    Assert.assertEquals("Ok", caseMapPage.getVerifierComment());
    Assert.assertEquals("Fail", caseMapPage.getInternalCreditComment());
    caseMapPage.clickRejectButton();
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(APPROVAL_LEVEL_2);
    Assert.assertEquals(0, taskWidgetPage.countTasks());
  }

  private void assertInputData() {
    Assert.assertEquals("John", caseMapPage.getCustomerLastName());
    Assert.assertEquals("Jack", caseMapPage.getCustomerFirstName());
    Assert.assertEquals("VN", caseMapPage.getCountry());
    Assert.assertEquals("20000", caseMapPage.getAmount());
    Assert.assertEquals("To buy a new car", caseMapPage.getReason());
    Assert.assertEquals("80000", caseMapPage.getSalary());
    Assert.assertEquals("100000", caseMapPage.getOtherCredits());
  }

  @Test
  public void testCollectPersonalDataValidation() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(CASE_MAP_URL);
    caseMapPage = new CaseMapPage();
    caseMapPage.inputFields("", "", "", "", "", "", "", "");
    Assert.assertEquals(
            "First name: Value is required.,"
            + "Country: Value is required.,"
            + "Amount (SFr.): Value is required.,"
            + "Yearly salary: Value is required.,"
            + "Amount of other open credits (SFr.)*: Value is required.",
        caseMapPage.clickSubmitAndGetValidationMsg());
  }

  private void startTaskByTaskName(String taskname) {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(taskname);
    taskWidgetPage.startTask(0);
  }
}
