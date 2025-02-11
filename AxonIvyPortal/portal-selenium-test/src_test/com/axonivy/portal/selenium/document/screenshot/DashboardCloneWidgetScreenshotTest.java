package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class DashboardCloneWidgetScreenshotTest extends ScreenshotBaseTest {

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.ENABLE_GROUP_CHAT.getKey(), "true");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);

    redirectToRelativeLink(grantDashboardWritePublicPermissionUrl);
    redirectToRelativeLink(grantDashboardWriteOwnPermissionUrl);

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void screenshotCloneWidget() throws IOException {
    DashboardConfigurationPage configurationPage = newDashboardPage
        .openDashboardConfigurationPage();
    configurationPage.openEditPublicDashboardsPage();
    configurationPage
        .clickButtonOnDashboardConfigurationActionMenu("Configuration", 2);

    NewDashboardDetailsEditPage detailsEditPage = new NewDashboardDetailsEditPage();

    detailsEditPage.openWelcomeWidgetActionMenu("welcome_1");
    ScreenshotUtils.executeDecorateJs("highlightWelcomeWidgetCloneMenu()");
    ScreenshotUtils.captureElementScreenshot(
        detailsEditPage.getWelcomeWidgetActionMenu("welcome_1")
            .getWrappedElement(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER
            + "clone-widget-menu-option");

    ScreenshotUtils.executeDecorateJs(
        String.format("highlightCloneButtonByIndex(%d)", 7));
    detailsEditPage.getCloneButtonByIndex(7);

    newDashboardPage = new NewDashboardPage();

    ScreenshotUtils.captureElementScreenshot(
        newDashboardPage.getWidgetByName("Tasks By Priority").parent().parent()
            .getWrappedElement(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER + "clone-widget-button");

    detailsEditPage.openCloneWidgetDialog("Your Tasks", 0);
    detailsEditPage.chooseDashboardToClone("Dashboard");
    detailsEditPage.waitCloneButtonClickable();

    ScreenshotUtils.captureElementScreenshot(
        detailsEditPage.getCloneWidgetDialog().getWrappedElement(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER
            + "clone-widget-dialog");

    detailsEditPage.closeCloneWidgetDialog();
    detailsEditPage.addWidget();
    
    ScreenshotUtils.executeDecorateJs("highlightCloneFromButton()");
    ScreenshotUtils.captureElementScreenshot(
        detailsEditPage.getNewWidgetDialog().getWrappedElement(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER
            + "clone-widget-from-button");

    detailsEditPage.openCloneFromDialog();
    detailsEditPage.fillCloneWidgetDialog("Dashboard", "Your Tasks");
    detailsEditPage.waitCloneFromButtonClickable();

    ScreenshotUtils.captureElementScreenshot(
        detailsEditPage.getCloneWidgetFromDialog().getWrappedElement(),
        ScreenshotUtils.DASHBOARD_CONFIGURATION_FOLDER
            + "clone-widget-from-dialog");
  }
}
