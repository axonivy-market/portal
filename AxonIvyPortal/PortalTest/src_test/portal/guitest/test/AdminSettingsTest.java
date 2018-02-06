package portal.guitest.test;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;

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
  
  @Test
  public void shouldChangeColor() {
    String expectedMainColor = "abcdef";
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openDesignTab();
    String mainColor = adminSettingsPage.getMainColor();
    adminSettingsPage.chooseMainColor(expectedMainColor);
    homePage = adminSettingsPage.applyNewColor();
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openDesignTab();
    assertEquals(expectedMainColor, adminSettingsPage.getMainColor());
    
    adminSettingsPage.chooseMainColor(mainColor);
    adminSettingsPage.applyNewColor();
  }
}
