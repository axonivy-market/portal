package portal.guitest.document.screenshot;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ConfigurationJsonUtil;
import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.Variable;
import portal.guitest.page.AbsencePage;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.ChangePasswordPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.NewAbsencePage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.ProjectVersionPage;
import portal.guitest.page.UserProfilePage;

public class SettingScreenshotTest extends ScreenshotTest {
  
  private HomePage homePage;
  private NewDashboardPage newDashboardPage;
  private static final LocalDate TODAY = LocalDate.now();
  private static final LocalDate TOMORROW = TODAY.plusDays(1);
  
  @Test
  public void screenshotAdminSettings() throws IOException {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    homePage = new HomePage();
    homePage.waitForStatisticRendered();
    
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 800));
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(), ScreenshotUtil.SETTINGS_FOLDER + "applications", new ScreenshotMargin(5, 20));
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAddApplicationDialog(), ScreenshotUtil.SETTINGS_FOLDER + "add-application", new ScreenshotMargin(20));

    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1000));
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openSettingTab();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(), ScreenshotUtil.SETTINGS_FOLDER + "global-settings", new ScreenshotMargin(5, 20));
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getEditSettingDialogOfFirstRow(), ScreenshotUtil.SETTINGS_FOLDER + "edit-global-settings", new ScreenshotMargin(20));

    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openAnnouncementTab();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingContainer(), ScreenshotUtil.SETTINGS_FOLDER + "announcement", new ScreenshotMargin(5, 20));

    ChangePasswordPage changePasswordPage = homePage.openChangePasswordPage();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(changePasswordPage.getChangePasswordDialog(), ScreenshotUtil.SETTINGS_FOLDER + "change-password", new ScreenshotMargin(20));

    ProjectVersionPage projectVersionPage = homePage.openProjectVersionPage();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(projectVersionPage.getProjectVersionDialog(), ScreenshotUtil.SETTINGS_FOLDER + "portal-version-information", new ScreenshotMargin(20));
  }

  @Test
  public void screenshotSettingWithAnnotation() throws IOException {
    login(TestAccount.ADMIN_USER);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.getUserSettings();
    executeDecorateJs("highlightAdminSettings()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.SETTINGS_FOLDER + "select-admin-settings");

    refreshPage();
    newDashboardPage.getUserSettings();
    executeDecorateJs("highlightUserName()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.SETTINGS_FOLDER + "user-settings", new Dimension(1100, 800));
  }
  
  @Test
  public void screenshotMyProfile() throws IOException {
    homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    ScreenshotUtil.resizeBrowser(new Dimension(1400, 1400));
    ScreenshotUtil.captureElementScreenshot(userProfilePage.getUserSettingCard(), ScreenshotUtil.MY_PROFILE_FOLDER + "my-profile");
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 900));
    userProfilePage.switchOnEmailOnTaskAssignmentSetting();
    userProfilePage.switchOnFurtherEmailFromAppSetting();
    executeDecorateJs("highlightEmailSettings()");
    ScreenshotUtil.captureHalfRightPageScreenShot(ScreenshotUtil.MY_PROFILE_FOLDER + "email-settings");
  }
  
  @Test
  public void screenshotAbsence() throws IOException {
    login(TestAccount.HR_ROLE_USER);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1200));
    redirectToRelativeLink(cleanUpAbsencesAndSubstituesLink);
    homePage = new HomePage();
    AbsencePage absencePage = homePage.openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "Personal leave", absencePage);
    createAbsenceForCurrentUser(TOMORROW, TOMORROW, "Vacation", absencePage);
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(absencePage.getAddAbsenceDialog(), ScreenshotUtil.SETTINGS_FOLDER + "new-absence", new ScreenshotMargin(20));
    newAbsencePage.closeAddAbsenceDialog();
    ScreenshotUtil.captureElementScreenshot(absencePage.getAbsenceForm(), ScreenshotUtil.SETTINGS_FOLDER + "absence");
    absencePage.setDeputy(Arrays.asList(TestAccount.DEMO_USER.getFullName(), TestAccount.GUEST_USER.getFullName()), 0);
    ScreenshotUtil.captureElementScreenshot(absencePage.getAbsenceForm(), ScreenshotUtil.SETTINGS_FOLDER + "set-deputy");
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
    login(TestAccount.ADMIN_USER);
    ConfigurationJsonUtil.updateJSONSetting("custom-user-menu.json", Variable.USER_MENU);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 1000));
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.getUserSettings();
    executeDecorateJs("highlightUserMenuConfiguration()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.SETTINGS_FOLDER + "user-menu-configuration");
  }

  @Test
  public void screenshotRoleAssignment() throws IOException {
    login(TestAccount.ADMIN_USER);
    ScreenshotUtil.resizeBrowser(new Dimension(1050, 850));
    homePage = new HomePage();
    var adminSettingsPage = homePage.openAdminSettings();
    var roleAssignmentTab = adminSettingsPage.openRoleManagementTab();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.SETTINGS_FOLDER + "role-assignment-tab");
    ScreenshotUtil.resizeBrowser(new Dimension(1050, 1200));
    roleAssignmentTab.openRoleCreationDialog();
    roleAssignmentTab.getRoleInfoDialogContent();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(roleAssignmentTab.getRoleInfoDialogContent(),
        ScreenshotUtil.SETTINGS_FOLDER + "role-assignment-creation-dialog", new ScreenshotMargin(60, 30));
    roleAssignmentTab.clickOnCancelLinkOfRoleDialog();
  }
}
