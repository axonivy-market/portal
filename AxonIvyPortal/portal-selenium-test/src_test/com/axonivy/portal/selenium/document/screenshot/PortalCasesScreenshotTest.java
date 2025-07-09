package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.Sleeper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.test.caze.CaseDetailsTest;
import com.axonivy.portal.selenium.util.ConfigurationJsonUtils;
import com.codeborne.selenide.SelenideElement;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class PortalCasesScreenshotTest extends ScreenshotBaseTest {

  private MainMenuPage mainMenuPage;
  private static final int SCREENSHOT_WIDTH = 1500;
  private static final int SCREENSHOT_MEDIUM_WIDTH = 1100;
  private static final int SCREENSHOT_HEIGHT = 800;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    refreshPage();
    showNewDashboard();
    mainMenuPage = new MainMenuPage();
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
  }

  @Test
  public void screenshotCasesNavigate() throws IOException {
    CaseWidgetNewDashBoardPage caseWidgetPage = mainMenuPage.openCaseList();
    mainMenuPage.expandMainMenu();
    ScreenshotUtils.executeDecorateJs("highlightCaseMenuItem()");
    ScreenshotUtils
        .captureHalfLeftPageScreenShot(ScreenshotUtils.CASE_WIDGET_FOLDER + "navigate-to-full-cases-list-page");

    refreshPage();
    ScreenshotUtils.resizeBrowser(new Dimension(1600, 600));
    mainMenuPage.closeMainMenu();
    caseWidgetPage.clickOnCaseActionLink(0);
    Sleeper.sleep(500); // use Sleeper to wait actions memu display clearly
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.CASE_WIDGET_FOLDER + "case-key-information");
  }

  @Test
  public void screenshotCaseDetails() throws IOException {
    CaseWidgetNewDashBoardPage caseWidget = mainMenuPage.openCaseList();
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1400));
    WaitHelper.waitForNavigation(() -> caseWidget.openDetailsCase("Order Pizza"));
    CaseDetailsPage detailsPage = new CaseDetailsPage();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(detailsPage.getGeneralInforBox(),
        ScreenshotUtils.CASE_DETAIL_FOLDER + "case-details-data-description", new ScreenshotMargin(50, 10, 10, 10));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.CASE_DETAIL_FOLDER + "case-details");
    ScreenshotUtils.captureElementScreenshot(detailsPage.getRelatedRunningTaskBox(),
        ScreenshotUtils.CASE_DETAIL_FOLDER + "case-details-related-tasks-cases");

    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    ScreenshotUtils.captureElementScreenshot(detailsPage.getAddNoteDialog(),
        ScreenshotUtils.CASE_DETAIL_FOLDER + "how-to-add-task-note");

    detailsPage.addNoteContent("Take Order");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(detailsPage.openAddAttachmentDialog(),
        ScreenshotUtils.CASE_DETAIL_FOLDER + "how-to-attach-document-to-case", new ScreenshotMargin(10));
    detailsPage.closeAddAttachmentDialog();
    detailsPage.uploadDocument(FileHelper.getAbsolutePathToTestFile("test-no-files-no-js.pdf"));

    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(detailsPage.getDocumentBox(),
        ScreenshotUtils.CASE_DETAIL_FOLDER + "case-details-documents", new ScreenshotMargin(10));

    ScreenshotUtils.executeDecorateJs("scrollToBottomOfLayoutContent()");
    ScreenshotUtils.captureElementScreenshot(detailsPage.getHistoriesBox(),
        ScreenshotUtils.CASE_DETAIL_FOLDER + "case-details-histories");

    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(detailsPage.getDeleteDocumentConfirmDialog(),
        ScreenshotUtils.CASE_DETAIL_FOLDER + "how-to-delete-an-attachment-from-case", new ScreenshotMargin(100, 200));

    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    ScreenshotUtils.executeDecorateJs("scrollToBottomOfLayoutContent()");
    ScreenshotUtils.executeDecorateJs("highlightShowMoreNoteLink()");
    detailsPage.getHistoriesBox();
    ScreenshotUtils.captureElementScreenshot(detailsPage.getHistoriesBox(),
        ScreenshotUtils.CASE_DETAIL_FOLDER + "how-to-show-note-details");

    detailsPage.showNoteHistory();
    detailsPage.switchLastBrowserTab();
    detailsPage.waitForShowNoteHistory();
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.CASE_WIDGET_FOLDER + "export-case-history",
        new Dimension(SCREENSHOT_MEDIUM_WIDTH, 1000));
  }

  @Test
  public void screenshotCustomizeCaseDetails() throws IOException {
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1200));
    redirectToRelativeLink(createNewPaymentUrl);
    CaseWidgetNewDashBoardPage caseWidget = mainMenuPage.openCaseList();

    CaseDetailsPage caseDetailsPage = caseWidget.openDetailsCase("Create New Payment");
    caseDetailsPage.waitForCaseDetailsDisplay();
    ScreenshotUtils.executeDecorateJs("highlightCaseDetailComponents()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-standard-1");

    refreshPage();
    caseDetailsPage.waitForCaseDetailsDisplay();
    ScreenshotUtils.executeDecorateJs("scrollToMiddleOfLayoutContent()");
    ScreenshotUtils.executeDecorateJs("highlightCaseDetailComponents()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-standard-2");
  }

  @Test
  public void screenshotExportToExcel() throws IOException {
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 600));
    mainMenuPage.openCaseList();
    ScreenshotUtils.executeDecorateJs("highlightCaseExportToExcelButton()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.CASE_WIDGET_FOLDER + "export-to-excel-button");
  }

  @Test
  public void testCustomWidgetInCaseDetails() throws IOException {
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1000));
    redirectToRelativeLink(createEventTestUrl);
    CaseDetailsPage detailsPage = setupCustomWidgetByJSONFile("custom-case-details.json");
    ScreenshotUtils.executeDecorateJs("highlightCustomWidgetInCaseDetails()");
    detailsPage.waitForIFrameWidgetLoad();
    ScreenshotUtils
        .capturePageScreenshot(ScreenshotUtils.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-customized-iframe-process");
    refreshPage();
    setupCustomWidgetByJSONFile("custom-case-details-with-url.json");
    ScreenshotUtils.executeDecorateJs("highlightCustomWidgetInCaseDetails()");
    detailsPage.waitForIFrameURLWidgetLoad();
    ScreenshotUtils
        .capturePageScreenshot(ScreenshotUtils.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-customized-iframe-url");
  }

  public CaseDetailsPage setupCustomWidgetByJSONFile(String configFile) throws IOException {
    ConfigurationJsonUtils.updateJSONSetting(configFile, Variable.CASE_DETAIL);
    CaseDetailsPage detailsPage = goToCaseList().openDetailsCase(CaseDetailsTest.CUSTOM_CASE_WIDGET_NAME);
    return detailsPage;
  }

  public CaseWidgetNewDashBoardPage goToCaseList() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    CaseWidgetNewDashBoardPage casePage = mainMenuPage.openCaseList();
    return casePage;
  }

  @Test
  public void screenshotActionButtonsOnCaseDetails() throws IOException {
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1440));
    login(TestAccount.ADMIN_USER);
    showNewDashboard();
    CaseWidgetNewDashBoardPage caseWidget = mainMenuPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidget.openDetailsCase("Order Pizza");
    caseDetailsPage.waitForCaseDetailsDisplay();

    ScreenshotUtils.executeDecorateJs("highlightSharePageButton()");
    SelenideElement sharePageBtn = caseDetailsPage.getSharePageButtonElement();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(sharePageBtn,
        ScreenshotUtils.CASE_DETAIL_FOLDER + "share-page-button", new ScreenshotMargin(100, 200));
    refreshPage();

    ScreenshotUtils.executeDecorateJs("highlightSwitchToEditMode()");
    SelenideElement switchToEditMode = caseDetailsPage.getSwitchToEditModeButton();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(switchToEditMode,
        ScreenshotUtils.CASE_DETAIL_FOLDER + "how-to-switch-to-edit-mode", new ScreenshotMargin(100, 200));

    caseDetailsPage.switchToEditMode();
    caseDetailsPage.waitForSaveButtonDisplayed();
    caseDetailsPage.drapAndDropWidgets("information", "document");
    ScreenshotUtils.executeDecorateJs("highlightSwitchToViewMode()");
    SelenideElement switchToViewMode = caseDetailsPage.getSwitchToViewModeButton();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(switchToViewMode,
        ScreenshotUtils.CASE_DETAIL_FOLDER + "how-to-switch-to-view-mode", new ScreenshotMargin(100, 200));

    caseDetailsPage.saveAndSwitchToViewMode();
    caseDetailsPage.switchToEditMode();
    ScreenshotUtils.executeDecorateJs("highlightResetToDefault()");
    SelenideElement resetButton = caseDetailsPage.getResetButton();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(resetButton,
        ScreenshotUtils.CASE_DETAIL_FOLDER + "how-to-reset-to-default", new ScreenshotMargin(100, 200));
    caseDetailsPage.resetToDefault();
  }

  @Test
  public void screenshotProcessOverviewLink() throws IOException {
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 900));
    login(TestAccount.ADMIN_USER);
    ProcessWidgetPage processWidget = mainMenuPage.openProcessList();
    processWidget.waitForStartListShow();
    processWidget.startProcessByName("Process With Process Steps");
    showNewDashboard();

    CaseWidgetNewDashBoardPage caseWidget = mainMenuPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidget.openDetailsCase("Process With Process Steps");
    caseDetailsPage.waitForCaseDetailsDisplay();
    caseDetailsPage.openActionPanel();

    ScreenshotUtils.executeDecorateJs("highlightProcessOverviewLink()");
    ScreenshotUtils
        .captureHalfLeftPageScreenShot(ScreenshotUtils.PROCESSES_INFORMATION_WIDGET_FOLDER + "process-overview-link");
  }
  
  @Test
  public void screenshotPreviewDocument() throws IOException{
    updateGlobalVariable(Variable.ENABLE_DOCUMENT_PREVIEW.getKey(), "true");
    CaseWidgetNewDashBoardPage caseWidget = mainMenuPage.openCaseList();
    ScreenshotUtils.resizeBrowser(new Dimension(1600, SCREENSHOT_WIDTH));
    WaitHelper.waitForNavigation(() -> caseWidget.openDetailsCase("Order Pizza"));
    CaseDetailsPage detailsPage = new CaseDetailsPage();
    detailsPage.uploadDocumentWithoutError(FileHelper.getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    ScreenshotUtils.executeDecorateJs("highlightCasePreviewDocument()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(detailsPage.getDocumentBox(),
        ScreenshotUtils.CASE_DETAIL_FOLDER + "how-to-preview-document", new ScreenshotMargin(10));
  }
}
