package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.SystemProperties;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;

public class AdminSettingsTest extends BaseTest {

	private static final String SHOW_ENVIRONMENT_INFO_SETTING = "SHOW_ENVIRONMENT_INFO";
	private static final String DEFAULT_SORT_FIELD_OF_TASK_LIST = "DEFAULT_SORT_FIELD_OF_TASK_LIST";
	private static final String DEFAULT_SORT_DIRECTION_OF_TASK_LIST = "DEFAULT_SORT_DIRECTION_OF_TASK_LIST";
	private static final String DEFAULT_SORT_FIELD_OF_CASE_LIST = "DEFAULT_SORT_FIELD_OF_CASE_LIST";
  private static final String DEFAULT_SORT_DIRECTION_OF_CASE_LIST = "DEFAULT_SORT_DIRECTION_OF_CASE_LIST";

  @Test
	public void whenLoginAsAdminThenAdminMenuItemDisplayed() {
		login(TestAccount.ADMIN_USER);
		HomePage homePage = new HomePage();
		assertTrue("Admin Settings menu item is not displayed", homePage.isAdminSettingsMenuItemPresent());
		AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
		assertTrue("Admin Settings dialog is not displayed", adminSettingsPage.isDisplayed());
	}

	@Test
	public void whenLoginAsNonAdminThenAdminMenuItemNotDisplayed() {
		HomePage homePage = new HomePage();
		assertFalse("Admin Settings menu item is displayed", homePage.isAdminSettingsMenuItemPresent());
	}

	@Test
	public void testDefaultEnvironmentInfo() {
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setEnviromentInfo();
    assertTrue(homePage.getEnviromentInfo().contains("Host: " + SystemProperties.getServerName() + " Env:Default"));
	}
	
	@Test
	public void testCustomizedEnvironmentInfo() {
	  updatePortalSetting(SHOW_ENVIRONMENT_INFO_SETTING, "true");
		login(TestAccount.ADMIN_USER);
		HomePage homePage = new HomePage();
		//Customize environment info in portal example 
		redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_PROCESS_CHAIN);
		
		assertTrue(homePage.getEnviromentInfo().contains("Dev Team: Wawa, Env: Dev"));
	}

	@Test
	public void testDefaultSortOptionsForTaskList() {
    updatePortalSetting(DEFAULT_SORT_FIELD_OF_TASK_LIST, "PRIORITY");
    updatePortalSetting(DEFAULT_SORT_DIRECTION_OF_TASK_LIST, "ASCENDING");

    createTestingTasks();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals("high", taskWidgetPage.getPriorityOfTask(0));
    assertEquals("low", taskWidgetPage.getPriorityOfTask(taskWidgetPage.countTasks() - 1));
	}

	 @Test
	  public void testDefaultSortOptionsForCaseList() {
	   redirectToRelativeLink(create12CasesWithCategoryUrl); 
	   updatePortalSetting(DEFAULT_SORT_FIELD_OF_CASE_LIST, "NAME");
	   updatePortalSetting(DEFAULT_SORT_DIRECTION_OF_CASE_LIST, "DESCENDING");

	   TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
	   MainMenuPage mainMenuPage = taskWidgetPage.openMainMenu();
	   CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
	   assertEquals("TestCase", caseWidgetPage.getCaseNameAt(0));
	   assertEquals("Create 12 Cases with category", caseWidgetPage.getCaseNameAt(caseWidgetPage.countCases() - 1));
	  }
}
