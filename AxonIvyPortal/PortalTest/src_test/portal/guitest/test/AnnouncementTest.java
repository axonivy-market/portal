package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.AnnouncementPage;
import portal.guitest.page.NewDashboardPage;
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
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    announcementPage.setAnnoucement(0, "lies mich");
    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.publish();
    adminSettingsPage.clickOnbackToNewDashboardPageOnAdminSetting();
    newDashboardPage = new NewDashboardPage();
    UserProfilePage userProfilePage = newDashboardPage.openMyProfilePage();
    userProfilePage.selectLanguage(3);
    userProfilePage.save();
    assertEquals("lies mich", newDashboardPage.getAnnouncementMessage());

    userProfilePage = newDashboardPage.openMyProfilePage();
    userProfilePage.selectLanguage(1);
    newDashboardPage = userProfilePage.save();
    assertEquals("Readme1", newDashboardPage.getAnnouncementMessage());
  }

  @Test
  public void testValidationForDefaultLanguage() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    announcementPage.publish();
    assertEquals("Announcement for application default language is required.", announcementPage.getInfoSummary());
  }

  @Test
  public void testShouldDisplayNotification() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisplayClearly();
    AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    assertTrue("Admin Settings dialog is displayed", announcementPage.isDisplayed());
    
    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.publish();
    adminSettingsPage.clickOnbackToNewDashboardPageOnAdminSetting();
    newDashboardPage = new NewDashboardPage();
    assertEquals("Readme1", newDashboardPage.getAnnouncementMessage());
  }

  @Test
  public void testDepulishNotification() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    assertTrue("Admin Settings dialog is not displayed", announcementPage.isDisplayed());

    announcementPage.setAnnoucement(0, "Readme");
    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.setAnnoucement(2, "Readme2");
    announcementPage.setAnnoucement(3, "Readme3");
    announcementPage.publish();
    announcementPage.dePublish();
    assertTrue("Announcement is displaying ", newDashboardPage.isAnnouncementMessageNotDisplayed());
  }
}
