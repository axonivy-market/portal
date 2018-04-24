package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskDescriptionChangeTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  private void login(TestAccount testAccount) {
    LoginPage loginPage = new LoginPage(testAccount);
    loginPage.login();
  }

  @Test
  public void testChangeTaskDescription() {
    login(TestAccount.ADMIN_USER);
    int firstTask = 0;
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    testChangeTaskDescription(firstTask, "Hello World!", "Hello World!", "Hello World!", taskWidgetPage);
    testChangeTaskDescription(
        firstTask,
        "<b>HTML</b> description could contain malicious script <script>alert('Attacking')</script> but it will be sanitized.",
        "HTML description could contain malicious script but it will be sanitized.",
        "HTML description could contain malicious script but it will be sanitized.", taskWidgetPage);
    testChangeTaskDescription(firstTask, "", "No description", "No description", taskWidgetPage);
    testChangeTaskDescription(firstTask, "And you can change description if it is empty",
        "And you can change description if it is empty", "And you can change description if it is empty",
        taskWidgetPage);
  }

  private void testChangeTaskDescription(int firstTask, String newDescription, String shownDescriptionInDetails,
      String shownDescriptionInHeader, TaskWidgetPage taskWidgetPage) {
    taskWidgetPage.changeDescriptionOfTask(firstTask, newDescription);
    assertEquals(taskWidgetPage.getDescriptionOfTaskAt(firstTask), shownDescriptionInDetails);
    assertEquals(taskWidgetPage.getDescriptionInHeaderOfTaskAt(firstTask), shownDescriptionInHeader);
  }

  @Test
  public void testUserWithoutPermissionCannotChangeTaskName() {
    login(TestAccount.DEMO_USER);
    int firstTask = 0;
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    assertFalse(taskWidgetPage.isTaskNameChangeComponentPresented(firstTask));
  }

}
