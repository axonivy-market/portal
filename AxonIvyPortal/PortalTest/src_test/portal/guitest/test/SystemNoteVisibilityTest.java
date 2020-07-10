package portal.guitest.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class SystemNoteVisibilityTest extends BaseTest {

  private static final String CREATE_TESTING_TASK_URL = "internalSupport/14B2FC03D2E87141/processWithSystemNote.ivp";
  private static final String SYSTEM_USER_NAME = "System user";

  @Override
  @Before
  public void setup() {
    super.setup();
    //hide all system task in history to avoid effecting to test assertion
    updatePortalSetting("HIDE_SYSTEM_TASKS_FROM_HISTORY", "true");
    updatePortalSetting("HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR", "true");
    redirectToRelativeLink(CREATE_TESTING_TASK_URL);
  }

  @Test
  public void testSystemNoteVisibilityInCaseForAdmin() {
    updatePortalSetting("HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR", "false");
    login(TestAccount.ADMIN_USER);
    
    CaseDetailsPage caseDetailsPage = openCaseDetails();
    String caseId = caseDetailsPage.getCaseId();
    List<String> caseNoteAuthors = caseDetailsPage.getCaseNoteAuthors();
    Assert.assertTrue(caseNoteAuthors.contains(SYSTEM_USER_NAME));
    
    NoteHistoryPage caseNoteHistoryPage = openCaseNoteHistory(caseId);
    caseNoteAuthors = caseNoteHistoryPage.getNoteAuthors();
    Assert.assertTrue(caseNoteAuthors.contains(SYSTEM_USER_NAME));
    
    updatePortalSetting("HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR", "true");
    caseDetailsPage = openCaseDetails();
    caseNoteAuthors = caseDetailsPage.getCaseNoteAuthors();
    Assert.assertFalse(caseNoteAuthors.contains(SYSTEM_USER_NAME));
    
    caseNoteHistoryPage = openCaseNoteHistory(caseId);
    caseNoteAuthors = caseNoteHistoryPage.getNoteAuthors();
    Assert.assertFalse(caseNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  @Test
  public void testSystemNoteVisibilityInCaseForNormalUser() {
    updatePortalSetting("HIDE_SYSTEM_NOTES_FROM_HISTORY", "true");
    CaseDetailsPage caseDetailsPage = openCaseDetails();
    String caseId = caseDetailsPage.getCaseId();
    List<String> caseNoteAuthors = caseDetailsPage.getCaseNoteAuthors();
    Assert.assertFalse(caseNoteAuthors.contains(SYSTEM_USER_NAME));
    
    NoteHistoryPage caseNoteHistoryPage = openCaseNoteHistory(caseId);
    caseNoteAuthors = caseNoteHistoryPage.getNoteAuthors();
    Assert.assertFalse(caseNoteAuthors.contains(SYSTEM_USER_NAME));
    
    updatePortalSetting("HIDE_SYSTEM_NOTES_FROM_HISTORY", "false");
    caseDetailsPage = openCaseDetails();
    caseNoteAuthors = caseDetailsPage.getCaseNoteAuthors();
    Assert.assertTrue(caseNoteAuthors.contains(SYSTEM_USER_NAME));
    
    caseNoteHistoryPage = openCaseNoteHistory(caseId);
    caseNoteAuthors = caseNoteHistoryPage.getNoteAuthors();
    Assert.assertTrue(caseNoteAuthors.contains(SYSTEM_USER_NAME));
  }
  
  @Test
  public void testSystemNoteVisibilityInTaskForAdmin() {
    updatePortalSetting("HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR", "false");
    login(TestAccount.ADMIN_USER);
    
    TaskDetailsPage taskDetailsPage = openTaskDetails();
    String taskId = taskDetailsPage.getTaskId();
    List<String> taskNoteAuthors = taskDetailsPage.getTaskNoteAuthors();
    Assert.assertTrue(taskNoteAuthors.contains(SYSTEM_USER_NAME));
    
    NoteHistoryPage taskNoteHistoryPage = openTaskNoteHistory(taskId);
    taskNoteAuthors = taskNoteHistoryPage.getNoteAuthors();
    Assert.assertTrue(taskNoteAuthors.contains(SYSTEM_USER_NAME));
    
    updatePortalSetting("HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR", "true");
    taskDetailsPage = openTaskDetails();
    taskNoteAuthors = taskDetailsPage.getTaskNoteAuthors();
    Assert.assertFalse(taskNoteAuthors.contains(SYSTEM_USER_NAME));
    
    taskNoteHistoryPage = openTaskNoteHistory(taskId);
    taskNoteAuthors = taskNoteHistoryPage.getNoteAuthors();
    Assert.assertFalse(taskNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  @Test
  public void testSystemNoteVisibilityInTaskDetailForNormalUser() {
    updatePortalSetting("HIDE_SYSTEM_NOTES_FROM_HISTORY", "true");
    TaskDetailsPage taskDetailsPage = openTaskDetails();
    String taskId = taskDetailsPage.getTaskId();
    List<String> taskNoteAuthors = taskDetailsPage.getTaskNoteAuthors();
    Assert.assertFalse(taskNoteAuthors.contains(SYSTEM_USER_NAME));
    
    NoteHistoryPage taskNoteHistoryPage = openTaskNoteHistory(taskId);
    taskNoteAuthors = taskNoteHistoryPage.getNoteAuthors();
    Assert.assertFalse(taskNoteAuthors.contains(SYSTEM_USER_NAME));
    
    updatePortalSetting("HIDE_SYSTEM_NOTES_FROM_HISTORY", "false");
    taskDetailsPage = openTaskDetails();
    taskNoteAuthors = taskDetailsPage.getTaskNoteAuthors();
    Assert.assertTrue(taskNoteAuthors.contains(SYSTEM_USER_NAME));
    
    taskNoteHistoryPage = openTaskNoteHistory(taskId);
    taskNoteAuthors = taskNoteHistoryPage.getNoteAuthors();
    Assert.assertTrue(taskNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  private TaskDetailsPage openTaskDetails() {
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    TaskWidgetPage taskWidget = mainMenuPage.selectTaskMenu();
    return taskWidget.openTaskDetails(0);
  }
  
  private CaseDetailsPage openCaseDetails() {
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    return casePage.openDetailsOfCaseHasName("Create note");
  }

  private NoteHistoryPage openCaseNoteHistory(String caseId) {
    goToCaseNoteHistoryPage(caseId);
    return new NoteHistoryPage();
  }
  
  private NoteHistoryPage openTaskNoteHistory(String taskId) {
    goToTaskNoteHistoryPage(taskId);
    return new NoteHistoryPage();
  }
  
}
