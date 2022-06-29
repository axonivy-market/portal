package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.DEFAULT_SORT_DIRECTION_OF_CASE_LIST;
import static portal.guitest.common.Variable.DEFAULT_SORT_DIRECTION_OF_TASK_LIST;
import static portal.guitest.common.Variable.DEFAULT_SORT_FIELD_OF_CASE_LIST;
import static portal.guitest.common.Variable.DEFAULT_SORT_FIELD_OF_TASK_LIST;
import static portal.guitest.common.Variable.SHOW_ENVIRONMENT_INFO;

import org.junit.Test;

import ch.ivy.addon.portalkit.enums.SortDirection;
import portal.guitest.common.BaseTest;
import portal.guitest.common.SystemProperties;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskWidgetPage;

public class AdminSettingsTest extends BaseTest {

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
    updatePortalSetting(SHOW_ENVIRONMENT_INFO.getKey(), "true");
		login(TestAccount.ADMIN_USER);
		HomePage homePage = new HomePage();
		//Customize environment info in portal example 
		redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_PROCESS_CHAIN);
		
		assertTrue(homePage.getEnviromentInfo().contains("Dev Team: Wawa, Env: Dev"));
	}

	@Test
	public void testDefaultSortOptionsForTaskList() {
    updatePortalSetting(DEFAULT_SORT_FIELD_OF_TASK_LIST.getKey(), "PRIORITY");
    updatePortalSetting(DEFAULT_SORT_DIRECTION_OF_TASK_LIST.getKey(), SortDirection.ASC.name());
    redirectToRelativeLink(cleanSessionCacheUrl);

    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals("high", taskWidgetPage.getPriorityOfTask(0));
    assertEquals("low", taskWidgetPage.getPriorityOfTask(taskWidgetPage.countTasks() - 1));
	}

	 @Test
	  public void testDefaultSortOptionsForCaseList() {
	   redirectToRelativeLink(create12CasesWithCategoryUrl); 
     updatePortalSetting(DEFAULT_SORT_FIELD_OF_CASE_LIST.getKey(), "NAME");
     updatePortalSetting(DEFAULT_SORT_DIRECTION_OF_CASE_LIST.getKey(), SortDirection.DESC.name());
     redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
	   TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
	   MainMenuPage mainMenuPage = taskWidgetPage.openMainMenu();
	   CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
	   assertEquals("TestCase", caseWidgetPage.getCaseNameAt(0));
	  }

    @Test
    public void testShowLegacyUISetting() {
      login(TestAccount.ADMIN_USER);
      HomePage homePage = new HomePage();
      AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
      adminSettingsPage.setShowLegacyUI();
      MainMenuPage mainMenuPage = homePage.openMainMenu();
      ProcessWidgetPage processWidgetPage = mainMenuPage.selectProcessesMenu();
      String currentView = processWidgetPage.getCurrentViewMode();
      assertTrue("COMPACT".equalsIgnoreCase(currentView));
      redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
      homePage = new HomePage();
      assertTrue(homePage.isDisplayed());

      adminSettingsPage = homePage.openAdminSettings();
      adminSettingsPage.resetShowLegacyUI();
      mainMenuPage = new MainMenuPage();
      processWidgetPage = mainMenuPage.selectProcessesMenu();
      currentView = processWidgetPage.getCurrentViewMode();
      assertTrue("IMAGE".equalsIgnoreCase(currentView));
      redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
      NewDashboardPage newDashboardPage = new NewDashboardPage();
      assertTrue(newDashboardPage.isDisplayed());

      adminSettingsPage = newDashboardPage.openAdminSettings();
      adminSettingsPage.setShowLegacyUI();
      redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
      homePage = new HomePage();
      assertTrue(homePage.isDisplayed());
      adminSettingsPage = homePage.openAdminSettings();
      adminSettingsPage.resetAllSettings();
      mainMenuPage = new MainMenuPage();
      processWidgetPage = mainMenuPage.selectProcessesMenu();
      currentView = processWidgetPage.getCurrentViewMode();
      assertTrue("IMAGE".equalsIgnoreCase(currentView));
      redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
      newDashboardPage = new NewDashboardPage();
      assertTrue(newDashboardPage.isDisplayed());
    }
}
