package com.axonivy.portal.selenium.test.userexample.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseMapPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.test.userexample.page.UserExamplesEndPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
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
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLinkWithEmbedInFrame(CASE_MAP_URL);
  }

  @Test
  public void testCaseMapApprovalWorkflow() {
    caseMapPage = new CaseMapPage();
    caseMapPage.switchToIFrameOfTask();
    caseMapPage.inputFields("John", "Jack", "1.1.2019", "VN", "20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitRequestButton();
    startTaskByTaskName(VERIFY_PERSONAL_DATA);
    caseMapPage.switchToIFrameOfTask();
    assertInputData();
    caseMapPage.inputField("form:verifier-comment", "Ok");
    caseMapPage.clickSubmitButton();
    startTaskByTaskName(INTERNAL_SOLVENCY_CHECK);
    caseMapPage.switchToIFrameOfTask();
    assertInputData();
    caseMapPage.getVerifierComment().shouldHave(Condition.value("OK"));
    caseMapPage.inputField("form:internal-comment", "Pass");
    caseMapPage.clickSubmitButton();
    startTaskByTaskName(APPROVAL_LEVEL_1);
    caseMapPage.switchToIFrameOfTask();
    assertInputData();
    caseMapPage.getVerifierComment().shouldHave(Condition.value("OK"));
    caseMapPage.getInternalCreditComment().shouldHave(Condition.value("Pass"));

    caseMapPage.clickApproveButton();
    startTaskByTaskName(APPROVAL_LEVEL_2);
    caseMapPage.switchToIFrameOfTask();
    assertInputData();
    caseMapPage.getVerifierComment().shouldHave(Condition.value("OK"));
    caseMapPage.getInternalCreditComment().shouldHave(Condition.value("Pass"));

    caseMapPage.clickApproveButton();
    startTaskByTaskName(CREATE_CONTRACT);
    caseMapPage.switchToIFrameOfTask();
    assertInputData();
    UserExamplesEndPage userExamplesEndPage = caseMapPage.clickSubmitContractButton();
    CaseDetailsPage caseDetailsPage = userExamplesEndPage.goToCaseDetail();
    assertEquals("Lending", caseDetailsPage.getCaseName());
  }

  @Test
  public void testCaseMapRejectedWorkflow() {
    caseMapPage = new CaseMapPage();
    caseMapPage.switchToIFrameOfTask();
    caseMapPage.inputFields("John", "Jack", "1.1.2019", "VN", "20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitRequestButton();
    startTaskByTaskName(VERIFY_PERSONAL_DATA);
    caseMapPage.switchToIFrameOfTask();
    assertInputData();
    caseMapPage.inputField("form:verifier-comment", "Ok");
    caseMapPage.clickSubmitButton();
    startTaskByTaskName(INTERNAL_SOLVENCY_CHECK);
    caseMapPage.switchToIFrameOfTask();
    assertInputData();
    caseMapPage.getVerifierComment().shouldHave(Condition.value("OK"));
    caseMapPage.inputField("form:internal-comment", "Fail");
    caseMapPage.clickSubmitButton();
    startTaskByTaskName(APPROVAL_LEVEL_1);
    caseMapPage.switchToIFrameOfTask();
    assertInputData();
    caseMapPage.getVerifierComment().shouldHave(Condition.value("Ok"));
    caseMapPage.getInternalCreditComment().shouldHave(Condition.value("Fail"));
    caseMapPage.clickRejectButton();
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksInExpandedModeBy(APPROVAL_LEVEL_2, 0);
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(0));
  }

  private void startTaskByTaskName(String taskname) {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy(taskname);
    taskWidgetPage.startTask(0);
  }

  private void assertInputData() {
    caseMapPage.getCustomerLastName().shouldHave(Condition.value("John"));
    caseMapPage.getCustomerFirstName().shouldHave(Condition.value("Jack"));
    caseMapPage.getCountry().shouldHave(Condition.value("VN"));
    caseMapPage.getAmount().shouldHave(Condition.value("20000"));
    caseMapPage.getReason().shouldHave(Condition.value("To buy a new car"));
    caseMapPage.getSalary().shouldHave(Condition.value("80000"));
    caseMapPage.getOtherCredits().shouldHave(Condition.value("100000"));
  }

  @Test
  public void testCollectPersonalDataValidation() {
    caseMapPage = new CaseMapPage();
    caseMapPage.switchToIFrameOfTask();
    caseMapPage.inputFields("", "", "", "", "", "", "", "");

    assertEquals(
            "First name: Value is required.," 
            + "Country: Value is required.," 
            + "Amount (SFr.): Value is required.,"
            + "Yearly salary: Value is required.," 
            + "Amount of other open credits (SFr.): Value is required.",
        caseMapPage.clickSubmitAndGetValidationMsg());
  }

}