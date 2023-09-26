package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.DEFAULT_SORT_DIRECTION_OF_CASE_LIST;
import static portal.guitest.common.Variable.DEFAULT_SORT_DIRECTION_OF_TASK_LIST;
import static portal.guitest.common.Variable.DEFAULT_SORT_FIELD_OF_CASE_LIST;
import static portal.guitest.common.Variable.DEFAULT_SORT_FIELD_OF_TASK_LIST;

import org.junit.Test;

import ch.ivy.addon.portalkit.enums.SortDirection;
import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.SystemProperties;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.TaskWidgetPage;

public class AdminSettingsTest extends BaseTest {

  @Test
	public void whenLoginAsAdminThenAdminMenuItemDisplayed() {
		login(TestAccount.ADMIN_USER);
		NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
		assertTrue("Admin Settings menu item is not displayed", newDashboardPage2.isAdminSettingsMenuItemPresent());
		AdminSettingsPage adminSettingsPage = newDashboardPage2.openAdminSettings();
		assertTrue("Admin Settings dialog is not displayed", adminSettingsPage.isDisplayed());
	}

	@Test
	public void whenLoginAsNonAdminThenAdminMenuItemNotDisplayed() {
		NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
		assertFalse("Admin Settings menu item is displayed", newDashboardPage2.isAdminSettingsMenuItemPresent());
	}

	@Test
	public void testDefaultEnvironmentInfo() {
    login(TestAccount.ADMIN_USER);
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    AdminSettingsPage adminSettingsPage = newDashboardPage2.openAdminSettings();
    adminSettingsPage.setEnviromentInfo();
    assertTrue(newDashboardPage2.getEnviromentInfo().contains("Host: " + SystemProperties.getServerName()));
	}

	@Test
	public void testDefaultSortOptionsForTaskList() {
    updatePortalSetting(DEFAULT_SORT_FIELD_OF_TASK_LIST.getKey(), "PRIORITY");
    updatePortalSetting(DEFAULT_SORT_DIRECTION_OF_TASK_LIST.getKey(), SortDirection.ASC.name());
    redirectToRelativeLink(cleanSessionCacheUrl);

    createTestingTasks();
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTasList();
    assertEquals("high", taskWidgetPage.getPriorityOfTask(0));
    assertEquals("low", taskWidgetPage.getPriorityOfTask(taskWidgetPage.countTasks() - 1));
	}

	 @Test
	  public void testDefaultSortOptionsForCaseList() {
	   redirectToRelativeLink(create12CasesWithCategoryUrl); 
     updatePortalSetting(DEFAULT_SORT_FIELD_OF_CASE_LIST.getKey(), "NAME");
     updatePortalSetting(DEFAULT_SORT_DIRECTION_OF_CASE_LIST.getKey(), SortDirection.DESC.name());
     redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
     TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTasList();
	   MainMenuPage mainMenuPage = taskWidgetPage.openMainMenu();
	   CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
	   assertEquals("TestCase", caseWidgetPage.getCaseNameAt(0));
	  }

}
