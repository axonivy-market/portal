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
    homePage.waitForStatisticRendered(HomePage.PORTAL_HOME_PAGE_URL);
    homePage.getUserSettings();
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.SETTINGS_FOLDER + "user-settings", new Dimension(1500, 800));
    
    refreshHomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ScreenshotUtil.captureElementScreenshot(adminSettingsPage.getAddApplicationDialog(), ScreenshotUtil.SETTINGS_FOLDER + "add-application");
    
    refreshHomePage();
    adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openSettingTab();
    ScreenshotUtil.captureElementScreenshot(adminSettingsPage.getAdminSettingDialog(), ScreenshotUtil.SETTINGS_FOLDER + "global-settings");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(adminSettingsPage.getEditSettingDialogOfFirstRow(), ScreenshotUtil.SETTINGS_FOLDER + "edit-global-settings", new ScreenshotMargin(20));
    
    refreshHomePage();
    ChangePasswordPage changePasswordPage = homePage.openChangePasswordPage();
    ScreenshotUtil.captureElementScreenshot(changePasswordPage.getChangePasswordDialog(), ScreenshotUtil.SETTINGS_FOLDER + "change-password");
    
    refreshHomePage();
    ProjectVersionPage projectVersionPage = homePage.openProjectVersionPage();
    ScreenshotUtil.captureElementScreenshot(projectVersionPage.getProjectVersionDialog(), ScreenshotUtil.SETTINGS_FOLDER + "portal-version-information");
  }
  
  @Test
  public void screenshotSettingWithAnnotation() throws IOException {
    login(TestAccount.ADMIN_USER);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    homePage = new HomePage();
    homePage.waitForStatisticRendered(HomePage.PORTAL_HOME_PAGE_URL);
    homePage.getUserSettings();
    Sleeper.sleep(300);//wait for animation finish to capture nice screenshot
    executeDecorateJs("highlightAdminSettings()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.SETTINGS_FOLDER + "select-admin-settings");
  }
  
  @Test
  public void screenshotMyProfile() throws IOException {
    homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    Sleeper.sleep(1000);//Wait for focus animation finish before capture screenshot
    ScreenshotUtil.captureElementScreenshot(userProfilePage.getUserSettingCard(), ScreenshotUtil.MY_PROFILE_FOLDER + "my-profile");
    userProfilePage.switchOnEmailOnTaskAssignmentSetting();
    userProfilePage.switchOnFurtherEmailFromAppSetting();
    ScreenshotUtil.captureElementScreenshot(userProfilePage.getUserSettingCard(), ScreenshotUtil.MY_PROFILE_FOLDER + "email-settings");
  }
  
  @Test
  public void screenshotAbsence() throws IOException {
    login(TestAccount.HR_ROLE_USER);
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 900));
    redirectToRelativeLink(cleanUpAbsencesAndSubstituesLink);
    homePage = new HomePage();
    AbsencePage absencePage = homePage.openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "Personal leave", absencePage);
    createAbsenceForCurrentUser(TOMORROW, TOMORROW, "Vacation", absencePage);
    ScreenshotUtil.captureElementScreenshot(absencePage.getAbsenceDialog(), ScreenshotUtil.SETTINGS_FOLDER + "absence");
    absencePage.openNewAbsenceDialog();
    Sleeper.sleep(1000);//wait for animation finish to capture nice screenshot
    ScreenshotUtil.captureElementScreenshot(absencePage.getAbsenceDialog(), ScreenshotUtil.SETTINGS_FOLDER + "new-absence");
    
    refreshPage();
    absencePage = homePage.openAbsencePage();
    absencePage.setDeputy(TestAccount.DEMO_USER.getFullName());
    ScreenshotUtil.captureElementScreenshot(absencePage.getAbsenceDialog(), ScreenshotUtil.SETTINGS_FOLDER + "set-deputy");
  }
  
  private void refreshHomePage() {
    refreshPage();
    homePage.waitForStatisticRendered(HomePage.PORTAL_HOME_PAGE_URL);
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
