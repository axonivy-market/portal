package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;

@IvyWebTest
public class SystemTaskHistoryVisibilityTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(complexPaymentUrl);
  }

  @Test
  public void testSystemTaskVisibilityInCaseHistory() {
    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY.getKey(), "true");
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    CaseWidgetNewDashBoardPage caseWidgetPage = newDashboardPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase("Create New Payment");
    String caseUuid = caseDetailsPage.getCaseUuid();
    assertFalse(noteAuthorsContainsUser(caseUuid));

    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY.getKey(), "false");
    assertTrue(noteAuthorsContainsUser(caseUuid));
  }

  @Test
  public void testSystemTaskVisibilityInCaseHistoryForAdmin() {
    login(TestAccount.ADMIN_USER);
    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR.getKey(), "false");
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    CaseWidgetNewDashBoardPage caseWidgetPage = newDashboardPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase("Create New Payment");
    String caseUuid = caseDetailsPage.getCaseUuid();
    assertTrue(noteAuthorsContainsUser(caseUuid));

    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR.getKey(), "true");
    goToCaseNoteHistoryPage(caseUuid);
    assertFalse(noteAuthorsContainsUser(caseUuid));
  }

  private boolean noteAuthorsContainsUser(String caseUuid) {
    goToCaseNoteHistoryPage(caseUuid);
    NoteHistoryPage noteHistoryPage = new NoteHistoryPage();
    return noteHistoryPage.getNoteAuthors().contains("System user");

  }
}
