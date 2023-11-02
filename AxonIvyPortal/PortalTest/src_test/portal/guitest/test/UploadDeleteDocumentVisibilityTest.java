package portal.guitest.test;

import static portal.guitest.common.Variable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE;

import org.junit.Assert;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.TaskWidgetPage;

public class UploadDeleteDocumentVisibilityTest extends BaseTest {

  private NewDashboardPage newDashboardPage;
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
    casePage = newDashboardPage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    
    Assert.assertFalse(caseDetailsPage.isAddDocumentLinkDisplayed());
    Assert.assertFalse(caseDetailsPage.isDeleteDocumentButtonPresented());
  }

  @Test
  public void testSettingHideUploadDeleteDocumentForDoneCase() {
    createCaseAndUploadDocumentByUser(TestAccount.ADMIN_USER);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.startTaskWithoutUI(0);
    taskWidgetPage.openTaskList();
    updatePortalSetting(HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE.getKey(), "true");
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);

    casePage = newDashboardPage.openCaseList();
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
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    casePage = newDashboardPage.openCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
  }

  private void createTestingCaseContainOneTaskByUser(TestAccount user) {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    initNewDashboardPage(user);
    redirectToRelativeLink(createTestingCaseContainOneTask);
  }

  private void initNewDashboardPage(TestAccount account) {
    login(account);
    newDashboardPage = new NewDashboardPage();
  }

  private String getAbsolutePathToTestFile(String fileName) {
    return System.getProperty("user.dir") + "\\resources\\testFile\\" + fileName;
  }

}
