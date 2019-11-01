package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.HomePage;

public class AdminSettingsTest extends BaseTest {

  @Test
  public void whenLoginAsAdminThenAdminMenuItemDisplayed() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    assertTrue("Admin Settings menu item is not displayed", homePage.isAdminSettingsMenuItemPresent());
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    assertTrue("Admin Settings dialog is not displayed", adminSettingsPage.isDisplayed());
  }

  @Test
  public void whenLoginAsNonAdminThenAdminMenuItemNotDisplayed() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    assertFalse("Admin Settings menu item is displayed", homePage.isAdminSettingsMenuItemPresent());
  }
  
  @Test
  @Ignore
  public void shouldChangeColor() {
    String expectedMainColor = "abcdef";
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    String originalMainColor = homePage.getMainColor();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openDesignTab();
    adminSettingsPage.chooseMainColor(expectedMainColor);
    homePage = adminSettingsPage.applyNewColor();
    
    
    assertEquals(expectedMainColor, homePage.getMainColor());
    
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openDesignTab();
    adminSettingsPage.chooseMainColor(originalMainColor);
    adminSettingsPage.applyNewColor();
  }
}
