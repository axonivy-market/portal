package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskNameChangeTest extends BaseTest {

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
  public void testChangeTaskName() {
    login(TestAccount.ADMIN_USER);
    int firstTask = 0;
    String newTaskName = "New Task Name";
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    TaskDetailsPage taskDetailsPage = taskWidgetPage.openTaskDetails(firstTask);
    taskDetailsPage.changeNameOfTask(newTaskName);
    assertEquals(taskDetailsPage.getNameOfTaskWhenDisplayingDetailsAt(), newTaskName);
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
