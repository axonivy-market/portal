package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.AnnouncementPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.UserProfilePage;

public class AnnouncementTest extends BaseTest {
  @Override
  @Before
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testNotificationLocalizationWhenChangingLanguageSetting() {
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    announcementPage.setAnnoucement(0, "lies mich");
    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.publish();
    adminSettingsPage.clickOnbackToHomepageOnAdminSetting();
    homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    userProfilePage.selectLanguage(3);
    userProfilePage.save();
    assertEquals("lies mich", homePage.getAnnouncementMessage());

    userProfilePage = homePage.openMyProfilePage();
    userProfilePage.selectLanguage(1);
    homePage = userProfilePage.save();
    assertEquals("Readme1", homePage.getAnnouncementMessage());
  }

  @Test
  public void testValidationForDefaultLanguage() {
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    announcementPage.publish();
    assertEquals("Announcement for application default language is required.", announcementPage.getInfoSummary());
  }

  @Test
  public void testShouldDisplayNotification() {
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    assertTrue("Admin Settings dialog is displayed", announcementPage.isDisplayed());
    
    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.publish();
    adminSettingsPage.clickOnbackToHomepageOnAdminSetting();
    homePage = new HomePage();
    assertEquals("Readme1", homePage.getAnnouncementMessage());
  }

  @Test
  public void testDepulishNotification() {
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    assertTrue("Admin Settings dialog is not displayed", announcementPage.isDisplayed());

    announcementPage.setAnnoucement(0, "Readme");
    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.setAnnoucement(2, "Readme2");
    announcementPage.setAnnoucement(3, "Readme3");
    announcementPage.publish();
    announcementPage.dePublish();
    assertTrue("Announcement is displaying ", homePage.isAnnouncementMessageNotDisplayed());
  }
}
