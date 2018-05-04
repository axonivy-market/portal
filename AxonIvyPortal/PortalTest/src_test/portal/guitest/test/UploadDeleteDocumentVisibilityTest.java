package portal.guitest.test;

import org.junit.Assert;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskWidgetPage;

public class UploadDeleteDocumentVisibilityTest extends BaseTest {

  private HomePage homePage;
  private AdminSettingsPage adminSettingsPage;
  private CasePage casePage;
  private CaseDetailsPage caseDetailsPage;
  private TaskWidgetPage taskWidgetPage;

  @Test
  public void testShowUploadDeleteDocumentWhenHasDocumentOfInvolvedCaseWritePemission() {
    createCaseAndUploadDocumentByUser(TestAccount.DEMO_USER);
    
    Assert.assertTrue(caseDetailsPage.isUploadDocumentButtonPresented());
    Assert.assertTrue(caseDetailsPage.isDeleteDocumentButtonPresented());
  }
  
  @Test
  public void testHideUploadDeleteDocumentWhenNotHasDocumentOfInvolvedCaseWritePemission() {
    createCaseAndUploadDocumentByUser(TestAccount.DEMO_USER);

    denyDocumentOfInvolvedCaseWritePemissionFromCurrentUser();
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    
    Assert.assertFalse(caseDetailsPage.isUploadDocumentButtonPresented());
    Assert.assertFalse(caseDetailsPage.isDeleteDocumentButtonPresented());
  }

  @Test
  public void testSettingHideUploadDeleteDocumentForDoneCase() {
    createCaseAndUploadDocumentByUser(TestAccount.ADMIN_USER);
    
    taskWidgetPage = caseDetailsPage.clickShowAllTasks();
    taskWidgetPage.startTaskWithoutUI(0);

    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setHideUploadDocumentForDoneCase();

    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    
    Assert.assertFalse(caseDetailsPage.isUploadDocumentButtonPresented());
    Assert.assertFalse(caseDetailsPage.isDeleteDocumentButtonPresented());
  }
  
  private void createCaseAndUploadDocumentByUser(TestAccount user) {
    createTestingCaseContainOneTaskByUser(user);
    uploadDocumentToTestingCaseByUser();
  }

  private void uploadDocumentToTestingCaseByUser() {
    grantDocumentOfInvolvedCaseWritePemissionToCurrentUser();
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
  }

  private void createTestingCaseContainOneTaskByUser(TestAccount user) {
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    initHomePage(user);
    navigateToUrl(createTestingCaseContainOneTask);
  }

  private void initHomePage(TestAccount account) {
    LoginPage loginPage = new LoginPage(account);
    loginPage.login();
    homePage = new HomePage();
  }

  private String getAbsolutePathToTestFile(String fileName) {
    return System.getProperty("user.dir") + "\\resources\\testFile\\" + fileName;
  }

}
