package com.axonivy.portal.selenium.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;
import com.axonivy.portal.selenium.page.TaskIFrameTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class ShowCaseNoteHistoryTest extends BaseTest {

  private CaseDetailsPage detailsPage;
  private NewDashboardPage newDashboardPage;
  private MainMenuPage mainMenuPage;
  private NoteHistoryPage caseHistoryPage;
  private static final String NOTE_CONTENT = "test";
  private static final String CASE_NAME = "Leave Request";
  private static final String CASE_STATUS = "Open";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
  }

  @Test
  public void testShowCaseNoteHistory() {
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(CASE_NAME);
    resizeBrowserTo2kResolution();
    String caseId = detailsPage.getCaseId();
    String uuid = detailsPage.getCaseUuid();
    detailsPage.addNote(NOTE_CONTENT);
    goToCaseNoteHistoryPage(uuid);

    caseHistoryPage = new NoteHistoryPage();
    int numberOfNotes = caseHistoryPage.countNotes();
    assertEquals(2, numberOfNotes);
    assertEquals(NOTE_CONTENT, caseHistoryPage.getNoteContentOfRow(0));
    assertEquals(CASE_NAME, caseHistoryPage.getCaseName());
    assertEquals(caseId, caseHistoryPage.getCaseId());
    assertTrue(StringUtils.equalsIgnoreCase(CASE_STATUS, caseHistoryPage.getCaseState()));
  }

  @Test
  public void testShowCaseNoteHistoryInTask() {
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
    CaseWidgetPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    String caseUuid = caseDetailsPage.getCaseUuid();
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskIFrameTemplatePage taskTemplatePage = taskWidgetPage.startTaskIFrame(0);
    taskTemplatePage.openCaseInfo();
    taskTemplatePage.addNewNote(NOTE_CONTENT);
    String caseName = taskTemplatePage.getCaseName();
    goToCaseNoteHistoryPage(caseUuid);

    caseHistoryPage = new NoteHistoryPage();
    int numberOfNotes = caseHistoryPage.countNotes();
    assertEquals(2, numberOfNotes);
    assertEquals(NOTE_CONTENT, caseHistoryPage.getNoteContentOfRow(0));
    assertEquals(caseName, caseHistoryPage.getCaseName());
    assertTrue(StringUtils.equalsIgnoreCase(CASE_STATUS, caseHistoryPage.getCaseState()));
  }

}
