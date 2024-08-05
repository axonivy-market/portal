package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
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
    CaseWidgetPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName("Create New Payment");
    String caseId = caseDetailsPage.getCaseId();
    assertFalse(noteAuthorsContainsUser(caseId));

    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY.getKey(), "false");
    assertTrue(noteAuthorsContainsUser(caseId));
  }

  @Test
  public void testSystemTaskVisibilityInCaseHistoryForAdmin() {
    login(TestAccount.ADMIN_USER);
    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR.getKey(), "false");
    CaseWidgetPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName("Create New Payment");
    String caseId = caseDetailsPage.getCaseId();
    assertTrue(noteAuthorsContainsUser(caseId));

    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR.getKey(), "true");
    goToCaseNoteHistoryPage(caseId);
    assertFalse(noteAuthorsContainsUser(caseId));
  }

  private boolean noteAuthorsContainsUser(String caseId) {
    goToCaseNoteHistoryPage(caseId);
    NoteHistoryPage noteHistoryPage = new NoteHistoryPage();
    return noteHistoryPage.getNoteAuthors().contains("System user");

  }
}
