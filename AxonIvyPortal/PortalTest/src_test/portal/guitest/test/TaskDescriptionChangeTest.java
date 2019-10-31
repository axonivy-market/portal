package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

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

  @Override
  protected void login(TestAccount testAccount) {
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
    testChangeTaskDescription("Hello World!", "Hello World!", taskWidgetPage);
    testChangeTaskDescription(
        "<b>HTML</b> description could contain malicious script <script>alert('Attacking')</script> but it will be sanitized.",
        "HTML description could contain malicious script but it will be sanitized.", taskWidgetPage);
    testChangeTaskDescription("", "No description", taskWidgetPage);
    testChangeTaskDescription("And you can change description if it is empty",
        "And you can change description if it is empty", taskWidgetPage);
  }

  private void testChangeTaskDescription(String newDescription, String shownDescriptionInDetails, TaskWidgetPage taskWidgetPage) {
    taskWidgetPage.changeDescriptionOfTask(newDescription);
    assertEquals(taskWidgetPage.getTaskDescription(), shownDescriptionInDetails);
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
