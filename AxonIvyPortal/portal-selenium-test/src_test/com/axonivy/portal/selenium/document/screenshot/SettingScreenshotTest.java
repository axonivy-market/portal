package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.AbsencePage;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.ChangePasswordPage;
import com.axonivy.portal.selenium.page.NewAbsencePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProjectVersionPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.axonivy.portal.selenium.util.ConfigurationJsonUtils;

@IvyWebTest
public class SettingScreenshotTest extends ScreenshotBaseTest {

  private static final LocalDate TODAY = LocalDate.now();
  private static final LocalDate TOMORROW = TODAY.plusDays(1);

  @Test
  public void screenshotAdminSettings() throws IOException {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);

    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    ScreenshotUtils.resizeBrowser(new Dimension(1366, 800));
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(),
        ScreenshotUtils.SETTINGS_FOLDER + "applications", new ScreenshotMargin(5, 20));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAddApplicationDialog(),
        ScreenshotUtils.SETTINGS_FOLDER + "add-application", new ScreenshotMargin(20));
    adminSettingsPage.closeAddApplicationDialog();

    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1000));
    adminSettingsPage.openSettingTab();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(),
        ScreenshotUtils.SETTINGS_FOLDER + "global-settings", new ScreenshotMargin(5, 20));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(adminSettingsPage.getEditSettingDialogOfFirstRow(),
        ScreenshotUtils.SETTINGS_FOLDER + "edit-global-settings", new ScreenshotMargin(20));
    adminSettingsPage.closeEditSettingDialog();
    refreshPage();

    adminSettingsPage.openAnnouncementTab();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(),
        ScreenshotUtils.SETTINGS_FOLDER + "announcement", new ScreenshotMargin(5, 20));

    showNewDashboard();
    homePage.waitForCaseWidgetLoaded();
    ChangePasswordPage changePasswordPage = homePage.openChangePasswordPage();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(changePasswordPage.getChangePasswordDialog(),
        ScreenshotUtils.SETTINGS_FOLDER + "change-password", new ScreenshotMargin(20));

    showNewDashboard();
    homePage.waitForCaseWidgetLoaded();
    ProjectVersionPage projectVersionPage = homePage.openProjectVersionPage();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(projectVersionPage.getProjectVersionDialog(),
        ScreenshotUtils.SETTINGS_FOLDER + "portal-version-information", new ScreenshotMargin(20));
  }

  @Test
  public void screenshotSettingWithAnnotation() throws IOException {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    homePage.openUserSettingMenu();

    ScreenshotUtils.resizeBrowser(new Dimension(1500, 800));
    ScreenshotUtils.executeDecorateJs("highlightUserName()");
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.SETTINGS_FOLDER + "user-settings",
        new Dimension(1100, 800));

    ScreenshotUtils.executeDecorateJs("clearHighlightUserName()");
    ScreenshotUtils.executeDecorateJs("highlightAdminSettings()");
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.SETTINGS_FOLDER + "select-admin-settings");
  }

  @Test
  public void screenshotMyProfile() throws IOException {
    login(TestAccount.ADMIN_USER);
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();

    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    userProfilePage.restoreDefaultNotificationSettings();
    ScreenshotUtils.resizeBrowser(new Dimension(1400, 1400));
    ScreenshotUtils.captureElementScreenshot(userProfilePage.getUserSettingCard(),
        ScreenshotUtils.MY_PROFILE_FOLDER + "my-profile");
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 900));
    userProfilePage.subscribeAllChannels();
    ScreenshotUtils.executeDecorateJs("highlightNotificationChannelSettings()");
    ScreenshotUtils
        .captureHalfRightPageScreenShot(ScreenshotUtils.MY_PROFILE_FOLDER + "notification-channels-settings");
  }

  @Test
  public void screenshotAbsence() throws IOException {
    login(TestAccount.HR_ROLE_USER);
    redirectToRelativeLink(cleanUpAbsencesAndSubstituesLink);

    ScreenshotUtils.resizeBrowser(new Dimension(1500, 1200));
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    AbsencePage absencePage = homePage.openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "Personal leave", absencePage);
    createAbsenceForCurrentUser(TOMORROW, TOMORROW, "Vacation", absencePage);
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.enterCommentForAbsence("Add new absence");

    ScreenshotUtils.captureElementWithMarginOptionScreenshot(absencePage.getAddAbsenceDialog(),
        ScreenshotUtils.SETTINGS_FOLDER + "new-absence", new ScreenshotMargin(20));
    newAbsencePage.closeAddAbsenceDialog();
    ScreenshotUtils.captureElementScreenshot(absencePage.getAbsenceForm(), ScreenshotUtils.SETTINGS_FOLDER + "absence");
    absencePage.setDeputy(Arrays.asList(TestAccount.DEMO_USER.getFullName(), TestAccount.GUEST_USER.getFullName()), 0);
    ScreenshotUtils.captureElementScreenshot(absencePage.getAbsenceForm(),
        ScreenshotUtils.SETTINGS_FOLDER + "set-deputy");
  }

  private void createAbsenceForCurrentUser(LocalDate from, LocalDate till, String comment, AbsencePage absencePage) {
    createAbsence("", from, till, comment, absencePage);
  }

  private void createAbsence(String fullname, LocalDate from, LocalDate till, String comment, AbsencePage absencePage) {
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(fullname, from, till, comment);
    newAbsencePage.proceed();
  }

  @Test
  public void screenshotUserMenuConfiguration() throws IOException {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    ConfigurationJsonUtils.updateJSONSetting("custom-user-menu.json", Variable.USER_MENU);
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 1000));
    showNewDashboard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();
    newDashboardPage.openUserSettingMenu();
    ScreenshotUtils.executeDecorateJs("highlightUserMenuConfiguration()");
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.SETTINGS_FOLDER + "user-menu-configuration");
  }

  @Test
  public void screenshotRoleManagement() throws IOException {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    ScreenshotUtils.resizeBrowser(new Dimension(1200, 1000));
    showNewDashboard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
    var roleManagementTab = adminSettingsPage.openRoleManagementTab();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.SETTINGS_FOLDER + "role-assignment-tab");
    ScreenshotUtils.maximizeBrowser();
    roleManagementTab.openRoleCreationDialog();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(roleManagementTab.getRoleCreationDialog(),
        ScreenshotUtils.SETTINGS_FOLDER + "role-assignment-creation-dialog", new ScreenshotMargin(10, 10));
    roleManagementTab.clickOnCancelLinkOfRoleDialog();
  }

  @Test
  public void screenshotPasswordValidation() throws IOException {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    ScreenshotUtils.resizeBrowser(new Dimension(1050, 750));
    showNewDashboard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    var adminSettingsPage = newDashboardPage.openAdminSettings();
    adminSettingsPage.openPasswordValidationTab();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.SETTINGS_FOLDER + "password-validation-tab");
  }

  @Test
  public void screenshotDashboardConfiguration() throws IOException {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 1000));
    showNewDashboard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();
    newDashboardPage.openUserSettingMenu();
    ScreenshotUtils.executeDecorateJs("highlightDashboardConfiguration()");
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.SETTINGS_FOLDER + "dashboard-configuration");
  }
}
