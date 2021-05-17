package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import portal.guitest.common.BaseTest;
import portal.guitest.common.CaseState;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;

public class CaseDetailsTest extends BaseTest {

  private HomePage homePage;
  private CaseDetailsPage detailsPage;

  private static final String BUSINESS_CASE_MAP_LEAVE_REQUEST = "Business Case Map: Leave Request";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String ORDER_PIZZA = "Order Pizza";

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);

    homePage = new HomePage();
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(LEAVE_REQUEST_CASE_NAME);
  }
  
  @Test
  public void testDestroyCase() {
    detailsPage.onClickDestroyCase();
    detailsPage.confimDestruction();
    CaseWidgetPage casePage = new CaseWidgetPage();
    CaseState caseState = casePage.getCaseState(0);
    assertEquals(CaseState.DESTROYED, caseState);
  }

  @Test
  public void testDisplayCaseProperties() {
    assertTrue(StringUtils.equalsIgnoreCase("LeaveRequest", detailsPage.getCaseCategory()));
    assertTrue(StringUtils.isNotBlank(detailsPage.getCaseDuration()));
  }

  @Test
  public void testAddCaseNote() {
    assertEquals(1, detailsPage.getNumberOfHistory());
    detailsPage.addNote("Consider the remaining annual leaves before the approval");
    assertEquals(2, detailsPage.getNumberOfHistory());
    assertEquals("Consider the remaining annual leaves before the approval", detailsPage.getLatestHistoryContent());
  }

  @Test
  public void testShowCaseDetail() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(ORDER_PIZZA);
    assertTrue(detailsPage.isGeneralInformationComponentPresented());
    assertTrue(detailsPage.isRelatedCasesComponentPresented());
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
  public void testHistoryShowDoneTasks() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    login(TestAccount.DEMO_USER);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openCaseDetailsFromActionMenuByCaseName(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    assertTrue(detailsPage.checkDoneTasksOfHistory());

    int relatedDoneTasks = detailsPage.countRelatedDoneTasks();
    detailsPage.showNoteHistory();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> detailsPage.countBrowserTab() > 1);
    detailsPage.switchLastBrowserTab();
    NoteHistoryPage caseHistoryPage = new NoteHistoryPage();
    assertEquals(relatedDoneTasks, caseHistoryPage.countDoneTasks());
  }
  
  @Test
  public void testDragDropWidgets() {
    detailsPage.switchToEditMode();
    detailsPage.waitForSaveButtonDisplayed();
    detailsPage.drapAndDropWidgets("information", "document");
    detailsPage.drapAndDropWidgets("document", "information");
    detailsPage.saveAndSwitchToViewMode();
    detailsPage.switchToEditMode();
    detailsPage.waitForResetButtonDisplayed();
  }

  @After
  public void teardown() {
    denySpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }
}
