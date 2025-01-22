package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.SelenideElement;

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

    @SuppressWarnings("unused")
    SelenideElement notficationsPanel = homepage.getNotificationsPanel();
    assertEquals(homepage.isOnlyUnreadDisplayed(), true);
    assertEquals(homepage.isMarkAllAsReadDisplayed(), true);
    assertEquals(homepage.isTodayGroupLineDisplayed(), true);
    assertEquals(homepage.findNumberOfNotificationsItem() >= 12, true);
    // mark as read first notification
    homepage.markAsRead(newBadge - 1);
    assertEquals(homepage.getNotificationsBadge(), newBadge - 1);
    homepage.markAsAllRead();
    homepage.clickOnlyUnreadDisplayed();
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

}
