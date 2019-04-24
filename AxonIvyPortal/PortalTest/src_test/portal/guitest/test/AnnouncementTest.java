package portal.guitest.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.AnnouncementPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LanguagePage;
import portal.guitest.page.LoginPage;

public class AnnouncementTest extends BaseTest {
  @Before
  public void setup() {
    // TODO Auto-generated method stub
    super.setup();
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    assertTrue("Admin Settings menu item is  displayed", homePage.isAdminSettingsMenuItemPresent());
    LanguagePage languagePage = homePage.openLanguagePage();

    languagePage.selectLanguage(1);;
    languagePage.save();
  }

  @Test
  public void notificationCanBeChangeLanguage() {
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    announcementPage.setAnnoucement(0, "lies mich");
    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.publish();
    adminSettingsPage.closeAdminSettingDialog();
    adminSettingsPage.closeInformConfigDialog();

    LanguagePage languagePage = homePage.openLanguagePage();
    languagePage.selectLanguage(0);
    languagePage.save();
    
    assertEquals("lies mich", homePage.getAnnouncementMessage());

  }

  @Test
  public void validationDefaultLanguage() {

    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    HomePage homePage = new HomePage();

    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    announcementPage.publish();
    assertEquals("TODO Announcement for application default language is required.", announcementPage.getInfoSummary());

  }

  @Test
  public void shouldNotification() {
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    assertTrue("Admin Settings menu item is not displayed", homePage.isAdminSettingsMenuItemPresent());
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    assertTrue("Admin Settings dialog is displayed", announcementPage.isDisplayed());
    adminSettingsPage.openAnnouncementTab();

    announcementPage.setAnnoucement(0, "Readme");
    announcementPage.setAnnoucement(1, "Readme1");

    announcementPage.setAnnoucement(3, "Readme3");
    announcementPage.publish();
    adminSettingsPage.closeAdminSettingDialog();
    adminSettingsPage.closeInformConfigDialog();
    assertEquals("Readme1", homePage.getAnnouncementMessage());
  }

  @Test
  public void depulishNotification() {
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    assertTrue("Admin Settings menu item is not displayed", homePage.isAdminSettingsMenuItemPresent());
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    assertTrue("Admin Settings dialog is displayed", announcementPage.isDisplayed());
    adminSettingsPage.openAnnouncementTab();

    announcementPage.setAnnoucement(0, "Readme");
    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.setAnnoucement(2, "Readme2");
    announcementPage.setAnnoucement(3, "Readme3");
    announcementPage.publish();
    announcementPage.dePublish();
    assertTrue("Announcement is not display ", homePage.isAnnouncementMessageNotDisplayed());
  }
}
