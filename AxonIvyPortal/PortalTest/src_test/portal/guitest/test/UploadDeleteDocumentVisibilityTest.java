package portal.guitest.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;

public class UploadDeleteDocumentVisibilityTest extends BaseTest {

  private HomePage homePage;
  private AdminSettingsPage adminSettingsPage;
  private CasePage casePage;
  private CaseDetailsPage caseDetailsPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    createDoneCaseWithOneTaskOneDocument();
  }
  
  @Test
  public void testShowUploadDeleteDocumentWhenHasDocumentOfInvolvedCaseWritePemission() {
    initHomePage(TestAccount.DEMO_USER);
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    Assert.assertTrue(caseDetailsPage.isUploadDocumentButtonPresented());
    Assert.assertTrue(caseDetailsPage.isDeleteDocumentButtonPresented());
  }

  @Test
  public void testHdieUploadDeleteDocumentWhenNotHasDocumentOfInvolvedCaseWritePemission() {
    initHomePage(TestAccount.DEMO_USER);
    denyDocumentOfInvolvedCaseWritePemissionFromCurrentUser();
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    Assert.assertFalse(caseDetailsPage.isUploadDocumentButtonPresented());
    Assert.assertFalse(caseDetailsPage.isDeleteDocumentButtonPresented());
  }

  @Test
  public void testSettingHideUploadDeleteDocumentForDoneCase() {
    initHomePage(TestAccount.DEMO_USER);
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setHideUploadDocumentForDoneCase();
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    Assert.assertFalse(caseDetailsPage.isUploadDocumentButtonPresented());
    Assert.assertFalse(caseDetailsPage.isDeleteDocumentButtonPresented());
  }
  
  private void createDoneCaseWithOneTaskOneDocument() {
    navigateToUrl(createTestingCaseContainOneTask);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    initHomePage(TestAccount.DEMO_USER);
    casePage = homePage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    finishCase();
  }

  private void finishCase() {
    homePage.getTaskWidget().startTask(0);
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
