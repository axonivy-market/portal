package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;

public class NotificationsTest extends BaseTest {

  @Test
  public void testNotifications() {
    HomePage homepage = new HomePage();
    int oldBadge = homepage.getNotificationsBadge();
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    int newBadge = homepage.getNotificationsBadge();
    assertEquals(newBadge - oldBadge, 12);

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

}
