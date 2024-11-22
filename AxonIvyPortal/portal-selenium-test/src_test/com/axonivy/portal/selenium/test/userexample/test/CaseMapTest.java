package com.axonivy.portal.selenium.test.userexample.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseMapPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
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

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.DEMO_USER);
    redirectToRelativeLinkWithEmbedInFrame(CASE_MAP_URL);
  }

  @Test
  public void testCaseMapApprovalWorkflow() {
    caseMapPage = new CaseMapPage();
    caseMapPage.switchToIFrameOfTask();
    caseMapPage.inputFields("John", "Jack", "1.1.2019", "VN", "20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitRequestButton();
    NavigationHelper.navigateToTaskList();
    startTaskByTaskName(VERIFY_PERSONAL_DATA);
    assertInputData();
    caseMapPage.inputField("form:verifier-comment", "Ok");
    caseMapPage.clickSubmitButtonCaseMap();
    startTaskByTaskName(INTERNAL_SOLVENCY_CHECK);
    assertInputData();
    caseMapPage.getVerifierComment().shouldHave(Condition.value("OK"));
    caseMapPage.inputField("form:internal-comment", "Pass");
    caseMapPage.clickSubmitButtonCaseMap();
    startTaskByTaskName(APPROVAL_LEVEL_1);
    assertInputData();
    caseMapPage.getVerifierComment().shouldHave(Condition.value("OK"));
    caseMapPage.getInternalCreditComment().shouldHave(Condition.value("Pass"));
    caseMapPage.clickApproveButton();
    startTaskByTaskName(APPROVAL_LEVEL_2);
    assertInputData();
    caseMapPage.getVerifierComment().shouldHave(Condition.value("OK"));
    caseMapPage.getInternalCreditComment().shouldHave(Condition.value("Pass"));
    caseMapPage.clickApproveButton();
    startTaskByTaskName(CREATE_CONTRACT);
    assertInputData();
    caseMapPage.clickSubmitContractButton();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();

    taskWidget.waitForPageLoad();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Done");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(6));
  }

  @Test
  public void testCaseMapRejectedWorkflow() {
    caseMapPage = new CaseMapPage();
    caseMapPage.switchToIFrameOfTask();
    caseMapPage.inputFields("John", "Jack", "1.1.2019", "VN", "20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitRequestButton();
    NavigationHelper.navigateToTaskList();
    startTaskByTaskName(VERIFY_PERSONAL_DATA);
    assertInputData();
    caseMapPage.inputField("form:verifier-comment", "Ok");
    caseMapPage.clickSubmitButtonCaseMap();
    startTaskByTaskName(INTERNAL_SOLVENCY_CHECK);
    assertInputData();
    caseMapPage.getVerifierComment().shouldHave(Condition.value("OK"));
    caseMapPage.inputField("form:internal-comment", "Fail");
    caseMapPage.clickSubmitButtonCaseMap();
    startTaskByTaskName(APPROVAL_LEVEL_1);
    assertInputData();
    caseMapPage.getVerifierComment().shouldHave(Condition.value("Ok"));
    caseMapPage.getInternalCreditComment().shouldHave(Condition.value("Fail"));
    caseMapPage.clickRejectButton();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, APPROVAL_LEVEL_1);
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Done");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1));
  }

  private void startTaskByTaskName(String taskname) {
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskByNameInIFrame(taskname);
  }

  private void assertInputData() {
    caseMapPage.getCustomerLastName().shouldHave(Condition.value("John"), DEFAULT_TIMEOUT);
    caseMapPage.getCustomerFirstName().shouldHave(Condition.value("Jack"), DEFAULT_TIMEOUT);
    caseMapPage.getCountry().shouldHave(Condition.value("VN"), DEFAULT_TIMEOUT);
    caseMapPage.getAmount().shouldHave(Condition.value("20000"), DEFAULT_TIMEOUT);
    caseMapPage.getReason().shouldHave(Condition.value("To buy a new car"), DEFAULT_TIMEOUT);
    caseMapPage.getSalary().shouldHave(Condition.value("80000"), DEFAULT_TIMEOUT);
    caseMapPage.getOtherCredits().shouldHave(Condition.value("100000"), DEFAULT_TIMEOUT);
  }


}
