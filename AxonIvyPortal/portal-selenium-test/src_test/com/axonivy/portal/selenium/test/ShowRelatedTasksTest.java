package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;

@IvyWebTest
public class ShowRelatedTasksTest extends BaseTest {
  
  private CaseDetailsPage detailsPage;
  private NewDashboardPage newDashboardPage;
  NoteHistoryPage caseHistoryPage;

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
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
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
    WaitHelper.assertTrueWithWait(() -> "Task Details".equals(taskDetailsPage.getPageTitle()));
  }

}
