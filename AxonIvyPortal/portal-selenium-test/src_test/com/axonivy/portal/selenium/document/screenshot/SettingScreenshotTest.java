package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.ChangePasswordPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProjectVersionPage;

@IvyWebTest
public class SettingScreenshotTest extends ScreenshotBaseTest {

  @Test
  public void screenshotAdminSettings() throws IOException {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);

    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    ScreenshotUtil.resizeBrowser(new Dimension(1366, 800));
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(), ScreenshotUtil.SETTINGS_FOLDER + "applications", new ScreenshotMargin(5, 20));
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAddApplicationDialog(), ScreenshotUtil.SETTINGS_FOLDER + "add-application", new ScreenshotMargin(20));
    adminSettingsPage.closeAddApplicationDialog();

    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1000));
    adminSettingsPage.openSettingTab();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(), ScreenshotUtil.SETTINGS_FOLDER + "global-settings", new ScreenshotMargin(5, 20));
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getEditSettingDialogOfFirstRow(), ScreenshotUtil.SETTINGS_FOLDER + "edit-global-settings", new ScreenshotMargin(20));
    adminSettingsPage.closeEditSettingDialog();

    adminSettingsPage.openAnnouncementTab();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(), ScreenshotUtil.SETTINGS_FOLDER + "announcement", new ScreenshotMargin(5, 20));

    showNewDashboard();
    homePage.waitForCaseWidgetLoaded();
    ChangePasswordPage changePasswordPage = homePage.openChangePasswordPage();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(changePasswordPage.getChangePasswordDialog(), ScreenshotUtil.SETTINGS_FOLDER + "change-password", new ScreenshotMargin(20));

    showNewDashboard();
    homePage.waitForCaseWidgetLoaded();
    ProjectVersionPage projectVersionPage = homePage.openProjectVersionPage();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(projectVersionPage.getProjectVersionDialog(), ScreenshotUtil.SETTINGS_FOLDER + "portal-version-information", new ScreenshotMargin(20));
  }
}
