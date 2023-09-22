package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.Sleeper;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.codeborne.selenide.SelenideElement;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest(headless = false)
public class PortalCasesScreenshotTest extends ScreenshotBaseTest{
  
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
    redirectToRelativeLink(createUserFavoriteProcess);
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    refreshPage();
    showNewDashboard();
    mainMenuPage = new MainMenuPage();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
  }
  
  @Test
  public void screenshotCasesNavigate() throws IOException {
    CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
    mainMenuPage.expandMainMenu();
    ScreenshotUtil.executeDecorateJs("highlightCaseMenuItem()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.CASE_WIDGET_FOLDER + "navigate-to-full-cases-list-page");
    
    refreshPage();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 600));
    mainMenuPage.closeMainMenu();
    caseWidgetPage.openActionStepMenu(0);
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_WIDGET_FOLDER + "case-key-information");
    
    ScreenshotUtil.maximizeBrowser();
    SelenideElement saveFilterDialog =  caseWidgetPage.getSaveFilterDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(saveFilterDialog, ScreenshotUtil.CASE_WIDGET_FOLDER + "how-to-create-case-filter", new ScreenshotMargin(100, 200));
  }
  
  @Test
  public void screenshotCaseDetails() throws IOException {
    CaseWidgetPage caseWidget = mainMenuPage.openCaseList();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1400));
    CaseDetailsPage detailsPage = caseWidget.openDetailsOfCaseHasName("Order Pizza");
    Sleeper.sleep(250); // Need to wait for bread-crumb finished render
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details");
    
//    Se generalInfor = detailsPage.getGeneralInforBox();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(detailsPage.getGeneralInforBox(), ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-data-description", new ScreenshotMargin(50, 10, 10, 10));
    
//    WebElement relatedTask = detailsPage.getRelatedRunningTaskBox();
    ScreenshotUtil.captureElementScreenshot(detailsPage.getRelatedRunningTaskBox(), ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-related-tasks-cases");
    
    refreshPage();
    detailsPage.waitForCaseDetailsDisplay();
    SelenideElement addNote = detailsPage.getAddNoteDialog();
    ScreenshotUtil.captureElementScreenshot(addNote, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-add-task-note1");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addNote, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-add-task-note", new ScreenshotMargin(50, 10, 10, 10));

//    detailsPage.addNote("Take Order");
//    
//    WebElement addDocument = detailsPage.getAddAttachmentDialog();
//    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addDocument, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-attach-document-to-case", new ScreenshotMargin(30, 60));
//    detailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
//    
//    refreshPage();
//    detailsPage.waitForCaseDetailsDisplay();
//    WebElement document = detailsPage.getDocumentBox();
//    ScreenshotUtil.captureElementWithMarginOptionScreenshot(document, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-documents", new ScreenshotMargin(10));
//    
//    executeDecorateJs("scrollToBottomOfLayoutContent()");
//    WebElement histories = detailsPage.getHistoriesBox();
//    ScreenshotUtil.captureElementScreenshot(histories, ScreenshotUtil.CASE_DETAIL_FOLDER + "case-details-histories");
//    
//    refreshPage();
//    detailsPage.waitForCaseDetailsDisplay();
//    WebElement deleteDocument = detailsPage.getDeleteDocumentConfirmDialog();
//    ScreenshotUtil.captureElementWithMarginOptionScreenshot(deleteDocument, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-delete-an-attachment-from-case", new ScreenshotMargin(100, 200));
//    
//    refreshPage();
//    detailsPage.waitForCaseDetailsDisplay();
//    executeDecorateJs("scrollToBottomOfLayoutContent()");
//    executeDecorateJs("highlightShowMoreNoteLink()");
//    histories = detailsPage.getHistoriesBox();
//    ScreenshotUtil.captureElementScreenshot(histories, ScreenshotUtil.CASE_DETAIL_FOLDER + "how-to-show-note-details");
//    
//    detailsPage.showNoteHistory();
//    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
//    homePage.switchLastBrowserTab();
//    WaitHelper.assertTrueWithWait(() -> ScreenshotUtil.isDOMStatusComplete());
//    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.CASE_WIDGET_FOLDER + "export-case-history", new Dimension(SCREENSHOT_MEDIUM_WIDTH, 1000));
  }

}
