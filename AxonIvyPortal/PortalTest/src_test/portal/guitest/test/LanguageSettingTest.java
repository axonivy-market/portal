package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage2;
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
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTasList();
    taskWidgetPage.startTask(0);
    taskWidgetPage.clickOnMyProfile();
    WorkingTaskDialogFromUserProfilePage workingTaskDialogPage = new WorkingTaskDialogFromUserProfilePage();
    workingTaskDialogPage.leaveTask();
    UserProfilePage userProfilePage = new UserProfilePage();
    assertEquals("Language", userProfilePage.getLanguageSettingTitle());
  }

  @Test
  public void testChangeLanguage() {
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    UserProfilePage userProfilePage = newDashboardPage2.openMyProfilePage();
    // select German
    userProfilePage.selectLanguage(3);
    newDashboardPage2 = userProfilePage.save();
    MainMenuPage mainMenuPage = newDashboardPage2.openMainMenu();
    assertEquals("Prozesse", mainMenuPage.getProcessMenuItemText());
    userProfilePage = newDashboardPage2.openMyProfilePage();
    userProfilePage.selectLanguage(1);
    userProfilePage.save();
    mainMenuPage = userProfilePage.openMainMenu();
    assertEquals("Processes", mainMenuPage.getProcessMenuItemText());
  }

  private void createTestData() {
    redirectToRelativeLink(createTestingTasksUrl);
  }

}
