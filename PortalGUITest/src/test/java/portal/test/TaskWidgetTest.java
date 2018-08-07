package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.CasePage;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.TaskWidgetPage;

public class TaskWidgetTest extends BaseTest {

  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
  }

  @Test
  public void testShowHideTaskDetailOnExpandedMode() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();

    assertFalse(taskWidgetPage.isFilterDisplayed());
    taskWidgetPage.expand();
    assertTrue(taskWidgetPage.isFilterDisplayed());

    taskWidgetPage.openTaskDetails(0);
    assertTrue(taskWidgetPage.isTaskShowDetails(0));

    taskWidgetPage.closeTaskDetails(0);
    assertFalse(taskWidgetPage.isTaskShowDetails(0));
  }

  @Test
  public void testTasksInPortalHomePageUpdatedAfterExpandToFullMode() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks());

    taskWidgetPage.createTestingTasksInNewWindow();
    taskWidgetPage.expand();
    assertEquals(6, taskWidgetPage.countTasks());
  }

  @Test
  public void testOpenRelatedCaseOfTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(0);

    String relatedCaseId = taskWidgetPage.getRelatedCaseId();
    CasePage casePage = taskWidgetPage.openRelatedCaseOfTask(0);

    String selectingCaseId = casePage.getIdOfSelectingCase();
    assertEquals(relatedCaseId, selectingCaseId);
  }

}
