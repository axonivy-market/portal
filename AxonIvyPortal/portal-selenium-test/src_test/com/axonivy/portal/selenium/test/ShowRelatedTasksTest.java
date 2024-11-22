package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;

@IvyWebTest
public class ShowRelatedTasksTest extends BaseTest {

  private static final String TASK_DETAILS_TITLE = "Task Details - Portal - Axon Ivy";
  private CaseDetailsPage detailsPage;
  private NewDashboardPage newDashboardPage;


  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink("portalKitTestHelper/153CACC26D0D4C3D/createRelatedTasksTestUser.ivp");
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.TEST_RELATED_TASKS_USER);
    denyReadAllPermissionFromCurrentUser();
  }

  private void openCaseDetail() {
    newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetNewDashBoardPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsCase("Leave Request");
  }

  @Test
  public void testRelatedTasksWithTaskReadAllPermission() {
    grantTaskReadAllPermissionsToCurrentUser();
    openCaseDetail();
    int numberOfTasks = detailsPage.countRelatedTasks().size();
    assertTrue(numberOfTasks == 4);
    assertTrue(detailsPage.hasDoneTask());
  }

  @Test
  public void testRelatedTasksWithTaskReadOwnCaseTasksPermission() {
    grantTaskReadOwnCaseTaskPermissionsToCurrentUser();
    openCaseDetail();
    int numberOfTasks = detailsPage.countRelatedTasks().size();
    assertTrue(numberOfTasks == 4);
    assertTrue(detailsPage.hasDoneTask());
  }

  @Test
  public void testRelatedTasksWithNoPermission() {
    denyReadAllPermissionFromCurrentUser();
    openCaseDetail();
    int numberOfTasks = detailsPage.countRelatedTasks().size();
    assertTrue(numberOfTasks == 2);
    assertFalse(detailsPage.hasDoneTask());
  }

  @Test
  public void testRelatedTasksWhenClickingRelatedTask() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    grantTaskReadOwnCaseTaskPermissionsToCurrentUser();
    openCaseDetail();
    detailsPage.waitPageLoaded();
    WaitHelper.waitForNavigation(() -> detailsPage.openTasksOfCasePage("Sick Leave Request"));
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    WaitHelper.assertTrueWithWait(() -> TASK_DETAILS_TITLE.equals(taskDetailsPage.getPageTitle()));
  }

  @Test
  public void testRelatedTasksWhenClickingDoneTask() {
    grantTaskReadOwnCaseTaskPermissionsToCurrentUser();
    openCaseDetail();
    resizeBrowserTo2kResolution();
    detailsPage.addNote("test");
    String doneTaskName = detailsPage.openDoneTask(0);
    WaitHelper.assertTrueWithWait(() -> newDashboardPage.countBrowserTab() > 1);
    newDashboardPage.switchLastBrowserTab();
    NoteHistoryPage caseHistoryPage = new NoteHistoryPage();
    int numberOfNotes;
    numberOfNotes = caseHistoryPage.countNotes();
    assertEquals(2, numberOfNotes);
    assertEquals("test", caseHistoryPage.getNoteContentOfRow(0));
    assertEquals("Task is done: " + doneTaskName, caseHistoryPage.getNoteContentOfRow(1));
  }
}
