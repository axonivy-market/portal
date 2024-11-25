package com.axonivy.portal.selenium.test;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;

@IvyWebTest
public class SystemNoteVisibilityTest extends BaseTest {

  private static final String CREATE_TESTING_TASK_URL = "internalSupport/14B2FC03D2E87141/processWithSystemNote.ivp";
  private static final String SYSTEM_USER_NAME = "System user";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    // hide all system task in history to avoid effecting to test assertion
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY.getKey(), "true");
    updatePortalSetting(Variable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR.getKey(), "true");
    redirectToRelativeLink(CREATE_TESTING_TASK_URL);
  }

  @Test
  public void testSystemNoteVisibilityInCaseForAdmin() {
    login(TestAccount.ADMIN_USER);

    CaseDetailsPage caseDetailsPage = openCaseDetails();
    String caseId = caseDetailsPage.getCaseUuid();
    List<String> caseNoteAuthors = caseDetailsPage.getCaseNoteAuthors();
    assertTrue(caseNoteAuthors.contains(SYSTEM_USER_NAME));

    NoteHistoryPage caseNoteHistoryPage = openCaseNoteHistory(caseId);
    caseNoteAuthors = caseNoteHistoryPage.getNoteAuthors();
    assertTrue(caseNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  @Test
  public void testSystemNoteVisibilityInCaseForNormalUser() {
    login(TestAccount.DEMO_USER);
    CaseDetailsPage caseDetailsPage = openCaseDetails();
    String caseId = caseDetailsPage.getCaseUuid();
    List<String> caseNoteAuthors = caseDetailsPage.getCaseNoteAuthors();
    assertFalse(caseNoteAuthors.contains(SYSTEM_USER_NAME));

    NoteHistoryPage caseNoteHistoryPage = openCaseNoteHistory(caseId);
    caseNoteAuthors = caseNoteHistoryPage.getNoteAuthors();
    assertFalse(caseNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  @Test
  public void testSystemNoteVisibilityInTaskForAdmin() {
    updatePortalSetting(Variable.HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR.getKey(), "false");
    login(TestAccount.ADMIN_USER);

    TaskDetailsPage taskDetailsPage = openTaskDetails();
    taskDetailsPage.waitPageLoaded();
    List<String> taskNoteAuthors = taskDetailsPage.getTaskNoteHasAuthors();
    assertTrue(taskNoteAuthors.contains(SYSTEM_USER_NAME));
    String taskUuid = taskDetailsPage.getTaskUuid();
    NoteHistoryPage taskNoteHistoryPage = openTaskNoteHistory(taskUuid);
    taskNoteAuthors = taskNoteHistoryPage.getNoteAuthors();
    assertTrue(taskNoteAuthors.contains(SYSTEM_USER_NAME));

    updatePortalSetting(Variable.HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR.getKey(), "true");
    taskDetailsPage = openTaskDetails();
    taskNoteAuthors = taskDetailsPage.getTaskNoteAuthors();
    assertFalse(taskNoteAuthors.contains(SYSTEM_USER_NAME));

    taskUuid = taskDetailsPage.getTaskUuid();
    taskNoteHistoryPage = openTaskNoteHistory(taskUuid);
    taskNoteAuthors = taskNoteHistoryPage.getNoteAuthors();
    assertFalse(taskNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  @Test
  public void testSystemNoteVisibilityInTaskDetailForNormalUser() {
    login(TestAccount.DEMO_USER);
    updatePortalSetting(Variable.HIDE_SYSTEM_NOTES_FROM_HISTORY.getKey(), "true");
    TaskDetailsPage taskDetailsPage = openTaskDetails();
    List<String> taskNoteAuthors = taskDetailsPage.getTaskNoteAuthors();
    assertFalse(taskNoteAuthors.contains(SYSTEM_USER_NAME));
    String taskUuid = taskDetailsPage.getTaskUuid();
    NoteHistoryPage taskNoteHistoryPage = openTaskNoteHistory(taskUuid);
    taskNoteAuthors = taskNoteHistoryPage.getNoteAuthors();
    assertFalse(taskNoteAuthors.contains(SYSTEM_USER_NAME));

    updatePortalSetting(Variable.HIDE_SYSTEM_NOTES_FROM_HISTORY.getKey(), "false");
    taskDetailsPage = openTaskDetails();
    taskNoteHistoryPage.clickOnCheckboxShowSystemNotes();
    taskNoteHistoryPage.waitForNoteTableDisplayed();
    taskDetailsPage.waitPageLoaded();
    taskNoteAuthors = taskDetailsPage.getTaskNoteHasAuthors();
    assertTrue(taskNoteAuthors.contains(SYSTEM_USER_NAME));
    taskUuid = taskDetailsPage.getTaskUuid();
    taskNoteHistoryPage = openTaskNoteHistory(taskUuid);
    taskNoteAuthors = taskNoteHistoryPage.getNoteAuthors();
    assertTrue(taskNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  private TaskDetailsPage openTaskDetails() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    mainMenuPage.selectTaskMenu();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    return taskWidget.openTaskDetailsPageByAction(0);
  }

  private CaseDetailsPage openCaseDetails() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetNewDashBoardPage casePage = mainMenuPage.selectCaseMenu();
    return casePage.openDetailsCase("Create note");
  }

  private NoteHistoryPage openCaseNoteHistory(String uuid) {
    goToCaseNoteHistoryPage(uuid);
    return new NoteHistoryPage();
  }

  private NoteHistoryPage openTaskNoteHistory(String uuid) {
    goToTaskNoteHistoryPage(uuid);
    return new NoteHistoryPage();
  }

}
