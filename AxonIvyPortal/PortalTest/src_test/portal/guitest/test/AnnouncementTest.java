package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.AnnouncementPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LanguagePage;

public class AnnouncementTest extends BaseTest {
  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    resetLanguageOfCurrentUser();
  }

  @Test
  public void notificationCanBeChangeLanguage() {
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

    homePage = new HomePage();
    languagePage = homePage.openLanguagePage();
    languagePage.selectLanguage(1);
    languagePage.save();
    assertEquals("Readme1", homePage.getAnnouncementMessage());

  }

  @Test
  public void validationDefaultLanguage() {
    HomePage homePage = new HomePage();

    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    announcementPage.publish();
    assertEquals("Announcement for application default language is required.", announcementPage.getInfoSummary());

  }

  @Test
  public void shouldNotification() {
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    assertTrue("Admin Settings dialog is displayed", announcementPage.isDisplayed());
    
    adminSettingsPage.openAnnouncementTab();
    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.publish();
    adminSettingsPage.closeAdminSettingDialog();
    adminSettingsPage.closeInformConfigDialog();
    
    assertEquals("Readme1", homePage.getAnnouncementMessage());
  }

  @Test
  public void depulishNotification() {
    HomePage homePage = new HomePage();
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
