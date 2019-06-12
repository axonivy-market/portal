package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.ChatPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;

public class ChatTest extends BaseTest {
	private AdminSettingsPage adminSettingsPage;

	@Override
	@Before
	public void setup() {
		super.setup();
		LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
		loginPage.login();
		navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
	}

	@Test
	public void chatDisplay() {
		HomePage homePage = new HomePage();
		adminSettingsPage = homePage.openAdminSettings();
		adminSettingsPage.setChatGroup();
		homePage.openAdminSettings();
		adminSettingsPage.setChatPrivate();
		assertTrue("Chat Shown", homePage.isChatDisplayed());
	}
}
