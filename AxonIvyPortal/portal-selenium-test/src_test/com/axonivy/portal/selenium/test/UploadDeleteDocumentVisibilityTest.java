package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class UploadDeleteDocumentVisibilityTest extends BaseTest {

  private CaseWidgetPage casePage;
  private CaseDetailsPage caseDetailsPage;
  private TaskWidgetPage taskWidgetPage;

  @Test
  public void testShowUploadDeleteDocumentWhenHasDocumentOfInvolvedCaseWritePemission() {
    createCaseAndUploadDocumentByUser(TestAccount.DEMO_USER);

    assertTrue(caseDetailsPage.isAddDocumentLinkDisplayed(true));
    assertTrue(caseDetailsPage.isDeleteDocumentButtonPresented(true));
  }

  @Test
  public void testHideUploadDeleteDocumentWhenNotHasDocumentOfInvolvedCaseWritePemission() {
    createCaseAndUploadDocumentByUser(TestAccount.DEMO_USER);

    denyDocumentOfInvolvedCaseWritePemissionFromCurrentUser();
    casePage = NavigationHelper.navigateToCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");

    assertTrue(caseDetailsPage.isAddDocumentLinkDisplayed(false));
    assertTrue(caseDetailsPage.isDeleteDocumentButtonPresented(false));
  }

  @Test
  public void testSettingHideUploadDeleteDocumentForDoneCase() {
    createCaseAndUploadDocumentByUser(TestAccount.ADMIN_USER);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.startTaskWithoutUI(0);
    redirectToNewDashBoard();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    updatePortalSetting(Variable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE.getKey(), "true");
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);

    casePage = NavigationHelper.navigateToCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");

    assertTrue(caseDetailsPage.isAddDocumentLinkDisplayed(false));
    assertTrue(caseDetailsPage.isDeleteDocumentButtonPresented(false));
  }

  private void createCaseAndUploadDocumentByUser(TestAccount user) {
    createTestingCaseContainOneTaskByUser(user);
    uploadDocumentToTestingCaseByUser();
  }

  private void uploadDocumentToTestingCaseByUser() {
    grantDocumentOfInvolvedCaseWritePemissionToCurrentUser();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    casePage = NavigationHelper.navigateToCaseList();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("SupportTicket");
    caseDetailsPage.uploadDocumentWithoutError(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
  }

  private void createTestingCaseContainOneTaskByUser(TestAccount user) {
    launchBrowserAndGotoRelativeLink(cleanupDataLink);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    initNewDashboardPage(user);
    redirectToRelativeLink(createTestingCaseContainOneTask);
  }

  private void initNewDashboardPage(TestAccount account) {
    login(account);
    new NewDashboardPage();
  }

  private String getAbsolutePathToTestFile(String fileName) {
    return System.getProperty("user.dir") + "\\resources\\testFile\\" + fileName;
  }
}
