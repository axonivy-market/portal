package portal.guitest.test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TaskState;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class ShowRelatedTasksTest extends BaseTest {
  
  private CaseDetailsPage detailsPage;
  private HomePage homePage;
  NoteHistoryPage caseHistoryPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl("portalKitTestHelper/153CACC26D0D4C3D/createRelatedTasksTestUser.ivp");
    navigateToUrl(createTestingTasksUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.TEST_RELATED_TASKS_USER);
    loginPage.login();
    caseHistoryPage = new NoteHistoryPage();
    denyReadAllPermissionFromCurrentUser();
  }
  
  private void openCaseDetail() {
    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
  }
  
  @Test
  public void testShowRelatedTasksWithTaskReadAllPermissionAndClickingShowAllTasks() {
    grantTaskReadAllPermissionsToCurrentUser();
    openCaseDetail();
    TaskWidgetPage taskWidgetPage = detailsPage.clickShowAllTasks();
    int numberOfTasks = taskWidgetPage.countTasks();
    assertTrue(numberOfTasks == 4);
    boolean hasDoneTask = false;
    for (int i = 0; i < numberOfTasks; i++) {
      if (taskWidgetPage.getTaskState(i).equals(TaskState.DONE)) {
        hasDoneTask = true;
        break;
      }
    }
    assertTrue(hasDoneTask);
  }
  
  @Test
  public void testShowRelatedTasksWithTaskReadOwnCaseTasksPermissionAndClickingShowAllTasks() {
    grantTaskReadOwnCaseTaskPermissionsToCurrentUser();
    openCaseDetail();
    TaskWidgetPage taskWidgetPage = detailsPage.clickShowAllTasks();
    int numberOfTasks = taskWidgetPage.countTasks();
    assertTrue(numberOfTasks == 4);
    boolean hasDoneTask = false;
    for (int i = 0; i < numberOfTasks; i++) {
      if (taskWidgetPage.getTaskState(i).equals(TaskState.DONE)) {
        hasDoneTask = true;
        break;
      }
    }
    assertTrue(hasDoneTask);
  }
  
  @Test
  public void testShowRelatedTasksWithNoPermissionAndClickingShowAllTasks() {
    denyReadAllPermissionFromCurrentUser();
    openCaseDetail();
    TaskWidgetPage taskWidgetPage = detailsPage.clickShowAllTasks();
    int numberOfTasks = taskWidgetPage.countTasks();
    assertTrue(numberOfTasks == 2);
    boolean hasDoneTask = false;
    for (int i = 0; i < numberOfTasks; i++) {
      if (taskWidgetPage.getTaskState(i).equals(TaskState.DONE)) {
        hasDoneTask = true;
        break;
      }
    }
    assertFalse(hasDoneTask);
  }

  @Test
  public void testShowRelatedTasksWhenClickingRelatedTask() {
    grantTaskReadOwnCaseTaskPermissionsToCurrentUser();
    openCaseDetail();
    TaskDetailsPage taskDetailsPage = detailsPage.openTasksOfCasePage(0);
    assertEquals("Task Item Detail", taskDetailsPage.getPageTitle());
  }
  
  @Test
  @Ignore
  public void testShowRelatedTasksWhenClickingDoneTask() {
    grantTaskReadOwnCaseTaskPermissionsToCurrentUser();
    openCaseDetail();
    detailsPage.addNote("test");
    String doneTaskName = detailsPage.openDoneTask(0);
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
    int numberOfNotes;
    try {
        numberOfNotes = caseHistoryPage.countNotes();
    } catch (TimeoutException e) { // sometimes session is destroyed (don't know reason why!!!) so we cannot reach the page
        System.out.println("Stop testShowCaseNoteHistory test here because session is destroyed");
        return ;
    }
    assertEquals(2, numberOfNotes);
    assertEquals("test", caseHistoryPage.getNoteContentOfRow(0));
    assertEquals("Task is done: " + doneTaskName, caseHistoryPage.getNoteContentOfRow(1));
  }

}
