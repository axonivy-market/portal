package portal.guitest.document.screenshot;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.AbsencePage;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.ChangePasswordPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.NewAbsencePage;
import portal.guitest.page.ProjectVersionPage;
import portal.guitest.page.UserProfilePage;

public class SettingScreenshotTest extends ScreenshotTest {
  
  private HomePage homePage;
  private static final LocalDate TODAY = LocalDate.now();
  private static final LocalDate TOMORROW = TODAY.plusDays(1);
  
  @Test
  public void screenshotAdminSettings() throws IOException {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    homePage = new HomePage();
    homePage.waitForStatisticRendered();
    homePage.getUserSettings();
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.SETTINGS_FOLDER + "user-settings", new Dimension(1366, 800));
    
    refreshHomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAddApplicationDialog(), ScreenshotUtil.SETTINGS_FOLDER + "add-application", new ScreenshotMargin(20));
    
    refreshHomePage();
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openSettingTab();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getAdminSettingDialog(), ScreenshotUtil.SETTINGS_FOLDER + "global-settings", new ScreenshotMargin(20));
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getEditSettingDialogOfFirstRow(), ScreenshotUtil.SETTINGS_FOLDER + "edit-global-settings", new ScreenshotMargin(20));
    
    refreshHomePage();
    ChangePasswordPage changePasswordPage = homePage.openChangePasswordPage();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(changePasswordPage.getChangePasswordDialog(), ScreenshotUtil.SETTINGS_FOLDER + "change-password", new ScreenshotMargin(20));
    
    refreshHomePage();
    ProjectVersionPage projectVersionPage = homePage.openProjectVersionPage();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(projectVersionPage.getProjectVersionDialog(), ScreenshotUtil.SETTINGS_FOLDER + "portal-version-information", new ScreenshotMargin(20));
  }
  
  @Test
  public void screenshotSettingWithAnnotation() throws IOException {
    login(TestAccount.ADMIN_USER);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    homePage = new HomePage();
    homePage.waitForStatisticRendered();
    homePage.getUserSettings();
    Sleeper.sleep(300);//wait for animation finish to capture nice screenshot
    executeDecorateJs("highlightAdminSettings()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.SETTINGS_FOLDER + "select-admin-settings");
  }
  
  @Test
  public void screenshotMyProfile() throws IOException {
    homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    ScreenshotUtil.resizeBrowser(new Dimension(1400, 1400));
    Sleeper.sleep(500);//Wait for focus animation finish before capture screenshot
    ScreenshotUtil.captureElementScreenshot(userProfilePage.getUserSettingCard(), ScreenshotUtil.MY_PROFILE_FOLDER + "my-profile");
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    userProfilePage.switchOnEmailOnTaskAssignmentSetting();
    userProfilePage.switchOnFurtherEmailFromAppSetting();
    Sleeper.sleep(500);//Wait for focus animation finish before capture screenshot
    ScreenshotUtil.captureElementScreenshot(userProfilePage.getUserSettingCard(), ScreenshotUtil.MY_PROFILE_FOLDER + "email-settings");
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
    absencePage.openNewAbsenceDialog();
    Sleeper.sleep(1000);//wait for animation finish to capture nice screenshot
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(absencePage.getAddAbsenceDialog(), ScreenshotUtil.SETTINGS_FOLDER + "new-absence", new ScreenshotMargin(20));
    WaitHelper.waitForNavigation(absencePage, () -> refreshPage());
    absencePage = homePage.openAbsencePage();
    ScreenshotUtil.captureElementScreenshot(absencePage.getAbsenceForm(), ScreenshotUtil.SETTINGS_FOLDER + "absence");
    absencePage.setDeputy(TestAccount.DEMO_USER.getFullName());
    ScreenshotUtil.captureElementScreenshot(absencePage.getAbsenceForm(), ScreenshotUtil.SETTINGS_FOLDER + "set-deputy");
  }
  
  private void refreshHomePage() {
    refreshPage();
    homePage.waitForStatisticRendered();
  }
  
  private void createAbsenceForCurrentUser(LocalDate from, LocalDate till, String comment, AbsencePage absencePage) {
    createAbsence("", from, till, comment, absencePage);
  }

  private void createAbsence(String fullname, LocalDate from, LocalDate till, String comment, AbsencePage absencePage) {
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(fullname, from, till, comment);
    newAbsencePage.proceed();
  }
}
