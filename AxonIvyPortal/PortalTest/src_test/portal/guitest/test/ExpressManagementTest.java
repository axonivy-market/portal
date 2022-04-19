package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.FileHelper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.ExpressApprovalPage;
import portal.guitest.page.ExpressBusinessViewPage;
import portal.guitest.page.ExpressManagementPage;
import portal.guitest.page.ExpressReviewPage;
import portal.guitest.page.ExpressTaskPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.UserTaskWithMailFormPage;
import vn.wawa.guitest.base.client.Browser;

public class ExpressManagementTest extends BaseTest {

  public static final String REQUEST_NEW_RESOURCE_PROCESS = "Request new Resources - Express process";
  private final String APPROVAL_STATUS = "Yes";
  private final String APPROVAL_USER_NAME = "Portal Admin User";
  public static final String FIRST_COMMENT = "This is great news";
  public static final String SECOND_COMMENT = "I totally agree";

  
  private HomePage homePage;
  private ProcessWidgetPage processWidget;
  private TaskWidgetPage taskWidgetPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    this.homePage = new HomePage();
  }

  @Test
  public void testImportUnsupportedExtension() {
    ExpressManagementPage expressManagementPage = uploadExpressJsonFile("unsupportedExtension.abc");
    String message = expressManagementPage.getUploadMessage();
    expressManagementPage.clickOnCloseButton();
    assertEquals("Invalid file type", message);
  }
  
  @Test
  public void testImportExpressProcess() {
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ExpressManagementPage expressManagementPage = uploadExpressJsonFile("express-test.json");
    expressManagementPage.deployExpressFile();
    adminSettingsPage.closeConfirmationDialog();
    startExpressHelperProcess("Express Test 1");
    executeUserTask();
  }
  
  @Test
  public void testViewEmptyExpressBusinessData() {
    prepareExpressWorkflowStep();
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.openCaseInfo();
    expressTaskPage.clickOnAdditionalBusinessDetailLink();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
    ExpressBusinessViewPage expressBusiness = new ExpressBusinessViewPage();
    assertTrue(StringUtils.contains(expressBusiness.getTextOfCurrentBreadcrumb(), "Business Details of Case #"));
    assertTrue(StringUtils.contains("There is no completed Express Task yet", expressBusiness.getEmptyFinishedTaskMessage()));
    homePage = expressBusiness.clickOnCloseButton();
  }

  public void prepareExpressWorkflowStep() {
    try {
      if (getBrowser() == null) {
        setBrowser(Browser.getBrowser());
      }
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
  
  @Test
  public void testViewExpressBusinessData() {
    prepareExpressWorkflowStep();
    completeExpressWorkflowTasks(FIRST_COMMENT, SECOND_COMMENT);
    
    openAdditionalBusinessPage(REQUEST_NEW_RESOURCE_PROCESS);
    
    ExpressBusinessViewPage expressBusiness = new ExpressBusinessViewPage();
    var currentStep = expressBusiness.getIndexOfCurrentProcessChain();
    assertTrue("ProcessChain should be 4 but " + currentStep, currentStep == 4);

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

  public void openAdditionalBusinessPage(String caseName) {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    CaseWidgetPage caseWidgetPage = homePage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName(caseName);
    caseDetailsPage.openActionMenu();
    caseDetailsPage.openAdditionalCaseDetailsPage();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
  }

  public void completeExpressWorkflowTasks(String firstComment, String secondComment) {
    executePromoteResourceTask();
    // Filter open Task
    gotoTaskList();
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    executeSendEmailTask();
    executeReviewTask();
    // back to home and refresh task list data
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    gotoTaskList();
    taskWidgetPage.resetFilter();
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    executeApprovalTask(firstComment);
    executeApprovalTask(secondComment);
    executeFinalReviewTask();
  }

  private void gotoTaskList() {
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
  }

  private void executeFinalReviewTask() {
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.clickOnStartTaskLink(0);
    ExpressReviewPage expressReview = new ExpressReviewPage();
    expressReview.finish();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
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

  private ExpressManagementPage uploadExpressJsonFile(String fileName) {
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ExpressManagementPage expressManagementPage = adminSettingsPage.openExpressManagementTab();
    expressManagementPage.openImportDialog();
    expressManagementPage.selectJSONFile(FileHelper.getAbsolutePathToTestFile(fileName));
    return expressManagementPage;
  }
  
  private void startExpressHelperProcess(String processName) {
    homePage = new HomePage();
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    assertTrue(processWidget.isExpandedMode());
    processWidget.enterSearchKeyword(processName);
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> processWidget.isProcessDisplay(processName));
    processWidget.startProcess(processName);
  }
  
  private void executeUserTask() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.finish();
    HomePage home = new HomePage();
    home.waitForPageLoaded();
  }
}
