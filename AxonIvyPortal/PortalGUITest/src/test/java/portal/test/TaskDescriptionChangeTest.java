package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.TaskWidgetPage;

public class TaskDescriptionChangeTest extends BaseTest {
  
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
    String newDescription = "new Description";
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    taskWidgetPage.changeDescriptionOfTask(firstTask, newDescription);
    assertEquals(taskWidgetPage.getDescriptionOfTaskAt(firstTask), newDescription);
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
