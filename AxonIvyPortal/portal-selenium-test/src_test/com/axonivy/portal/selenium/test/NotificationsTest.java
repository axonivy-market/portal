package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.SelenideElement;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class NotificationsTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testNotifications() {
    NewDashboardPage homepage = new NewDashboardPage();
    int oldBadge = homepage.getNotificationsBadge();
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    homepage = new NewDashboardPage();
    assertEquals(homepage.getNotificationsPanel().isDisplayed(), true);
    redirectToRelativeLink(BaseTest.PORTAL_HOME_PAGE_URL);
    int newBadge = homepage.getNotificationsBadge();
    assertEquals(newBadge - oldBadge >= 0, true);

    SelenideElement notficationsPanel = homepage.getNotificationsPanel();
    assertEquals(homepage.isOnlyUnreadDisplayed(notficationsPanel), true);
    assertEquals(homepage.isMarkAllAsReadDisplayed(notficationsPanel), true);
    assertEquals(homepage.isTodayGroupLineDisplayed(notficationsPanel), true);
    assertEquals(homepage.findNumberOfNotificationsItem(notficationsPanel) >= 12, true);
    // mark as read first notification
    homepage.markAsRead(notficationsPanel, newBadge - 1);
    assertEquals(homepage.getNotificationsBadge(), newBadge - 1);
    homepage.markAsAllRead(notficationsPanel);
    homepage.clickOnlyUnreadDisplayed(notficationsPanel);
    assertEquals(homepage.getNotificationsBadge(), 0);
    homepage.hideNotificationsPanel();
  }
  
  @Test
  public void testLinkToNotificationSetting() {
    NewDashboardPage homepage = new NewDashboardPage();
    homepage.getNotificationsPanel();
    homepage.clickNotificationSetting();
    homepage.waitForUserProfileDisplay();
  }
  
  @Test
  public void testLinkToNotificationFullPage() {
    NewDashboardPage homepage = new NewDashboardPage();
    homepage.getNotificationsPanel();
    homepage.clickNotificationFullPage();
    homepage.waitForNotificationFullpageDisplay();
  }

  @Test
  public void testDisableNotificationIcon() {
    login(TestAccount.ADMIN_USER);
	grantSpecificPortalPermission(PortalPermission.NOTIFICATION_CHANNELS_SETTING);
    NewDashboardPage homepage = new NewDashboardPage();
    UserProfilePage userProfilePage = homepage.openMyProfilePage();
    userProfilePage.unsubscribeAllChannels();
    userProfilePage.save();
    homepage.hideNotificationsIcon();
    homepage.openMyProfilePage();
    userProfilePage = new UserProfilePage();
    userProfilePage.subscribeAllChannels();
    userProfilePage.save();
    homepage.showNotificationsIcon();
  }
  
  @Test
  public void testDisableNotificationChannelsSettingCheckbox() {
	login(TestAccount.DEMO_USER);
	grantSpecificPortalPermission(PortalPermission.NOTIFICATION_CHANNELS_SETTING);
	NewDashboardPage homePage = new NewDashboardPage();
	UserProfilePage userProfilePage = homePage.openMyProfilePage();
	assertFalse(userProfilePage.isNoticationChannelsSettingCheckboxDisabled());
	userProfilePage.subscribeAllChannels();
	userProfilePage.save();

	denySpecificPortalPermission(PortalPermission.NOTIFICATION_CHANNELS_SETTING);
	homePage.openMyProfilePage();
	userProfilePage = new UserProfilePage();
	assertTrue(userProfilePage.isNoticationChannelsSettingCheckboxDisabled());
  }

}
