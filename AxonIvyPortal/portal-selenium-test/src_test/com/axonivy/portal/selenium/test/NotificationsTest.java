package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest(headless = false)
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
    redirectToRelativeLink(BaseTest.PORTAL_HOME_PAGE_URL);
    int newBadge = homepage.getNotificationsBadge();
    assertEquals(newBadge - oldBadge >= 0, true);

    WebElement notficationsPanel = homepage.getNotificationsPanel();
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

}
