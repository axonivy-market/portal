package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static portal.guitest.common.FileHelper.getAbsolutePathToTestFile;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;

public class UploadDocumentTest extends BaseTest{
  
  private HomePage homePage;
  private AdminSettingsPage adminSettingsPage;
  private CaseWidgetPage casePage;
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
    int numberOfDocument = caseDetailsPage.countNumberOfDocument();
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    assertEquals(numberOfDocument + 1, caseDetailsPage.countNumberOfDocument());
  }
  
  @Test
  public void uploadScriptDocumentAndGetError() {
    initHomePage(TestAccount.ADMIN_USER);
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setEnableScriptCheckingGlobalVariable();
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
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setDisableScriptCheckingGlobalVariable();
  }
  
  @Test
  public void uploadUnsupportedFileType(){
    initHomePage(TestAccount.ADMIN_USER);
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setEnableScriptCheckingGlobalVariable();
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    String error = caseDetailsPage.uploadDocumentWithError(getAbsolutePathToTestFile("unsupportedExtension.abc"));
    assertEquals("This file type is not accepted!", error);
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setDisableScriptCheckingGlobalVariable();
  }
  
  @Test
  public void addUnspportedFileTypeToSettingAndUploadFile() {
    initHomePage(TestAccount.ADMIN_USER);
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setFileExtensionWhiteList();
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    int numberOfDocument = caseDetailsPage.countNumberOfDocument();
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("unsupportedExtension.abc"));
    assertEquals(numberOfDocument + 1, caseDetailsPage.countNumberOfDocument());
  }
  
  private void initHomePage(TestAccount account) {
    login(account);
    homePage = new HomePage();
  }
  
}
