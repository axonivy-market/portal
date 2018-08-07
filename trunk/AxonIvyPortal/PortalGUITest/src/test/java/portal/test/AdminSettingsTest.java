package portal.test;

import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.AdminSettingsPage;
import portal.page.HomePage;
import portal.page.LoginPage;

public class AdminSettingsTest extends BaseTest {

  @Test
  public void whenLoginAsAdminThenAdminMenuItemDisplayed() {
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    assertTrue("Admin Settings menu item is not displayed", homePage.isAdminSettingsMenuItemPresent());
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    assertTrue("Admin Settings dialog is not displayed", adminSettingsPage.isDisplayed());
  }

  @Test
  public void whenLoginAsNonAdminThenAdminMenuItemNotDisplayed() {
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    assertFalse("Admin Settings menu item is displayed", homePage.isAdminSettingsMenuItemPresent());
  }
}
