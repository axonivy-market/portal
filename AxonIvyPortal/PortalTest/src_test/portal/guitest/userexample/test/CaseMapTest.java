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

  private static final String CASE_MAP_URL = "/portal-user-examples/70765b37-a3e8-418a-a8d5-c2b3a539408e.icm";
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
    redirectToRelativeLink(CASE_MAP_URL);
    caseMapPage = new CaseMapPage();
    caseMapPage.inputFields("John", "Jack", "1.1.2019", "VN", "20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitButton();
    startTaskWithoutUI("Verify Personal Data");
    startTaskWithoutUI("Internal Solvency Check");
    login(TestAccount.DEMO_USER);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Approve Level 1");
    taskWidgetPage.startTask(0);
    assertInputData();
    caseMapPage.clickApproveButton();
    login(TestAccount.DEMO_USER);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Approve Level 2");
    taskWidgetPage.startTask(0);
    assertInputData();
    caseMapPage.clickApproveButton();
  }

  @Test
  public void testCaseMapRejectedWorkflow() {
    redirectToRelativeLink(CASE_MAP_URL);
    caseMapPage = new CaseMapPage();
    caseMapPage.inputFields("John", "Jack", "1.1.2019", "VN", "20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitButton();
    startTaskWithoutUI("Verify Personal Data");
    startTaskWithoutUI("Internal Solvency Check");
    login(TestAccount.DEMO_USER);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Approve Level 1");
    taskWidgetPage.startTask(0);
    assertInputData();
    caseMapPage.clickRejectButton();
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Approve Level 2");
    Assert.assertEquals(0, taskWidgetPage.countTasks());
  }

  private void assertInputData() {
    Assert.assertEquals("John", caseMapPage.getCustomerName());
    Assert.assertEquals("Jack", caseMapPage.getCustomerFirstName());
    Assert.assertEquals("VN", caseMapPage.getCountry());
    Assert.assertEquals("20000", caseMapPage.getAmount());
    Assert.assertEquals("To buy a new car", caseMapPage.getReason());
    Assert.assertEquals("80000", caseMapPage.getSalary());
    Assert.assertEquals("100000", caseMapPage.getOtherCredits());
  }

  @Test
  public void testCollectPersonalDataValidation() {
    redirectToRelativeLink(CASE_MAP_URL);
    CaseMapPage caseMapPage = new CaseMapPage();
    caseMapPage.inputFields("", "", "", "", "", "", "", "");
    Assert.assertEquals(
            "Name: Value is required.,"
            + "Country: Value is required.,"
            + "Amount (SFr.): Value is required.,"
            + "Yearly salary: Value is required.,"
            + "Amount of other open credits (SFr.)*: Value is required.",
        caseMapPage.clickSubmitAndGetValidationMsg());
    caseMapPage.inputFields("John", "Jack", "1.1.2020", "VN", "20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitButton();
  }

  private void startTaskWithoutUI(String taskname) {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(taskname);
    taskWidgetPage.startTaskWithoutUI(0);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }
}
