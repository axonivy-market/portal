package com.axonivy.portal.selenium.test.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.DashboardNotificationWidgetConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardNotificationWidgetPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

@IvyWebTest
public class DashboardNotificationWidgetTest extends BaseTest {

  private static final String NOTIFICATION_WIDGET_NAME = "My Notifications";
  private NewDashboardPage newDashboardPage;
  private DashboardNotificationWidgetPage notificationWidget;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testAddNotificationWidget() {
    login(TestAccount.ADMIN_USER);
    var configurationPage = LinkNavigator.navigateToPortalDashboardConfiguration();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    newDashboardDetailsEditPage.addWidget();
    DashboardNotificationWidgetConfigurationPage notificationWidgetConfiguration = newDashboardDetailsEditPage.addNotificationWidget();
    notificationWidgetConfiguration.changeWidgetTitle(NOTIFICATION_WIDGET_NAME);
    notificationWidgetConfiguration.changeFilter();
    notificationWidgetConfiguration.save();
    notificationWidget = newDashboardPage.selectNotificationWidget();
    assertEquals(notificationWidget.getWidgetName(), NOTIFICATION_WIDGET_NAME);
    ElementsCollection notifications = notificationWidget.getListNotifications();
    if (notifications.size() == 0) {
      redirectToRelativeLink(createTestingTasksUrl);
      redirectToRelativeLink(BaseTest.PORTAL_HOME_PAGE_URL);
      notifications = notificationWidget.getListNotifications();
    }
    notifications.shouldBe(CollectionCondition.sizeGreaterThan(0));
  }
}
