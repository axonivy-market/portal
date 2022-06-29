package portal.guitest.document.screenshot;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.FileHelper;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.ExpressBusinessViewPage;
import portal.guitest.page.ExpressFormDefinitionPage;
import portal.guitest.page.ExpressManagementPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.test.ExpressManagementTest;

public class PortalExpressScreenshotTest extends ScreenshotTest {
  
  private static final int USER_TASK_INDEX = 0;
  private static final int USER_TASK_WITH_EMAIL_INDEX = 1;
  private static final int INFORMATION_EMAIL_INDEX = 2;
  private String expressAdditionalBusinessUrl = "AxonIvyExpress/17326FC2F133FBEA/startExpressBusinessView.ivp";

  private HomePage homePage;
  private NewDashboardPage newDashboardPage;
  private ProcessWidgetPage processWidget;
  private ExpressProcessPage expressProcessPage;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    homePage = new HomePage();
  }

  @Test
  public void screenshotProcessNavigate() throws IOException {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createUserFavoriteProcess);
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.openMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(1400, 500));
    executeDecorateJs("highlightShowAllProcesses()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.EXPRESS_FOLDER + "navigate-to-axon-ivy-express");
  }
  
  @Test
  public void screenshotStartCreateExpressWorkflow() throws IOException {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    executeDecorateJs("highlightCreateExpressWorkflow()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.EXPRESS_FOLDER + "start-link-for-axon-ivy-express", new Dimension(1100, 800));
    
    expressProcessPage = processWidget.openExpressPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1100, 800));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "express-workflow-process-properties");
    
    executeDecorateJs("highlightExpressTaskResponsible(0)");
    WebElement taskStep1 = expressProcessPage.getDefineTaskStep(0);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskStep1, ScreenshotUtil.EXPRESS_FOLDER + "express-able-to-start", new ScreenshotMargin(20, 20, 0, 20));
    expressProcessPage.fillProcessProperties(true, true, "Request new resources process", "Request description");
    
    ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
    expressProcessPage.createTask(0, USER_TASK_INDEX, "Request resource", "Task request description", Arrays.asList(responsible1));
    expressProcessPage.waitForChooseResponsibleDialogHidden();
    
    ScreenshotUtil.resizeBrowser(new Dimension(1100, 1200));
    refreshPage();
    expressProcessPage.waitUntilExpressProcessDisplay();
    expressProcessPage.addNewTask(0);
    ExpressResponsible responsible2 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    expressProcessPage.createTask(1, USER_TASK_WITH_EMAIL_INDEX, "Approval request", "Approval request description", Arrays.asList(responsible2));
    expressProcessPage.waitForChooseResponsibleDialogHidden();
    executeDecorateJs("highlightExpressTaskResponsible(1)");
    WebElement taskStep2 = expressProcessPage.getDefineTaskStep(1);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskStep2, ScreenshotUtil.EXPRESS_FOLDER + "express-task-responsible", new ScreenshotMargin(20, 20, 0, 20));

    refreshPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1100, 1100));
    expressProcessPage.waitUntilExpressProcessDisplay();
    WaitHelper.assertTrueWithWait(() -> ScreenshotUtil.isDOMStatusComplete());
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "define-express-workflow-process-steps");
    
    ScreenshotUtil.resizeBrowser(new Dimension(1100, 1100));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.switchToCheckBoxTab();
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.EXPRESS_FOLDER + "express-workflow");
    
    formDefinition.createUploadComponent("Upload");
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.waitForLeftMenuActive();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "express-workflow-dialog-editor");
  }
  
  @Test
  public void screenshotEmailInformation() throws IOException {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    ScreenshotUtil.resizeBrowser(new Dimension(1400, 1200));
    processWidget.openExpressPage();
    expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Request new resources process", "Request description");
    
    ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
    expressProcessPage.createTask(0, USER_TASK_INDEX, "Request resource", "Task request description", Arrays.asList(responsible1));
    
    expressProcessPage.addNewTask(0);
    expressProcessPage.createTask(1, INFORMATION_EMAIL_INDEX, null, null, null);
    ExpressFormDefinitionPage expressFormDefinition = expressProcessPage.goToFormDefinition();
    expressFormDefinition.nextStep();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "express-workflow-email-editor");
  }

  @Test
  public void screenshotExpressManagement() throws IOException {
    login(TestAccount.ADMIN_USER);
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openExpressManagementTab();
    executeDecorateJs("highlightManagementExpressTab()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "express-management-tab");
  }
  
  @Test
  public void screenshotExpressManagementImport() throws IOException {
    login(TestAccount.ADMIN_USER);
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 1200));
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ExpressManagementPage expressManagementPage = adminSettingsPage.openExpressManagementTab();
    expressManagementPage.openImportDialog();
    WebElement importDialog = expressManagementPage.getImportExpressDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(importDialog, ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "import-selection", new ScreenshotMargin(40));
    
    expressManagementPage.selectJSONFile(FileHelper.getAbsolutePathToTestFile("express-test.json"));
    expressManagementPage.waitForDeployButtonEnabled();
    executeDecorateJs("highlightDeployExpress()");
    WebElement importDialogDeploy = expressManagementPage.getImportExpressDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(importDialogDeploy, ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "import-deployment", new ScreenshotMargin(40));
    expressManagementPage.clickOnDeployExpress();
    WebElement importDialogResult = expressManagementPage.getImportExpressDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(importDialogResult, ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "deployment-result", new ScreenshotMargin(40));
    expressManagementPage.clickOnCloseButton();
    expressManagementPage.clickOnSelectAllExpresses();
    executeDecorateJs("highlightExportExpress()");
    WebElement adminDialog = adminSettingsPage.getAdminSettingContainer();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminDialog, ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "express-export-tab", new ScreenshotMargin(60));
    executeDecorateJs("cleanHighlightExportExpress()");
    expressManagementPage.clickOnExportButton();
    WebElement exportDialog = expressManagementPage.getExportExpressDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(exportDialog, ScreenshotUtil.EXPRESS_MANAGEMENT_FOLDER + "export-list-summary", new ScreenshotMargin(60));
  }
  
  @Test
  public void screenshotExpressBusinessPage() throws IOException {
    login(TestAccount.ADMIN_USER);
    ExpressManagementTest expressManagementTest = new ExpressManagementTest();
    expressManagementTest.prepareExpressWorkflowStep();
    expressManagementTest.completeExpressWorkflowTasks(ExpressManagementTest.FIRST_COMMENT, ExpressManagementTest.SECOND_COMMENT);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    ScreenshotUtil.resizeBrowser(new Dimension(1050, 800));
    CaseWidgetPage caseWidgetPage = homePage.openCaseList();
    caseWidgetPage.closeMainMenu();
    caseWidgetPage.openActionStepMenu();
    executeDecorateJs("highlightShowAdditionalLink()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.EXPRESS_FOLDER + "express-case");
    
    String caseId = caseWidgetPage.getCaseId(0);
    redirectToRelativeLink(expressAdditionalBusinessUrl + "?caseId=" + caseId);
    ScreenshotUtil.resizeBrowser(new Dimension(1050, 1000));
    ExpressBusinessViewPage expressBusinessView = new ExpressBusinessViewPage();
    expressBusinessView.clickOnLegendOfFieldset(1);
    expressBusinessView.clickOnLegendOfFieldset(2);
    expressBusinessView.closeMainMenu();
    expressBusinessView.waitForLeftMenuActive();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.EXPRESS_FOLDER + "express-business-summary");
  }

}
