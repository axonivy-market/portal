package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.NoteHistoryPage;

public class SystemTaskHistoryVisibilityTest extends BaseTest {
  
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createBetaCompanyUrl);
  }

  @Test
  public void testSystemTaskVisibilityInCaseHistory() {
    updatePortalSetting("HIDE_SYSTEM_TASKS_FROM_HISTORY", "true");
    HomePage homePage = new HomePage();
    CaseWidgetPage caseWidgetPage = homePage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName("Beta Company");
    String caseId = caseDetailsPage.getCaseId();
    goToCaseNoteHistoryPage(caseId);
    NoteHistoryPage noteHistoryPage = new NoteHistoryPage();
    assertFalse(noteHistoryPage.getNoteAuthors().contains("System user"));
    
    updatePortalSetting("HIDE_SYSTEM_TASKS_FROM_HISTORY", "false");
    goToCaseNoteHistoryPage(caseId);
    noteHistoryPage = new NoteHistoryPage();
    assertTrue(noteHistoryPage.getNoteAuthors().contains("System user"));
  }
  
  @Test
  public void testSystemTaskVisibilityInCaseHistoryForAdmin() {
    login(TestAccount.ADMIN_USER);
    updatePortalSetting("HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR", "false");
    HomePage homePage = new HomePage();
    CaseWidgetPage caseWidgetPage = homePage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName("Beta Company");
    String caseId = caseDetailsPage.getCaseId();
    goToCaseNoteHistoryPage(caseId);
    NoteHistoryPage noteHistoryPage = new NoteHistoryPage();
    assertTrue(noteHistoryPage.getNoteAuthors().contains("System user"));
    
    updatePortalSetting("HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR", "true");
    goToCaseNoteHistoryPage(caseId);
    noteHistoryPage = new NoteHistoryPage();
    assertFalse(noteHistoryPage.getNoteAuthors().contains("System user"));
  }
}
