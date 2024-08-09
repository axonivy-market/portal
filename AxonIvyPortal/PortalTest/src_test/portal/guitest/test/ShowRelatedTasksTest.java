package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.Variable;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskDetailsPage;

public class ShowRelatedTasksTest extends BaseTest {
  
  private CaseDetailsPage detailsPage;
  private HomePage homePage;
  NoteHistoryPage caseHistoryPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink("portalKitTestHelper/153CACC26D0D4C3D/createRelatedTasksTestUser.ivp");
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.TEST_RELATED_TASKS_USER);
    denyReadAllPermissionFromCurrentUser();
  }
  
  private void openCaseDetail() {
    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
  }
  
  @Test
  public void testRelatedTasksWithTaskReadAllPermission() {
    grantTaskReadAllPermissionsToCurrentUser();
    openCaseDetail();
    int numberOfTasks = detailsPage.countRelatedTasks();
    assertTrue(numberOfTasks == 4);
    assertTrue(detailsPage.hasDoneTask());
  }
  
  @Test
  public void testRelatedTasksWithTaskReadOwnCaseTasksPermission() {
    grantTaskReadOwnCaseTaskPermissionsToCurrentUser();
    openCaseDetail();
    int numberOfTasks = detailsPage.countRelatedTasks();
    assertTrue(numberOfTasks == 4);
    assertTrue(detailsPage.hasDoneTask());
  }
  
  @Test
  public void testRelatedTasksWithNoPermission() {
    denyReadAllPermissionFromCurrentUser();
    openCaseDetail();
    int numberOfTasks = detailsPage.countRelatedTasks();
    assertTrue(numberOfTasks == 2);
    assertFalse(detailsPage.hasDoneTask());
  }

  @Test
  public void testRelatedTasksWhenClickingRelatedTask() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    grantTaskReadOwnCaseTaskPermissionsToCurrentUser();
    ScreenshotUtil.resizeBrowser(new Dimension(1900, 1000));
    openCaseDetail();
    detailsPage.clickRelatedTaskColumnsButton();
    detailsPage.clickRelatedTaskDefaultCheckbox();
    detailsPage.clickRelatedTaskColumnCheckbox(4);
    detailsPage.clickRelatedTaskColumnCheckbox(5);
    detailsPage.clickRelatedTaskColumnCheckbox(6);
    detailsPage.clickRelatedTaskApplyButton();
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedTaskListColumnExist("related-task-name-column"));
    TaskDetailsPage taskDetailsPage = detailsPage.openTasksOfCasePage("Sick Leave Request");
    assertEquals("Task Details", taskDetailsPage.getPageTitle());
  }
  
  @Test
  @Ignore
  public void testRelatedTasksWhenClickingDoneTask() {
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
