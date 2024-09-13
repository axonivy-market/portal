package com.axonivy.portal.selenium.test.express;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.ExpressApprovalPage;
import com.axonivy.portal.selenium.page.ExpressBusinessViewPage;
import com.axonivy.portal.selenium.page.ExpressManagementPage;
import com.axonivy.portal.selenium.page.ExpressReviewPage;
import com.axonivy.portal.selenium.page.ExpressTaskPage;
import com.axonivy.portal.selenium.page.HomePage;
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

  private final String APPROVAL_STATUS = "Yes";
  private final String APPROVAL_USER_NAME = "Portal Admin User";

  private NewDashboardPage newDashboardPage;
  private ProcessWidgetPage processWidget;
  private TaskWidgetPage taskWidgetPage;
  private HomePage homePage;

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
    redirectToRelativeLink(
        "PortalKitTestHelper/153CACC26D0D4C3D/createSampleExpressWorkflowProcess.ivp?filePath=" + filepath);
  }

  private void startExpressHelperProcess(String processName) {
    newDashboardPage = new NewDashboardPage();
    processWidget = NavigationHelper.navigateToProcessList();
    assertTrue(processWidget.isExpandedMode());
    processWidget.enterSearchKeyword(processName);
    new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30))
        .until((driver) -> processWidget.isProcessDisplay(processName));
    processWidget.startProcess(processName);
  }
  
  private void startExpressHelperProcessForLegacy(String processName) {
    homePage = new HomePage();
    processWidget = NavigationHelper.navigateToProcessList();
    assertTrue(processWidget.isExpandedMode());
    processWidget.enterSearchKeyword(processName);
    new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30))
        .until((driver) -> processWidget.isProcessDisplay(processName));
    processWidget.startProcess(processName);
  }


  public void openAdditionalBusinessPage(String caseName) {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
    CaseWidgetPage caseWidgetPage = newDashboardPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName(caseName);
    caseDetailsPage.openActionMenu();
    caseDetailsPage.openAdditionalCaseDetailsPage();
    new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30))
        .until((driver) -> newDashboardPage.countBrowserTab() > 1);
    newDashboardPage.switchLastBrowserTab();
  }
  
  public void openAdditionalBusinessPageForLegacy(String caseName) {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    CaseWidgetPage caseWidgetPage = homePage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName(caseName);
    caseDetailsPage.openActionMenu();
    caseDetailsPage.openAdditionalCaseDetailsPage();
    new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30))
        .until((driver) -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
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
  
  public void completeExpressWorkflowTasksForLegacy(String firstComment, String secondComment) {
    executePromoteResourceTask();
    // Filter open Task
    gotoTaskListForLegacy();
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    executeSendEmailTask();
    executeReviewTask();
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.waitForGlobalGrowlDisappear();
    taskWidgetPage.resetFilter();
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    executeApprovalTask(firstComment);
    executeApprovalTask(secondComment);
    executeFinalReviewTask();
  }

  private void gotoTaskListForLegacy() {
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
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
    expressTaskPage.enterRequiredInputFieldByLabel("Approval date", "01/09/2023 10:35");
    expressTaskPage.finish();
  }

  private void executeSendEmailTask() {
    taskWidgetPage.clickOnStartTaskLink(0);
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.waitForExpressFieldSetDisplay();
    expressTaskPage.enterRequiredInputFieldByLabel("Welcome", "Welcome Mr.David to axon");
    expressTaskPage.enterRequiredInputFieldByLabel("Start date", "01/09/2023 10:35");

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
  
  private ExpressManagementPage uploadExpressJsonFile(String fileName) {
    homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ExpressManagementPage expressManagementPage = adminSettingsPage.openExpressManagementTab();
    expressManagementPage.openImportDialog();
    expressManagementPage.selectJSONFile(FileHelper.getAbsolutePathToTestFile(fileName));
    return expressManagementPage;
  }

  private void executeUserTask() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.finish();
    HomePage home = new HomePage();
    home.waitForPageLoad();
  }
  
  private void startExpressHelperProcessInLegacyMode(String processName) {
    homePage = new HomePage();
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    assertTrue(processWidget.isExpandedMode());
    processWidget.enterSearchKeyword(processName);
    new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30))
        .until((driver) -> processWidget.isProcessDisplay(processName));
    processWidget.startProcess(processName);
  }

  public void setupForLegacyExpressTests() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testImportExpressProcess() {
    setupForLegacyExpressTests();
    ExpressManagementPage expressManagementPage = uploadExpressJsonFile("express-test.json");
    expressManagementPage.deployExpressFile();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.closeConfirmationDialog();
    startExpressHelperProcessInLegacyMode("Express Test 1");
    executeUserTask();
  }
  

  
  @Test
  public void testImportUnsupportedExtension() {
    setupForLegacyExpressTests();
    ExpressManagementPage expressManagementPage = uploadExpressJsonFile("unsupportedExtension.abc");
    String message = expressManagementPage.getUploadMessage();
    expressManagementPage.clickOnCloseButton();
    assertEquals("Invalid file type", message);
  }

  
  @Test
  public void testViewEmptyExpressBusinessData() {
    setupForLegacyExpressTests();
    
    prepareExpressWorkflowStepForLegacy();
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.openCaseInfo();
    expressTaskPage.clickOnAdditionalBusinessDetailLink();
    assertTrue(homePage.countBrowserTab() > 1);
//    homePage.countBrowserTab();
    homePage.switchLastBrowserTab();
    ExpressBusinessViewPage expressBusiness = new ExpressBusinessViewPage();
    assertTrue(StringUtils.contains(expressBusiness.getTextOfCurrentBreadcrumb(), "Business Details of Case #"));
    assertTrue(StringUtils.contains("There is no completed Express Task yet", expressBusiness.getEmptyFinishedTaskMessage()));
    expressBusiness.clickOnCloseButton();
  }

  public void prepareExpressWorkflowStepForLegacy() {
    try {
      importExpressFile(FileHelper.getAbsolutePathToTestFile("express-wf-request-resource.json"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    startExpressHelperProcessForLegacy(REQUEST_NEW_RESOURCE_PROCESS);
  }


  @Test
  public void testViewExpressBusinessData() {
    setupForLegacyExpressTests();
    prepareExpressWorkflowStepForLegacy();

    completeExpressWorkflowTasksForLegacy(FIRST_COMMENT, SECOND_COMMENT);
    openAdditionalBusinessPageForLegacy(REQUEST_NEW_RESOURCE_PROCESS);
    
    ExpressBusinessViewPage expressBusiness = new ExpressBusinessViewPage();
    var currentStep = expressBusiness.getIndexOfCurrentProcessChain();
    assertEquals(4, currentStep);
    

    String fieldSetTitle = expressBusiness.getTextOfLegendFinishedTask(0);
    assertEquals("Promote new resource", fieldSetTitle);
    expressBusiness.clickOnLegendOfFieldset(0);
    
    fieldSetTitle = expressBusiness.getTextOfLegendFinishedTask(1);
    assertEquals("Send email to HR", fieldSetTitle);
    expressBusiness.clickOnLegendOfFieldset(1);
    
    fieldSetTitle = expressBusiness.getTextOfLegendFinishedTask(2);
    assertEquals("HR review", fieldSetTitle);
    expressBusiness.clickOnLegendOfFieldset(2);
    
    fieldSetTitle = expressBusiness.getTextOfLegendApprovalResult(0);
    assertEquals("Approval Request", fieldSetTitle);
    assertEquals(String.format("%s,%s,%s,%s,%s,%s", APPROVAL_USER_NAME, FIRST_COMMENT, APPROVAL_STATUS, APPROVAL_USER_NAME,
        SECOND_COMMENT, APPROVAL_STATUS), expressBusiness.getApprovalResultsText());
  }
}
