package portal.guitest.document.screenshot;

import static portal.guitest.common.FileHelper.getAbsolutePathToTestFile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.util.ConfigurationJsonUtil;
import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.Variable;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.test.CaseDetailsTest;

public class PortalCasesScreenshotTest extends ScreenshotTest {

  private HomePage homePage;
  private CaseWidgetPage caseWidget;
  private static final int SCREENSHOT_WIDTH = 1500;
  private static final int SCREENSHOT_MEDIUM_WIDTH = 1100;
  private static final int SCREENSHOT_HEIGHT = 800;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createUserFavoriteProcess);
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    refreshPage();
    homePage = new HomePage();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
  }

  @Test
  public void screenshotCasesNavigate() throws IOException {
    homePage.openMainMenu();
    caseWidget = homePage.openCaseList();
    executeDecorateJs("highlightCaseMenuItem()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.CASE_WIDGET_FOLDER + "navigate-to-full-cases-list-page");
    
    refreshPage();
    caseWidget.waitUntilCaseFilterDisplayed();
    homePage.closeMainMenu();
    caseWidget.openActionStepMenu();
    homePage.waitForLeftMenuActive();
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.CASE_WIDGET_FOLDER + "case-key-information", new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
    
    WebElement saveFilterDialog =  caseWidget.getSaveFilterDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(saveFilterDialog, ScreenshotUtil.CASE_WIDGET_FOLDER + "how-to-create-case-filter", new ScreenshotMargin(100, 200));
  }

  @Test
  public void screenshotCaseDetails() throws IOException {
    caseWidget = homePage.openCaseList();
    homePage.closeMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1400));
    CaseDetailsPage detailsPage = caseWidget.openDetailsOfCaseHasName("Order Pizza");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details");
    
    WebElement generalInfor = detailsPage.getGeneralInforBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(generalInfor, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-data-description", new ScreenshotMargin(50, 10, 10, 10));
    
    executeDecorateJs("scrollToMiddleOfLayoutContent2()");
    WebElement relatedTask = detailsPage.getRelatedRunningTaskBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(relatedTask, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-related-tasks-cases", new ScreenshotMargin(10));
    
    WebElement addNote = detailsPage.getAddNoteDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addNote, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-add-task-note", new ScreenshotMargin(30, 50));
    detailsPage.addNote("Take Order");
    
    WebElement addDocument = detailsPage.getAddAttachmentDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addDocument, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-attach-document-to-case", new ScreenshotMargin(30, 60));
    detailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    
    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    WebElement document = detailsPage.getDocumentBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(document, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-documents", new ScreenshotMargin(10));
    
    executeDecorateJs("scrollToBottomOfLayoutContent()");
    WebElement histories = detailsPage.getHistoriesBox();
    ScreenshotUtil.captureElementScreenshot(histories, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-histories");
    
    WebElement deleteDocument = detailsPage.getDeleteDocumentConfirmDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(deleteDocument, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-delete-an-attachment-from-case", new ScreenshotMargin(100, 200));
    
    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    executeDecorateJs("scrollToBottomOfLayoutContent()");
    executeDecorateJs("highlightShowMoreNoteLink()");
    histories = detailsPage.getHistoriesBox();
    ScreenshotUtil.captureElementScreenshot(histories, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-show-note-details");
    
    detailsPage.showNoteHistory();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
    WaitHelper.assertTrueWithWait(() -> ScreenshotUtil.isDOMStatusComplete());
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.CASE_WIDGET_FOLDER + "export-case-history", new Dimension(SCREENSHOT_MEDIUM_WIDTH, 1000));
  }
  
  @Test
  public void screenshotCustomCaseList() throws IOException {
    caseWidget = homePage.openCaseList();
    executeDecorateJs("highlightCustomCaseList()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.CASE_WIDGET_CUSTOMIZATION_FOLDER + "case-list", new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
  }
  
  @Test
  public void screenshotCustomCaseListColumnConfig() throws IOException {
    ScreenshotUtil.maximizeBrowser();
    redirectToRelativeLink(createCasesForCaseListCustomization);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    caseWidget = homePage.openMainMenu().selectCaseMenu();
    caseWidget.clickColumnsButton();
    executeDecorateJs("highlightCustomColumnsConfigOnCaseList()");
    ScreenshotUtil.captureHalfTopRightPageScreenShot(ScreenshotUtil.CASE_WIDGET_CUSTOMIZATION_FOLDER + "case-columns-configuration");
  }
  
  @Test
  public void screenshotCaseFilter() throws IOException {
    ScreenshotUtil.maximizeBrowser();
    caseWidget = homePage.openCaseList();
    caseWidget.openAdvancedFilter("Creator", "creator");
    caseWidget.filterByCreator("Demo");
    caseWidget.getCreator();
    executeDecorateJs("highlightCaseCreatorFilter()");
    ScreenshotUtil.captureHalfCenterTopPageScreenShot(ScreenshotUtil.CASE_WIDGET_CUSTOMIZATION_FOLDER + "case-filter");
  }
  
  @Test
  public void screenshotCustomizeCaseDetails() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1200));
    redirectToRelativeLink(createNewPaymentUrl);
    login(TestAccount.ADMIN_USER);
    caseWidget = homePage.openMainMenu().selectCaseMenu();
    homePage.closeMainMenu();
    CaseDetailsPage caseDetailsPage = caseWidget.openDetailsOfCaseHasName("Create New Payment");
    caseDetailsPage.waitForCaseDetailsDisplay();
    executeDecorateJs("highlightCaseDetailComponents()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-standard-1");

    refreshPage();
    caseDetailsPage.waitForCaseDetailsDisplay();
    executeDecorateJs("scrollToMiddleOfLayoutContent()");
    executeDecorateJs("highlightCaseDetailComponents()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-standard-2");
  }
  
  @Test
  public void testCustomWidgetInCaseDetails() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1000));
    redirectToRelativeLink(CaseDetailsTest.CREATE_EVENT_TEST_URL);
    CaseDetailsPage detailsPage = setupCustomWidgetByJSONFile("custom-case-details.json");
    executeDecorateJs("highlightCustomWidgetInCaseDetails()");
    detailsPage.waitForIFrameWidgetLoad();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-customized-iframe-process");
    
    setupCustomWidgetByJSONFile("custom-case-details-with-url.json");
    executeDecorateJs("highlightCustomWidgetInCaseDetails()");
    detailsPage.waitForIFrameURLWidgetLoad();
    detailsPage.switchToDefaultContent();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-customized-iframe-url");
  }

  public CaseDetailsPage setupCustomWidgetByJSONFile(String configFile) throws IOException {
    ConfigurationJsonUtil.updateJSONSetting(configFile, Variable.CASE_DETAIL);
    CaseDetailsPage detailsPage = goToCaseList().openDetailsOfCaseHasName(CaseDetailsTest.CUSTOM_CASE_WIDGET_NAME);
    detailsPage.closeMainMenu();
    return detailsPage;
  }

  public CaseWidgetPage goToCaseList() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    return casePage;
  }

  @Test
  public void screenshotExportToExcel() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 600));
    caseWidget = homePage.openCaseList();
    executeDecorateJs("highlightCaseExportToExcelButton()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_WIDGET_FOLDER + "export-to-excel-button");
  }
  
  @Test
  public void screenshotActionButtonsOnCaseDetails() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1440));
    login(TestAccount.ADMIN_USER);
    caseWidget = homePage.openMainMenu().selectCaseMenu();
    homePage.closeMainMenu();
    CaseDetailsPage caseDetailsPage = caseWidget.openDetailsOfCaseHasName("Order Pizza");
    caseDetailsPage.waitForCaseDetailsDisplay();

    executeDecorateJs("highlightSwitchToEditMode()");
    WebElement switchToEditMode = caseDetailsPage.getSwitchToEditModeButton();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(switchToEditMode,
        ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-switch-to-edit-mode", new ScreenshotMargin(100, 200));

    caseDetailsPage.switchToEditMode();
    caseDetailsPage.waitForSaveButtonDisplayed();
    caseDetailsPage.drapAndDropWidgets("information", "document");
    executeDecorateJs("highlightSwitchToViewMode()");
    WebElement switchToViewMode = caseDetailsPage.getSwitchToViewModeButton();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(switchToViewMode,
        ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-switch-to-view-mode", new ScreenshotMargin(100, 200));

    caseDetailsPage.saveAndSwitchToViewMode();
    caseDetailsPage.switchToEditMode();
    executeDecorateJs("highlightResetToDefault()");
    WebElement resetButton = caseDetailsPage.getResetButton();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(resetButton,
        ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-reset-to-default", new ScreenshotMargin(100, 200));
    caseDetailsPage.resetToDefault();
  }

  @Test
  public void screenshotProcessOverviewLink() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1920, 900));
    login(TestAccount.ADMIN_USER);
    ProcessWidgetPage processWidget = homePage.getProcessWidget();
    processWidget.expand();
    processWidget.startProcess("Process With Process Steps");
    
    homePage = new HomePage();
    caseWidget = homePage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidget.openDetailsOfCaseHasName("Process With Process Steps");
    caseDetailsPage.waitForCaseDetailsDisplay();
    caseDetailsPage.closeMainMenu();
    
    executeDecorateJs("highlightProcessOverviewLink()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_INFORMATION_WIDGET_FOLDER + "process-overview-link");
  }
}
