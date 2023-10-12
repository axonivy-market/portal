package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.DEFAULT_SORT_DIRECTION_OF_CASE_LIST;
import static portal.guitest.common.Variable.DEFAULT_SORT_DIRECTION_OF_TASK_LIST;
import static portal.guitest.common.Variable.DEFAULT_SORT_FIELD_OF_CASE_LIST;
import static portal.guitest.common.Variable.DEFAULT_SORT_FIELD_OF_TASK_LIST;
import static portal.guitest.common.Variable.GLOBAL_FOOTER_INFO;

import org.junit.Test;

import ch.ivy.addon.portalkit.enums.SortDirection;
import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.TaskWidgetPage;

public class AdminSettingsTest extends BaseTest {

  @Test
	public void whenLoginAsAdminThenAdminMenuItemDisplayed() {
		login(TestAccount.ADMIN_USER);
		NewDashboardPage newDashboardPage = new NewDashboardPage();
		assertTrue("Admin Settings menu item is not displayed", newDashboardPage.isAdminSettingsMenuItemPresent());
		AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
		assertTrue("Admin Settings dialog is not displayed", adminSettingsPage.isDisplayed());
	}

	@Test
	public void whenLoginAsNonAdminThenAdminMenuItemNotDisplayed() {
		NewDashboardPage newDashboardPage = new NewDashboardPage();
		assertFalse("Admin Settings menu item is displayed", newDashboardPage.isAdminSettingsMenuItemPresent());
	}

  @Test
  public void testDefaultEnvironmentInfo() {
    login(TestAccount.ADMIN_USER);
    NewDashboardPage homePage = new NewDashboardPage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setGlobalFooterInfo();
    assertTrue(homePage.getGlobalFooterInfo().contains("Wawa"));
  }

  @Test
  public void testCustomizedEnvironmentInfo() {
    updatePortalSetting(GLOBAL_FOOTER_INFO.getKey(), "Dev Team: Wawa, Env: Dev");
    login(TestAccount.ADMIN_USER);
    NewDashboardPage homePage = new NewDashboardPage();
    // Customize environment info in portal example
    redirectToRelativeLinkWithEmbedInFrame(NewDashboardPage.PORTAL_EXAMPLES_EMPLOYEE_SEARCH);

    assertTrue(homePage.getGlobalFooterInfo().contains("Wawa"));
  }

  @Test
  public void testDefaultSortOptionsForTaskList() {
    updatePortalSetting(DEFAULT_SORT_FIELD_OF_TASK_LIST.getKey(), "PRIORITY");
    updatePortalSetting(DEFAULT_SORT_DIRECTION_OF_TASK_LIST.getKey(), SortDirection.ASC.name());
    redirectToRelativeLink(cleanSessionCacheUrl);

    createTestingTasks();
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    assertEquals("high", taskWidgetPage.getPriorityOfTask(0));
    assertEquals("low", taskWidgetPage.getPriorityOfTask(taskWidgetPage.countTasks() - 1));
  }

	 @Test
	  public void testDefaultSortOptionsForCaseList() {
	   redirectToRelativeLink(create12CasesWithCategoryUrl); 
     updatePortalSetting(DEFAULT_SORT_FIELD_OF_CASE_LIST.getKey(), "NAME");
     updatePortalSetting(DEFAULT_SORT_DIRECTION_OF_CASE_LIST.getKey(), SortDirection.DESC.name());
     TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();

	   MainMenuPage mainMenuPage = taskWidgetPage.openMainMenu();
	   CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
	   assertEquals("TestCase", caseWidgetPage.getCaseNameAt(0));
	  }

}
