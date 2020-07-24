package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static portal.guitest.common.FileHelper.getAbsolutePathToTestFile;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;

public class UploadDocumentTest extends BaseTest{
  
  private HomePage homePage;
  private CaseWidgetPage casePage;
  private CaseDetailsPage caseDetailsPage;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
  }
  
  @Test
  public void uploadNormalDocument() {
    initHomePage(TestAccount.ADMIN_USER);
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    int numberOfDocument = caseDetailsPage.countNumberOfDocument();
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    assertEquals(numberOfDocument + 1, caseDetailsPage.countNumberOfDocument());
  }
  
  @Test
  public void uploadScriptDocumentAndGetError() {
    enableScriptCheckingInPortalSetting();
    initHomePage(TestAccount.ADMIN_USER);
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    String error = caseDetailsPage.uploadDocumentWithError(getAbsolutePathToTestFile("test-with-macro.doc"));
    assertEquals("This file is not allowed to upload because it contains some script!", error);
    Sleeper.sleep(1000); // make Firefox passed, maybe a bug of Portal

    error = caseDetailsPage.uploadDocumentWithError(getAbsolutePathToTestFile("test-with-macro.xls"));
    assertEquals("This file is not allowed to upload because it contains some script!", error);
    Sleeper.sleep(1000);// make Firefox passed, maybe a bug of Portal

    error = caseDetailsPage.uploadDocumentWithError(getAbsolutePathToTestFile("test-no-files-with-js.pdf"));
    assertEquals("This file is not allowed to upload because it contains some script!", error);
    disableScriptCheckingInPortalSetting();
  }
  
  @Test
  public void uploadUnsupportedFileType(){
    enableScriptCheckingInPortalSetting();
    initHomePage(TestAccount.ADMIN_USER);
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    String error = caseDetailsPage.uploadDocumentWithError(getAbsolutePathToTestFile("unsupportedExtension.abc"));
    assertEquals("This file type is not accepted!", error);
    disableScriptCheckingInPortalSetting();
  }
  
  @Test
  public void addUnspportedFileTypeToSettingAndUploadFile() {
    updateFileExtensionWhiteListInPortalSetting();
    initHomePage(TestAccount.ADMIN_USER);
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    int numberOfDocument = caseDetailsPage.countNumberOfDocument();
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("unsupportedExtension.abc"));
    assertEquals(numberOfDocument + 1, caseDetailsPage.countNumberOfDocument());
  }
  

  @Test
  public void uploadDocumentAndCheckDocumentName() {
    final String pdfFile = "test-no-files-no-js.pdf";
    final String wordFile = "test-ms-word-extension.doc";
    final String unsupportFile = "unsupportedExtension.abc";
    
    updateFileExtensionWhiteListInPortalSetting();
    initHomePage(TestAccount.ADMIN_USER);

    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile(pdfFile));
    Assert.assertTrue(isCorrectIconExtension(pdfFile, "icon ivyicon-office-file-pdf-1"));
    
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile(wordFile));
    Assert.assertTrue(isCorrectIconExtension(wordFile, "icon ivyicon-office-file-doc-1"));
    
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile(unsupportFile));
    Assert.assertTrue(isCorrectIconExtension(unsupportFile, "icon ivyicon-common-file-empty"));
  }
  
  private boolean isCorrectIconExtension(String fileName, String iconClass) {
    final String caseDetailDocumentClass = "case-details-document-download-icon";
    List<WebElement> documentItems = caseDetailsPage.findDocumentItemInCaseDetailsDocumentTable();
    for (WebElement document:documentItems) {
      String uploadedFileName = document.findElement(By.cssSelector(".js-document-name")).getText();
      if (uploadedFileName.equalsIgnoreCase(fileName)) {
        String symbol = document.findElement(By.cssSelector("." + caseDetailDocumentClass)).getAttribute("class");
        assertEquals(symbol, iconClass.concat(StringUtils.SPACE.concat(caseDetailDocumentClass)));
        return true;
      }
    }
    return false;
  }
  
  private void initHomePage(TestAccount account) {
    login(account);
    homePage = new HomePage();
  }
  
  private void updateFileExtensionWhiteListInPortalSetting() {
    updatePortalSetting("UPLOAD_DOCUMENT_WHITELIST_EXTENSION", ", abc, pdf, doc");
  }
  
  private void disableScriptCheckingInPortalSetting() {
    updatePortalSetting("ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT", "false");
  }
  
  private void enableScriptCheckingInPortalSetting() {
    updatePortalSetting("ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT", "true");
  }
}
