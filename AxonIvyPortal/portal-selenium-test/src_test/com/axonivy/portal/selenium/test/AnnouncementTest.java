package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.AnnouncementPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.UserProfilePage;

@IvyWebTest
public class AnnouncementTest extends BaseTest {
  @Override
  @BeforeEach
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
    announcementPage.clickOnSwitchButton();
    announcementPage.clickOnSaveButton();
    adminSettingsPage.clickOnHomeLogo();
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
    announcementPage.clickOnSwitchButton();
    announcementPage.clickOnSaveButton();
    assertEquals("Announcement for application default language is required.", announcementPage.getInfoSummary());
  }

  @Test
  public void testShouldDisplayNotification() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisplayClearly();
    AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    assertTrue(announcementPage.isDisplayed());

    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.clickOnSwitchButton();
    announcementPage.clickOnSaveButton();
    adminSettingsPage.clickOnHomeLogo();
    newDashboardPage = new NewDashboardPage();
    assertEquals("Readme1", newDashboardPage.getAnnouncementMessage());
  }

  @Test
  public void testDepublishNotification() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
    AnnouncementPage announcementPage = adminSettingsPage.openAnnouncementTab();
    assertTrue(announcementPage.isDisplayed());

    announcementPage.setAnnoucement(0, "Readme");
    announcementPage.setAnnoucement(1, "Readme1");
    announcementPage.setAnnoucement(2, "Readme2");
    announcementPage.setAnnoucement(3, "Readme3");
    announcementPage.clickOnSwitchButton();
    announcementPage.clickOnSwitchButton();
    announcementPage.clickOnSaveButton();
    assertTrue(newDashboardPage.isAnnouncementMessageNotDisplayed());
  }
}
