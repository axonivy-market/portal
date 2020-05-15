package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.SystemProperties;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.HomePage;

public class AdminSettingsTest extends BaseTest {

	private static final String SHOW_ENVIRONMENT_INFO_SETTING = "SHOW_ENVIRONMENT_INFO";

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
}
