package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import portal.guitest.common.BaseTest;
import portal.guitest.common.CaseState;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;

public class CaseDetailsTest extends BaseTest {
  
  private static final String CASE_DETAILS_TITLE = "Case Details";
  private static final String BETA_CASE_NAME = "Beta Company";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String CASE_LIST_TITLE = "Cases";

  private CaseDetailsPage detailsPage;
  private HomePage homePage;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);

    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
  }

  @Test
  public void testDestroyCase() {
    detailsPage.onClickDestroyCase();
    detailsPage.confimDestruction();
    detailsPage.waitAjaxIndicatorDisappear();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    CaseState caseState = casePage.getCaseState(0);
    assertEquals(CaseState.DESTROYED, caseState);
  }
  
  @Test
  public void testDisplayCaseProperties() {
    assertTrue(StringUtils.equalsIgnoreCase("Leave Request", detailsPage.getCaseCategory()));
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
    assertEquals(TestAccount.ADMIN_USER.getFullName(), detailsPage.getHistoryAuthor());
  }

  @Test
  public void testOpenViewNoteDialog() {
    detailsPage.addNote("Consider the remaining annual leaves before the approval");
    detailsPage.clickViewNote();
    assertTrue(detailsPage.isViewNoteDialogPresented());
  }

  @Test
  public void testNavigateFromTechToBusinessCase() {
    assertEquals(CASE_DETAILS_TITLE, detailsPage.getPageTitle());
    Assert.assertTrue(detailsPage.isBackButtonDisplayed());
    CaseWidgetPage casePage = detailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, casePage.getPageTitle());
    Assert.assertTrue(casePage.isCaseDisplayed(LEAVE_REQUEST_CASE_NAME));
    
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    redirectToRelativeLink(createBetaCompanyUrl);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();

    detailsPage = casePage.openDetailsOfCaseHasName(BETA_CASE_NAME);
    assertEquals(CASE_DETAILS_TITLE, detailsPage.getPageTitle());

    Assert.assertTrue(detailsPage.isRelatedTasksComponentPresented());
    detailsPage = detailsPage.openRelatedCaseOfBusinessCase(0);
    assertEquals(CASE_DETAILS_TITLE, detailsPage.getPageTitle());
    String techCaseName = detailsPage.getCaseName();
    techCaseName = techCaseName + "-Technical Case";
    detailsPage.changeCaseName(techCaseName);

    Assert.assertTrue(detailsPage.isBackButtonDisplayed());
    detailsPage.clickBackButton();
    detailsPage = new CaseDetailsPage();
    assertEquals(CASE_DETAILS_TITLE, detailsPage.getPageTitle());
    assertEquals(BETA_CASE_NAME, detailsPage.getCaseName());

    Assert.assertTrue(detailsPage.isBackButtonDisplayed());
    CaseWidgetPage caseWidgetPage = detailsPage.goBackToCaseListFromCaseDetails();
    assertEquals(CASE_LIST_TITLE, caseWidgetPage.getPageTitle());
  }

  @After
  public void teardown() {
    denySpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }

}
