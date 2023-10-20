package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage;
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
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.startTask(0);
    taskWidgetPage.clickOnMyProfile();
    WorkingTaskDialogFromUserProfilePage workingTaskDialogPage = new WorkingTaskDialogFromUserProfilePage();
    workingTaskDialogPage.leaveTask();
    UserProfilePage userProfilePage = new UserProfilePage();
    assertEquals("Language", userProfilePage.getLanguageSettingTitle());
  }

  @Test
  public void testChangeLanguage() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    UserProfilePage userProfilePage = newDashboardPage.openMyProfilePage();
    // select German
    userProfilePage.selectLanguage(3);
    newDashboardPage = userProfilePage.save();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    assertEquals("Prozesse", mainMenuPage.getProcessMenuItemText());
    userProfilePage = newDashboardPage.openMyProfilePage();
    userProfilePage.selectLanguage(1);
    userProfilePage.save();
    mainMenuPage = userProfilePage.openMainMenu();
    assertEquals("Processes", mainMenuPage.getProcessMenuItemText());
  }

  private void createTestData() {
    redirectToRelativeLink(createTestingTasksUrl);
  }

}
