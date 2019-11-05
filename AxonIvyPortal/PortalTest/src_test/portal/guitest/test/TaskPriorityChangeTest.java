package portal.guitest.test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskPriorityChangeTest extends BaseTest {
	
	@Override
  @Before
	public void setup() {
		super.setup();
		createTestingTasks();
		redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
	}

	@Test
	public void testChangeTaskPriority() {
		login(TestAccount.ADMIN_USER);
		int firstTask = 0;
		int priorityIntValue = 2;
		String priorityStringValue = "NORMAL"; 
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.expand();
		TaskDetailsPage taskDetailsPage = taskWidgetPage.openTaskDetails(firstTask);
		taskDetailsPage.changePriorityOfTask(priorityIntValue);
		assertTrue(priorityStringValue.equals(taskDetailsPage.getPriorityOfTask()));
	}
	
	@Test
	public void testUserWithoutPermissionCannotChangeTaskPriority() {
		int firstTask = 0;
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.expand();
		taskWidgetPage.openTaskDetails(firstTask);
		assertFalse(taskWidgetPage.isTaskPriorityChangeComponentPresented(firstTask));
	}
	
}
