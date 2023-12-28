package com.axonivy.portal.selenium.test.express;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.ExpressApprovalPage;
import com.axonivy.portal.selenium.page.ExpressReviewPage;
import com.axonivy.portal.selenium.page.ExpressTaskPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.UserTaskWithMailFormPage;
import com.codeborne.selenide.WebDriverRunner;

@IvyWebTest
public class ExpressManagementTest extends BaseTest {

  public static final String REQUEST_NEW_RESOURCE_PROCESS = "Request new Resources - Express process";
  public static final String FIRST_COMMENT = "This is great news";
  public static final String SECOND_COMMENT = "I totally agree";

  private NewDashboardPage newDashboardPage;
  private ProcessWidgetPage processWidget;
  private TaskWidgetPage taskWidgetPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    this.newDashboardPage = new NewDashboardPage();
  }

  public void prepareExpressWorkflowStep() {
    try {
      importExpressFile(FileHelper.getAbsolutePathToTestFile("express-wf-request-resource.json"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    startExpressHelperProcess(REQUEST_NEW_RESOURCE_PROCESS);
  }

  private void importExpressFile(String pathName) throws UnsupportedEncodingException {
    String filepath = URLEncoder.encode(pathName, "UTF-8");
    redirectToRelativeLink("PortalKitTestHelper/153CACC26D0D4C3D/createSampleExpressWorkflowProcess.ivp?filePath=" + filepath);
  }

  private void startExpressHelperProcess(String processName) {
    newDashboardPage = new NewDashboardPage();
    processWidget = NavigationHelper.navigateToProcessList();
    assertTrue(processWidget.isExpandedMode());
    processWidget.enterSearchKeyword(processName);
    new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30)).until((driver) -> processWidget.isProcessDisplay(processName));
    processWidget.startProcess(processName);
  }

  public void openAdditionalBusinessPage(String caseName) {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
    CaseWidgetPage caseWidgetPage = newDashboardPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName(caseName);
    caseDetailsPage.openActionMenu();
    caseDetailsPage.openAdditionalCaseDetailsPage();
    new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30)).until((driver) -> newDashboardPage.countBrowserTab() > 1);
    newDashboardPage.switchLastBrowserTab();
  }

  public void completeExpressWorkflowTasks(String firstComment, String secondComment) {
    executePromoteResourceTask();
    // Filter open Task
    gotoTaskList();
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    executeSendEmailTask();
    executeReviewTask();
    taskWidgetPage.resetFilter();
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    executeApprovalTask(firstComment);
    executeApprovalTask(secondComment);
    executeFinalReviewTask();
  }

  private void gotoTaskList() {
    newDashboardPage = new NewDashboardPage();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
  }

  private void executeFinalReviewTask() {
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.clickOnStartTaskLink(0);
    ExpressReviewPage expressReview = new ExpressReviewPage();
    expressReview.finish();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
  }

  private void executeApprovalTask(String comment) {
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.clickOnStartTaskLink(0);
    ExpressApprovalPage expressApproval = new ExpressApprovalPage();
    expressApproval.waitForCommentContainerDisplay();
    expressApproval.comment(comment);
    expressApproval.clickOnApprove();
  }

  private void executeReviewTask() {
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.clickOnStartTaskLink(0);
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.waitForExpressFieldSetDisplay();
    expressTaskPage.enterRequiredInputFieldByLabel("Comment", "Mr.David to axon");
    expressTaskPage.finish();
  }

  private void executeSendEmailTask() {
    taskWidgetPage.clickOnStartTaskLink(0);
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.waitForExpressFieldSetDisplay();
    expressTaskPage.enterRequiredInputFieldByLabel("Welcome", "Welcome Mr.David to axon");

    UserTaskWithMailFormPage userTaskWithMail = new UserTaskWithMailFormPage();
    userTaskWithMail.selectEmailTab();
    userTaskWithMail.inputData("hr@email.com", "Please review this applicant", "Hello HR team, please proceed.");
    userTaskWithMail.finish();
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksInExpandedModeBy("HR", 1);
    taskWidgetPage.waitForActionGroupDisplay();
  }

  public void executePromoteResourceTask() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.waitForExpressFieldSetDisplay();
    expressTaskPage.enterRequiredInputFieldByLabel("Applicant name", "David Rafi");
    expressTaskPage.enterRequiredInputFieldByLabel("Email", "David@email.com");
    expressTaskPage.enterRequiredInputFieldByLabel("Address", "39b Truong Son, Tan Binh");
    expressTaskPage.finish();
  }

}
