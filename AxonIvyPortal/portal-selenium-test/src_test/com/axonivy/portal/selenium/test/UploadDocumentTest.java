package com.axonivy.portal.selenium.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


@IvyWebTest
public class UploadDocumentTest extends BaseTest {

  private NewDashboardPage newDashboardPage;
  private MainMenuPage menuPage;
  private CaseWidgetPage casePage;
  private CaseDetailsPage caseDetailsPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestingTasks();
  }

  @Test
  public void uploadNormalDocument() {
    updateGlobalVariable(Variable.ENABLE_DOCUMENT_PREVIEW.getKey(), "true");
    initNewDashboardPage(TestAccount.ADMIN_USER);
    casePage = menuPage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    int numberOfDocument = caseDetailsPage.countNumberOfDocument();
    caseDetailsPage.uploadDocumentWithoutError(FileHelper.getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    caseDetailsPage.checkNumberOfDocument(numberOfDocument + 1);
    assertTrue(caseDetailsPage.getFirstItemPreviewDocumentVisible());
  }

  @Test
  public void uploadScriptDocumentAndGetError() {
    enableScriptCheckingInPortalSetting();
    initNewDashboardPage(TestAccount.ADMIN_USER);
    casePage = menuPage.openCaseList();

    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    caseDetailsPage.openAddDocumentDialogAndUploadDocument(FileHelper.getAbsolutePathToTestFile("test-with-macro.doc"));
    caseDetailsPage
        .checkUploadDocumentErrorContent("This file is not allowed to upload because it contains some script!");
    caseDetailsPage.closeUploadDocumentDialog();

    caseDetailsPage.openAddDocumentDialogAndUploadDocument(FileHelper.getAbsolutePathToTestFile("test-with-macro.xls"));
    caseDetailsPage
        .checkUploadDocumentErrorContent("This file is not allowed to upload because it contains some script!");
    caseDetailsPage.closeUploadDocumentDialog();

    caseDetailsPage
        .openAddDocumentDialogAndUploadDocument(FileHelper.getAbsolutePathToTestFile("test-no-files-with-js.pdf"));
    caseDetailsPage
        .checkUploadDocumentErrorContent("This file is not allowed to upload because it contains some script!");
    caseDetailsPage.closeUploadDocumentDialog();
  }

  @Test
  public void uploadUnsupportedFileType() {
    enableScriptCheckingInPortalSetting();
    initNewDashboardPage(TestAccount.ADMIN_USER);
    casePage = menuPage.openCaseList();

    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    caseDetailsPage
        .openAddDocumentDialogAndUploadDocument(FileHelper.getAbsolutePathToTestFile("unsupportedExtension.abc"));
    caseDetailsPage.checkUploadDocumentErrorContent("This file type is not accepted!");

    disableScriptCheckingInPortalSetting();
  }

  @Test
  public void uploadDocumentAndCheckDocumentName() {
    updateGlobalVariable(Variable.ENABLE_DOCUMENT_PREVIEW.getKey(), "true");
    final String pdfFile = "test-no-files-no-js.pdf";
    final String wordFile = "test-ms-word-extension.doc";
    final String unsupportFile = "unsupportedExtension.abc";

    updateFileExtensionWhiteListInPortalSetting();
    initNewDashboardPage(TestAccount.ADMIN_USER);
    casePage = menuPage.openCaseList();

    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    caseDetailsPage.uploadDocumentWithoutError(FileHelper.getAbsolutePathToTestFile(pdfFile));
    isCorrectIconExtension(pdfFile, "si si-office-file-pdf-1");
    // can preview this document
    assertTrue(caseDetailsPage.getFirstItemPreviewDocumentVisible());

    caseDetailsPage.uploadDocumentWithoutError(FileHelper.getAbsolutePathToTestFile(wordFile));
    isCorrectIconExtension(wordFile, "si si-office-file-doc-1");
    // can not preview
    assertFalse(caseDetailsPage.getFirstItemPreviewDocumentVisible());

    // can not preview
    caseDetailsPage.uploadDocumentWithoutError(FileHelper.getAbsolutePathToTestFile(unsupportFile));
    isCorrectIconExtension(unsupportFile, "si si-common-file-empty");
    assertTrue(caseDetailsPage.getFirstItemPreviewDocumentVisible());
  }

  private boolean isCorrectIconExtension(String fileName, String iconClass) {
    final String caseDetailDocumentClass = "case-details-document-download-icon";
    ElementsCollection documentItems = caseDetailsPage.findDocumentItemInCaseDetailsDocumentTable();

    documentItems.asFixedIterable().forEach(doc -> {
      String uploadedFileName = doc.$(".js-document-name").shouldBe(Condition.appear, DEFAULT_TIMEOUT).getText();
      if (uploadedFileName.equalsIgnoreCase(fileName)) {
        SelenideElement docSymbol =
            doc.$(By.cssSelector("." + caseDetailDocumentClass)).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
        docSymbol.is(Condition.cssClass(iconClass.concat(StringUtils.SPACE.concat(caseDetailDocumentClass))));
      }
    });
    return true;
  }
  
  @Test
  public void addUnspportedFileTypeToSettingAndUploadFile() {
    updateFileExtensionWhiteListInPortalSetting();
    initNewDashboardPage(TestAccount.ADMIN_USER);
    casePage = menuPage.openCaseList();

    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    int numberOfDocument = caseDetailsPage.countNumberOfDocument();
    caseDetailsPage.uploadDocumentWithoutError(FileHelper.getAbsolutePathToTestFile("unsupportedExtension.abc"));
    caseDetailsPage.checkNumberOfDocument(numberOfDocument + 1);
  }

  private void initNewDashboardPage(TestAccount account) {
    login(account);
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();
    menuPage = new MainMenuPage();
  }

  private void updateFileExtensionWhiteListInPortalSetting() {
    updatePortalSetting(Variable.UPLOAD_DOCUMENT_WHITELIST_EXTENSION.getKey(), ", abc, pdf, doc");
  }

  private void disableScriptCheckingInPortalSetting() {
    updatePortalSetting(Variable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getKey(), "false");
  }

  private void enableScriptCheckingInPortalSetting() {
    updatePortalSetting(Variable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getKey(), "true");
  }
}
