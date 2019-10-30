package portal.guitest.test;

import org.junit.Assert;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskWidgetPage;

public class UploadDeleteDocumentVisibilityTest extends BaseTest {

  private HomePage homePage;
  private AdminSettingsPage adminSettingsPage;
  private CaseWidgetPage casePage;
  private CaseDetailsPage caseDetailsPage;
  private TaskWidgetPage taskWidgetPage;

  @Test
  public void testShowUploadDeleteDocumentWhenHasDocumentOfInvolvedCaseWritePemission() {
    createCaseAndUploadDocumentByUser(TestAccount.DEMO_USER);
    
    Assert.assertTrue(caseDetailsPage.isAddDocumentLinkDisplayed());
    Assert.assertTrue(caseDetailsPage.isDeleteDocumentButtonPresented());
  }
  
  @Test
  public void testHideUploadDeleteDocumentWhenNotHasDocumentOfInvolvedCaseWritePemission() {
    createCaseAndUploadDocumentByUser(TestAccount.DEMO_USER);

    denyDocumentOfInvolvedCaseWritePemissionFromCurrentUser();
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    
    Assert.assertFalse(caseDetailsPage.isAddDocumentLinkDisplayed());
    Assert.assertFalse(caseDetailsPage.isDeleteDocumentButtonPresented());
  }

  @Test
  public void testSettingHideUploadDeleteDocumentForDoneCase() {
    createCaseAndUploadDocumentByUser(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTaskWithoutUI(0);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setHideUploadDocumentForDoneCase();

    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    
    Assert.assertFalse(caseDetailsPage.isAddDocumentLinkDisplayed());
    Assert.assertFalse(caseDetailsPage.isDeleteDocumentButtonPresented());
  }
  
  private void createCaseAndUploadDocumentByUser(TestAccount user) {
    createTestingCaseContainOneTaskByUser(user);
    uploadDocumentToTestingCaseByUser();
  }

  private void uploadDocumentToTestingCaseByUser() {
    grantDocumentOfInvolvedCaseWritePemissionToCurrentUser();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
  }

  private void createTestingCaseContainOneTaskByUser(TestAccount user) {
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    initHomePage(user);
    redirectToRelativeLink(createTestingCaseContainOneTask);
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
