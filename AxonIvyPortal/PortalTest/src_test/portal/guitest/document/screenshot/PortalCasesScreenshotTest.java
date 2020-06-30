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
import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;

public class PortalCasesScreenshotTest extends BaseTest {

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
    redirectToRelativeLink(createBetaCompanyUrl);
    redirectToRelativeLink(createTestingCaseMapUrl);
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
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(saveFilterDialog, ScreenshotUtil.CASE_WIDGET_FOLDER + "how-to-create-case-filter", new ScreenshotMargin(100, 200));
  }

  @Test
  public void screenshotCaseDetails() throws IOException {
    caseWidget = homePage.openCaseList();
    homePage.closeMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1000));
    CaseDetailsPage detailsPage = caseWidget.openDetailsOfCaseHasName("Beta Company");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details");
    
    WebElement generalInfor = detailsPage.getGeneralInforBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(generalInfor, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-data-description", new ScreenshotMargin(50, 10, 10, 10));
    
    WebElement relatedTask = detailsPage.getRelatedRunningTaskBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(relatedTask, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-related-tasks-cases", new ScreenshotMargin(10));
    
    WebElement addNote = detailsPage.getAddNoteDialog();
    Sleeper.sleep(500);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addNote, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-add-task-note", new ScreenshotMargin(30, 50));
    detailsPage.addNote("Beta Company");
    
    WebElement addDocument = detailsPage.getAddAttachmentDialog();
    Sleeper.sleep(500);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addDocument, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-attach-document-to-case", new ScreenshotMargin(30, 60));
    detailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    
    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    WebElement document = detailsPage.getDocumentBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(document, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-documents", new ScreenshotMargin(10));
    
    WebElement histories = detailsPage.getHistoriesBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(histories, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-histories", new ScreenshotMargin(10));
    
    WebElement deleteDocument = detailsPage.getDeleteDocumentConfirmDialog();
    Sleeper.sleep(500);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(deleteDocument, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-delete-an-attachment-from-case", new ScreenshotMargin(100, 200));
    
    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    executeDecorateJs("highlightShowMoreNoteLink()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-show-note-details", new Dimension(SCREENSHOT_MEDIUM_WIDTH, 1400));
    
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
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1000));
    login(TestAccount.ADMIN_USER);
    caseWidget = homePage.openMainMenu().selectCaseMenu();
    homePage.closeMainMenu();
    CaseDetailsPage caseDetailsPage = caseWidget.openDetailsOfCaseHasName("Beta Company");
    caseDetailsPage.waitForCaseDetailsDisplay();
    executeDecorateJs("highlightCaseDetailComponents()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-standard");
    
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1400));
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    caseWidget = homePage.openMainMenu().selectCaseMenu();
    homePage.closeMainMenu();
    caseDetailsPage = caseWidget.openDetailsOfCaseHasName("Beta Company");
    caseDetailsPage.waitForCaseDetailsDisplay();
    executeDecorateJs("highlightCustomCaseDetail()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-customized-top");
    
    refreshPage();
    caseDetailsPage.waitForCaseDetailsDisplay();
    executeDecorateJs("scrollToBottomOfLayoutContent()");
    Sleeper.sleep(500);
    executeDecorateJs("highlightCustomCaseDetail()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_CUSTOMIZATION_FOLDER + "case-customized-bottom");
  }
  
}
