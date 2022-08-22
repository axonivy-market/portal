package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.UserProfilePage;
import portal.guitest.page.WorkingTaskDialogFromUserProfilePage;

public class LanguageSettingTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    createTestData();
  }

  @Test
  public void testChangeLanguageWhenWorkingOnTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTask(0);
    taskWidgetPage.clickOnMyProfile();
    WorkingTaskDialogFromUserProfilePage workingTaskDialogPage = new WorkingTaskDialogFromUserProfilePage();
    workingTaskDialogPage.leaveTask();
    UserProfilePage userProfilePage = new UserProfilePage();
    assertEquals("Language settings", userProfilePage.getLanguageSettingTitle());
  }

  @Test
  public void testChangeLanguage() {
    HomePage homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    // select German
    userProfilePage.selectLanguage(3);
    homePage = userProfilePage.save();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    assertEquals("Prozesse", mainMenuPage.getProcessMenuItemText());
    userProfilePage = homePage.openMyProfilePage();
    userProfilePage.selectLanguage(1);
    userProfilePage.save();
    mainMenuPage = userProfilePage.openMainMenu();
    assertEquals("Processes", mainMenuPage.getProcessMenuItemText());
  }

  private void createTestData() {
    redirectToRelativeLink(createTestingTasksUrl);
  }

}
