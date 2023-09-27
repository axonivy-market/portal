package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class ShowCaseNoteHistoryTest extends BaseTest {

  private CaseDetailsPage detailsPage;
  private NewDashboardPage2 newDashboardPage2;
  private MainMenuPage mainMenuPage;
  private NoteHistoryPage caseHistoryPage;
  private static final String NOTE_CONTENT = "test";
  private static final String CASE_NAME = "Leave Request";
  private static final String CASE_STATUS = "Open";

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    newDashboardPage2 = new NewDashboardPage2();
    mainMenuPage = newDashboardPage2.openMainMenu();
  }

  @Test
  public void testShowCaseNoteHistory() {
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(CASE_NAME);
    String caseName = detailsPage.getCaseName();
    String caseId = detailsPage.getCaseId();
    String uuid = detailsPage.getCaseUuid();
    detailsPage.addNote(NOTE_CONTENT);
    goToCaseNoteHistoryPage(uuid);

    caseHistoryPage = new NoteHistoryPage();
    int numberOfNotes = caseHistoryPage.countNotes();
    assertEquals(2, numberOfNotes);
    assertEquals(NOTE_CONTENT, caseHistoryPage.getNoteContentOfRow(0));
    assertEquals(caseName, caseHistoryPage.getCaseName());
    assertEquals(caseId, caseHistoryPage.getCaseId());
    assertTrue(StringUtils.equalsIgnoreCase(CASE_STATUS, caseHistoryPage.getCaseState()));
  }

  @Test
  public void testShowCaseNoteHistoryInTask() {
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
    CaseWidgetPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    String caseUuid = caseDetailsPage.getCaseUuid();
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTasList();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.openCaseInfo();
    taskTemplatePage.addNewNote(NOTE_CONTENT);
    String caseName = taskTemplatePage.getCaseName();
    getBrowser().getDriver().switchTo().defaultContent();
    goToCaseNoteHistoryPage(caseUuid);

    caseHistoryPage = new NoteHistoryPage();
    int numberOfNotes = caseHistoryPage.countNotes();
    assertEquals(2, numberOfNotes);
    assertEquals(NOTE_CONTENT, caseHistoryPage.getNoteContentOfRow(0));
    assertEquals(caseName, caseHistoryPage.getCaseName());
    assertTrue(StringUtils.equalsIgnoreCase(CASE_STATUS, caseHistoryPage.getCaseState()));
  }

}
