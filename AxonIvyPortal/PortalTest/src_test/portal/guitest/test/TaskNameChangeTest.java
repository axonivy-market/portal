package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskNameChangeTest extends BaseTest {
	
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
	public void testChangeTaskName() {
		login(TestAccount.ADMIN_USER);
		int firstTask = 0;
		String newTaskName = "New Task Name";
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.expand();
		taskWidgetPage.openTaskDetails(firstTask);
		taskWidgetPage.changeNameOfTask(firstTask, newTaskName);
    assertEquals(taskWidgetPage.getNameOfTaskWhenDisplayingDetailsAt(firstTask), newTaskName);
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
