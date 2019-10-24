package portal.guitest.test;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;

public class CaseDetailsTest extends BaseTest {

  private CaseDetailsPage detailsPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
  }

  @Test
  public void testDisplayCaseProperties() {
    assertTrue(StringUtils.equalsIgnoreCase("LeaveRequest", detailsPage.getCaseCategory()));
  }

  @Test
  public void testAddCaseNote() {
    detailsPage.onClickHistoryIcon();
    assertEquals(1, detailsPage.getNumberOfHistory());
    detailsPage.addNote("Consider the remaining annual leaves before the approval");
    assertEquals(2, detailsPage.getNumberOfHistory());
    assertEquals("Consider the remaining annual leaves before the approval", detailsPage.getLatestHistoryContent());
  }

  @Test
  public void testShowCaseDetail() {
    assertTrue(detailsPage.isGeneralInformationComponentPresented());
    assertTrue(detailsPage.isRelatedTasksComponentPresented());
    assertTrue(detailsPage.isHistoryComponentPresented());
    assertTrue(detailsPage.isDocumentComponentPresented());
  }

  @Test
  public void testHistoryAuthorIsUserFullName() {
    detailsPage.addNote("Sample case note");
    assertEquals(TestAccount.DEMO_USER.getFullName(), detailsPage.getHistoryAuthor());
  }

  @Test
  public void testOpenViewNoteDialog() {
    detailsPage.addNote("Consider the remaining annual leaves before the approval");
    detailsPage.clickViewNote();
    assertTrue(detailsPage.isViewNoteDialogPresented());
  }

  @After
  public void teardown() {
    denySpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }

}
