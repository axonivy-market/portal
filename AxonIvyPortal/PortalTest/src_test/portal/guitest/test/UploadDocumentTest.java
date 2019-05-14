package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;

public class UploadDocumentTest extends BaseTest{
  
  private HomePage homePage;
  private AdminSettingsPage adminSettingsPage;
  private CasePage casePage;
  private CaseDetailsPage caseDetailsPage;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }
  
  @Test
  public void uploadNormalDocument() {
    initHomePage(TestAccount.ADMIN_USER);
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    int numberOfDocument = countNumberOfDocument();
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    assertEquals(numberOfDocument + 1, countNumberOfDocument());
  }
  
  @Test
  public void uploadScriptDocumentAndGetError() {
    initHomePage(TestAccount.ADMIN_USER);
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setEnableScriptCheckingGlobalVariable();
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    String error = caseDetailsPage.uploadDocumentWithError(getAbsolutePathToTestFile("test-with-macro.doc"));
    assertEquals(error, "This file is not allowed to upload because it contains some script!");
    
    error = caseDetailsPage.uploadDocumentWithError(getAbsolutePathToTestFile("test-with-macro.xls"));
    assertEquals(error, "This file is not allowed to upload because it contains some script!");
    
    error = caseDetailsPage.uploadDocumentWithError(getAbsolutePathToTestFile("test-no-files-with-js.pdf"));
    assertEquals(error, "This file is not allowed to upload because it contains some script!");
  }
  
  @Test
  public void uploadUnsupportedFileType(){
    initHomePage(TestAccount.ADMIN_USER);
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    String error = caseDetailsPage.uploadDocumentWithError(getAbsolutePathToTestFile("unsupportedExtension.abc"));
    assertEquals(error, "This file type is not accepted!");
  }
  
  @Test
  public void addUnspportedFileTypeToSettingAndUploadFile() {
    initHomePage(TestAccount.ADMIN_USER);
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setFileExtensionWhiteList();
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    int numberOfDocument = countNumberOfDocument();
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("unsupportedExtension.abc"));
    assertEquals(numberOfDocument + 1, countNumberOfDocument());
    
  }
  
  private void initHomePage(TestAccount account) {
    LoginPage loginPage = new LoginPage(account);
    loginPage.login();
    homePage = new HomePage();
  }
  
  private int countNumberOfDocument() {
    WebElement caseDocument = caseDetailsPage.findElementById("case-widget:case-list-scroller:0:case-item:document");
    return caseDocument.findElements(By.cssSelector(".case-details-document-scrollpanel form")).size();
  }
  
  private String getAbsolutePathToTestFile(String fileName){
    return System.getProperty("user.dir") + "\\resources\\testFile\\" + fileName;
  }
  
}
