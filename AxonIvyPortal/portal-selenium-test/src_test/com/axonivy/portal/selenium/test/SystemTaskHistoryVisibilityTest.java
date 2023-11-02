package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
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
    CaseWidgetPage caseWidgetPage = newDashboardPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName("Create New Payment");
    String caseUuid = caseDetailsPage.getCaseUuid();
    goToCaseNoteHistoryPage(caseUuid);
    NoteHistoryPage noteHistoryPage = new NoteHistoryPage();
    assertFalse(noteHistoryPage.getNoteAuthors().contains("System user"));

    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY.getKey(), "false");
    WaitHelper.assertTrueWithRefreshPage(() -> {
      goToCaseNoteHistoryPage(caseUuid);
      return new NoteHistoryPage().getNoteAuthors().contains("System user");
    });
  }

  @Test
  public void testSystemTaskVisibilityInCaseHistoryForAdmin() {
    login(TestAccount.ADMIN_USER);
    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR.getKey(), "false");
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    CaseWidgetPage caseWidgetPage = newDashboardPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCaseDetailsFromActionMenuByCaseName("Create New Payment");
    String caseUuid = caseDetailsPage.getCaseUuid();
    WaitHelper.assertTrueWithRefreshPage(() -> {
      goToCaseNoteHistoryPage(caseUuid);
      return new NoteHistoryPage().getNoteAuthors().contains("System user");
    });

    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR.getKey(), "true");
    goToCaseNoteHistoryPage(caseUuid);
    assertFalse(new NoteHistoryPage().getNoteAuthors().contains("System user"));
  }
}
