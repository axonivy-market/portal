package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static portal.guitest.common.Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY;
import static portal.guitest.common.Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.NoteHistoryPage;

public class SystemTaskHistoryVisibilityTest extends BaseTest {
  
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(complexPaymentUrl);
  }

  @Test
  public void testSystemTaskVisibilityInCaseHistory() {
    updatePortalSetting(HIDE_SYSTEM_TASKS_FROM_HISTORY.getKey(), "true");
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    CaseWidgetPage caseWidgetPage = newDashboardPage2.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName("Create New Payment");
    String caseId = caseDetailsPage.getCaseId();
    goToCaseNoteHistoryPage(caseId);
    NoteHistoryPage noteHistoryPage = new NoteHistoryPage();
    assertFalse(noteHistoryPage.getNoteAuthors().contains("System user"));
    
    updatePortalSetting(HIDE_SYSTEM_TASKS_FROM_HISTORY.getKey(), "false");
    WaitHelper.assertTrueWithRefreshPage(newDashboardPage2, () -> {
      goToCaseNoteHistoryPage(caseId);
      return new NoteHistoryPage().getNoteAuthors().contains("System user");
    });
  }
  
  @Test
  public void testSystemTaskVisibilityInCaseHistoryForAdmin() {
    login(TestAccount.ADMIN_USER);
    updatePortalSetting(HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR.getKey(), "false");
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    CaseWidgetPage caseWidgetPage = newDashboardPage2.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName("Create New Payment");
    String caseId = caseDetailsPage.getCaseId();
    WaitHelper.assertTrueWithRefreshPage(newDashboardPage2, () -> {
      goToCaseNoteHistoryPage(caseId);
      return new NoteHistoryPage().getNoteAuthors().contains("System user");
    });
    
    updatePortalSetting(HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR.getKey(), "true");
    goToCaseNoteHistoryPage(caseId);
    assertFalse(new NoteHistoryPage().getNoteAuthors().contains("System user"));
  }
}
