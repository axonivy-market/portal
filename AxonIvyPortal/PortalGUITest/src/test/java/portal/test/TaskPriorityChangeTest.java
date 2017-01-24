package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.TaskWidgetPage;

public class TaskPriorityChangeTest extends BaseTest {
	
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
	public void testChangeTaskPriority() {
		login(TestAccount.ADMIN_USER);
		int firstTask = 0;
		int priorityIntValue = 2;
		String priorityStringValue = "NORMAL"; 
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.expand();
		taskWidgetPage.openTaskDetails(firstTask);
		taskWidgetPage.changePriorityOfTask(firstTask, priorityIntValue);
		assertTrue(priorityStringValue.equals(taskWidgetPage.getPriorityOfTaskAt(0)));
	}
	
	@Test
	public void testUserWithoutPermissionCannotChangeTaskPriority() {
		login(TestAccount.DEMO_USER);
		int firstTask = 0;
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.expand();
		taskWidgetPage.openTaskDetails(firstTask);
		assertFalse(taskWidgetPage.isTaskPriorityChangeComponentPresented(firstTask));
	}
	
}
