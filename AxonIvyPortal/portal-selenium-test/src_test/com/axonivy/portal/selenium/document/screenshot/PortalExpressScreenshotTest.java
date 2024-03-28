package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.bean.ExpressResponsible;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
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


@IvyWebTest
public class PortalExpressScreenshotTest extends ScreenshotBaseTest {

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
    ScreenshotUtils.resizeBrowser(new Dimension(1400, 800));
    ScreenshotUtils.executeDecorateJs("highlightShowAllProcesses()");
    ScreenshotUtils.captureHalfLeftPageScreenShot(ScreenshotUtils.EXPRESS_FOLDER + "navigate-to-axon-ivy-express");

    mainMenuPage.closeMainMenu();
    ProcessWidgetPage processWidget = mainMenuPage.openProcessList();
    processWidget.waitUtilProcessWidgetDisplayed();

    ScreenshotUtils.executeDecorateJs("highlightCreateExpressWorkflow()");
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.EXPRESS_FOLDER + "start-link-for-axon-ivy-express",
        new Dimension(1100, 1000));

    ExpressProcessPage expressProcessPage = processWidget.openExpressPage();
    ScreenshotUtils.resizeBrowser(new Dimension(1100, 800));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "express-workflow-process-properties");

    ScreenshotUtils.executeDecorateJs("highlightExpressTaskResponsible(0)");
    WebElement taskStep1 = expressProcessPage.getDefineTaskStep(0);
    ScreenshotUtils.captureElementScreenshot(taskStep1, ScreenshotUtils.EXPRESS_FOLDER + "express-able-to-start");
    expressProcessPage.fillProcessProperties(true, true, "Request new resources process", "Request description");

    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Request resource", "Task request description",
        Arrays.asList(responsible1));
    ScreenshotUtils.resizeBrowser(new Dimension(1100, 1200));
    refreshPage();
    expressProcessPage.waitUntilExpressProcessDisplay();
    expressProcessPage.addNewTask(0);

    ExpressResponsible responsible2 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);

    expressProcessPage.createTask(1, USER_TASK_WITH_EMAIL_INDEX, "Approval request", "Approval request description",
        Arrays.asList(responsible2));
    ScreenshotUtils.executeDecorateJs("highlightExpressTaskResponsible(1)");
    WebElement taskStep2 = expressProcessPage.getDefineTaskStep(1);
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskStep2,
        ScreenshotUtils.EXPRESS_FOLDER + "express-task-responsible", new ScreenshotMargin(20, 20, 0, 20));

    refreshPage();
    ScreenshotUtils.resizeBrowser(new Dimension(1100, 1100));
    expressProcessPage.waitUntilExpressProcessDisplay();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "define-express-workflow-process-steps");

    ScreenshotUtils.resizeBrowser(new Dimension(1100, 1100));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinitionDefaultResolution();
    formDefinition.createUploadComponent("Upload");
    formDefinition.countElementPrepareToDrag(1);
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.countElementPrepareToDrag(0);
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "express-workflow-dialog-editor");

    formDefinition.switchToCheckBoxTab();
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.EXPRESS_FOLDER + "express-workflow");

    showNewDashboard();
    mainMenuPage = new MainMenuPage();
    processWidget = mainMenuPage.openProcessList();

    ScreenshotUtils.resizeBrowser(new Dimension(1400, 1200));
    processWidget.openExpressPage();
    expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Request new resources process", "Request description");

    responsible1 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
    expressProcessPage.createTask(0, USER_TASK_INDEX, "Request resource", "Task request description",
        Arrays.asList(responsible1));

    expressProcessPage.addNewTask(0);
    expressProcessPage.createTask(1, INFORMATION_EMAIL_INDEX, null, null, null);
    ExpressFormDefinitionPage expressFormDefinition = expressProcessPage.goToFormDefinitionDefaultResolution();
    expressFormDefinition.nextStep();
    expressFormDefinition.waitForEmailEditorDisplayed();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "express-workflow-email-editor");
  }

  @Test
  public void screenshotExpressManagement() throws IOException {
    ScreenshotUtils.resizeBrowser(new Dimension(1200, 800));
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openExpressManagementTab();
    ScreenshotUtils.executeDecorateJs("highlightManagementExpressTab()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_MANAGEMENT_FOLDER + "express-management-tab");

    refreshPage();
    ExpressManagementPage expressManagementPage = adminSettingsPage.openExpressManagementTab();
    expressManagementPage.openImportDialog();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(expressManagementPage.getImportExpressDialog(),
        ScreenshotUtils.EXPRESS_MANAGEMENT_FOLDER + "import-selection", new ScreenshotMargin(40));
    expressManagementPage.selectJSONFile(FileHelper.getAbsolutePathToTestFile("express-test.json"));

    ScreenshotUtils.executeDecorateJs("highlightDeployExpress()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(expressManagementPage.getImportExpressDialog(),
        ScreenshotUtils.EXPRESS_MANAGEMENT_FOLDER + "import-deployment", new ScreenshotMargin(40));

    expressManagementPage.clickOnDeployExpress();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(expressManagementPage.getImportExpressDialog(),
        ScreenshotUtils.EXPRESS_MANAGEMENT_FOLDER + "deployment-result", new ScreenshotMargin(40));

    expressManagementPage.clickOnCloseButton();
    expressManagementPage.clickOnSelectAllExpresses();
    ScreenshotUtils.executeDecorateJs("highlightExportExpress()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(),
        ScreenshotUtils.EXPRESS_MANAGEMENT_FOLDER + "express-export-tab", new ScreenshotMargin(60));

    ScreenshotUtils.executeDecorateJs("cleanHighlightExportExpress()");
    expressManagementPage.clickOnExportButton();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(expressManagementPage.getExportExpressDialog(),
        ScreenshotUtils.EXPRESS_MANAGEMENT_FOLDER + "export-list-summary", new ScreenshotMargin(60));

    ScreenshotUtils.maximizeBrowser();
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
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    completeExpressWorkflowTasks(FIRST_COMMENT, SECOND_COMMENT);

    ScreenshotUtils.resizeBrowser(new Dimension(1050, 600));
    showNewDashboard();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    mainMenu = new MainMenuPage();
    CaseWidgetPage caseWidgetPage = mainMenu.openCaseList();
    SelenideElement actionMenu = caseWidgetPage.openActionStepMenu(0);
    ScreenshotUtils.executeDecorateJs("highlightShowAdditionalLink()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "express-case");

    caseWidgetPage.openAdditionalCaseDetails(actionMenu);
    caseWidgetPage.switchLastBrowserTab();

    ScreenshotUtils.resizeBrowser(new Dimension(1050, 1000));
    ExpressBusinessViewPage expressBusinessView = new ExpressBusinessViewPage();
    expressBusinessView.clickOnLegendOfFieldset(1);
    expressBusinessView.clickOnLegendOfFieldset(2);
    expressBusinessView.clickTaskTitle();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.EXPRESS_FOLDER + "express-business-summary");
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
