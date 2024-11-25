package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NotificationCompactPage;

@IvyWebTest
public class NotificationScreenshotTest extends ScreenshotBaseTest {
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
  }
  
  @Test
  public void screenshotNotification() throws IOException{
    NewDashboardPage homePage= new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    NotificationCompactPage notificationPanel = homePage.openNotificationPanel();
    
    ScreenshotUtils.resizeBrowser(new Dimension(1400, 700));
    notificationPanel.openNotificationMoreActionsMenu();
    
    ScreenshotUtils.executeDecorateJs("highlightNotificationIcon()");
    ScreenshotUtils.captureHalfRightPageScreenShot(ScreenshotUtils.NOTIFICATION_FOLDER + "notification-panel");

    ScreenshotUtils.resizeBrowser(new Dimension(1400, 1000));
    notificationPanel.openNotificationFullPage();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.NOTIFICATION_FOLDER + "notification-full-page");
  }
}
