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
import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessWidgetPage;

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
    redirectToRelativeLink(createAlphaCompanyUrl);
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
    Sleeper.sleep(500); // wait for Layout.js renders left menu
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.CASE_WIDGET_FOLDER + "case-key-information", new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
    
    WebElement saveFilterDialog =  caseWidget.getSaveFilterDialog();
    Sleeper.sleep(1000);//wait for focus animation finish to capture screenshot
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(saveFilterDialog, ScreenshotUtil.CASE_WIDGET_FOLDER + "how-to-create-case-filter", new ScreenshotMargin(100, 200));
  }

  @Test
  public void screenshotCaseDetails() throws IOException {
    caseWidget = homePage.openCaseList();
    homePage.closeMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1000));
    CaseDetailsPage detailsPage = caseWidget.openDetailsOfCaseHasName("Order Pizza");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details");
    
    WebElement generalInfor = detailsPage.getGeneralInforBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(generalInfor, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-data-description", new ScreenshotMargin(50, 10, 10, 10));
    
    executeDecorateJs("scrollToMiddleOfLayoutContent2()");
    Sleeper.sleep(500);
    WebElement relatedTask = detailsPage.getRelatedRunningTaskBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(relatedTask, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-related-tasks-cases", new ScreenshotMargin(10));
    
    WebElement addNote = detailsPage.getAddNoteDialog();
    Sleeper.sleep(2000);//wait for focus animation to capture screenshot
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addNote, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-add-task-note", new ScreenshotMargin(30, 50));
    detailsPage.addNote("Alpha Company");
    
    WebElement addDocument = detailsPage.getAddAttachmentDialog();
    Sleeper.sleep(2000);//wait for focus animation to capture screenshot
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addDocument, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-attach-document-to-case", new ScreenshotMargin(30, 60));
    detailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    
    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    WebElement document = detailsPage.getDocumentBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(document, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-documents", new ScreenshotMargin(10));
    
    executeDecorateJs("scrollToBottomOfLayoutContent()");
    Sleeper.sleep(500);
    WebElement histories = detailsPage.getHistoriesBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(histories, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-histories", new ScreenshotMargin(10));
    
    WebElement deleteDocument = detailsPage.getDeleteDocumentConfirmDialog();
    Sleeper.sleep(500);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(deleteDocument, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-delete-an-attachment-from-case", new ScreenshotMargin(100, 200));
    
    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    executeDecorateJs("scrollToBottomOfLayoutContent()");
    Sleeper.sleep(500);
    executeDecorateJs("highlightShowMoreNoteLink()");
    histories = detailsPage.getHistoriesBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(histories, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-show-note-details", new ScreenshotMargin(10));
    
    detailsPage.showNoteHistory();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
    Sleeper.sleep(3000);
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
    Sleeper.sleep(500);
    executeDecorateJs("highlightCaseDetailComponents()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-standard-2");
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
    CaseDetailsPage caseDetailsPage = caseWidget.openDetailsOfCaseHasName("Choose Alpha business name");
    caseDetailsPage.waitForCaseDetailsDisplay();

    executeDecorateJs("highlightSwitchToEditMode()");
    WebElement switchToEditMode = caseDetailsPage.getSwitchToEditModeButton();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(switchToEditMode,
        ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-switch-to-edit-mode", new ScreenshotMargin(100, 200));

    caseDetailsPage.switchToEditMode();
    caseDetailsPage.waitForSaveButtonDisplayed();
    caseDetailsPage.drapAndDropWidgets("information", "document");
    Sleeper.sleep(2000);// wait for focus animation to capture screenshot
    executeDecorateJs("highlightSwitchToViewMode()");
    WebElement switchToViewMode = caseDetailsPage.getSwitchToViewModeButton();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(switchToViewMode,
        ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-switch-to-view-mode", new ScreenshotMargin(100, 200));

    caseDetailsPage.saveAndSwitchToViewMode();
    caseDetailsPage.switchToEditMode();
    Sleeper.sleep(2000);// wait for focus animation to capture screenshot
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
    
    executeDecorateJs("highlightProcessOverviewLink()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_INFORMATION_WIDGET_FOLDER + "process-overview-link");
  }
}
