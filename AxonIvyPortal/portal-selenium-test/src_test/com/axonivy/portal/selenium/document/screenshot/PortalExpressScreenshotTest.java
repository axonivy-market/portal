package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.ExpressApprovalPage;
import com.axonivy.portal.selenium.page.ExpressBusinessViewPage;
import com.axonivy.portal.selenium.page.ExpressFormDefinitionPage;
import com.axonivy.portal.selenium.page.ExpressManagementPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
import com.axonivy.portal.selenium.page.ExpressReviewPage;
import com.axonivy.portal.selenium.page.ExpressTaskPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.UserTaskWithMailFormPage;
import com.codeborne.selenide.SelenideElement;

import ch.ivyteam.ivy.project.portal.test.ExpressResponsible;

@IvyWebTest
public class PortalExpressScreenshotTest extends ScreenshotBaseTest{

  private static final int USER_TASK_INDEX = 0;
  private static final int USER_TASK_WITH_EMAIL_INDEX = 1;
  private static final int INFORMATION_EMAIL_INDEX = 2;
  public static final String REQUEST_NEW_RESOURCE_PROCESS = "Request new Resources - Express process";
  public static final String FIRST_COMMENT = "This is great news";
  public static final String SECOND_COMMENT = "I totally agree";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    showNewDashboard();
  }

  @Test
  public void screenshotStartCreateExpressWorkflow() throws IOException {
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.expandMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(1400, 800));
    ScreenshotUtil.executeDecorateJs("highlightShowAllProcesses()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.EXPRESS_FOLDER + "navigate-to-axon-ivy-express");

    mainMenuPage.closeMainMenu();
    ProcessWidgetPage processWidget = mainMenuPage.openProcessList();
    processWidget.waitUtilProcessWidgetDisplayed();

    ScreenshotUtil.executeDecorateJs("highlightCreateExpressWorkflow()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.EXPRESS_FOLDER + "start-link-for-axon-ivy-express", new Dimension(1100, 1000));

    ExpressProcessPage expressProcessPage = processWidget.openExpressPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1100, 800));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "express-workflow-process-properties");

    ScreenshotUtil.executeDecorateJs("highlightExpressTaskResponsible(0)");
    WebElement taskStep1 = expressProcessPage.getDefineTaskStep(0);
    ScreenshotUtil.captureElementScreenshot(taskStep1, ScreenshotUtil.EXPRESS_FOLDER + "express-able-to-start");
    expressProcessPage.fillProcessProperties(true, true, "Request new resources process", "Request description");

    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Request resource", "Task request description", Arrays.asList(responsible1));
    ScreenshotUtil.resizeBrowser(new Dimension(1100, 1200));
    refreshPage();
    expressProcessPage.waitUntilExpressProcessDisplay();
    expressProcessPage.addNewTask(0);

    ExpressResponsible responsible2 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);

    expressProcessPage.createTask(1, USER_TASK_WITH_EMAIL_INDEX, "Approval request", "Approval request description", Arrays.asList(responsible2));
    ScreenshotUtil.executeDecorateJs("highlightExpressTaskResponsible(1)");
    WebElement taskStep2 = expressProcessPage.getDefineTaskStep(1);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskStep2, ScreenshotUtil.EXPRESS_FOLDER + "express-task-responsible", new ScreenshotMargin(20, 20, 0, 20));

    refreshPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1100, 1100));
    expressProcessPage.waitUntilExpressProcessDisplay();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "define-express-workflow-process-steps");

    ScreenshotUtil.resizeBrowser(new Dimension(1100, 1100));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createUploadComponent("Upload");
    formDefinition.moveAllElementToDragAndDrogPanel();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "express-workflow-dialog-editor");
    

    showNewDashboard();
    mainMenuPage = new MainMenuPage();
    processWidget = mainMenuPage.openProcessList();

    ScreenshotUtil.resizeBrowser(new Dimension(1400, 1200));
    processWidget.openExpressPage();
    expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Request new resources process", "Request description");

    responsible1 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
    expressProcessPage.createTask(0, USER_TASK_INDEX, "Request resource", "Task request description", Arrays.asList(responsible1));

    expressProcessPage.addNewTask(0);
    expressProcessPage.createTask(1, INFORMATION_EMAIL_INDEX, null, null, null);
    ExpressFormDefinitionPage expressFormDefinition = expressProcessPage.goToFormDefinition();
    expressFormDefinition.nextStep();
    expressFormDefinition.waitForEmailEditorDisplayed();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "express-workflow-email-editor");
  }
  
  @Test
  public void screenshotExpressManagement() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openExpressManagementTab();
    ScreenshotUtil.executeDecorateJs("highlightManagementExpressTab()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "express-management-tab");

    refreshPage();
    ExpressManagementPage expressManagementPage = adminSettingsPage.openExpressManagementTab();
    expressManagementPage.openImportDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(expressManagementPage.getImportExpressDialog(), ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "import-selection", new ScreenshotMargin(40));
    expressManagementPage.selectJSONFile(FileHelper.getAbsolutePathToTestFile("express-test.json"));

    ScreenshotUtil.executeDecorateJs("highlightDeployExpress()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(expressManagementPage.getImportExpressDialog(), ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "import-deployment", new ScreenshotMargin(40));

    expressManagementPage.clickOnDeployExpress();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(expressManagementPage.getImportExpressDialog(), ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "deployment-result", new ScreenshotMargin(40));

    expressManagementPage.clickOnCloseButton();
    expressManagementPage.clickOnSelectAllExpresses();
    ScreenshotUtil.executeDecorateJs("highlightExportExpress()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(), ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "express-export-tab", new ScreenshotMargin(60));

    ScreenshotUtil.executeDecorateJs("cleanHighlightExportExpress()");
    expressManagementPage.clickOnExportButton();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(expressManagementPage.getExportExpressDialog(), ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "export-list-summary", new ScreenshotMargin(60));

    ScreenshotUtil.maximizeBrowser();
    refreshPage();

    expressManagementPage = adminSettingsPage.openExpressManagementTab();
    expressManagementPage.openImportDialog();
    expressManagementPage.selectJSONFile(FileHelper.getAbsolutePathToTestFile("express-wf-request-resource.json"));
    expressManagementPage.clickOnDeployExpress();
    expressManagementPage.clickOnCloseButton();

    MainMenuPage mainMenu = new MainMenuPage();
    ProcessWidgetPage processWidget = mainMenu.openProcessList();
    processWidget.waitForStartListShow();
    processWidget.startProcessByName(REQUEST_NEW_RESOURCE_PROCESS);
    ScreenshotUtil.resizeBrowser(new Dimension(1920, 1080));
    completeExpressWorkflowTasks(FIRST_COMMENT, SECOND_COMMENT);

    ScreenshotUtil.resizeBrowser(new Dimension(1050, 600));
    showNewDashboard();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    mainMenu = new MainMenuPage();
    CaseWidgetPage caseWidgetPage = mainMenu.openCaseList();
    SelenideElement actionMenu = caseWidgetPage.openActionStepMenu(0);
    ScreenshotUtil.executeDecorateJs("highlightShowAdditionalLink()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "express-case");

    caseWidgetPage.openAdditionalCaseDetails(actionMenu);
    caseWidgetPage.switchLastBrowserTab();

    ScreenshotUtil.resizeBrowser(new Dimension(1050, 1000));
    ExpressBusinessViewPage expressBusinessView = new ExpressBusinessViewPage();
    expressBusinessView.clickOnLegendOfFieldset(1);
    expressBusinessView.clickOnLegendOfFieldset(2);
    expressBusinessView.clickTaskTitle();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "express-business-summary");
  }

  public void completeExpressWorkflowTasks(String firstComment, String secondComment) {
    executePromoteResourceTask();
    
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    homePage.selectTaskWidget("Your Tasks").openTask("Send email to HR");
    executeSendEmailTask();

    homePage.selectTaskWidget("Your Tasks").openTask("HR review");
    executeReviewTask();

    homePage.selectTaskWidget("Your Tasks").openTask("Approval Request");
    executeApprovalTask(firstComment);

    homePage.selectTaskWidget("Your Tasks").openTask("Approval Request");
    executeApprovalTask(secondComment);

    homePage.selectTaskWidget("Your Tasks").openTask("Request new Resources - Express process: Final Review");
    executeFinalReviewTask();
  }

  public void executePromoteResourceTask() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.waitForExpressFieldSetDisplay();
    expressTaskPage.enterRequiredInputFieldByLabel("Applicant name", "David Rafi");
    expressTaskPage.enterRequiredInputFieldByLabel("Email", "David@email.com");
    expressTaskPage.enterRequiredInputFieldByLabel("Address", "39b Truong Son, Tan Binh");
    expressTaskPage.finish();
  }

  private void executeSendEmailTask() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.waitForExpressFieldSetDisplay();
    expressTaskPage.enterRequiredInputFieldByLabel("Welcome", "Welcome Mr.David to axon");
    expressTaskPage.enterRequiredInputFieldByLabel("Start date", "01/09/2023 10:35");

    UserTaskWithMailFormPage userTaskWithMail = new UserTaskWithMailFormPage();
    userTaskWithMail.selectEmailTab();
    userTaskWithMail.inputData("hr@email.com", "Please review this applicant", "Hello HR team, please proceed.");
    userTaskWithMail.finish();
  }

  private void executeReviewTask() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.waitForExpressFieldSetDisplay();
    expressTaskPage.enterRequiredInputFieldByLabel("Comment", "Mr.David to axon");
    expressTaskPage.enterRequiredInputFieldByLabel("Approval date", "01/09/2023 10:35");
    expressTaskPage.finish();
  }

  private void executeApprovalTask(String comment) {
    ExpressApprovalPage expressApproval = new ExpressApprovalPage();
    expressApproval.waitForCommentContainerDisplay();
    expressApproval.comment(comment);
    expressApproval.clickOnApprove();
  }

  private void executeFinalReviewTask() {
    ExpressReviewPage expressReview = new ExpressReviewPage();
    expressReview.finish();
  }
}
